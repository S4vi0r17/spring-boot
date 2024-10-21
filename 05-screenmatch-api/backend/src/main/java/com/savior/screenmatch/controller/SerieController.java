package com.savior.screenmatch.controller;

import com.savior.screenmatch.dto.EpisodioDto;
import com.savior.screenmatch.dto.SerieDto;
import com.savior.screenmatch.model.Episodio;
import com.savior.screenmatch.repository.SerieRepository;
import com.savior.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping()
    public List<SerieDto> getSeries() {
        return serieService.getSeries();
    }

    @GetMapping("/top5")
    public List<SerieDto> getTop5Series() {
        return serieService.getTop5Series();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDto> getLanzaRecientes() {
        return serieService.getLanzaRecientes();
    }

    @GetMapping("/{id}")
    public SerieDto getSerieById(@PathVariable Long id) {
        return serieService.getSerieById(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDto> getAllSeasons(@PathVariable Long id) {
        return serieService.getAllSeasons(id);
    }

    @GetMapping("/{id}/temporadas/{temporada}")
    public List<EpisodioDto> getSeason(@PathVariable Long id, @PathVariable Integer temporada) {
        return serieService.getSeason(id, temporada);
    }
}
