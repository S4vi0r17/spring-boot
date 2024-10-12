package com.savior.screenmatch.principal;

import com.savior.screenmatch.model.DatosSerie;
import com.savior.screenmatch.model.DatosTemporadas;
import com.savior.screenmatch.service.ConsumoAPI;
import com.savior.screenmatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String API = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=991b34ae";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraMenu() {
        System.out.print("Escribe el nombre de la serie que deseas buscar: ");
        var serie = scanner.nextLine();
        var json = consumoAPI.obtenerDatos(API + serie.replace(" ", "+") + API_KEY);
        var datosSerie = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datosSerie);

        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datosSerie.totalTemporadas(); i++) {
            json = consumoAPI.obtenerDatos(API + serie.replace(" ", "+") + "&season=" + i + API_KEY);
            // System.out.println(json);
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        temporadas.forEach(System.out::println);
    }
}
