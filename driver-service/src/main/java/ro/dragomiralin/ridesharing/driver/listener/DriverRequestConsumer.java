package ro.dragomiralin.ridesharing.driver.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ridesharing.driver.config.KafkaTopics;
import ro.dragomiralin.ridesharing.driver.dto.DriverAcceptedEvent;
import ro.dragomiralin.ridesharing.driver.dto.DriverRequestEvent;

import java.util.UUID;

@Slf4j
@Service
public class DriverRequestConsumer {

    @KafkaListener(topics = KafkaTopics.DRIVER_REQUESTS, groupId = KafkaTopics.GROUP_ID)
//    @SendTo(KafkaTopics.DRIVER_ACCEPTED)
    public DriverAcceptedEvent consume(DriverRequestEvent driverRequestEvent) {
        log.info("Received trip request with id: {}", driverRequestEvent.getEventId());

        log.info("Sending driver accepted event: {}", driverRequestEvent.getTripDTO().id());
        return DriverAcceptedEvent.builder()
                .tripId(driverRequestEvent.getTripDTO().id())
                .driverId(Long.parseLong(UUID.randomUUID().toString()))
                .build();
    }
}
