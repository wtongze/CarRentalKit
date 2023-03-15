package com.wtongze.carrentalkit.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wtongze.carrentalkit.validation.FutureLocalDateTime;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentalQuery {
    @FutureLocalDateTime
    
    private LocalDateTime start;

    private LocalDateTime end;

    @NotNull
    @Parameter(description = "Pickup Location")
    private String pickupLocation = "San Jose, CA, USA";

    private String promotionCode;
}

