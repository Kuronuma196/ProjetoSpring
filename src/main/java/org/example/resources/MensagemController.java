package org.example.resources;

import java.util.Optional;
import javax.validation.Valid;
import org.example.entities.Mensagem;
import org.example.services.MensagemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import dto.MensagemDTO;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "*")
@Validated
public class MensagemController {

    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @GetMapping("/conversation")
    public ResponseEntity<Page<Mensagem>> getConversation(
            @RequestParam Long userId1,
            @RequestParam Long userId2,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<Mensagem> conversation = mensagemService.getConversation(userId1, userId2, pageable);
        return ResponseEntity.ok(conversation);
    }

    @GetMapping("/received/{userId}")
    public ResponseEntity<Page<Mensagem>> getReceivedMessages(
            @PathVariable Long userId,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<Mensagem> messages = mensagemService.listarMensagensRecebidas(userId, pageable);
        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public ResponseEntity<Mensagem> sendMessage(
            @Valid @RequestBody MensagemDTO dto) {
        Mensagem sentMessage = mensagemService.enviarMensagem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(sentMessage);
    }

    @PutMapping("/{messageId}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long messageId) {
        mensagemService.marcarComoLida(messageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<Mensagem> getMessage(@PathVariable Long messageId) {
        Optional<Mensagem> message = mensagemService.getMessage(messageId);
        return message.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
}