package org.example.resources;

import dto.MensagemDTO;
import org.example.entities.Mensagem;
import org.example.services.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final MensagemService mensagemService;

    @Autowired
    public ChatController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<List<Mensagem>> buscarMensagens(
        @PathVariable Long contactId, 
        @RequestParam Long userId
    ) {
        List<Mensagem> mensagens = mensagemService.buscarMensagensEntreUsuarios(userId, contactId);
        return ResponseEntity.ok(mensagens);
    }

    @PostMapping("/{contactId}")
    public ResponseEntity<Mensagem> enviarMensagem(
        @PathVariable Long contactId, 
        @RequestBody MensagemDTO dto
    ) {
        // Validate DTO before processing
        if (dto.getConteudo() == null || dto.getConteudo().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        
        dto.setDestinatarioId(contactId);
        Mensagem mensagemEnviada = mensagemService.enviarMensagem(dto);
        return ResponseEntity.ok(mensagemEnviada);
    }
}