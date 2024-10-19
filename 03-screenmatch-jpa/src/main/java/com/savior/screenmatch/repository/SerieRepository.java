package com.savior.screenmatch.repository;

import com.savior.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Serie findByTitulo(String titulo);
}
