package com.microservices.demo.analytics.service.business.impl;

import com.microservices.demo.analytics.service.api.AnalyticsController;
import com.microservices.demo.analytics.service.business.AnalyticsService;
import com.microservices.demo.analytics.service.dataaccess.repository.AnalyticsRepository;
import com.microservices.demo.analytics.service.model.AnalyticsResponseModel;
import com.microservices.demo.analytics.service.transformer.EntityToResponseModelTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TwitterAnalyticsService implements AnalyticsService {
    private static final Logger LOG = LoggerFactory.getLogger(TwitterAnalyticsService.class);
    private final AnalyticsRepository analyticsRepository;

    private final EntityToResponseModelTransformer entityToResponseModelTransformer;

    public TwitterAnalyticsService(AnalyticsRepository repository,
                                   EntityToResponseModelTransformer transformer) {
        this.analyticsRepository = repository;
        this.entityToResponseModelTransformer = transformer;
    }

    @Override
    public Optional<AnalyticsResponseModel> getWordAnalytics(String word) {

        LOG.info("Getting word analytics - by word: {}", word);

        Optional<AnalyticsResponseModel> analyticsResponseModel = entityToResponseModelTransformer
                .getResponseModel(analyticsRepository
                        .getAnalyticsEntitiesByWord(word, PageRequest.of(0, 1))
                        .stream().findFirst().orElse(null));

        LOG.info("AnalyticsResponseMode returned: wordCount: {}", analyticsResponseModel.toString());
        return analyticsResponseModel;
    }
}
