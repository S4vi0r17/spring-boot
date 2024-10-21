package com.savior.screenmatch.dto;

import com.savior.screenmatch.model.Categoria;

public record SerieDto(
        Long id,
        String titulo,
        Integer totalTemporadas,
        Double evaluacion,
        String poster,
        Categoria genero,
        String sinopsis,
        String actores
) { }
