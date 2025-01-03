package com.example.savior.gutendex.principal;

import com.example.savior.gutendex.model.Datos;
import com.example.savior.gutendex.model.DatosLibro;
import com.example.savior.gutendex.service.ConsumoAPI;
import com.example.savior.gutendex.service.ConvierteDatos;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
        var datos = convierteDatos.obtenerDatos(json, Datos.class);
        System.out.println(datos);

        // Top 10 de libros más descargados
        System.out.println("Top 10 de libros más descargados");
        datos.libros().stream()
                .sorted(Comparator.comparing(DatosLibro::numeroDescargas).reversed())
                .limit(10)
                .map(libro -> libro.titulo().toUpperCase())
                .forEach(System.out::println);

        // Busque da de libros por nombre
        System.out.print("Ingrese el nombre del libro a buscar: ");
        var nombreLibro = scanner.nextLine();
        System.out.println("Libros encontrados con el nombre " + nombreLibro);
        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+")); // %20
        var datosBusqueda = convierteDatos.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libro = datosBusqueda.libros().stream()
                .filter(libro1 -> libro1.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();

        if (libro.isPresent()) {
            System.out.println("Libro encontrado: " + libro.get());
        } else {
            System.out.println("No se encontraron libros con el nombre " + nombreLibro);
        }

        // Trabajando con estadísticas
        DoubleSummaryStatistics estadisticas = datos.libros().stream()
                .filter(libro1 -> libro1.numeroDescargas() > 0)
                // .collect(Collectors.summarizingDouble(DatosLibro::numeroDescargas)); // Un poco menos eficiente
                .mapToDouble(DatosLibro::numeroDescargas)
                .summaryStatistics();

        System.out.println("Estadísticas de descargas");
        System.out.println("Promedio: " + estadisticas.getAverage());
        System.out.println("Máximo: " + estadisticas.getMax());
        System.out.println("Mínimo: " + estadisticas.getMin());
        System.out.println("Suma: " + estadisticas.getSum());
    }
}
