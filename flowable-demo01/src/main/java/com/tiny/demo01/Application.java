package com.tiny.demo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tiny lin
 * @date 2019/3/4
 */
@SpringBootApplication(scanBasePackages = {"com.tiny.conf", "com.tiny.demo01"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
