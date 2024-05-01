package com.example.PaymentService.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PaymentService {


    public String paynow(String price, String cartId, String token){
        String[] paymentStatus = {"FAILED","SUCCESS"};
        String transactionId = "akjsnasbas";
        RestTemplate restTemplate = new RestTemplate();
//        String uri = "http://localhost:8082/auth/getusername";
//        String name = restTemplate.postForObject(uri,token,String.class);

        Random random =new Random();
        int i = random.nextInt(2);
        if(paymentStatus[1].equals("SUCCESS")){
            String uri1 = "http://localhost:8081/api/order/saveOrder/"+cartId;
            return restTemplate.postForObject(uri1, transactionId,String.class);
        }
        return "Payment failed";
    }
}
