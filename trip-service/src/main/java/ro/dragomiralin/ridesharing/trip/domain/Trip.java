package ro.dragomiralin.ridesharing.trip.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Trip {
    private long id;
    private long customerId;
    private long driverId;
    private TripStatus status;
    private String startLocation;
    private String endLocation;
    private Date startTime;
    private Date endTime;
}
