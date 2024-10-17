package com.example.savior.gutendex;

import com.example.savior.gutendex.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GutendexApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GutendexApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		ConsumoAPI consumoAPI = new ConsumoAPI();
		System.out.println(consumoAPI.obtenerLibros());
	}
}
