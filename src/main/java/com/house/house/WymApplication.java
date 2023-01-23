package com.house.house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WymApplication {

    public static void main(String[] args) {
        SpringApplication.run(WymApplication.class, args);
    }

}
