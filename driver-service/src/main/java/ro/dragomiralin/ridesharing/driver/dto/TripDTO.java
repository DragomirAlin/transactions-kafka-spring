package ro.dragomiralin.ridesharing.driver.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record TripDTO(
        long id,
        long customerId,
        long driverId,
        TripStatus status,
        String startLocation,
        String endLocation,
        Instant startTime,
        Instant endTime,
        Instant createdAt
) {

}
