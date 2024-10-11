package com.savior.screenmatch;

import com.savior.screenmatch.model.DatosEpisodio;
import com.savior.screenmatch.model.DatosSerie;
import com.savior.screenmatch.model.DatosTemporadas;
import com.savior.screenmatch.service.ConsumoAPI;
import com.savior.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&apikey=991b34ae");
		// var json = consumoAPI.obtenerDatos("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
		ConvierteDatos convierteDatos = new ConvierteDatos();
		var datosSerie = convierteDatos.obtenerDatos(json, DatosSerie.class);
		System.out.println(datosSerie);

		json = consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&season=1&episode=1&apikey=991b34ae");
		System.out.println(json);
		DatosEpisodio datosEpisodio = convierteDatos.obtenerDatos(json, DatosEpisodio.class);
		System.out.println(datosEpisodio);

		List<DatosTemporadas> temporadas = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			json = consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&season=" + i + "&apikey=991b34ae");
			// System.out.println(json);
			var datosTemporadas = convierteDatos.obtenerDatos(json, DatosTemporadas.class);
			temporadas.add(datosTemporadas);
		}
		temporadas.forEach(System.out::println);
	}
}
