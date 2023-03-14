package ro.dragomiralin.ridesharing.driver.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {

    @Bean
    public NewTopic driversTopic() {
        return TopicBuilder.name("drivers")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
