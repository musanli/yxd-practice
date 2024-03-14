package org.example.thread.wait;

import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

//        new Condition().await();



        Thread thread = new Thread(() -> {
            System.out.println("start thread " + Thread.currentThread().isInterrupted());
            LockSupport.park();
            Thread.currentThread();
            LockSupport.park();
            System.out.println("end thread " + Thread.currentThread().isInterrupted());
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();

//        Thread thread1 = null;
//        for (int i = 0; i < 5; i++) {
//            int j = i;
//            Thread thread = new Thread() {
//                @Override
//                public void run() {
//                    System.out.println("start thread " + j);
//                    if (j == 3) {
//                        LockSupport.park();
//                    }
//                    atomicInteger.incrementAndGet();
//                    System.out.println("end thread " + j);
//                }
//            };
//            thread.start();
//        }


    }
}