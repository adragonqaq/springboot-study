package com.lzl.sleuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SearchController {
    @Autowired
    RestTemplate restTemplate;

    private static final String cartUrl = "http://localhost:8083/cart/add/1";
    private static final String orderUrl = "http://localhost:8084/order/create";

    @GetMapping("/search")
    public String search() {
        ResponseEntity<String> cartRes = restTemplate.getForEntity(cartUrl, String.class);
        ResponseEntity<String> orderRes = restTemplate.getForEntity(orderUrl, String.class);
        return "cart:" + cartRes.getBody() + "- order:" + orderRes.getBody();

    }
}