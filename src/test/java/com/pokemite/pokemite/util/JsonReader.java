package com.pokemite.pokemite.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class JsonReader {

    public static <T> T read(String path, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = JsonReader.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new FileNotFoundException("Archivo no encontrado: " + path);
            }
            return mapper.readValue(is, clazz);
        }
    }
}
