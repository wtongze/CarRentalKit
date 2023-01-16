package com.wtongze.carrentalkit.model;

import com.wtongze.carrentalkit.model.QuoteService.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalQuote {
    private RentalLocation pickupLocation;
    private RentalLocation returnLocation;
    private CarType carType;
    private Float price;
    private Boolean promoCodeApplied;

    public static RentalQuote fromQuoteServiceResult(Result r) {
        RentalQuote quote = new RentalQuote();
        quote.setPickupLocation(RentalLocation.fromQuoteServiceLocation(r.getPickupLocation()));
        quote.setReturnLocation(RentalLocation.fromQuoteServiceLocation(r.getReturnLocation()));
        quote.setCarType(CarType.findByCode(r.getCarDetail().getCarType().getCode()));
        quote.setPrice(r.getPaylater().getTotalCost().getAmount());
        quote.setPromoCodeApplied(r.getPromoCodeApplied());
        return quote;
    }
}
