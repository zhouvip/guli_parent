package com.nanjing.zhou.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@Slf4j
@Component
@ServerEndpoint("/info")    // 指定websocket连接的url
public class WebSocketServer {
    @OnOpen
    public void onOpen(Session session) {
        log.info("客户端：{}连接成功",session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        log.info("客户端：{}连接断开",session.getId());
    }

    //这里只实现个简单功能，收到客户端消息后，将收到的内容大写后，再返回给客户端
    @OnMessage
    public String onMsg(String message,Session session) {
        log.info("从客户端：{} 收到<--:{}", session.getId(),message);

        String send=message.toUpperCase();
        String result="客户：%s您好，来自server 的消息:%s";
        result = String.format(result, session.getId(), send);
        return "来自server 的消息：" + result;
    }
}
