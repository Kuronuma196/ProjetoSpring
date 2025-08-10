package org.example.security;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(
        ServerHttpRequest request,
        ServerHttpResponse response,
        WebSocketHandler wsHandler,
        Map<String, Object> attributes) {

        // Exemplo: token passado como query param ws://host/ws-chat?token=JWT
        String uri = request.getURI().toString();
        if (uri.contains("token=")) {
            String token = uri.substring(uri.indexOf("token=") + 6);
            attributes.put("token", token);
        }

        return true; // pode validar token aqui também se quiser bloquear na conexão
    }

    @Override
    public void afterHandshake(
        ServerHttpRequest request,
        ServerHttpResponse response,
        WebSocketHandler wsHandler,
        Exception exception) {
    }
}
