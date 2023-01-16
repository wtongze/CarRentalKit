package com.wtongze.carrentalkit.model.QuoteService;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Address {
    private String full;
    @JsonProperty("short")
    private String name;
}
