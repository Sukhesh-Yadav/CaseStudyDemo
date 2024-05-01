package com.CaseStudy.ProductService.Controller;

import com.CaseStudy.ProductService.Model.Product;
import com.CaseStudy.ProductService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product,@RequestHeader HttpHeaders header){
        String token = header.getFirst(HttpHeaders.AUTHORIZATION).substring(7);
        return productService.addProduct(product,token);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> list = productService.getAllProducts();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{productName}")
    public Product getProductByName(@PathVariable String productName){
       return productService.getProductByName(productName);
    }

    @PutMapping("/updateProduct/{productId}")
    public Product updateProduct(@PathVariable String productId,@RequestBody Product product,@RequestHeader HttpHeaders header){
        String token = header.getFirst(HttpHeaders.AUTHORIZATION).substring(7);
        return productService.updateProduct(productId,product,token);
    }

    @PostMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable String productId,@RequestHeader HttpHeaders header){
        String token = header.getFirst(HttpHeaders.AUTHORIZATION).substring(7);
        productService.deleteProduct(productId,token);
        return "Product deleted";
    }

    @GetMapping("/price/{pid}")
    public Integer getPrice(@PathVariable String pid){
        return productService.getPrice(pid);
    }
}
