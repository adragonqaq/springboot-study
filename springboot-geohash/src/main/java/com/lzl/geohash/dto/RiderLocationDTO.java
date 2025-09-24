package com.lzl.geohash.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
/**
 * 骑手位置上报DTO
 */
@Data
public class RiderLocationDTO {
    @NotNull(message = "骑手ID不能为空")
    private String riderId;
    @NotNull(message = "纬度不能为空")
//    @Pattern(regexp = "^-?([0-9]\\d*\\.\\d*|0\\.\\d*[0-9]\\d*|[0-9]+)$", message = "纬度格式错误")

    @DecimalMin(value = "-90.0", message = "纬度必须在-90到90之间")
    @DecimalMax(value = "90.0", message = "纬度必须在-90到90之间")
    private Double lat; // 纬度

    @NotNull(message = "经度不能为空")
//    @Pattern(regexp = "^-?([0-9]\\d*\\.\\d*|0\\.\\d*[0-9]\\d*|[0-9]+)$", message = "经度格式错误")
    @DecimalMin(value = "-180.0", message = "经度必须在-180到180之间")
    @DecimalMax(value = "180.0", message = "经度必须在-180到180之间")
    private Double lng; // 经度
    private Boolean onlineStatus = true; // 在线状态，默认在线
}
    
    
    