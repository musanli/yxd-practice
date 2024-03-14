package org.example.thread.join;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestThreadJoin {
    @SneakyThrows
    public static void main(String[] args) {
        Thread run1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("run1");
        });
        Thread run2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("run2");
        });

        run1.start();
        run2.start();
        run2.join();
        log.info("start");

    }

}
