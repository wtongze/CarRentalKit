package com.wtongze.carrentalkit.model.QuoteService;

import lombok.Data;

@Data
public class Result {
    private CarDetail carDetail;
    private Boolean promoCodeApplied;
    private Price paylater;
    private Location pickupLocation;
    private Location returnLocation;
    private Boolean isDropOffWithinHours;
    private Boolean isPickupWithinHours;
}
