package com.junaid.farm2table.WebSocket;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junaid.farm2table.model.CustomWebSocketMessage;

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
        ObjectMapper om = new ObjectMapper();
        CustomWebSocketMessage obj = new CustomWebSocketMessage();
        obj.setMessage(incomingMessage);
        String customId = session.getId().substring(0,2);
        obj.setSessionId("USER " + customId + " : ");
        String preparedMessage = om.writeValueAsString(obj);
        circulateMessage(preparedMessage,session);
    }

    public static void circulateMessage(String msg,WebSocketSession sessionToOmmit){
        for(WebSocketSession session : usersList){
            TextMessage preparedMessage = new TextMessage(msg);
            if(!session.getId().equals(sessionToOmmit.getId())){

                try {
                    session.sendMessage(preparedMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    } 
}
