package com.lzl.wsnetty.config;


import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author eren.liao
 * @version v1.0
 * @description
 *
 * 因为原生 WebSocket 缺少类似 Session 的连接管理，所以我们实现了一个自定义的连接池 NioWebSocketChannelPool 来管理客户端的连接通道。
 * 由于原生的websocket 并没有像starter 一样采用session 管理连接，而是通道，所以这里也建立一个保存连接的容器池

 * @date 2025/11/3 16:56
 **/
@Slf4j
@Component
@Data
public class NioWebSocketChannelPool {

    // 使用 DefaultChannelGroup 管理通道组，使用 ConcurrentMap 高效查找通道
    private final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private final ConcurrentMap<String, Channel> channelMap = new ConcurrentHashMap<>();

    /**
     * 新增一个客户端通道
     *如果觉得channl的id的asLongText()方法过于冗长可以推荐使用业务:id:id的形式作为key
     * @param channel 新的通道
     */
    public void addChannel(Channel channel) {
        channels.add(channel);
        channelMap.put(channel.id().asLongText(), channel);  // 使用 map 高效查找
    }

    /**
     * 移除一个客户端连接通道
     *
     * @param channel 要移除的通道
     */
    public void removeChannel(Channel channel) {
        channels.remove(channel);
        channelMap.remove(channel.id().asLongText());  // 从 map 中移除
    }

    /**
     * 获取所有活跃的客户端连接
     *
     * @return 活跃通道组
     */
    public ChannelGroup getChannels() {
        return channels;
    }

    /**
     * 通过通道 ID 获取通道
     *
     * @param channelId 通道 ID
     * @return 通道，若未找到则返回 null
     */
    public Channel getChannelById(String channelId) {
        return channelMap.get(channelId);  // 使用 map 的 O(1) 查找时间
    }

    /**
     * 向所有连接的客户端广播消息
     *
     * @param message 要广播的消息
     */
    public void broadcastMessage(String message) {
        channels.forEach(channel -> {
            if (channel.isActive()) {
                channel.writeAndFlush(message).addListener(future -> {
                    if (!future.isSuccess()) {
                        log.error("发送消息到客户端失败，目标客户端：{}", channel.id());
                    }
                });
            }
        });
    }
}


    
    
    