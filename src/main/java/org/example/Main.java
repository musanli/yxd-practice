package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 使用ImmutableList初始化一个List
        List<String> userNames = new ArrayList<String>() {{
            add("Hollis");
            add("hollis");
            add("HollisChuang");
            add("H");
        }};
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set("Hollis");
        inheritableThreadLocal.get();
//        new Thread()
        Iterator iterator = userNames.iterator();
        do {
            if (!iterator.hasNext())
                break;
            String userName = (String) iterator.next();
            if (userName.equals("Hollis"))
                userNames.remove(userName);
        } while (true);
        System.out.println(userNames);
    }

}