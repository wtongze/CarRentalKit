package com.wtongze.carrentalkit.service;

import com.wtongze.carrentalkit.model.CarType;
import com.wtongze.carrentalkit.model.RentalLocation;
import com.wtongze.carrentalkit.model.RentalQuote;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A service that provides fake rental quotes.
 */
@Service
public class SimpleRentalQuoteService implements RentalQuoteService {
    private final Random r = new Random();

    @Override
    public Flux<RentalQuote> getStandardQuotes(String location, Timestamp start, Timestamp end, String promotionCode) {
        return this.getOneWayQuotes(location, null, start, end, promotionCode);
    }

    @Override
    public Flux<RentalQuote> getOneWayQuotes(String pickUpLocation, String dropOffLocation, Timestamp start, Timestamp end, String promotionCode) {
        List<RentalQuote> quotes = new ArrayList<>();
        int count = r.nextInt(1, 5);
        for (int i = 0; i < count; i++) {
            quotes.add(new RentalQuote(new RentalLocation(), new RentalLocation(), randomCarType(), randomPrice(), false));
        }
        return Flux.fromIterable(quotes);
    }

    private Float randomPrice() {
        return Math.round(r.nextFloat(20, 100) * 100) / 100f;
    }

    private CarType randomCarType() {
        CarType[] carTypes = CarType.values();
        return carTypes[r.nextInt(carTypes.length)];
    }
}
