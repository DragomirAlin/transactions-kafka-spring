package ro.dragomiralin.ridesharing.trip.dto;

import lombok.Builder;
import ro.dragomiralin.ridesharing.trip.domain.TripStatus;

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
