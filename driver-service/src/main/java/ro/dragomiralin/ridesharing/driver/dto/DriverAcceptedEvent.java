package ro.dragomiralin.ridesharing.driver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DriverAcceptedEvent {
    private long driverId;
    private long tripId;
}
