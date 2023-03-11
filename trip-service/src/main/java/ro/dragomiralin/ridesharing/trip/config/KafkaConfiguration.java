package ro.dragomiralin.ridesharing.trip.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static ro.dragomiralin.ridesharing.trip.config.KafkaTopics.*;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic tripRequests() {
        return TopicBuilder.name(TRIP_REQUESTS)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic driverRequests() {
        return TopicBuilder.name(DRIVER_REQUESTS)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic driverConfirmation() {
        return TopicBuilder.name(DRIVER_ACCEPTED)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
