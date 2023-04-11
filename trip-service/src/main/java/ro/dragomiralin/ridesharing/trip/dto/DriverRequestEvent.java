package ro.dragomiralin.ridesharing.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DriverRequestEvent implements Serializable {
    private UUID eventId;
    private TripDTO tripDTO;


}
