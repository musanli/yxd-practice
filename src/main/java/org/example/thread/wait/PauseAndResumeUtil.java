package org.example.thread.wait;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class PauseAndResumeUtil {
    private static Thread incrementPushThread;
    private static final AtomicInteger PAUSE_COUNT = new AtomicInteger(0);



    public static synchronized void init() {
        incrementPushThread = Thread.currentThread();
    }

    public static synchronized void waiting() {
        if (PAUSE_COUNT.get() > 0) {
            LockSupport.park();
        }
    }

    public static synchronized boolean pauseIncrPush() {
        PAUSE_COUNT.incrementAndGet();
        return true;
    }

    public static synchronized void resume() {
        if (PAUSE_COUNT.get() > 0) {
//            PAUSE_COUNT.decrementAndGet();
            int i = PAUSE_COUNT.addAndGet(-1);
            if (i == 0) {
                LockSupport.unpark(incrementPushThread);
            }
        }
    }
}
