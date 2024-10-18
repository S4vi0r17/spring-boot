package com.savior.screenmatch.model;

import java.util.OptionalDouble;

public class Serie {
    private String titulo;
    private Integer totalTemporadas;
    private Double evaluacion;
    private String poster;
    private Categoria genero;
    private String sinopsis;
    private String actores;

    public Serie(DatosSerie datosSerie) {
        this.titulo = datosSerie.titulo();
        this.totalTemporadas = datosSerie.totalTemporadas();
        this.evaluacion = OptionalDouble.of(Double.parseDouble(datosSerie.evaluacion())).orElse(0.0);
        this.poster = datosSerie.poster();
        this.genero = Categoria.fromString(datosSerie.genero().split(",")[0].trim());
        this.sinopsis = datosSerie.sinopsis();
        this.actores = datosSerie.actores();
    }

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", evaluacion=" + evaluacion +
                ", poster='" + poster + '\'' +
                ", genero=" + genero +
                ", sinopsis='" + sinopsis + '\'' +
                ", actores='" + actores + '\'';
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }
}
