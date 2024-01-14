package com.mapigateway;

import com.mapigateway.security.RsaKeyProperties;
import com.mapigateway.user.model.MyUser;
import com.mapigateway.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
@EnableDiscoveryClient
public class MApiGatewayApplication implements CommandLineRunner {

    @Autowired
    UserService users;

    public static void main(String[] args) {
        SpringApplication.run(MApiGatewayApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
