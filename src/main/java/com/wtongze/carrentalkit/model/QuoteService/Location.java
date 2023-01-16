package com.wtongze.carrentalkit.model.QuoteService;

import lombok.Data;

@Data
public class Location {
    private String name;
    private Address address;
    private LocationNumbers locationNumbers;
    private String phone;
    private String[] hours;
    private String locationKey;
    private Boolean privateAirport;
    private Boolean restricted;
}
