package com.example.Order.Service;


import com.example.Order.Model.Cart;
import com.example.Order.Model.Order;
import com.example.Order.Repository.CartRepo;
import com.example.Order.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    JwtService jwtService;

    public void setCart(HashMap<String, Integer> map) {
        Cart cart = new Cart();
        cart.setCartItems(map);
        cartRepo.save(cart);
    }

    public void saveOrder(String transactionId,String cartId) {
        Order order = new Order();
        order.setCartId(cartId);
        order.setTransactionId(transactionId);
        orderRepo.save(order);
//        String user = jwtService.extractRole(token);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("mailtrap@demomailtrap.com");
        simpleMailMessage.setTo("sukheshyadavupputuri@gmail.com");
        simpleMailMessage.setSubject("Inventory alert ");
        simpleMailMessage.setText("An Order "+ order.getOrderId() + " has been placed");
        mailSender.send(simpleMailMessage);
    }



//    public Integer getPrice(List<String> pidList) {
//        Integer totalPrice = 0;
//        for(String pid : pidList){
//            RestTemplate restTemplate = new RestTemplate();
//            String url = "http://localhost:8080/api/product/price"+pid;
//            totalPrice=totalPrice+restTemplate.getForObject(url,Integer.class);
//        }
//        return totalPrice;
//    }
}
