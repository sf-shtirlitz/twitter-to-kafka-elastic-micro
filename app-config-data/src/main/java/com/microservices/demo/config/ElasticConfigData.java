package com.microservices.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "elastic-config")
public class ElasticConfigData {
    private String indexName;
    private String connectionUrl;
    private Integer connectTimeoutMs;
    private Integer socketTimeoutMs;

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public Integer getConnectTimeoutMs() {
        return connectTimeoutMs;
    }

    public Integer getSocketTimeoutMs() {
        return socketTimeoutMs;
    }

    public String getIndexName() {
        return indexName;
    }
}