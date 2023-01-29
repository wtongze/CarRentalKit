package com.wtongze.carrentalkit.service;

import com.wtongze.carrentalkit.model.CarType;
import com.wtongze.carrentalkit.model.QuoteService.Data;
import com.wtongze.carrentalkit.model.QuoteService.Session;
import com.wtongze.carrentalkit.model.RentalQuote;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class ComplexRentalQuoteService implements RentalQuoteService {
    private final WebClient webClient;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    public ComplexRentalQuoteService(@Value("${QUOTE_SERVICE_ENDPOINT:http://localhost:3000}") String endpoint) {
        this.webClient = WebClient.create(endpoint);
    }

    @Override
    public Flux<RentalQuote> getStandardQuotes(String location, LocalDateTime start, LocalDateTime end, String promotionCode) {
        return this.getOneWayQuotes(location, null, start, end, promotionCode);
    }

    @Override
    public Flux<RentalQuote> getOneWayQuotes(String pickUpLocation, String dropOffLocation, LocalDateTime start, LocalDateTime end, String promotionCode) {
        MultiValueMap<String, String> basicParams = new LinkedMultiValueMap<>();
        basicParams.add("pickup.location", pickUpLocation);
        basicParams.add("dropoff.location", dropOffLocation == null ? "" : dropOffLocation);
        basicParams.add("pickup.date", start.format(dateFormatter));
        basicParams.add("pickup.time", start.format(timeFormatter));
        basicParams.add("dropoff.date", end.format(dateFormatter));
        basicParams.add("dropoff.time", end.format(timeFormatter));
        basicParams.add("promotion.code", promotionCode == null ? "" : promotionCode);
        basicParams.add("loyaltyNum", "");
        basicParams.add("tqs2", "true");
        basicParams.add("hdt", "true");
        basicParams.add("hdtplus", "false");

        MultiValueMap<String, String> addiParams = new LinkedMultiValueMap<>();
        addiParams.add("vender", "Hertz");
        addiParams.add("sortBy", "price");
        addiParams.add("sortDir", "asc");
        addiParams.add("cityView", "listView");
        addiParams.add("transmission", "Automatic");

        Set<CarType> carTypeSet = new HashSet<>();

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/car/availability/results").queryParams(basicParams).build())
                .header("User-Agent",
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:108.0) Gecko/20100101 Firefox/108.0")
                .exchangeToMono(response -> {
                    String[] parts = response.headers().header("Location").get(0).split("\\?|&");
                    for (String p : parts) {
                        if (p.startsWith("search-id=")) {
                            String searchId = p.substring(10);
                            String sessionId = response.cookies().toSingleValueMap().get("PLAY_SESSION").getValue();
                            return Mono.just(new Session(searchId, sessionId));
                        }
                    }
                    return response.createError();
                })
                .flatMap(searchSession -> webClient.get()
                        .uri(uriBuilder ->
                                uriBuilder.path("/car/availability/data")
                                        .queryParams(basicParams)
                                        .queryParams(addiParams)
                                        .queryParam("search-id", searchSession.getSearchId())
                                        .build()
                        ).cookie("PLAY_SESSION", searchSession.getSessionId())
                        .retrieve().bodyToMono(Data.class))
                .flux()
                .flatMap(data -> Flux.fromArray(data.getResults()))
                .map(RentalQuote::fromQuoteServiceResult).filter(data -> {
                    var type = data.getCarType();
                    if (carTypeSet.contains(type)) {
                        return false;
                    } else {
                        carTypeSet.add(type);
                        return true;
                    }
                });
    }
}
