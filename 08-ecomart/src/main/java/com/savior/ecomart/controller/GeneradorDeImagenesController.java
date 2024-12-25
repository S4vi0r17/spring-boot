package com.savior.ecomart.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagen")
public class GeneradorDeImagenesController {

    private final ImageModel imageModel;

    public GeneradorDeImagenesController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    // localhost:8080/imagen?promt=Un%20perro%20feliz
    @GetMapping
    public String generadorDeImagenes(String promt) {
        var options = ImageOptionsBuilder.builder()
                .withHeight(1024)
                .withWidth(1024)
                .build();
        var response = imageModel.call(new ImagePrompt(promt, options));
        return response.getResult().getOutput().getUrl();
    }
}
