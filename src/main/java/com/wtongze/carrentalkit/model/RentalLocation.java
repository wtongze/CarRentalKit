package com.wtongze.carrentalkit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class RentalLocation {
    /**
     * ID of the rental location
     * @example 1
     */
    @JsonIgnore
    private Long id;

    /**
     * Name of the rental location
     * @example San Jose Airport Branch
     */
    private String name;

    /**
     * Full address of the rental location
     * @example 123 Main St, San Jose, CA
     */
    private String address;

    /**
     * Latitude of the rental location
     * @example 37.363949
     */
    private Float latitude;

    /**
     * Longitude of the rental location
     * @example -121.928940
     */
    private Float longitude;
}
