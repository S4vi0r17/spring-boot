package com.savior.screenmatch.service;

import com.savior.screenmatch.dto.EpisodioDto;
import com.savior.screenmatch.dto.SerieDto;
import com.savior.screenmatch.model.Categoria;
import com.savior.screenmatch.model.Episodio;
import com.savior.screenmatch.model.Serie;
import com.savior.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDto> getSeries() {
        return convierteDatos(serieRepository.findAll());
    }

    public List<SerieDto> getTop5Series() {
        return convierteDatos(serieRepository.findTop5ByOrderByEvaluacionDesc());
    }

    public List<SerieDto> getLanzaRecientes() {
        return convierteDatos(serieRepository.lazamientosRecientes());
    }

    public List<SerieDto> convierteDatos(List<Serie> series) {
        return series.stream()
                .map(serie -> new SerieDto(
                        serie.getId(),
                        serie.getTitulo(),
                        serie.getTotalTemporadas(),
                        serie.getEvaluacion(),
                        serie.getPoster(),
                        serie.getGenero(),
                        serie.getSinopsis(),
                        serie.getActores()
                ))
                .toList();
    }

    public SerieDto getSerieById(Long id) {
        return serieRepository.findById(id)
                .map(serie -> new SerieDto(
                        serie.getId(),
                        serie.getTitulo(),
                        serie.getTotalTemporadas(),
                        serie.getEvaluacion(),
                        serie.getPoster(),
                        serie.getGenero(),
                        serie.getSinopsis(),
                        serie.getActores()
                ))
                .orElse(null);
    }

    public List<EpisodioDto> getAllSeasons(Long id) {
        return serieRepository.findById(id)
                .map(serie -> serie.getEpisodios().stream()
                        .map(episodio -> new EpisodioDto(
                                episodio.getTemporada(),
                                episodio.getTitulo(),
                                episodio.getNumeroEpisodio()
                        ))
                        .toList())
                .orElse(null);
    }

    public List<EpisodioDto> getSeason(Long id, Integer temporada) {
        /*
        * return serieRepository.findById(id)
                .map(serie -> serie.getEpisodios().stream()
                        .filter(episodio -> episodio.getTemporada().equals(temporada))
                        .map(episodio -> new EpisodioDto(
                                episodio.getTemporada(),
                                episodio.getTitulo(),
                                episodio.getNumeroEpisodio()
                        ))
                        .toList())
                .orElse(null);
        */
        return serieRepository.getSeasonBySerieNumber(id, temporada).stream()
                .map(episodio -> new EpisodioDto(
                        episodio.getTemporada(),
                        episodio.getTitulo(),
                        episodio.getNumeroEpisodio()
                ))
                .toList();
    }

    public List<SerieDto> getSeriesByCategory(String categoria) {
        Categoria cat = Categoria.fromStringEspanol(categoria);
        return convierteDatos(serieRepository.findByGenero(cat));
    }

    public List<EpisodioDto> getTopSeason(Long id) {
        /*
        return serieRepository.findById(id)
            .map(serie -> serie.getEpisodios().stream()
                    .sorted(Comparator.comparing(Episodio::getEvaluacion).reversed())
                    .limit(5)
                    .map(episodio -> new EpisodioDto(
                            episodio.getTemporada(),
                            episodio.getTitulo(),
                            episodio.getNumeroEpisodio()
                    ))
                    .toList())
            .orElse(null);
        */
        var serie = serieRepository.findById(id).get();
        return serieRepository.top5Episodios(serie).stream()
                .map(episodio -> new EpisodioDto(
                        episodio.getTemporada(),
                        episodio.getTitulo(),
                        episodio.getNumeroEpisodio()
                ))
                .toList();
    }
}
