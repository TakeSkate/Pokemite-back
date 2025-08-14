package com.pokemite.pokemite.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Esta clase representa un juego de pokemon, con el nombre del juego
 * la imagen de la caratula y la lista de pokemons iniciales que habia en ese juego
 * */

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(name = "PokemonGame")
public class PokemonGameDto {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    private String imagePath;

    @NotEmpty
    private List<String> starterPokemonNames;

    @NotEmpty
    private List<SimplePokemonDto> starterPokemons;

    @Size(max = 20)
    private String regionGameVersion;

}
