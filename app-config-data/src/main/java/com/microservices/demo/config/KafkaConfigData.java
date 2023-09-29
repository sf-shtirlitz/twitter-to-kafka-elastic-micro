package com.microservices.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaConfigData {
    private String bootstrapServers;
    private String schemaRegistryUrlKey;
    private String schemaRegistryUrl;
    private String topicName;
    private List<String> topicNamesToCreate;
    private Integer numOfPartitions;
    private Short replicationFactor;

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public String getSchemaRegistryUrlKey() {
        return schemaRegistryUrlKey;
    }

    public String getSchemaRegistryUrl() {
        return schemaRegistryUrl;
    }

    public String getTopicName() {
        return topicName;
    }

    public List<String> getTopicNamesToCreate() {
        return topicNamesToCreate;
    }

    public Integer getNumOfPartitions() {
        return numOfPartitions;
    }

    public Short getReplicationFactor() {
        return replicationFactor;
    }
}
