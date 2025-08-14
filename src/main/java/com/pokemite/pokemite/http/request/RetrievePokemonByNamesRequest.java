package com.pokemite.pokemite.http.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import static com.pokemite.pokemite.util.Constant.PATTERN_ONLY_LETTERS_REGEX;

import java.util.List;

@Data
public class RetrievePokemonByNamesRequest {

    @NotEmpty(message = "La lista no puede estar vacía")
    private List<
            @NotBlank(message = "El nombre del Pokémon no puede estar en blanco")
            @Pattern(regexp = PATTERN_ONLY_LETTERS_REGEX, message = "Este campo solo admite carácteres")
            String> pokemonNames;
}
