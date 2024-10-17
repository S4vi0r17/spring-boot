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


        // Mostrar solo el titulo de los episodios para todas las temporadas
        // temporadas.forEach(temporada -> temporada.episodios().forEach(episodio -> System.out.println(episodio.titulo())));
        for (int i = 0; i < datosSerie.totalTemporadas(); i++) {
            System.out.println("Temporada " + (i + 1));
            temporadas.get(i).episodios().forEach(episodio -> System.out.println(episodio.titulo()));
        }


        // Convertir toda la informacion en una lista de tipo DatosEpisodio
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(temporada -> temporada.episodios().stream())
                // .collect(Collectors.toList()); // Lista mutable
                .toList(); // Lista inmutable

        // System.out.println("DatosEpisodio");
        // System.out.println(datosEpisodios);


        // Top 5 episodios con mayor evaluacion
        System.out.println("Top 5 episodios con mayor evaluacion");
        datosEpisodios.stream()
                .filter(episodio -> !episodio.evaluacion().equalsIgnoreCase("N/A"))
                .peek(episodio -> System.out.println("Primer filtro (N/A): " + episodio))
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .peek(episodio -> System.out.println("Segundo filtro (ordenamiento): " + episodio))
                .map(episode -> episode.titulo().toUpperCase())
                .peek(episodio -> System.out.println("Tercer filtro (mapeo): " + episodio))
                .limit(5)
                .peek(episodio -> System.out.println("Cuarto filtro (limit): " + episodio))
                .forEach(System.out::println);


        // Convertir datos a una lista de tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(temporada -> temporada.episodios().stream()
                                .map(episodio -> new Episodio(
                                        temporada.numeroTemporada(),
                                        episodio
                                ))
                        ).collect(Collectors.toList());

        // episodios.forEach(System.out::println);


        // Busqueda de episodos a partir de una fecha
        System.out.print("Escribe la fecha de estreno (yyyy): ");
        var fecha = scanner.nextInt();
        scanner.nextLine();

        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);
        System.out.println("Episodios a partir de la fecha " + fechaBusqueda);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(episodio -> episodio.getFechaEstreno() != null && episodio.getFechaEstreno().isAfter(fechaBusqueda))
                .forEach(episodio -> System.out.println(
                        "Temporada: " + episodio.getTemporada() +
                                " Episodio: " + episodio.getNumeroEpisodio() +
                                " Titulo: " + episodio.getTitulo() +
                                " Evaluacion: " + episodio.getEvaluacion() +
                                " Fecha de estreno: " + episodio.getFechaEstreno().format(formatter)
                ));


        // Busca episodios por un pedazo de titulo
        System.out.print("Escribe el pedazo de titulo que deseas buscar: ");
        var pedazoTitulo = scanner.nextLine();
        System.out.println("Episodios que contienen " + pedazoTitulo);

        // episodios.stream().
        //        filter(episodio -> episodio.getTitulo().contains(pedazoTitulo))
        //        .findFirst()
        //        .ifPresentOrElse(System.out::println, () -> System.out.println("No se encontro ningun episodio"));

        /*
        Optional<Episodio> episodioOptional = episodios.stream().
                filter(episodio -> episodio.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
                // .findAny();
                .findFirst();

        if (episodioOptional.isPresent()) {
            System.out.println("Episodio encontrado: " +episodioOptional.get());
        } else {
            System.out.println("No se encontro ningun episodio");
        }
        */

        /*
        * Diferencia entre findAny y findFirst:
        * findAny: Devuelve cualquier elemento que cumpla con el predicado, no necesariamente el primero.
        * findFirst: Devuelve el primer elemento que cumpla con el predicado.
        */

        Map<Integer,Double> evaluacionPorTemporada = episodios.stream()
                .filter(episodio -> episodio.getEvaluacion() != 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getEvaluacion)));

        System.out.println("Evaluacion por temporada");
        System.out.println(evaluacionPorTemporada);

        DoubleSummaryStatistics estadisticas = episodios.stream()
                .filter(episodio -> episodio.getEvaluacion() != 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));

        System.out.println("Estadisticas de evaluacion de " + estadisticas.getCount() + " episodios");
        System.out.println("Media: " + estadisticas.getAverage());
        System.out.println("Episodio con mayor evaluacion: " + estadisticas.getMax());
        System.out.println("Episodio con menor evaluacion: " + estadisticas.getMin());
    }
}
