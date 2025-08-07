package org.example.resources;

import org.example.entities.PerfilProfissional;
import org.example.services.PerfilProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil-profissional")
@CrossOrigin(origins = "*")
public class PerfilProfissionalController {

    @Autowired
    private PerfilProfissionalService perfilProfissionalService;

    @PostMapping
    public ResponseEntity<PerfilProfissional> criar(@RequestBody PerfilProfissional perfil) {
        return ResponseEntity.ok(perfilProfissionalService.salvarPerfil(perfil));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilProfissional> buscarPorId(@PathVariable Long id) {
        return perfilProfissionalService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<PerfilProfissional> buscarPorUsuarioId(@PathVariable Long usuarioId) {
        return perfilProfissionalService.buscarPorUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PerfilProfissional>> listarTodos() {
        return ResponseEntity.ok(perfilProfissionalService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilProfissional> atualizar(@PathVariable Long id, @RequestBody PerfilProfissional perfilAtualizado) {
        return perfilProfissionalService.buscarPorId(id).map(perfil -> {
            perfilAtualizado.setId(id);
            return ResponseEntity.ok(perfilProfissionalService.salvarPerfil(perfilAtualizado));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        perfilProfissionalService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}

