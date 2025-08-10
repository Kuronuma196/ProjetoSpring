package org.example.services;

import org.example.entities.Usuario;
import org.example.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepo, PasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario validateCredentials(String email, String senha) {
        return usuarioRepo.findByEmail(email)
            .filter(usuario -> passwordEncoder.matches(senha, usuario.getSenha()))
            .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
    }
}