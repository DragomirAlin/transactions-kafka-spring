package ro.dragomiralin.ridesharing.trip.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ridesharing.trip.dto.DriverAcceptedEvent;
import ro.dragomiralin.ridesharing.trip.service.TripService;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverAcceptedConsumer {
    private final TripService tripService;
    public static final String DRIVER_ACCEPTED = "driver-accepted";

    @KafkaListener(topics = "driver-accepted", groupId = "trip")
    public void consume(DriverAcceptedEvent driverAcceptedEvent) {
        log.info("Received driver accepted event: {}", driverAcceptedEvent);
        tripService.updateTripDriver(driverAcceptedEvent.getTripId(), driverAcceptedEvent.getDriverId());
    }

}
