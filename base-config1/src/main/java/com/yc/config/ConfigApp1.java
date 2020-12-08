package com.yc.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigApp1 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApp1.class, args);
    }
}

