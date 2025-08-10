package org.example.services;

import org.example.entities.Usuario;
import org.example.repositories.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Lazy;

import java.util.Collection;
import java.util.Collections;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDetailsService(UsuarioRepository usuarioRepo, 
                                @Lazy PasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

   
@Override
public UserDetails loadUserByUsername(String username) {
    Usuario usuario = usuarioRepo.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    
    // Don't fail if client is null
    return new org.springframework.security.core.userdetails.User(
        usuario.getEmail(),
        usuario.getSenha(),
        Collections.emptyList()
    );
}
    private Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getTipo().name()));
    }

    private boolean isPasswordEncoded(String password) {
        // Basic check for BCrypt encoding format
        return password != null && password.startsWith("$2a$");
    }
}