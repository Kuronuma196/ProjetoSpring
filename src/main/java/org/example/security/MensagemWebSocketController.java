package org.example.security;


import org.example.entities.Mensagem;
import org.example.services.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MensagemWebSocketController {

    @Autowired
    private MensagemService mensagemService;

    // Recebe mensagens do cliente no destino /app/chat.sendMessage
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Mensagem enviarMensagem(Mensagem mensagem) {
        // Salva a mensagem no banco
        return mensagemService.enviarMensagem(mensagem);
    }
}

