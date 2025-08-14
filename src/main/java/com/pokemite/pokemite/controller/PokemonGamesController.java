package com.pokemite.pokemite.controller;

import com.pokemite.pokemite.dto.PokemonGameDto;
import com.pokemite.pokemite.service.PokeApiFetchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/pokemon/games", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Pokémon Games", description = "Obtener información sobre los juegos de la franquicia")
public class PokemonGamesController {

    private final PokeApiFetchService pokeApiFetchService;

    @Operation(
            summary = "Obtener lista de juegos Pokémon junto con información de los pokemon iniciales de cada juego",
            description = "Devuelve la lista de juegos disponibles con los Pokémon iniciales de cada juego."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Listado de juegos obtenida correctamente",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = PokemonGameDto.class))
            )
    )
    @GetMapping
    @Cacheable(value = "pokemonFullGames", sync = true)
    public ResponseEntity<List<PokemonGameDto>> getGames() {
        return ResponseEntity.ok(pokeApiFetchService.processPokemonGamesRequest());
    }
}
