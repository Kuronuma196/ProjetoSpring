package org.example.security;

import dto.MensagemDTO;
import org.example.entities.Mensagem;
import org.example.services.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MensagemWebSocketController {

    private final MensagemService mensagemService;

    // Use constructor injection instead of @Autowired
    public MensagemWebSocketController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    // Changed to unique mapping
    @MessageMapping("/chat.sendSecureMessage")
    @SendTo("/topic/secure")
    public Mensagem enviarMensagem(MensagemDTO dto) {
        return mensagemService.enviarMensagem(dto);
    }
}