package com.pokemite.pokemite.dto;


import java.util.List;

public record SimplePokemonDto (String name, List<String> types, String imagePath) {

}
