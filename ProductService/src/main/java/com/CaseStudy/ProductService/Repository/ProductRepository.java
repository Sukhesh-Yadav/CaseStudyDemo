package com.CaseStudy.ProductService.Repository;

import com.CaseStudy.ProductService.Model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import org.springframework.stereotype.Repository;

@Repository
@EnableElasticsearchRepositories
public interface ProductRepository extends ElasticsearchRepository<Product,String > {
}
