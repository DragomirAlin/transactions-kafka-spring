package ro.dragomiralin.ridesharing.trip.config.kafka;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopics {
    public static final String GROUP_ID = "ride";
    public static final String DRIVER_ACCEPTED = "driver-accepted";
    public static final String DRIVER_REQUESTS = "driver-requests";
    public static final String TRIP_REQUESTS = "trip-requests";

}
