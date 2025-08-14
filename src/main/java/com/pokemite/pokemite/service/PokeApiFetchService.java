package com.pokemite.pokemite.service;

import com.pokemite.pokemite.dto.PokemonGameDto;
import com.pokemite.pokemite.dto.SimplePokemonDto;
import com.pokemite.pokemite.mapper.PokemonSimplePokemonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skaro.pokeapi.client.PokeApiClient;
import skaro.pokeapi.client.PokeApiEntityFactory;
import skaro.pokeapi.client.ReactiveCachingPokeApiClient;
import skaro.pokeapi.client.WebClientEntityFactory;
import skaro.pokeapi.resource.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PokeApiFetchService {

    private final PokeApiClient pokeApiClient;

    private final PokemonSimplePokemonMapper pokemonSimplePokemonMapper;

    private final LoadPokemonGamesService calculatePokemonGame;


    public List<Pokemon> retrievePokemonByNames(List<String> pokemonNames) {
        List<Pokemon> pokeList = new ArrayList<>();
        for (String pokemonName : pokemonNames) {
            pokeList.add(pokeApiClient.getResource(Pokemon.class, pokemonName).block());
        }
        return pokeList;
    }

    public List<PokemonGameDto> processPokemonGamesRequest() {
        List<PokemonGameDto> pokemonList = calculatePokemonGame.loadPokemonGamesFromFile();

        pokemonList.forEach(pokemonGameDto -> {
            List<String> names = pokemonGameDto.getStarterPokemonNames();

            List<Pokemon> detailedPokemons = this.retrievePokemonByNames(names);

            List<SimplePokemonDto> simplified = detailedPokemons.stream()
                    .map(pokemonSimplePokemonMapper::toSimpleDto)
                    .collect(Collectors.toList());

            pokemonGameDto.setStarterPokemons(simplified);
        });
        return pokemonList;
    }

}
