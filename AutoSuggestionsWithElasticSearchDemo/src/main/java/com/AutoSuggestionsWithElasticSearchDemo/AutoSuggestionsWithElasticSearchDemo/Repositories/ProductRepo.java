package com.AutoSuggestionsWithElasticSearchDemo.AutoSuggestionsWithElasticSearchDemo.Repositories;

import com.AutoSuggestionsWithElasticSearchDemo.AutoSuggestionsWithElasticSearchDemo.Models.MyProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends ElasticsearchRepository<MyProduct , Integer> {
}
