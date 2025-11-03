package com.lzl.websocket.ws;



import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.lzl.websocket.config.GetHttpSessionConfig;
import com.lzl.websocket.pojo.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@CrossOrigin(origins = "*")
@ServerEndpoint(value = "/chat/{userName}",configurator = GetHttpSessionConfig.class)//协议升级路由
public class ChatEndpoint {//只要和该路由建立连接 就new 一个新的实列 对应一个该endpoint对象
    //模拟储存当前用户的朋友全信息
    private  static final     Map<String, Session> Friendgroup=new ConcurrentHashMap<String,Session>();//线程安全的银蛇
   private HttpSession httpSession;
    /**
     * 定义的当前用户
     */
    private String userId;

    /**
     * 第一个参数必须是session
     * @param session
     * @param sec   不能是Server
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig sec, @PathParam("userName") String userName){

        this.userId=userName;//用户上下文填充
//2.把成功建立升级的会话让放入会话组
        String sessionKey=userName;
        Friendgroup.put(userName,session);
//之所以获取http session 是为了获取获取httpsession中的数据 (用户名 /账号/信息)
        System.out.println("websocket建立成功");
//        2.广播消息(如果是好咧别表上下) 模拟放房间提示
        String content="用户id"+sessionKey+"已经上线 愉快玩耍吧";
        Message message = Message.builder()
                .content(content)
                .isSystem(true).build();
        broadcast(message);
        System.out.println("WebSocket 连接建立成功: " + sessionKey);
//        3.

    }
    /**
     * 当断开连接
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        // 找到关闭会话对应的用户 ID 并从 Friendgroup 中移除
        String sessionKey = this.userId;

        if (sessionKey != null) {
            Friendgroup.remove(sessionKey);

            // 广播消息给所有好友
            String content = "用户ID " + sessionKey + " 已经下线";
            Message message = Message.builder()
                    .content(content)
                    .isSystem(true)
                    .build();
            broadcast(message);
        }
    }

    /**
     * 这是接收和处理来自用户的消息的地方。我们需要在这里处理消息逻辑，可能包括广播消息给所有连接的用户。
     * @param //前端可以携带来自forname 但是我在这个实列化内部做了一个上下文
     *
     * 如果接收的消息的是对象 需要解码器,@PathParam("roomId") String roomId, 如果参数写在了第一位 那么就需要使用该注解获取路由的参数信息
     */
    @OnMessage
    public void onMessage(Session session,String message) throws IOException {
        System.out.println("接收到消息"+message);
        JSONObject json = JSON.parseObject(message);
        // 从JSONObject中提取必要的字段
        String sender = json.getString("sender");
        String content = json.getString("content");
        String toReceiver = json.getString("toReceiver");

        // 创建Message对象
        Message message1 = Message.builder()
                .sender(sender)
//                .toReceiver(toReceiver) //发给谁这个信息无需填写
                .content(content)
                .build();
//调用发送方的会话 发送给他的客户端显示
        Session session1 = Friendgroup.get(toReceiver);
        session1.getBasicRemote().sendText(JSON.toJSONString(message1));
        // 你的其他逻辑

        }

    /**
     * 处理WebSocket中发生的任何异常。可以记录这些错误或尝试恢复。
     */
    @OnError
    public void onError(Throwable error) {
        System.out.println("onError......"+error.getMessage());

    }


    /**
     * 将系统的公告等需要推送的消息发布给所有已经建立连接的用户
     * 用于系统更细发布公告之类的 或者用户上线通知其他
     * @param message
     */
    private  void broadcast(Message message) throws RuntimeException {
        if (message.isSystem()){
        Friendgroup.entrySet()
                .forEach(item->{
//                    遍历每一个键值对
                    Session userSession = item.getValue();
                    try {
                        userSession
                                .getBasicRemote() //同步消息发送器
                                .sendText(JSON.toJSONString(message));
                    } catch (IOException e) {
//                        记录日志 保存文件便于查看
                        throw new RuntimeException(e);
                    }
                    ;
                });
        return;
    }
        else{
            try {
                Friendgroup.get(message.getSender())
                        .getBasicRemote()
                        .sendText(JSON.toJSONString(message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
