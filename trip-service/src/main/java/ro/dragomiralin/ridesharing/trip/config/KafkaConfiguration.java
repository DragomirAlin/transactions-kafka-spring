package ro.dragomiralin.ridesharing.trip.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic tripRequests() {
        return TopicBuilder.name("tripRequests")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic driverRequests() {
        return TopicBuilder.name("driverRequests")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic driverConfirmation() {
        return TopicBuilder.name("driverRequests")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
