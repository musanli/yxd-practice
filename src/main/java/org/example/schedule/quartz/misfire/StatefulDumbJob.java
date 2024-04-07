package org.example.schedule.quartz.misfire;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

/**
 * @ClassName: StatefulDumbJob
 * @Description: 通过设置错失触发后的调度策略，来处理错过的任务.
 * @author: Mr.Yang
 * @date: 2017年11月14日 下午9:36:52
 */

@Slf4j
// 在Job被执行结束后，将会更新JobDataMap，这样下次Job执行后就会使用新的值而不是初始值
@PersistJobDataAfterExecution
// 同一时间将只有一个Job实例被执行, 为了避免并发问题导致数据紊乱,建议这两个注解一起使用
@DisallowConcurrentExecution
public class StatefulDumbJob implements Job {

    // 静态常量，作为任务在调用间，保持数据的键(key)

    public static final String NUM_EXECUTIONS = "NumExecutions"; // 保存的计数每次递增1
    public static final String EXECUTION_DELAY = "ExecutionDelay"; // 任务在执行时，中间睡眠的时间。本例中睡眠时间过长导致了错失触发

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        log.info("jobKey={}, fireTime={}, jobRunTime={}, nextFireTime={}, currentTime={}, misfireInstruction={}",
                context.getJobDetail().getKey().getName(),
                dateFormat.format(context.getFireTime()),
                dateFormat.format(context.getScheduledFireTime()),
                dateFormat.format(context.getNextFireTime()),
                dateFormat.format(new Date()),
                context.getTrigger().getMisfireInstruction());

        // 任务执行计数 累加
        JobDataMap map = context.getJobDetail().getJobDataMap();
        int executeCount = 0;
        if (map.containsKey(NUM_EXECUTIONS)) {
            executeCount = map.getInt(NUM_EXECUTIONS);
        }
        executeCount++;
        map.put(NUM_EXECUTIONS, executeCount);

        // 睡眠时间: 由调度类重新设置值 ,本例为 睡眠10s
        long delay = 5000l;
        if (map.containsKey(EXECUTION_DELAY)) {
            delay = map.getLong(EXECUTION_DELAY);
        }

        try {
            Thread.sleep(delay);
        } catch (Exception ignore) {
        }
        log.info("jobKey={}, fireTime={}, jobRunTime={}, nextFireTime={}, currentTime={}, misfireInstruction={}, executeCount={}",
                context.getJobDetail().getKey().getName(),
                dateFormat.format(context.getFireTime()),
                dateFormat.format(context.getScheduledFireTime()),
                dateFormat.format(context.getNextFireTime()),
                dateFormat.format(new Date()),
                context.getTrigger().getMisfireInstruction(),
                executeCount);

    }

}