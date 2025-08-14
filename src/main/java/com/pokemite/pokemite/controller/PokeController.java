package com.pokemite.pokemite.controller;

import com.pokemite.pokemite.http.request.RetrievePokemonByNamesRequest;
import com.pokemite.pokemite.service.PokeApiFetchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skaro.pokeapi.resource.pokemon.Pokemon;

import java.util.List;


@RestController
@RequestMapping("/api/v1/pokemon")
@RequiredArgsConstructor
@Tag(name = "Pokémon", description = "Obtener información especifica sobre algun/os Pokémon")
public class PokeController {

    private final PokeApiFetchService pokeApiFetchService;

    @Operation(
            summary = "Obtener información de Pokémons",
            description = "Dado los nombres de pokemon obtenemos y retornamos su información de la pokeApi"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Información de Pokémon/s obtenida correctamente"),
            @ApiResponse(responseCode = "500", description = "Error al comunicarse con la API externa")
    })
    @GetMapping
    public List<Pokemon> getPokemonByNames(@Valid @ModelAttribute RetrievePokemonByNamesRequest request) {
        return pokeApiFetchService.retrievePokemonByNames(request.getPokemonNames());
    }

}
