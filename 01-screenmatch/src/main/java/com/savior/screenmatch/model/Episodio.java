package com.savior.screenmatch.model;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double evaluacion;
    private LocalDate fechaEstreno;

    public Episodio(Integer numero, DatosEpisodio datosEpisodio) {
        this.temporada = numero;
        this.titulo = datosEpisodio.titulo();
        this.numeroEpisodio = datosEpisodio.numeroEpisodio();
        // this.evaluacion = Double.parseDouble(datosEpisodio.evaluacion());
        this.evaluacion = datosEpisodio.evaluacion().equals("N/A") ? 0.0 : Double.parseDouble(datosEpisodio.evaluacion());
        // this.fechaEstreno = LocalDate.parse(datosEpisodio.fechaEstreno());
        // this.fechaEstreno = datosEpisodio.fechaEstreno().equals("N/A") ? LocalDate.now() : LocalDate.parse(datosEpisodio.fechaEstreno());
        try {
            this.fechaEstreno = LocalDate.parse(datosEpisodio.fechaEstreno());
        } catch (DateTimeException e) {
            this.fechaEstreno = null;
        }
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    @Override
    public String toString() {
        return  "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", evaluacion=" + evaluacion +
                ", fechaEstreno=" + fechaEstreno;
    }
}
