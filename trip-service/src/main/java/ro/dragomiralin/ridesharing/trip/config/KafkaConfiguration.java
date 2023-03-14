package ro.dragomiralin.ridesharing.trip.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ro.dragomiralin.ridesharing.trip.domain.Trip;

import java.util.Map;

import static ro.dragomiralin.ridesharing.trip.config.KafkaTopics.*;

@Configuration
@EnableKafka
@EnableConfigurationProperties(KafkaProperties.class)
@RequiredArgsConstructor
public class KafkaConfiguration {
    private final KafkaProperties properties;
    private final ObjectProvider<RecordMessageConverter> messageConverterProvider;

    @Bean
    public KafkaTemplate<Integer, String> simpleKafkaTemplate(ProducerFactory<Integer, String> simpleProducerFactory) {
        KafkaTemplate<Integer, String> kafkaTemplate = new KafkaTemplate<>(simpleProducerFactory);
        RecordMessageConverter messageConverter = messageConverterProvider.getIfUnique();
        if (messageConverter != null) {
            kafkaTemplate.setMessageConverter(messageConverter);
        }
        return kafkaTemplate;
    }

    @Bean
    public ProducerFactory<Integer, String> simpleProducerFactory() {
        Map<String, Object> producerProperties = this.properties.buildProducerProperties();
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        DefaultKafkaProducerFactory<Integer, String> factory = new DefaultKafkaProducerFactory<>(producerProperties);
        String transactionIdPrefix = this.properties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, String> simpleKafkaConsumerFactory() {
        Map<String, Object> consumerProperties = this.properties.buildConsumerProperties();
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return new DefaultKafkaConsumerFactory<>(consumerProperties);
    }


    @Bean("simpleKafkaListenerContainerFactory")
    ConcurrentKafkaListenerContainerFactory<Integer, String> simpleKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(simpleKafkaConsumerFactory());
        return factory;
    }

    // Second consumer/producer config

    @Bean
    public KafkaTemplate<String, Trip> jsonKafkaTemplate(ProducerFactory<String, Trip> jsonProducerFactory) {
        KafkaTemplate<String, Trip> kafkaTemplate = new KafkaTemplate<>(jsonProducerFactory);
        RecordMessageConverter messageConverter = messageConverterProvider.getIfUnique();
        if (messageConverter != null) {
            kafkaTemplate.setMessageConverter(messageConverter);
        }
        return kafkaTemplate;
    }

    @Bean
    public ProducerFactory<String, Trip> jsonProducerFactory() {
        Map<String, Object> producerProperties = this.properties.buildProducerProperties();
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        DefaultKafkaProducerFactory<String, Trip> factory = new DefaultKafkaProducerFactory<>(producerProperties);
        String transactionIdPrefix = this.properties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Trip> jsonKafkaConsumerFactory() {
        Map<String, Object> consumerProperties = this.properties.buildConsumerProperties();
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        consumerProperties.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(consumerProperties);
    }


    @Bean("jsonKafkaListenerContainerFactory")
    ConcurrentKafkaListenerContainerFactory<String, Trip> jsonKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Trip> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(jsonKafkaConsumerFactory());
        return factory;
    }


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
