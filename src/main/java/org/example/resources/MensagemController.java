package org.example.resources;

import org.example.entities.Mensagem;
import org.example.services.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensagens")
@CrossOrigin(origins = "*")
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @PostMapping
    public ResponseEntity<Mensagem> enviarMensagem(@RequestBody Mensagem mensagem) {
        return ResponseEntity.ok(mensagemService.enviarMensagem(mensagem));
    }

    @GetMapping("/{remetenteId}/{destinatarioId}")
    public ResponseEntity<List<Mensagem>> listarMensagensEntreUsuarios(
            @PathVariable Long remetenteId,
            @PathVariable Long destinatarioId) {
        return ResponseEntity.ok(mensagemService.listarMensagensEntreUsuarios(remetenteId, destinatarioId));
    }

    @GetMapping("/recebidas/{usuarioId}")
    public ResponseEntity<List<Mensagem>> listarMensagensRecebidas(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(mensagemService.listarMensagensRecebidas(usuarioId));
    }
}
