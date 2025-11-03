package com.lzl.websocket.config;




import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Map;
import java.util.UUID;

/**
 * config-info
 * 是的，modifyHandshake方法在ServerEndpointConfig.Configurator类中被设计用来拦截和修改WebSocket握手阶段的行为。
 * 这个方法确实只在WebSocket握手过程中第一次接收到客户端升级请求时被调用。在这个握手过程中，
 * 服务器和客户端就协议的版本、协议的子协议、扩展以及任何其他必要的协议细节达成一致。
 * 在你的代码中，modifyHandshake方法被用于从HTTP请求中获取到HttpSession对象，
 * 并且有可能将其存储或用于进一步的处理，比如验证用户的身份或维持会话状态。
 * 这个方法为WebSocket连接提供了一种机制，可以让WebSocket会话访问HTTP会话中的属性，
 * 这对于确保在HTTP会话和WebSocket会话之间共享状态非常有用。
 * 例如，如果你希望在WebSocket会话中使用在HTTP会话中设置的用户认证信息，
 * 你可以在modifyHandshake方法中将HttpSession中的某些属性复制到WebSocket的ServerEndpointConfig中，
 * 这样一来，这些属性就可以在WebSocket的生命周期内被访问和使用了。这种技术常用于实现基于Web的实时应用程序，确保WebSocket连接可以安全且有效地利用已经建立的HTTP会话信息。
 */
@Configuration
public class GetHttpSessionConfig  extends ServerEndpointConfig.Configurator {
    /**
     * 注意:没有一个客户端发起握手,端点就有一个新的实列 那么引用的这个配置也是新的实列 所以内存地址不一样 这里sec的用胡属性也不同就不会产生冲突
     * 修改握手机制  就是第一次http发送过来的握手
     * @param sec   服务器websocket端点的配置
     * @param request
     * @param response
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
//        super.modifyHandshake(sec, request, response);
        HttpSession httpSession =(HttpSession) request.getHttpSession();
//        将从握手的请求中获取httpsession

        /**
         * 一般会在请求头中添加token 解析出来id作为键值对
         */
        Map<String, Object> properties = sec.getUserProperties();
        /**
         * 一个客户端和和服务器发起一次请求交互 就有一个唯一session
           *存储session 是为了能够从其中用户用户info 到时候作为wssession的key 但是session 不共享 为此redis改进 或者token也是可以的
         * 这里使用UUID作为标识
         */
//        properties.put(HttpSession.class.getName(),httpSession);
        String sessionKey = UUID.randomUUID().toString().replaceAll("-", "");
        properties.put("Connected",sessionKey);
    }
}
