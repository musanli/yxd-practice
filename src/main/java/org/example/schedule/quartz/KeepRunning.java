package org.example.schedule.quartz;

public class KeepRunning {
    public static void main(String[] args) {
        // 创建并启动线程
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.currentThread().yield();
                    System.out.println("invoice");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(false);
        thread.start();
        System.out.println(1);
    }
}