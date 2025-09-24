package com.lzl.geohash.utils;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
/**
 * 经纬度距离计算工具（Haversine公式）
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistanceUtils {
    private static final double EARTH_RADIUS = 6371000; // 地球半径（米）
    /**
     * 计算两点间距离（米）
     * @param lat1 点1纬度
     * @param lng1 点1经度
     * @param lat2 点2纬度
     * @param lng2 点2经度
     * @return 距离（米）
     */
    public static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        // 角度转弧度
        double radLat1 = Math.toRadians(lat1);
        double radLng1 = Math.toRadians(lng1);
        double radLat2 = Math.toRadians(lat2);
        double radLng2 = Math.toRadians(lng2);
        // Haversine公式
        double deltaLat = radLat2 - radLat1;
        double deltaLng = radLng2 - radLng1;
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.sin(deltaLng / 2) * Math.sin(deltaLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
}
    
    
    