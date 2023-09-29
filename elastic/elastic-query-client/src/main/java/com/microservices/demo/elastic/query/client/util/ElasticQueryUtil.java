package com.microservices.demo.elastic.query.client.util;

import com.microservices.demo.elastic.model.index.IndexModel;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collections;

/*@Component
public class ElasticQueryUtil<T extends IndexModel> {

    public Query getSearchQueryById(String id) {
        return new NativeSearchQueryBuilder()
                .withIds(Collections.singleton(id))
                .build();
    }

    public Query getSearchQueryByFieldText(String field, String text) {
        return new NativeSearchQueryBuilder()
                .withQuery(new BoolQueryBuilder()
                        .must(QueryBuilders.matchQuery(field, text)))
                .build();
    }

    public Query getSearchQueryForAll() {
        return new NativeSearchQueryBuilder()
                .withQuery(new BoolQueryBuilder()
                        .must(QueryBuilders.matchAllQuery()))
                .build();
    }
}*/
@Component
public class ElasticQueryUtil<T extends IndexModel> {

    public Query getSearchQueryById(String id) {
        return NativeQuery.builder()//NativeSearchQueryBuilder()
                .withIds(Collections.singleton(id))
                .build();
    }

    public Query getSearchQueryByFieldText(String field, String text) {

        return new NativeSearchQueryBuilder()
                .withQuery(new MatchQueryBuilder(field, text))
//                        .must(QueryBuilders.matchQuery(field, text)))
                .build();

//        NativeQuery query = NativeQuery.builder()
//                .withQuery(QueryBuilder.termQueryAsQuery("fieldName", ""))
//
//        NativeQueryBuilder builder = QueryBuilders.termQuery(field, text);
//
//        Query query = new NativeQuery(builder);
//
//        QueryBuilders.commonTermsQuery(field, text
//        builder.
//        return NativeQuery.builder()
//                .withQuery()
//                .build();
    }


    public Query getSearchQueryForAll() {
//        Queries.matchAllQuery();//boolQuery().must(Queries.matchAllQuery());

        return NativeQuery.builder().withQuery(Query.findAll()).build();

/*                .withQuery(
                        QueryBuilders.boolQuery()
                        new BoolQueryBuilder()
                        .must(QueryBuilders.matchAllQuery()))
                .build();*/
    }
}
