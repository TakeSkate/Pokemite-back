package com.pokemite.pokemite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PokemiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokemiteApplication.class, args);
    }


}
