package ro.dragomiralin.ridesharing.driver.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ro.dragomiralin.ridesharing.driver.dto.DriverAcceptedEvent;
import ro.dragomiralin.ridesharing.driver.dto.DriverRequestEvent;

import java.util.Map;

@Configuration
@EnableKafka
@EnableConfigurationProperties(KafkaProperties.class)
@RequiredArgsConstructor
public class KafkaConfig {
    private final KafkaProperties properties;
    private final ObjectProvider<RecordMessageConverter> messageConverterProvider;

    @Bean
    public KafkaTemplate<String, DriverAcceptedEvent> jsonKafkaTemplate(ProducerFactory<String, DriverAcceptedEvent> jsonProducerFactory) {
        KafkaTemplate<String, DriverAcceptedEvent> kafkaTemplate = new KafkaTemplate<>(jsonProducerFactory);
        RecordMessageConverter messageConverter = messageConverterProvider.getIfUnique();
        if (messageConverter != null) {
            kafkaTemplate.setMessageConverter(messageConverter);
        }
        return kafkaTemplate;
    }

    @Bean
    public ProducerFactory<String, DriverAcceptedEvent> jsonProducerFactory() {
        Map<String, Object> producerProperties = this.properties.buildProducerProperties();
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        DefaultKafkaProducerFactory<String, DriverAcceptedEvent> factory = new DefaultKafkaProducerFactory<>(producerProperties);
        String transactionIdPrefix = this.properties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }

    @Bean
    public ConsumerFactory<String, DriverRequestEvent> jsonKafkaConsumerFactory() {
        Map<String, Object> consumerProperties = this.properties.buildConsumerProperties();
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        consumerProperties.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(consumerProperties);
    }


    @Bean("jsonKafkaListenerContainerFactory")
    ConcurrentKafkaListenerContainerFactory<String, DriverRequestEvent> jsonKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DriverRequestEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(jsonKafkaConsumerFactory());
        return factory;
    }
}
