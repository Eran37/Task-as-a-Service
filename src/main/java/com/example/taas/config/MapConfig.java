package com.example.taas.config;

import com.example.taas.security.Information;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class MapConfig {

    //  Collection Injection
    @Bean
    public Map<UUID, Information> map() {
        return new HashMap<>();
    }


}
