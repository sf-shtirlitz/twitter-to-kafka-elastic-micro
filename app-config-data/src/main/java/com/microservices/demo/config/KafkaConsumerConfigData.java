package com.microservices.demo.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-consumer-config")
public class KafkaConsumerConfigData {
    private String keyDeserializer;
    private String valueDeserializer;
    private String consumerGroupId;
    private String autoOffsetReset;
    private String specificAvroReaderKey;
    private String specificAvroReader;
    private Boolean batchListener;
    private Boolean autoStartup;
    private Integer concurrencyLevel;
    private Integer sessionTimeoutMs;
    private Integer heartbeatIntervalMs;
    private Integer maxPollIntervalMs;
    private Integer maxPollRecords;
    private Integer maxPartitionFetchBytesDefault;
    private Integer maxPartitionFetchBytesBoostFactor;
    private Long pollTimeoutMs;

    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    public String getValueDeserializer() {
        return valueDeserializer;
    }

    public String getConsumerGroupId() {
        return consumerGroupId;
    }

    public String getAutoOffsetReset() {
        return autoOffsetReset;
    }

    public String getSpecificAvroReaderKey() {
        return specificAvroReaderKey;
    }

    public String getSpecificAvroReader() {
        return specificAvroReader;
    }

    public Boolean getBatchListener() {
        return batchListener;
    }

    public Boolean getAutoStartup() {
        return autoStartup;
    }

    public Integer getConcurrencyLevel() {
        return concurrencyLevel;
    }

    public Integer getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public Integer getHeartbeatIntervalMs() {
        return heartbeatIntervalMs;
    }

    public Integer getMaxPollIntervalMs() {
        return maxPollIntervalMs;
    }

    public Integer getMaxPollRecords() {
        return maxPollRecords;
    }

    public Integer getMaxPartitionFetchBytesDefault() {
        return maxPartitionFetchBytesDefault;
    }

    public Integer getMaxPartitionFetchBytesBoostFactor() {
        return maxPartitionFetchBytesBoostFactor;
    }

    public Long getPollTimeoutMs() {
        return pollTimeoutMs;
    }
}
