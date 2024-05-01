package com.CaseStudy.ProductService.Service;

import com.CaseStudy.ProductService.Auth.JwtService;
import com.CaseStudy.ProductService.Model.Product;
import com.CaseStudy.ProductService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    JwtService jwtService;
    @Autowired
    ProductRepository productRepository;

    public Product addProduct(Product product,String token){
        if(jwtService.extractRole(token).equals("ADMIN")){
            return productRepository.save(product);
        }
        return null;
    }

    public List<Product> getAllProducts(){
        Iterable<Product> iterable = productRepository.findAll();
        return convertIterableToList(iterable);
    }

    public List<Product> convertIterableToList(Iterable<Product> iterable){
        List<Product> list = new ArrayList<Product>();
        iterable.forEach(list::add);
        return list;
    }

    public Product getProductByName(String productName){
        Iterable<Product> iterable = productRepository.findAll();
        List<Product> list = convertIterableToList(iterable);
        for(Product l : list){
            if(l.getProductName().equals(productName)){
                return l;
            }
        }
        return null;
    }

    public Integer getPrice(String productId) {
        Product product = productRepository.findById(productId).get();
        return product.getPrice();
    }

    public Product updateProduct(String productId,Product product,String token) {
        if(jwtService.extractRole(token).equals("ADMIN")){
            Product newproduct = productRepository.findById(productId).get();
            newproduct.setProductName(product.getProductName());
            newproduct.setCategoryName(product.getCategoryName());
            newproduct.setPrice(product.getPrice());
            productRepository.save(newproduct);
            return newproduct;
        }
        return null;
    }

    public void deleteProduct(String productId,String token) {
        if(jwtService.extractRole(token).equals("ADMIN")){
            productRepository.deleteById(productId);
        }

    }
}
