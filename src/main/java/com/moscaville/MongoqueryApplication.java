package com.moscaville;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MongoqueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoqueryApplication.class, args);
    }
}
