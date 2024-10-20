package com.savior.screenmatch.repository;

import com.savior.screenmatch.model.Categoria;
import com.savior.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String titulo);
    Optional<Serie> findByTituloContainingIgnoreCase(String titulo);
    List<Serie> findTop5ByOrderByEvaluacionDesc();
    List<Serie> findByGenero(Categoria categoria);
    // Buscar una serie por un cierto numero de temporadas y una evaluacion en espacifico. Ejemplo: Buscar todas las series que tengan hasta 5 temporadas y una evaluacion mayor a 8.0
    List<Serie> findByTotalTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(Integer totalTemporadas, Double evaluacion);
}
