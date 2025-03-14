package com.rale4j.sb.websocket;

import com.rale4j.sb.annotation.Rale4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Example WebSocket handler with rate-limiting annotations.
 */
@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
    /**
     * Handles incoming WebSocket text messages with rate limiting.
     *
     * @param session the WebSocket session
     * @param message the incoming text message
     * @throws Exception if an error occurs while handling the message
     */
    @Rale4j(strategy = "guava", limit = 10, duration = 60)
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle WebSocket message
        session.sendMessage(new TextMessage("Echo: " + message.getPayload()));
    }
}