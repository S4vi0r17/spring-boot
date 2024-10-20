package com.savior.screenmatch.principal;

import java.util.List;
import java.util.Locale;

public class EjemploStreams {
    public void muestraEjemplo() {
        List<String> nombres = List.of("Juan", "Pedro", "Luis", "Ana", "Maria", "Jose", "Carlos", "Sofia", "Luisa", "Marta");
        nombres.stream()
                .sorted()
                .limit(4)
                .filter(name -> name.startsWith("J"))
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
