package com.savior.ecomart.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generador")
public class GeneradorDeProductosController {

    private final ChatClient chatClient;

    public GeneradorDeProductosController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    /*
    public GeneradorDeProductosController(@Qualifier("gpt-4o") ChatClient chatClient) {
        this.chatClient = chatClient;
    }
     */

    @GetMapping
    public String generarProductos() {
        var pregunta = "Generar 5 productos ecologicos";
        return this.chatClient.prompt()
                .user(pregunta)
                .call()
                .content();
    }
}
