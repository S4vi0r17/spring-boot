package com.example.savior.gutendex.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("tittle")
        String titulo,
        @JsonAlias("authors")
        List<DatosAutor> autores,
        @JsonAlias("languages")
        List<String> idiomas,
        @JsonAlias("download_count")
        Double numeroDescargas
) { }