package com.wtongze.carrentalkit.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalQuote {
    private RentalLocation pickUpLocation;
    private RentalLocation dropOffLocation;
    private CarType carType;
    private Float price;
}
