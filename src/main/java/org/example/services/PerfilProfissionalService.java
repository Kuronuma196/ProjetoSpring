package org.example.services;

import org.example.entities.PerfilProfissional;
import org.example.repositories.PerfilProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilProfissionalService {

    @Autowired
    private PerfilProfissionalRepository perfilProfissionalRepository;

    public PerfilProfissional salvarPerfil(PerfilProfissional perfil) {
        return perfilProfissionalRepository.save(perfil);
    }

    public Optional<PerfilProfissional> buscarPorId(Long id) {
        return perfilProfissionalRepository.findById(id);
    }

    public Optional<PerfilProfissional> buscarPorUsuarioId(Long usuarioId) {
        return perfilProfissionalRepository.findByUsuarioId(usuarioId);
    }

    public List<PerfilProfissional> listarTodos() {
        return perfilProfissionalRepository.findAll();
    }

    public void deletarPorId(Long id) {
        perfilProfissionalRepository.deleteById(id);
    }
}
