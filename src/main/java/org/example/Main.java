package org.example;


import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.addAndGet(-10));
        System.out.println(atomicInteger.addAndGet(1));
    }

}