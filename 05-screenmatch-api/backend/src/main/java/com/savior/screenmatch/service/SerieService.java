package com.savior.screenmatch.service;

import com.savior.screenmatch.dto.SerieDto;
import com.savior.screenmatch.model.Serie;
import com.savior.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
