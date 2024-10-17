package com.example.savior.gutendex.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
