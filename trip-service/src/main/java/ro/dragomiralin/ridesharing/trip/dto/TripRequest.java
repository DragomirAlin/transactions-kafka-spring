package ro.dragomiralin.ridesharing.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public record TripRequest(
        long customerId,
        String startLocation,
        String endLocation
) {
}
