package com.openpay.marvelservice.config;

import com.openpay.marvelservice.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Autowired
    CharacterService characterService;
    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args) -> characterService.getCharacters();
    }
}
