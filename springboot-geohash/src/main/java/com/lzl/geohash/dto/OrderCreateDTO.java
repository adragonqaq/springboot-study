package com.lzl.geohash.dto;


import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
/**
 * 订单创建DTO（触发派单）
 */
@Data
public class OrderCreateDTO {
    @NotNull(message = "订单ID不能为空")
    private String orderId;
    @NotNull(message = "用户ID不能为空")
    private String userId;
    @NotNull(message = "收货地址纬度不能为空")
    private Double recvLat; // 收货纬度
    @NotNull(message = "收货地址经度不能为空")
    private Double recvLng; // 收货经度
}

    
    
    