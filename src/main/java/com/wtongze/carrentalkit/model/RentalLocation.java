package com.wtongze.carrentalkit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wtongze.carrentalkit.model.QuoteService.Location;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("rental_location")
public class RentalLocation {
    /**
     * ID of the rental location
     *
     * @example 1
     */
    @JsonIgnore
    @Id
    private Long id;

    /**
     * Name of the rental location
     *
     * @example San Jose Airport Branch
     */
    private String name;

    /**
     * Full address of the rental location
     *
     * @example 123 Main St, San Jose, CA
     */
    private String address;

    /**
     * Latitude of the rental location
     *
     * @example 37.363949
     */
    private Double latitude;

    /**
     * Longitude of the rental location
     *
     * @example -121.928940
     */
    private Double longitude;

    public static RentalLocation fromQuoteServiceLocation(Location location) {
        if (location == null) {
            return null;
        } else {
            RentalLocation rentalLocation = new RentalLocation();
            rentalLocation.setName(location.getAddress().getName());
            rentalLocation.setAddress(location.getAddress().getFull());
            rentalLocation.setLatitude(location.getLocationNumbers().getLatitude());
            rentalLocation.setLongitude(location.getLocationNumbers().getLongitude());
            return rentalLocation;
        }
    }
}
