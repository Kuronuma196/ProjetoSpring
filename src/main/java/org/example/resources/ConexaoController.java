package org.example.resources;


import org.example.entities.Conexao;
import org.example.services.ConexaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conexoes")
@CrossOrigin(origins = "*")
public class ConexaoController {

    @Autowired
    private ConexaoService conexaoService;

    @PostMapping
    public ResponseEntity<Conexao> adicionarConexao(@RequestBody Conexao conexao) {
        return ResponseEntity.ok(conexaoService.adicionarConexao(conexao));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Conexao>> listarConexoesDoUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(conexaoService.listarConexoesPorUsuario(usuarioId));
    }

    @GetMapping("/favoritos/{usuarioId}")
    public ResponseEntity<List<Conexao>> listarFavoritos(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(conexaoService.listarFavoritos(usuarioId));
    }

    @DeleteMapping("/{conexaoId}")
    public ResponseEntity<Void> deletarConexao(@PathVariable Long conexaoId) {
        conexaoService.removerConexao(conexaoId);
        return ResponseEntity.noContent().build();
    }
}
