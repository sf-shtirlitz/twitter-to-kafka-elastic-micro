package com.microservices.demo.gateway.service.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import zipkin2.reporter.kafka.KafkaSender;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class ZipkinConfig {
    @Bean("zipkinSender")
    KafkaSender senderZipkin(KafkaProperties config, Environment environment) {
        String topic = environment.getProperty("management.tracing.kafka.topic", "zipkin");
        String serviceName = environment.getProperty("management.tracing.service.name", "gateway-service");
        Map<String, Object> properties = config.buildProducerProperties();
        properties.put("key.serializer", ByteArraySerializer.class.getName());
        properties.put("value.serializer", ByteArraySerializer.class.getName());
        properties.put(CommonClientConfigs.CLIENT_ID_CONFIG, serviceName);
        properties.put("bootstrap.servers", "kafka-broker-1:9092,kafka-broker-2:9092,kafka-broker-3:9092");
        properties.put("observationEnabled", true);

        return KafkaSender.newBuilder().topic(topic).overrides(properties).build();
    }
}
