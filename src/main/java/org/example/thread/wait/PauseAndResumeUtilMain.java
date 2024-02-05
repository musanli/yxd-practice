package org.example.thread.wait;

import lombok.SneakyThrows;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class PauseAndResumeUtilMain {


    @SneakyThrows
    public static void main(String[] args) {

        threadFactory(() -> {
            while (true) {
                PauseAndResumeUtil.waiting();
                System.out.println(new Date());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        });
        TimeUnit.SECONDS.sleep(1);

        for (int i = 0; i < 5; i++) {
            int j = i;
            threadFactory(() -> {
                System.out.println("start thread " + j);
//                if (j == 3) {
//                    PauseAndResumeUtil.waiting();
//                }
                PauseAndResumeUtil.pauseIncrPush();
                System.out.println("end thread " + j);
            });
            TimeUnit.SECONDS.sleep(1);
        }
    }

    static void threadFactory(Runnable runnable) {
        new Thread(runnable).start();
    }
}


