package org.example.thread.wait;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static void main(String[] args) {
        Status status = new Status();
        Thread thread = new Thread() {
            @Override
            public void run() {
                Object blocker = LockSupport.getBlocker(Thread.currentThread());
                if (blocker != null && blocker instanceof Status) {
                    System.out.println(status);
                    if (status.isS1() && status.isS2()) {
                        LockSupport.park(status);
                    }
                }
                while (true) {
                    System.out.println(LocalDateTime.now());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
//        thread.start();
        LockSupport.unpark(Thread.currentThread());
        LockSupport.unpark(Thread.currentThread());
//        LockSupport.park();
        System.out.println("end");
//        LockSupport.getBlocker(thread);

    }
}

@Data
class Status {
    boolean s1;
    boolean s2;
}