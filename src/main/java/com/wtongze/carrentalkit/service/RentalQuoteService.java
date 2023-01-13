package com.wtongze.carrentalkit.service;

import com.wtongze.carrentalkit.model.RentalQuote;
import reactor.core.publisher.Flux;

import java.sql.Timestamp;
import java.util.List;

public interface RentalQuoteService {
    /**
     * Get quote for rentals with same pick-up and drop-off location.
     * @param location Pick-up / Drop-off Location
     * @param start Start Timestamp
     * @param end End Timestamp
     * @param promotionCode Promotion Code
     */
    public Flux<RentalQuote> getStandardQuotes(String location, Timestamp start, Timestamp end, String promotionCode);

    /**
     * Get quote for one-way rentals
     * @param pickUpLocation Pick-up Location
     * @param dropOffLocation Drop-off Location
     * @param start Start Timestamp
     * @param end End Timestamp
     * @param promotionCode Promotion Code
     */
    public Flux<RentalQuote> getOneWayQuotes(String pickUpLocation, String dropOffLocation, Timestamp start, Timestamp end, String promotionCode);
}
