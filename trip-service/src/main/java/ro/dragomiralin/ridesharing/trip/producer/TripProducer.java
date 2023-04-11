package ro.dragomiralin.ridesharing.trip.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ridesharing.trip.config.kafka.KafkaTopics;
import ro.dragomiralin.ridesharing.trip.dto.DriverRequestEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripProducer {
    private final KafkaTemplate<String, DriverRequestEvent> kafkaTemplate;

    public void sendDriverRequest(DriverRequestEvent driverRequestEvent) {
        log.info("Sending trip request event: {}", driverRequestEvent);
        kafkaTemplate.send(KafkaTopics.DRIVER_REQUESTS, driverRequestEvent);
    }
}
