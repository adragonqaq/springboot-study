package com.lzl.geohash.service;
import com.lzl.geohash.dto.RiderLocationDTO;
import com.lzl.geohash.utils.GeoHashUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
/**
 * 骑手位置处理服务（接收位置、更新Redis）
 */
@Service
@Slf4j
public class RiderLocationService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Value("${dispatch.geohash-precision}")
    private int geoHashPrecision;
    @Value("${rider.location.timeout}")
    private long locationTimeout;
    // Redis Key常量
    private static final String RIDER_GEO_KEY = "rider:locations"; // 骑手地理坐标
    private static final String RIDER_INFO_KEY_PREFIX = "rider:info:"; // 骑手信息
    private static final String RIDER_GEOHASH_KEY_PREFIX = "rider:geohash:"; // GeoHash-骑手映射
    /**
     * 处理骑手位置上报，更新Redis
     */
    public void updateRiderLocation(RiderLocationDTO dto) {
        String riderId = dto.getRiderId();
        double lat = dto.getLat();
        double lng = dto.getLng();
        boolean online = dto.getOnlineStatus();
        try {
            // 1. 生成GeoHash
            String geoHash = GeoHashUtils.encode(lat, lng, geoHashPrecision);
            // 2. 更新骑手地理坐标（Redis GEO）
            redisTemplate.opsForGeo().add(RIDER_GEO_KEY, new Point(lng, lat), riderId);
            // 3. 更新骑手基础信息（在线状态、GeoHash、最后更新时间）
            String riderInfoKey = RIDER_INFO_KEY_PREFIX + riderId;
            redisTemplate.opsForHash().put(riderInfoKey, "onlineStatus", online);
            redisTemplate.opsForHash().put(riderInfoKey, "geoHash", geoHash);
            redisTemplate.opsForHash().put(riderInfoKey, "lastUpdateTime", System.currentTimeMillis());
            redisTemplate.expire(riderInfoKey, locationTimeout, TimeUnit.MILLISECONDS); // 位置超时自动过期
            // 4. 更新GeoHash-骑手映射（Hash结构，便于前缀查询）
            String geoHashKey = RIDER_GEOHASH_KEY_PREFIX + geoHash;
            redisTemplate.opsForHash().put(geoHashKey, riderId, online);
            redisTemplate.expire(geoHashKey, locationTimeout, TimeUnit.MILLISECONDS);
            log.info("骑手位置更新成功：riderId={}, geoHash={}, lat={}, lng={}", riderId, geoHash, lat, lng);
        } catch (Exception e) {
            log.error("骑手位置更新失败：riderId={}, error={}", riderId, e.getMessage(), e);
            throw new RuntimeException("位置上报失败");
        }
    }
    /**
     * 根据GeoHash获取骑手ID列表
     */
    public String[] getRidersByGeoHash(String geoHash) {
        String geoHashKey = RIDER_GEOHASH_KEY_PREFIX + geoHash;
        return redisTemplate.opsForHash().keys(geoHashKey).toArray(new String[0]);
    }
}
    
    
    