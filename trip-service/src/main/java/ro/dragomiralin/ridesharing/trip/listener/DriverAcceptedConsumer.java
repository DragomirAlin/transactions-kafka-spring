package ro.dragomiralin.ridesharing.trip.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ridesharing.trip.config.kafka.KafkaTopics;
import ro.dragomiralin.ridesharing.trip.dto.DriverAcceptedEvent;
import ro.dragomiralin.ridesharing.trip.service.TripService;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverAcceptedConsumer {
    private final TripService tripService;

    @KafkaListener(topics = KafkaTopics.DRIVER_ACCEPTED, groupId = KafkaTopics.GROUP_ID)
    public void consume(DriverAcceptedEvent driverAcceptedEvent) {
        log.info("Received driver accepted event: {}", driverAcceptedEvent);
        tripService.updateTripDriver(driverAcceptedEvent.getTripId(), driverAcceptedEvent.getDriverId());
    }

}
