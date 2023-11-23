package com.projetsuivi.dangerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.projetsuivi")
public class DangerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangerServiceApplication.class, args);
    }

}
