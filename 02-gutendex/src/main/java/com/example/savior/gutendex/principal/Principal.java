package com.example.savior.gutendex.principal;

import com.example.savior.gutendex.model.Datos;
import com.example.savior.gutendex.model.DatosLibro;
import com.example.savior.gutendex.service.ConsumoAPI;
import com.example.savior.gutendex.service.ConvierteDatos;

import java.util.Comparator;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

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
    }
}
