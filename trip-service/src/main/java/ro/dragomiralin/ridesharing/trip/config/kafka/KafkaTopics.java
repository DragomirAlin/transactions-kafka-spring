package ro.dragomiralin.ridesharing.trip.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopics {
    public static final String GROUP_ID = "ride";
    public static final String DRIVER_ACCEPTED = "driver-accepted";
    public static final String TRIP_REQUESTS = "trip-requests";

    @Bean
    public NewTopic tripRequests() {
        return TopicBuilder.name(TRIP_REQUESTS)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
