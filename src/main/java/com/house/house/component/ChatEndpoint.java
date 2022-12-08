package com.house.house.component;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.house.entity.Users;
import com.house.house.entity.websocket.Message;
import com.house.house.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author websocket服务
 */
@ServerEndpoint(value = "/server/{username}",configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatEndpoint {
    private static final Logger log = LoggerFactory.getLogger(ChatEndpoint.class);
    /**
     * 用来存储每个客户端对象对应的ChatEndpoint对象
     */
    public static Map<String, ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();

    //声明session对象，通过该对象可以发送消息给指定用户
    private Session session;

    //声明一个HttpSession对象，我们之前在HttpSession对象中存储了用户名
    private HttpSession httpSession;

    /**
     * 广播推送系统消息
     * @param message
     */
    private void broadcastMsgToAllOnlineUsers(String message) {
        //所有登录用户名称
        Set<String> names = onlineUsers.keySet();
        for (String name : names) {
            ChatEndpoint chatEndpoint = onlineUsers.get(name);
            //获取推送对象
            RemoteEndpoint.Basic basicRemote = chatEndpoint.session.getBasicRemote();
            try {
                basicRemote.sendText(message);
            } catch (IOException e) {
                log.error("广播发送系统消息失败！{}", e);
                e.printStackTrace();
            }
        }
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,EndpointConfig config) {
        //将局部的session对象赋值给成员session
        this.session = session;
        //获取httpSession对象
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession=httpSession;

        //从httpSession对象中获取用户名
       String s= (String) httpSession.getAttribute("loginUserName");
//         String username=user.getuName();
        //当前在线用户的用户名推送给所有客户端
        onlineUsers.put(s,this);
        System.out.println(onlineUsers.keySet().size());
        //获取消息
        String message = MessageUtils.getMessage(true, null, getAllOnlineUsername());
        broadcastMsgToAllOnlineUsers(message);
        log.info("获取消息onOpen");
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        onlineUsers.remove(username);
        log.info("有一连接关闭，移除username={}的用户session, 当前在线人数为：{}", username, onlineUsers.size());
    }
    /**
     * 收到客户端消息后调用的方法
     * 后台收到客户端发送过来的消息
     * onMessage 是一个消息的中转站
     * 接受 浏览器端 socket.send 发送过来的 json数据
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username) {
        ObjectMapper objectMapper =new ObjectMapper();
        try {
            Message msg=objectMapper.readValue(message,Message.class);
            //获取接收信息的用户
            String toName = msg.getToName();
            //获取发送的消息
            String msgData = msg.getMessage();
            //获取当前登录的用户
//            String username = (String) httpSession.getAttribute("loginUserName");
            //封装发送的消息
            String sendMsg = MessageUtils.getMessage(false, username, msgData);
            log.info(sendMsg);
            //发送消息
            onlineUsers.get(toName).session.getBasicRemote().sendText(sendMsg);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            log.error("接收客户端的消息，转换出错了！");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
    private Object getAllOnlineUsername() {
        return ChatEndpoint.onlineUsers.keySet();
    }
    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }
    /**
     * 服务端发送消息给所有客户端
     */

}
