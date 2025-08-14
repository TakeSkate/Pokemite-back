package com.pokemite.pokemite.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @Operation(
            summary = "Retorna la pagina principal de angular",
            description = "Retorna la vista front la cual llama a los endpoints de la api para obtener y mostrar la información"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornamos la pagina correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @Hidden
    @GetMapping("/")
    public String index() {
        return "redirect:/front/index.html";
    }
}
