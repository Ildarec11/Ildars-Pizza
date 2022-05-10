//package configs;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import handlers.AuthHandshakeHandler;
//import handlers.MessagesWebSocketHandler;
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfiguration implements WebSocketConfigurer {
//
//    @Autowired
//    private AuthHandshakeHandler authHandshakeHandler;
//
//    @Autowired
//    private MessagesWebSocketHandler messageWebSocketHandler;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(messageWebSocketHandler, "/chat")
//                .setHandshakeHandler(authHandshakeHandler);
//    }
//}