package org.example.resources;

import org.example.entities.Contato;
import org.example.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.services.UserDetailsCustomizado;
import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping("/meus")
    public ResponseEntity<List<Contato>> listarContatosFornecedorAutenticado(Authentication authentication) {
        // Exemplo simples:
        // Supondo que no Authentication.getPrincipal() você tenha um objeto UserDetailsCustomizado que
        // tem método getFornecedorId() que retorna o ID do fornecedor autenticado.

        // Ajuste conforme sua implementação de UserDetails

        UserDetailsCustomizado usuario = (UserDetailsCustomizado) authentication.getPrincipal();
        Long fornecedorId = usuario.getFornecedorId();

        List<Contato> contatos = contatoService.listarContatosPorFornecedorId(fornecedorId);
        return ResponseEntity.ok(contatos);
    }
}
