package com.example.taas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableScheduling
public class TaaSApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaaSApplication.class, args);



    }

}
