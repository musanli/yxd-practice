package org.example.spring.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 测试 spring transaction 开启的时机
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionStartPoint {

    private final JdbcTemplate jdbcTemplate;

    public String getJdbcThreadId() {
        String connectionId = jdbcTemplate.queryForObject("select connection_id()", String.class);
        log.info("getJdbcThreadId threadId={}, threadName={}, connection_id={}", Thread.currentThread().getId(), Thread.currentThread().getName(), connectionId);
        return connectionId;
    }

    /**
     * 启动一个新的线程 获取连接查看是否是同一个连接
     * 方法添加 @Transactional 则 两者不是同一个连接
     * 没有事务注解时 两者是同一个连接
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */

    public String getJdbcThreadIdAsNewThread() throws ExecutionException, InterruptedException {
        getJdbcThreadId();
        log.info("async start");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> getJdbcThreadId(), Executors.newSingleThreadExecutor());
        return future.get();
    }

    /**
     * 再进入该方法前， 事务 已经开启
     * 开启事务的逻辑 org.springframework.jdbc.datasource.DataSourceTransactionManager#doBegin(java.lang.Object, org.springframework.transaction.TransactionDefinition)
     * 整个链路为
     * org.springframework.transaction.interceptor.TransactionInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     * org.springframework.transaction.interceptor.TransactionAspectSupport#invokeWithinTransaction(java.lang.reflect.Method, java.lang.Class, org.springframework.transaction.interceptor.TransactionAspectSupport.InvocationCallback)
     * org.springframework.transaction.interceptor.TransactionAspectSupport#createTransactionIfNecessary(org.springframework.transaction.PlatformTransactionManager, org.springframework.transaction.interceptor.TransactionAttribute, java.lang.String)
     * 。。。。。。
     * org.springframework.jdbc.datasource.DataSourceTransactionManager#doBegin(java.lang.Object, org.springframework.transaction.TransactionDefinition)
     *
     * 基于以上测试， 得出结果
     * 1. 避免大事务的方式是 尽量减小代码块，不要冗余其他逻辑
     * 2. spring 连接是关联线程的， 异步会导致失效
     * 3. 异步线程获取事务，有可能导致连接池耗尽导致死锁
     * 4. jdbcTemplate 关联的是线程池。而不是一个具体的连接。所以，jdbc 执行sql 具体是 哪个 连接执行是不确定的。
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void addRow() {
        log.info("addRow");
        jdbcTemplate.query("select * from user where id = 1", (rs, rowNum) -> {
            log.info("id: {}, name: {}, age: {}", rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
            return "";
        });
        jdbcTemplate.update("UPDATE user set age = age + 1 WHERE id = 1");

        jdbcTemplate.query("select * from user where id = 1", (rs, rowNum) -> {
            log.info("id: {}, name: {}, age: {}", rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
            return "";
        });
        throw new RuntimeException("addRow");
    }
}
