package com.AutoSuggestionsWithElasticSearchDemo.AutoSuggestionsWithElasticSearchDemo.Controllers;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.AutoSuggestionsWithElasticSearchDemo.AutoSuggestionsWithElasticSearchDemo.Models.MyProduct;
import com.AutoSuggestionsWithElasticSearchDemo.AutoSuggestionsWithElasticSearchDemo.Services.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EsController {


    @Autowired
    private EsService esService ;


    @GetMapping("/get/{potname}")
    public List<String> GetSuggs(@PathVariable String potname) throws IOException {
        SearchResponse<MyProduct> myProductSearchResponse = esService.MatchName(potname);

        List<Hit<MyProduct>> hits = myProductSearchResponse.hits().hits();
        List<MyProduct> myProducts = new ArrayList<>();

        for(Hit<MyProduct> hit : hits){
            myProducts.add(hit.source());
        }
    List<String> GetProductsNames = new ArrayList<>();

        for(MyProduct myProduct : myProducts){
            GetProductsNames.add(myProduct.getName());
        }
        return GetProductsNames;
    }


    @PostMapping("/add")
    public void AddProduct(@RequestBody MyProduct myProduct){
        esService.SaveProduct(myProduct);
    }


}
