package com.wtongze.carrentalkit.service;

import com.wtongze.carrentalkit.model.RentalQuote;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface RentalQuoteService {
    /**
     * Get quote for rentals with same pick-up and drop-off location.
     *
     * @param location      Pick-up / Drop-off Location
     * @param start         Start LocalDateTime
     * @param end           End LocalDateTime
     * @param promotionCode Promotion Code
     */
    public Flux<RentalQuote> getStandardQuotes(String location, LocalDateTime start, LocalDateTime end, String promotionCode);

    /**
     * Get quote for one-way rentals
     *
     * @param pickUpLocation  Pick-up Location
     * @param dropOffLocation Drop-off Location
     * @param start           Start Timestamp
     * @param end             End Timestamp
     * @param promotionCode   Promotion Code
     */
    public Flux<RentalQuote> getOneWayQuotes(String pickUpLocation, String dropOffLocation, LocalDateTime start, LocalDateTime end, String promotionCode);
}
