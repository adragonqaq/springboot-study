package com.lzl.geohash.service;

import com.lzl.geohash.dto.OrderCreateDTO;
import com.lzl.geohash.utils.DistanceUtils;
import com.lzl.geohash.utils.GeoHashUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
/**
 * 实时派单服务（筛选附近骑手、排序、派单）
 */
@Service
@Slf4j
public class DispatchService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RiderLocationService riderLocationService;
    @Value("${dispatch.max-distance}")
    private double maxDistance; // 最大派单距离（米）
    @Value("${dispatch.max-order-count}")
    private int maxOrderCount; // 骑手最大接单量
    @Value("${dispatch.geohash-precision}")
    private int geoHashPrecision;
    // Redis Key常量
    private static final String RIDER_GEO_KEY = "rider:locations";
    private static final String RIDER_INFO_KEY_PREFIX = "rider:info:";
    private static final String ORDER_INFO_KEY_PREFIX = "order:info:";
    /**
     * 订单创建后触发派单
     * @return 指派的骑手ID（若无合适骑手返回null）
     */
    public String dispatchOrder(OrderCreateDTO dto) {
        String orderId = dto.getOrderId();
        double orderLat = dto.getRecvLat();
        double orderLng = dto.getRecvLng();
        try {
            // 1. 生成订单GeoHash，获取目标及相邻GeoHash（共9个）
            String orderGeoHash = GeoHashUtils.encode(orderLat, orderLng, geoHashPrecision);
            String[] adjacentGeoHashes = GeoHashUtils.getAdjacentGeoHashes(orderGeoHash);
            // 2. 收集所有候选骑手ID（去重）
            Set<String> candidateRiderIds = new HashSet<>();
            for (String geoHash : adjacentGeoHashes) {
                String[] riders = riderLocationService.getRidersByGeoHash(geoHash);
                candidateRiderIds.addAll(Arrays.asList(riders));
            }
            if (candidateRiderIds.isEmpty()) {
                log.warn("订单派单失败：无候选骑手，orderId={}", orderId);
                return null;
            }
            // 3. 筛选符合条件的骑手（在线、订单量≤maxOrderCount、距离≤maxDistance）
            List<Map<String, Object>> validRiders = new ArrayList<>();
            for (String riderId : candidateRiderIds) {
                String riderInfoKey = RIDER_INFO_KEY_PREFIX + riderId;
                // 3.1 校验骑手在线状态
                Boolean online = (Boolean) redisTemplate.opsForHash().get(riderInfoKey, "onlineStatus");
                if (online == null || !online) {
                    continue;
                }
                // 3.2 校验骑手当前订单量
                Integer currentOrderCount = (Integer) redisTemplate.opsForHash().get(riderInfoKey, "currentOrderCount");
                if (currentOrderCount == null) {
                    currentOrderCount = 0; // 初始订单量为0
                }
                if (currentOrderCount >= maxOrderCount) {
                    continue;
                }
                // 3.3 计算骑手与订单的距离
                Point riderPoint = redisTemplate.opsForGeo().position(RIDER_GEO_KEY, riderId).get(0);
                if (riderPoint == null) {
                    continue; // 骑手位置不存在，跳过
                }
                double riderLat = riderPoint.getY();
                double riderLng = riderPoint.getX();
                double distance = DistanceUtils.calculateDistance(orderLat, orderLng, riderLat, riderLng);
                if (distance > maxDistance) {
                    continue;
                }
                // 3.4 保存有效骑手信息（骑手ID、距离、订单量）
                Map<String, Object> riderMap = new HashMap<>();
                riderMap.put("riderId", riderId);
                riderMap.put("distance", distance);
                riderMap.put("currentOrderCount", currentOrderCount);
                validRiders.add(riderMap);
            }
            // 4. 按距离升序排序，取最近的骑手
            if (validRiders.isEmpty()) {
                log.warn("订单派单失败：无符合条件的骑手，orderId={}", orderId);
                return null;
            }
            validRiders.sort(Comparator.comparingDouble(r -> (Double) r.get("distance")));
            String assignedRiderId = (String) validRiders.get(0).get("riderId");
            int newOrderCount = (Integer) validRiders.get(0).get("currentOrderCount") + 1;
            // 5. 更新骑手订单量与订单状态
            redisTemplate.opsForHash().put(RIDER_INFO_KEY_PREFIX + assignedRiderId, "currentOrderCount", newOrderCount);
            String orderInfoKey = ORDER_INFO_KEY_PREFIX + orderId;
            redisTemplate.opsForHash().put(orderInfoKey, "dispatchStatus", "已派单");
            redisTemplate.opsForHash().put(orderInfoKey, "assignedRiderId", assignedRiderId);
            redisTemplate.opsForHash().put(orderInfoKey, "createTime", System.currentTimeMillis());
            log.info("订单派单成功：orderId={}, assignedRiderId={}, distance={}m",
                    orderId, assignedRiderId, validRiders.get(0).get("distance"));
            return assignedRiderId;
        } catch (Exception e) {
            log.error("订单派单异常：orderId={}, error={}", orderId, e.getMessage(), e);
            throw new RuntimeException("派单失败");
        }
    }
}
    
    
    