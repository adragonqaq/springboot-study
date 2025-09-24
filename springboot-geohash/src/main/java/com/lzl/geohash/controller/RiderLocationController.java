package com.lzl.geohash.controller;

import com.lzl.geohash.dto.RiderLocationDTO;
import com.lzl.geohash.service.RiderLocationService;
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
 * 骑手位置上报接口
 */
@RestController
@RequestMapping("/api/rider")
@RequiredArgsConstructor
public class RiderLocationController {

    @Resource
    private RiderLocationService riderLocationService;
    @PostMapping("/location")
    public ResponseEntity<String> reportLocation(@Valid @RequestBody RiderLocationDTO dto) {
        riderLocationService.updateRiderLocation(dto);
        return new ResponseEntity<>("位置上报成功", HttpStatus.OK);
    }
}
    
    
    