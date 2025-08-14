package com.pokemite.pokemite.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import skaro.pokeapi.resource.pokemon.Pokemon;
import com.pokemite.pokemite.dto.SimplePokemonDto;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PokemonSimplePokemonMapper {


    public SimplePokemonDto toSimpleDto(Pokemon p) {
        String imagePath = null;
        List<String> types = null;
        if (p == null) return null;


        if (p.getTypes() != null) {
            types = p.getTypes().stream()
                    .map(t -> t.getType().getName())
                    .toList();
        }

        if (p.getSprites() != null) {
            imagePath = p.getSprites().getFrontDefault();
        }

        return new SimplePokemonDto(p.getName(), types, imagePath);
    }
}

