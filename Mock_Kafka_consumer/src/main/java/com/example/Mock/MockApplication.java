package com.example.Mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan; // добавлено

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.Mock.repository")
@ComponentScan(basePackages = {"com.example.Mock"}) // добавлено
public class MockApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockApplication.class, args);
    }
}