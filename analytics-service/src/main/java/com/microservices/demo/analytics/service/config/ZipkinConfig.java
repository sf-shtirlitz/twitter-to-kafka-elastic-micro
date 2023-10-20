package com.microservices.demo.analytics.service.config;

import com.microservices.demo.analytics.service.business.impl.AnalyticsKafkaConsumer;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(ZipkinConfig.class);
    @Bean("zipkinSender")
    KafkaSender senderZipkin(KafkaProperties config, Environment environment) {

        LOG.info("Configuring zipkinSender...");

        String topic = environment.getProperty("management.tracing.kafka.topic", "zipkin");
        String serviceName = environment.getProperty("management.tracing.service.name", "analytics-service");
        Map<String, Object> properties = config.buildProducerProperties();
        properties.put("key.serializer", ByteArraySerializer.class.getName());
        properties.put("value.serializer", ByteArraySerializer.class.getName());
        properties.put(CommonClientConfigs.CLIENT_ID_CONFIG, serviceName);
        properties.put("bootstrap.servers", "kafka-broker-1:9092,kafka-broker-2:9092,kafka-broker-3:9092");
        properties.put("observationEnabled", true);

        LOG.info("Configuring senderZipkin: properties are formed... Building KafkaSender next....");

        return KafkaSender.newBuilder().topic(topic).overrides(properties).build();
    }
}
