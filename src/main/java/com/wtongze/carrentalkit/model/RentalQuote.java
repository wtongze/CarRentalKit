package com.wtongze.carrentalkit.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalQuote {
    private String pickUpLocation;
    private String dropOffLocation;
    private CarType carType;
    private Float price;
}
