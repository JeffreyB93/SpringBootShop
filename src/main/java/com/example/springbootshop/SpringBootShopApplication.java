package com.example.springbootshop;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class SpringBootShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShopApplication.class, args);
    }
}