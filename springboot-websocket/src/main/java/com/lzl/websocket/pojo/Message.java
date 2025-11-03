package com.lzl.websocket.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * 定义的消息发送对象
 */
@Data
@Builder
public class Message {
    //没有toname toname 是发送请求时候携带的

    //是否系统消息
    private boolean isSystem;
    //私聊情况 :a->服务器->b显示      弹幕：a->服务器->广播 ->前端消息
     private String sender;//来自哪一位用户发的 如果是私聊
    private String content;  //消息内容
    //A->服务器 指定的发送私聊人 这里id
    private String toReceiver;
}
