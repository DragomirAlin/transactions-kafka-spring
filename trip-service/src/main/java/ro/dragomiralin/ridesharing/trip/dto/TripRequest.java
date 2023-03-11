package ro.dragomiralin.ridesharing.trip.dto;

import lombok.Builder;

@Builder
public record TripRequest(
        long customerId,
        String startLocation,
        String endLocation
) {
}
