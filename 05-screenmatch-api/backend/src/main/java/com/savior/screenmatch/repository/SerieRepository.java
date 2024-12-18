package com.savior.screenmatch.repository;

import com.savior.screenmatch.model.Categoria;
import com.savior.screenmatch.model.Episodio;
import com.savior.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String titulo);
    Optional<Serie> findByTituloContainingIgnoreCase(String titulo);
    List<Serie> findTop5ByOrderByEvaluacionDesc();
    List<Serie> findByGenero(Categoria categoria);

    // Derived Queries
    // Buscar una serie por un cierto numero de temporadas y una evaluacion en espacifico. Ejemplo: Buscar todas las series que tengan hasta 5 temporadas y una evaluacion mayor a 8.0
    List<Serie> findByTotalTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(Integer totalTemporadas, Double evaluacion);

    // Native Queries: Específico para una base de datos en particular
    @Query(value = "SELECT * FROM series WHERE total_temporadas <= ?1 AND evaluacion >= ?2", nativeQuery = true)
    List<Serie> buscarPorTemporadasYEvaluacion(Integer totalTemporadas, Double evaluacion);

    // JPQL Queries
    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.evaluacion >= :evaluacion")
    List<Serie> buscarPorTemporadasYEvaluacionJPQL(Integer totalTemporadas, Double evaluacion);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nombreEpisodio%")
    List<Episodio> episodiosPorNombre(String nombreEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.evaluacion DESC LIMIT 5")
    List<Episodio> top5Episodios(Serie serie);

    @Query("SELECT s FROM Serie s JOIN s.episodios e GROUP BY s ORDER BY MAX(e.fechaEstreno) DESC LIMIT 5")
    List<Serie> lazamientosRecientes();

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :temporada")
    List<Episodio> getSeasonBySerieNumber(Long id, Integer temporada);
}
