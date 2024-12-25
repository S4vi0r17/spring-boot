package com.savior.ecomart.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorizador")
public class CategorizadorrDeProductosController {

    private final ChatClient chatClient;

    public CategorizadorrDeProductosController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public String generarProductos() {
        var pregunta = "Generar 5 productos ecologicos";
        return this.chatClient.prompt()
                .user(pregunta)
                .call()
                .content();
    }
}
