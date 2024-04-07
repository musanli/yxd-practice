package org.example.runtime;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 程序的内存使用情况
 * 程序的总体运行时间
 * 可用的处理器数量
 * 系统的负载情况
 * 可用的内存量
 * 程序的线程数量
 * 系统的环境变量
 * 运行时的 JVM 版本信息
 * 可用的文件描述符数量
 */
public class RuntimeInfo {

    public static void main(String[] args) throws UnknownHostException {
        HashMap<Integer, Integer> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(1, 1);
        objectObjectHashMap.put(2, 1);
        objectObjectHashMap.put(3, 1);
        new HashMap<>(objectObjectHashMap).keySet().forEach(itm -> {
            if (itm == 2) {
                objectObjectHashMap.remove(itm);
            }
        });
        System.out.println(objectObjectHashMap);

//        try {
//            Runtime runtime = Runtime.getRuntime();
//            Mono.just(runtime)
//                    .map(Runtime::availableProcessors)
//                    .subscribe(System.out::println);
//            Process process = runtime.exec("jps -l");
//
//            // 模拟等待进程执行完成
//            Thread.sleep(100); // 等待5秒钟
//
//            boolean isProcessAlive = process.isAlive();
//            System.out.println("pid: " + process.pid());
//            System.out.println("进程是否还在运行: " + isProcessAlive);
//        } catch (Exception  e) {
//            e.printStackTrace();
//        }
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//        Runtime runtime = Runtime.getRuntime();
//        while (true) {
//            System.out.println(runtime.exec("jps -l").isAlive());
//            TimeUnit.SECONDS.sleep(1);
//        }
//    }
}
