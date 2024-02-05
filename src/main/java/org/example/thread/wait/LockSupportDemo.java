package org.example.thread.wait;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread thread1 = null;
        for (int i = 0; i < 5; i++) {
            int j = i;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    System.out.println("start thread " + j);
                    if (j == 3) {
                        LockSupport.park();
                    }
                    atomicInteger.incrementAndGet();
                    System.out.println("end thread " + j);
                }
            };
            thread.start();
        }


    }
}