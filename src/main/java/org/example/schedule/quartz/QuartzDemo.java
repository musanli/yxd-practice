package org.example.schedule.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class QuartzDemo {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Properties properties = new Properties();
//        Executors.newCachedThreadPool()
        DelayQueue delayQueue = new DelayQueue();
        delayQueue.put(new Delayed() {
            @Override
            public long getDelay(TimeUnit unit) {
                return 0;
            }

            @Override
            public int compareTo(Delayed o) {
                return 0;
            }
        });
        Delayed take = delayQueue.take();

        stdSchedulerFactory.initialize(properties);// 针对
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        // 动态新增任务
        addJob(scheduler, "job1", "group1", "0/5 * * * * ?", MyJob.class);

        // 动态删除任务
        deleteJob(scheduler, "job1", "group1");

        // 支持指定间隔时间执行
        addIntervalJob(scheduler, "job2", "group2", 5000L, MyJob.class);

        // 支持解析cron表达式执行
        addCronJob(scheduler, "job3", "group3", "0 0/1 * 1/1 * ? *", MyJob.class);
    }

    public static void addJob(Scheduler scheduler, String jobName, String groupName, String cronExpression, Class<? extends Job> jobClass) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(jobClass)
                .withIdentity(jobName, groupName)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName + "Trigger", groupName)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();

        scheduler.scheduleJob(job, trigger);
    }

    public static void deleteJob(Scheduler scheduler, String jobName, String groupName) throws SchedulerException {
        scheduler.deleteJob(new JobKey(jobName, groupName));
    }

    public static void addIntervalJob(Scheduler scheduler, String jobName, String groupName, long interval, Class<? extends Job> jobClass) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(jobClass)
                .withIdentity(jobName, groupName)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName + "Trigger", groupName)
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(interval).repeatForever())
                .build();

        scheduler.scheduleJob(job, trigger);
    }

    public static void addCronJob(Scheduler scheduler, String jobName, String groupName, String cronExpression, Class<? extends Job> jobClass) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(jobClass)
                .withIdentity(jobName, groupName)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName + "Trigger", groupName)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing())
                .build();

        scheduler.scheduleJob(job, trigger);
    }
}
