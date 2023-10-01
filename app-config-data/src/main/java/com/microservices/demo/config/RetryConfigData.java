package com.microservices.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "retry-config")
public class RetryConfigData {

    private Long initialIntervalMs;
    private Long maxIntervalMs;
    private Double multiplier;
    private Integer maxAttempts;
    private Long sleepTimeMs;

    public Long getInitialIntervalMs() {
        return initialIntervalMs;
    }

    public Long getMaxIntervalMs() {
        return maxIntervalMs;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public Long getSleepTimeMs() {
        return sleepTimeMs;
    }
}