package com.savior.quotes.controllers;

import com.savior.quotes.dto.QuoteDto;
import com.savior.quotes.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/series/frases")
    public QuoteDto getRandomQuote() {
        return quoteService.getRandomQuote();
    }
}
