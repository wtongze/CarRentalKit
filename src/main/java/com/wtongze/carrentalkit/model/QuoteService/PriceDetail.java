package com.wtongze.carrentalkit.model.QuoteService;

import lombok.Data;

@Data
public class PriceDetail {
    private String symbol;
    private String currency;
    private Float amount;
}
