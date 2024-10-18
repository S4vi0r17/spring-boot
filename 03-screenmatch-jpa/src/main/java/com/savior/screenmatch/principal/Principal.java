package com.savior.screenmatch.principal;

import com.savior.screenmatch.model.DatosEpisodio;
import com.savior.screenmatch.model.DatosSerie;
import com.savior.screenmatch.model.DatosTemporadas;
import com.savior.screenmatch.model.Episodio;
import com.savior.screenmatch.service.ConsumoAPI;
import com.savior.screenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private static final String API = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=991b34ae";
    private final ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosSerie> datosSeries = new ArrayList<>();

    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            System.out.println("1. Buscar serie");
            System.out.println("2. Buscar episodios");
            System.out.println("3. Mostrar series buscadas");
            System.out.println("0. Salir");
            System.out.print("Escribe la opcion que deseas: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1 -> buscarSerieWeb();
                case 2 -> buscarEpisodioPorSerie();
                case 3 -> mostrarSeriesBuscadas();
                case 0 -> System.out.println("Cerrando aplicacion...");
                default -> System.out.println("Opcion no valida");
            }
        }
    }

    private DatosSerie getDatosSerie() {
        System.out.print("Escribe el nombre de la serie que deseas buscar: ");
        var nombreSerie = scanner.nextLine();
        var json = consumoAPI.obtenerDatos(API + nombreSerie.replace(" ", "+") + API_KEY);
        System.out.println(json);
        DatosSerie datosSerie = conversor.obtenerDatos(json, DatosSerie.class);
        return datosSerie;
    }

    private void buscarEpisodioPorSerie() {
        DatosSerie datosSerie = getDatosSerie();
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datosSerie.totalTemporadas(); i++) {
            var json = consumoAPI.obtenerDatos(API + datosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            // System.out.println(json);
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        temporadas.forEach(System.out::println);
    }

    private void buscarSerieWeb() {
        DatosSerie datosSerie = getDatosSerie();
        datosSeries.add(datosSerie);
        System.out.println(datosSerie);
    }

    private void mostrarSeriesBuscadas() {
        datosSeries.forEach(System.out::println);
    }
}
