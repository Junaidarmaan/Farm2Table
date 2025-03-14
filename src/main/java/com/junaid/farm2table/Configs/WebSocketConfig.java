package com.junaid.farm2table.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import com.junaid.farm2table.WebSocket.CustomWebSocketHandler;


@Configuration
@EnableWebSocket          
public class  WebSocketConfig implements WebSocketConfigurer{

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        
        registry.addHandler(new CustomWebSocketHandler(), "/chat").setAllowedOrigins("*");
    }

}