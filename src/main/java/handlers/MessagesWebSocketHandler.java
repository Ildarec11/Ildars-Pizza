package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import services.UserService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MessagesWebSocketHandler extends TextWebSocketHandler {

    private static Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserService userService;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        HttpHeaders headers = session.getHandshakeHeaders();
        String messageAsString = (String) message.getPayload();
        MessageDto messageDto = objectMapper.readValue(messageAsString, MessageDto.class);

        switch (messageDto.getType()) {
            case "enterChat":
                sessions.put(messageDto.getUsername(), session);
                messageDto.setType("chat");
                messageDto.setBody("вошёл в чат...");
                for (WebSocketSession currentSession : sessions.values()) {
                    currentSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(messageDto)));
                }
                break;
            case "chat":
                for (WebSocketSession currentSession : sessions.values()) {
                    currentSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(messageDto)));
                }
                break;
        }
    }
}