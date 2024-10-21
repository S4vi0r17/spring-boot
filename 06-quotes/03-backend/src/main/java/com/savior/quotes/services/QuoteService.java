package com.savior.quotes.services;

import com.savior.quotes.dto.QuoteDto;
import com.savior.quotes.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    public QuoteDto getRandomQuote() {
        var quote = quoteRepository.findRandomQuote();
        System.out.println(quote);
        return new QuoteDto(quote.getTitle(), quote.getAuthor(), quote.getText(), quote.getPoster());
    }
}
