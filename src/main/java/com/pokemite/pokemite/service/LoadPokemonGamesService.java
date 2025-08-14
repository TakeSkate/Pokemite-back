package com.pokemite.pokemite.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemite.pokemite.dto.PokemonGameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase carga el archivo pokemon_games.json el cual tiene los datos de los juegos de pokemon
 * como la lista de pokemons iniciales, nombre del juego, ruta de la imagen de la caratula del juego...
 * */

@Service
@RequiredArgsConstructor
public class LoadPokemonGamesService {

    @Value("${pokemite.games.file-path}")
    private Resource gamesFile;

    @Cacheable("pokemonGames")
    public List<PokemonGameDto> loadPokemonGamesFromFile() {
        List<PokemonGameDto> pokemonGamesList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = gamesFile.getInputStream()) {
            pokemonGamesList = mapper.readValue(is, new TypeReference<>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pokemonGamesList;
    }

}
