package org.example.schedule.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.atomic.AtomicInteger;

public class MyJob implements Job {
    static final AtomicInteger count = new AtomicInteger();

    public MyJob() {
        count.incrementAndGet();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t" + thread.getThreadGroup() + "  任务被执行了。。。" + count);
    }
}