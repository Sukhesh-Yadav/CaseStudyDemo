package com.example.Order.Controller;


import com.example.Order.Model.Order;
import com.example.Order.Repository.OrderRepo;
import com.example.Order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @GetMapping("/")
    public String greet(){
        return "Hello";
    }
    @GetMapping("/price")
    public Integer getPrice(@RequestBody HashMap<String, Integer> map){
        Integer total=0;
        orderService.setCart(map);
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            String url = "http://localhost:8080/api/product/price/" + key;
            RestTemplate restTemplate = new RestTemplate();
            total = total+restTemplate.getForObject(url,Integer.class)*value;
        }
        return total;

    }

    @PostMapping("/saveOrder/{cartId}")
    public String saveOrder(@RequestBody String transactionId,@PathVariable String cartId){
//        String token = header.getFirst(HttpHeaders.AUTHORIZATION).substring(7);
        orderService.saveOrder(transactionId,cartId);
        return "Order is placed";
    }
}
