package com.epam.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.epam.learn"})
public class FirstBeModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstBeModuleApplication.class, args);
    }
}
