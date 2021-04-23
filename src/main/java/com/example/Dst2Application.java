package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The crawler wrapper
 *
 * @author Jie Jin
 * @author Yaqi-SU
 */
@SpringBootApplication
@EnableScheduling // To enable the task be executed as scheduled (fixDelay=1 week)
public class Dst2Application {
    public static void main(String[] args) {
        SpringApplication.run(Dst2Application.class, args);
    }
}
