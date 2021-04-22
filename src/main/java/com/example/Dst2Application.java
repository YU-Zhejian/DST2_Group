package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //To eable the task be excecuted as scheduled (fixDelay=1 week)
public class Dst2Application {

    public static void main(String[] args) {
        SpringApplication.run(Dst2Application.class, args);


    }
}
