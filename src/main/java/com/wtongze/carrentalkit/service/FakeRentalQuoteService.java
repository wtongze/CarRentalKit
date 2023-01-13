package com.wtongze.carrentalkit.service;

import com.wtongze.carrentalkit.model.CarType;
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
@Service("FakeRentalQuoteService")
public class FakeRentalQuoteService implements RentalQuoteService {
    private Random r;

    @Override
    public Flux<RentalQuote> getStandardQuotes(String location, Timestamp start, Timestamp end, String promotionCode) {
        return this.getOneWayQuotes(location, location, start, end, promotionCode);
    }

    @Override
    public Flux<RentalQuote> getOneWayQuotes(String pickUpLocation, String dropOffLocation, Timestamp start, Timestamp end, String promotionCode) {
        Random r = new Random();
        List<RentalQuote> quotes = new ArrayList<>();
        int count = r.nextInt(1, 5);
        for (int i = 0; i < count; i++) {
            quotes.add(new RentalQuote(pickUpLocation, dropOffLocation, randomCarType(), randomPrice()));
        }
        return Flux.fromIterable(quotes);
    }

    private Float randomPrice() {
        Random r = new Random();
        return Math.round(r.nextFloat(20, 100) * 100) / 100f;
    }

    private CarType randomCarType() {
        Random r = new Random();
        CarType[] carTypes = CarType.values();
        return carTypes[r.nextInt(carTypes.length)];
    }
}
