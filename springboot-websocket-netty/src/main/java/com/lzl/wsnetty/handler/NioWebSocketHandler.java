package com.lzl.wsnetty.handler;


/**
 * @author eren.liao
 * @version v1.0
 * @description
 * NioWebSocketHandler 负责处理 WebSocket 事件，包括连接、断开、消息接收、握手处理等。
 *
 *
 * @date 2025/11/3 16:58
 **/

import com.lzl.wsnetty.config.NioWebSocketChannelPool;
import com.lzl.wsnetty.config.WebSocketProperties;
import com.lzl.wsnetty.utils.RequestUriUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.websocketx.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 通道初始化的时候注释掉 WebSocketServerProtocolHandler(webSocketProperties.getPath(), null, true, 65536))的逻辑
 * 因为在客户端请求的时候，会先发送一个 HTTP 请求，然后再发送 WebSocket 请求，导致 WebSocketServerProtocolHandler 无法正确处理 WebSocket 请求
 * 所以注释掉 WebSocketServerProtocolHandler 相关逻辑，由 NioWebSocketHandler 自己处理 WebSocket 请求
 * 如果不注释掉 WebSocketServerProtocolHandler 相关逻辑，则会导致客户端无法正常连接到 WebSocket 服务端
 */
@Slf4j
@ChannelHandler.Sharable
@Component
@AllArgsConstructor
public class NioWebSocketHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private final NioWebSocketChannelPool webSocketChannelPool;
    private final WebSocketProperties webSocketProperties;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("客户端连接成功：{}", ctx.channel().id().asLongText());
        webSocketChannelPool.addChannel(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("客户端断开连接：{}", ctx.channel().id().asLongText());
        // 移除连接并释放资源
        webSocketChannelPool.removeChannel(ctx.channel());
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            if (msg instanceof FullHttpRequest) { // HTTP 请求处理 携带参数的
                handleHttpRequest(ctx, (FullHttpRequest) msg);
            } else if (msg instanceof WebSocketFrame) { // WebSocket 帧处理
                super.channelRead(ctx, msg); // 转交给 `channelRead0` 处理
            }
        } catch (Exception e) {
            log.error("处理客户端请求时发生异常：", e);
            ctx.close();
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
        } else if (frame instanceof TextWebSocketFrame) {
            handleTextFrame(ctx, (TextWebSocketFrame) frame);
        } else if (frame instanceof CloseWebSocketFrame) {
            ctx.close();
        } else {
            log.warn("收到未知类型的 WebSocket 帧：{}", frame.getClass().getName());
        }
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        try {
            String uri = request.uri();
            log.info("客户端请求路径：{}", uri);

            // 解析查询参数
            String path = uri.split("\\?", 2)[0];
            Map<String, String> params = RequestUriUtils.getParams(uri);
            log.info("解析出的参数：{}", params);
            //解析请求头
            System.out.println("携带的token：" + request.headers().get("Authorization"));
//            遍历携带参数
            params.forEach((k, v) -> {
                log.info("参数：{}={}", k, v);
            });
            if (webSocketProperties.getPath().equals(RequestUriUtils.getBasePath(path))) {
                WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                        getWebSocketLocation(request), null, true, 5 * 1024 * 1024);
                WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(request);
                if (handshaker == null) {
                    WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
                } else {
                    handshaker.handshake(ctx.channel(), request);
                }
            } else {
                log.warn("路径不匹配：{}", path);
                ctx.close();
            }
        } finally {
            request.release(); // 确保 FullHttpRequest 被正确释放
        }
    }

    private void handleTextFrame(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
        String message = frame.text();
        log.info("客户端发送消息：{}", message);

        if (message.startsWith("BROADCAST:")) {
            broadcastMessageToAll(message.substring("BROADCAST:".length()));
        } else if (message.startsWith("TO:")) {
            String[] parts = message.split(":", 3);
            if (parts.length == 3) {
                sendMessageToOne(parts[1], parts[2]);
            } else {
                log.warn("私信格式错误：{}", message);
            }
        } else {
            ctx.channel().writeAndFlush(new TextWebSocketFrame("收到消息：" + message));
        }
    }

    private String getWebSocketLocation(FullHttpRequest request) {
        String location = request.headers().get(HttpHeaderNames.HOST) + webSocketProperties.getPath();
        return "ws://" + location;
    }

    private void broadcastMessageToAll(String message) {
        // 使用异步任务避免阻塞主线程
        webSocketChannelPool.getChannels().forEach(channel -> {
            if (channel.isActive()) {
                channel.writeAndFlush(new TextWebSocketFrame(message)).addListener(future -> {
                    if (!future.isSuccess()) {
                        log.error("广播消息失败，目标客户端：{}", channel.id(), future.cause());
                    }
                });
            }
        });
    }


    private void sendMessageToOne(String channelId, String message) {
        Channel channel = webSocketChannelPool.getChannelById(channelId);
        if (channel != null && channel.isActive()) {
            channel.writeAndFlush(new TextWebSocketFrame(message)).addListener(future -> {
                if (!future.isSuccess()) {
                    log.error("发送消息失败，目标客户端：{}", channelId, future.cause());
                }
            });
        } else {
            log.warn("目标客户端未在线：{}", channelId);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("处理过程中发生异常：", cause);
        ctx.close();
    }
}


    
    
    