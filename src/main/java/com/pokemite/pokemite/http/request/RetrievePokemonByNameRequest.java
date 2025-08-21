package com.pokemite.pokemite.http.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import static com.pokemite.pokemite.util.Constant.PATTERN_ONLY_LETTERS_REGEX;

import java.util.List;

@Data
@Schema(
        name = "RetrievePokemonByNameRequest",
        description = "Lista de nombres de Pokémon a consultar",
        example = """
  {
    "name": ["pikachu","charmander","bulbasaur"]
  }
  """
)
public class RetrievePokemonByNameRequest {

    @NotEmpty(message = "La lista no puede estar vacía")
    private List<
            @NotBlank(message = "El nombre del Pokémon no puede estar en blanco")
            @Pattern(regexp = PATTERN_ONLY_LETTERS_REGEX, message = "Este campo solo admite carácteres")
            String> name;
}
