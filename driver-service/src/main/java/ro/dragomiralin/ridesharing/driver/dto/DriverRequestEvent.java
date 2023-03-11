package ro.dragomiralin.ridesharing.driver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DriverRequestEvent {
    private UUID eventId;
    private TripDTO tripDTO;


}
