package org.example;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class ExampleApplication implements InitializingBean {

    @Autowired
    Environment environment;
    public static void main(String[] args) {
        System.out.println(new NullPointerException().getMessage() == null);
        System.out.println(new NullPointerException().toString());
        System.out.println(Duration.ofSeconds(1).toMillis());
//        SpringApplication.run(ExampleApplication.class, args);C
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(environment.getProperty("spring.datasource.log-abandoned"));
        System.out.println(environment.getProperty("spring.datasource.log-validation-errors"));
        System.out.println(environment.getProperty("spring.datasource.log-expired-connections"));
        System.out.println(environment.getProperty("spring.datasource.log-idle-timeout"));
        System.out.println(environment.getProperty("spring.datasource.log-create"));
        System.out.println(environment.getProperty("spring.datasource.log-level"));
        System.out.println(environment.getProperty("spring.datasource.log-full"));
        System.out.println(environment.getProperty("spring.datasource.min-evictable-idle-time-millis"));

    }
}
