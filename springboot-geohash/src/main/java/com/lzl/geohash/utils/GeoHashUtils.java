package com.lzl.geohash.utils;

import ch.hsr.geohash.GeoHash;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


/**
 * @author eren.liao
 * @version v1.0
 * @description
 * @date 2025/9/24 9:01
 **/
/**
 * GeoHash编码/解码工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeoHashUtils {
    /**
     * 经纬度转GeoHash字符串
     * @param latitude 纬度
     * @param longitude 经度
     * @param precision 精度（6-7位常用）
     * @return GeoHash字符串
     */
    public static String encode(double latitude, double longitude, int precision) {
        if (precision < 1 || precision > 12) {
            throw new IllegalArgumentException("GeoHash精度需在1-12之间");
        }
//        GeoHash geoHash = GeoHash.geoHashStringWithCharacterPrecision(latitude, longitude, precision);
        GeoHash geoHash = GeoHash.withCharacterPrecision(latitude, longitude, precision);
        return geoHash.toBase32();
    }
    /**
     * 获取目标GeoHash的相邻8个GeoHash（解决边界问题）
     * @param targetGeoHash 目标GeoHash
     * @return 相邻GeoHash列表（含目标自身，共9个）
     */
    public static String[] getAdjacentGeoHashes(String targetGeoHash) {
        GeoHash geoHash = GeoHash.fromGeohashString(targetGeoHash);
        return new String[]{
                targetGeoHash,
                geoHash.getNorthernNeighbour().toBase32(),  // 北
                geoHash.getSouthernNeighbour().toBase32(),  // 南
                geoHash.getEasternNeighbour().toBase32(),   // 东
                geoHash.getWesternNeighbour().toBase32(),   // 西
                // 使用级联调用来获取对角线邻居
                geoHash.getNorthernNeighbour().getEasternNeighbour().toBase32(), // 东北
                geoHash.getNorthernNeighbour().getWesternNeighbour().toBase32(), // 西北
                geoHash.getSouthernNeighbour().getEasternNeighbour().toBase32(), // 东南
                geoHash.getSouthernNeighbour().getWesternNeighbour().toBase32()  // 西南
        };
    }
}
    
    