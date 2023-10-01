package com.microservices.demo.elastic.query.client.util;

import com.microservices.demo.elastic.model.index.IndexModel;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ElasticQueryUtil<T extends IndexModel> {

    public Query getSearchQueryById(String id) {
        return NativeQuery.builder()
                .withIds(Collections.singleton(id))
                .build();
    }

    public Query getSearchQueryByFieldText(String field, String text) {

        return new NativeSearchQueryBuilder()
                .withQuery(new MatchQueryBuilder(field, text))
                .build();
    }


    public Query getSearchQueryForAll() {
        return NativeQuery.builder().withQuery(Query.findAll()).build();
    }
}
