package com.hataki.seven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SevenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevenApplication.class, args);
    }

}
