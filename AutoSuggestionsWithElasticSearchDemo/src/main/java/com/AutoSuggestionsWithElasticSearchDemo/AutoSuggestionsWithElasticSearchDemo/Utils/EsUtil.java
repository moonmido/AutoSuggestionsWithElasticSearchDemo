package com.AutoSuggestionsWithElasticSearchDemo.AutoSuggestionsWithElasticSearchDemo.Utils;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import java.util.function.Supplier;

public class EsUtil {



    public static Supplier<Query> MatchQueriesSupplier(String potentialName){
        return  ()->Query.of(q -> q.match(getMatchQueries(potentialName)));
    }

    public static MatchQuery getMatchQueries(String potentialName){
        return new MatchQuery.Builder().field("name").query(potentialName).build();
    }


}
