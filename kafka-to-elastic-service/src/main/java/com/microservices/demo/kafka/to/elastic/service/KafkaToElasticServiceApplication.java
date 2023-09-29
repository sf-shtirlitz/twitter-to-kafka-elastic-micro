package com.microservices.demo.kafka.to.elastic.service;

import com.microservices.demo.kafka.to.elastic.service.consumer.impl.TwitterKafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.microservices.demo")
public class KafkaToElasticServiceApplication {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaToElasticServiceApplication.class);
    public static void main(String[] args) {
        LOG.info("Initializing KafkaToElasticService...");

        SpringApplication.run(KafkaToElasticServiceApplication.class, args);
    }
}

