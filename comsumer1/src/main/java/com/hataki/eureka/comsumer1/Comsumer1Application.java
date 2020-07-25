package com.hataki.eureka.comsumer1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Comsumer1Application {

    public static void main(String[] args) {
        SpringApplication.run(Comsumer1Application.class, args);
    }

}
