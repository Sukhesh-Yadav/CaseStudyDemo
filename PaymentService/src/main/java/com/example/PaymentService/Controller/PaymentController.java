package com.example.PaymentService.Controller;

import com.example.PaymentService.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/paynow")
    public String paynow(@RequestParam("price") String price, @RequestParam("cartId") String cartId,@RequestHeader HttpHeaders header){
        String token = header.getFirst(HttpHeaders.AUTHORIZATION).substring(7);
        return  paymentService.paynow(price,cartId,token);
    }
}
