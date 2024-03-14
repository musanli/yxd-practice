package org.example.thread.local;

import cn.hutool.cron.task.RunnableTask;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TransmittableTest {
    @SneakyThrows
    public static void main(String[] args) {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 在父线程中设置
        context.set("value-set-in-parent");
        Runnable task = () -> {
            log.info("Task中可以读取，值是{}", context.get());
            context.set("value-set-in-task");
            log.info("Task中可以读取，值是{}", context.get());
        };
        // 额外的处理，生成修饰了的对象ttlRunnable
        Runnable ttlRunnable = TtlRunnable.get(task);
        executorService.submit(ttlRunnable);
        TimeUnit.SECONDS.sleep(1);


        // Task中可以读取，值是"value-set-in-parent"
        String value = context.get();
        context.remove();
        executorService.submit(ttlRunnable);
        TimeUnit.SECONDS.sleep(1);
        executorService.submit(TtlRunnable.get(task));

    }
}
