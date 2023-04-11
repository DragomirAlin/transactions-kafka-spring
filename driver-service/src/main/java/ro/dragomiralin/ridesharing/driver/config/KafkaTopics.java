package ro.dragomiralin.ridesharing.driver.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopics {
    public static final String GROUP_ID = "ride";
    public static final String DRIVER_REQUESTS = "driver-requests";
    public static final String DRIVER_ACCEPTED = "driver-accepted";

    @Bean
    public NewTopic driverRequests() {
        return TopicBuilder.name(DRIVER_REQUESTS)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic driverAccepted() {
        return TopicBuilder.name(DRIVER_ACCEPTED)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
