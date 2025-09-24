package com.lzl.geohash.controller;


import com.lzl.geohash.dto.OrderCreateDTO;
import com.lzl.geohash.service.DispatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
/**
 * 订单创建与派单接口
 */
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    @Resource
    private DispatchService dispatchService;
    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderCreateDTO dto) {
        String assignedRiderId = dispatchService.dispatchOrder(dto);
        if (assignedRiderId == null) {
            return new ResponseEntity<>("派单失败：无合适骑手", HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>("订单创建成功，指派骑手：" + assignedRiderId, HttpStatus.OK);
    }
}
    