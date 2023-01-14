package com.wtongze.carrentalkit.repository;

import com.wtongze.carrentalkit.model.RentalLocation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalLocationRepository extends ReactiveCrudRepository<RentalLocation, Long> {
}
