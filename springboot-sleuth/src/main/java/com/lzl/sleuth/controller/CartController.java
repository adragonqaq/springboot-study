package com.lzl.sleuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("cart")
@RestController
public class CartController {
    @Autowired
    RestTemplate restTemplate;

    private static final String orderUrl = "http://localhost:8084/order/create";

    @GetMapping("/add/{cartId}")
    public String addToCart(@PathVariable("cartId") String cartId) {

        ResponseEntity<String> res = restTemplate.getForEntity(orderUrl, String.class);
        return res.getBody();
    }
}