package com.wtongze.carrentalkit.controller;

import com.wtongze.carrentalkit.model.RentalQuote;
import com.wtongze.carrentalkit.service.RentalQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/quote")
public class RentalQuoteController {
    private final RentalQuoteService quoteService;

    @Autowired
    public RentalQuoteController(RentalQuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/standard")
    public Flux<RentalQuote> get() {
        return quoteService.getStandardQuotes(
                "San Jose, CA",
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now().plus(1, ChronoUnit.DAYS)),
                null);
    }
}