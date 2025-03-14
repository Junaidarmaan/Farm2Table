package com.junaid.farm2table.WebSocket;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class CustomWebSocketHandler extends TextWebSocketHandler{
    
    static ArrayList<WebSocketSession> usersList = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        usersList.add(session);
        System.out.println("new connection : " + session.getId());
    }


    @Override
    protected void handleTextMessage(WebSocketSession session,TextMessage message) throws Exception{
        String incomingMessage = message.getPayload();
        circulateMessage(incomingMessage);
    }

    public static void circulateMessage(String msg){
        for(WebSocketSession session : usersList){
            TextMessage preparedMessage = new TextMessage(msg);
            try {
                session.sendMessage(preparedMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } 
}
