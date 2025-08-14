package com.pokemite.pokemite.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemite.pokemite.mapper.PokemonSimplePokemonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import skaro.pokeapi.client.PokeApiClient;
import skaro.pokeapi.resource.pokemon.Pokemon;

import java.io.IOException;
import java.util.List;

import static com.pokemite.pokemite.util.JsonReader.read;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PokeApiFetchServiceTest {


    private PokeApiClient pokeApiClient;
    private PokemonSimplePokemonMapper mapper;
    private LoadPokemonGamesService pokemonGamesLoaderService;
    private PokeApiFetchService pokeApiFetchService;

    @BeforeEach
    void setUp() {
        pokeApiClient = mock(PokeApiClient.class);
        mapper = mock(PokemonSimplePokemonMapper.class);
        pokemonGamesLoaderService = mock(LoadPokemonGamesService.class);
        pokeApiFetchService = new PokeApiFetchService(pokeApiClient, mapper, pokemonGamesLoaderService);
    }

    /**
     * @class: PokeApiFetchService
     * @method: retrievePokemon
     * @param: List<String> ["pikachu"]
     * @return List<Pokemon>
     * */
    @Test
    void retrievePokemonByName_Pikachu() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Pokemon pikachuJson = read("PokemonApi/Response/Pikachu.json", Pokemon.class);

        when(pokeApiClient.getResource(Pokemon.class, "pikachu"))
                .thenReturn(Mono.just(pikachuJson));

        List<Pokemon> result = pokeApiFetchService.retrievePokemonByNames(List.of("pikachu"));

        assertEquals(1, result.size());
        assertEquals("pikachu", result.get(0).getName());

        JsonNode expectedJson = mapper.valueToTree(pikachuJson);
        JsonNode serviceResponseJson = mapper.valueToTree(result.get(0));
        assertEquals(expectedJson, serviceResponseJson);
    }
}
