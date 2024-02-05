package org.example;


import cn.hutool.core.date.DateUtil;
import lombok.SneakyThrows;
import org.example.schedule.quartz.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.math.BigDecimal;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(new BigDecimal(10).multiply(new BigDecimal("0.9")).intValue());
    }

}