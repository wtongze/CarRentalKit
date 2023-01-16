package com.wtongze.carrentalkit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wtongze.carrentalkit.model.RentalLocation;
import com.wtongze.carrentalkit.model.RentalQuery;
import com.wtongze.carrentalkit.model.RentalQuote;
import com.wtongze.carrentalkit.repository.RentalLocationRepository;
import com.wtongze.carrentalkit.service.ComplexRentalQuoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@RestController
@RequestMapping("/quote")
public class RentalQuoteController {
    @Autowired
    private ComplexRentalQuoteService quoteService;

    @Autowired
    private RentalLocationRepository locationRepository;

    @GetMapping("/test")
    public Flux<RentalLocation> testLocation() {
        return locationRepository.findAll();
    }

    @GetMapping("/standard")
    public Flux<RentalQuote> get(@Valid RentalQuery q) {
        return quoteService.getStandardQuotes(
                q.getPickupLocation(),
                q.getStart(),
                q.getEnd() == null ? q.getStart().plusDays(1) : q.getEnd(),
                q.getPromotionCode()
        );
    }
}
