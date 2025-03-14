package com.junaid.farm2table.model;

public class CustomWebSocketMessage {
    String message;
    String sessionId;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
      
}
