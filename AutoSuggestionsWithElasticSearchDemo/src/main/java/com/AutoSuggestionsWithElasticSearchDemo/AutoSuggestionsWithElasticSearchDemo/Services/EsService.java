package com.AutoSuggestionsWithElasticSearchDemo.AutoSuggestionsWithElasticSearchDemo.Services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.AutoSuggestionsWithElasticSearchDemo.AutoSuggestionsWithElasticSearchDemo.Models.MyProduct;
import com.AutoSuggestionsWithElasticSearchDemo.AutoSuggestionsWithElasticSearchDemo.Repositories.ProductRepo;
import com.AutoSuggestionsWithElasticSearchDemo.AutoSuggestionsWithElasticSearchDemo.Utils.EsUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

@Service
public class EsService {

    private final ProductRepo productRepo;
    private final ElasticsearchClient elasticsearchClient;

    public EsService(ProductRepo productRepo, ElasticsearchClient elasticsearchClient) {
        this.productRepo = productRepo;
        this.elasticsearchClient = elasticsearchClient;
    }


    public void SaveProduct(MyProduct myProduct){
        productRepo.save(myProduct);
    }

    public Iterable<MyProduct> getAllProducts(){
        return productRepo.findAll();
    }

    public SearchResponse<MyProduct> MatchName(String potentialName) throws IOException {

        Supplier<Query> supplier = EsUtil.MatchQueriesSupplier(potentialName);

        return elasticsearchClient.search(

                i -> i.index("products_v2")
                        .query(supplier.get())
                , MyProduct.class
        );

    }


}
