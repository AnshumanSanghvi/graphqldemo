package com.anshuman.graphqldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GraphQlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphQlDemoApplication.class, args);
    }

}
