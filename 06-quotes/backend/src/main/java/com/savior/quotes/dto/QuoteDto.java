package com.savior.quotes.dto;

public record QuoteDto(
        String title,
        String author,
        String text,
        String poster
) { }
