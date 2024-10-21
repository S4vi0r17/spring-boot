package com.savior.quotes.repositories;

import com.savior.quotes.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query("SELECT q FROM Quote q ORDER BY function('RANDOM') LIMIT 1")
    public Quote findRandomQuote();
}
