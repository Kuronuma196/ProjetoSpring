package org.example.services;

import dto.UsuarioBaseDTO;
import dto.UsuarioComPerfilDTO;
import dto.UsuarioDTO;
import org.example.entities.PerfilProfissional;
import org.example.entities.Usuario;
import org.example.repositories.UsuarioRepository;
import org.example.security.TipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder encoder;

    public Usuario criarOuAtualizarUsuario(UsuarioBaseDTO dto) {
        Usuario usuario = new Usuario();
        
        // Use setEmail instead of setUsername
        usuario.setEmail(dto.getEmail());
        // Use setSenha instead of setPassword
        usuario.setSenha(encoder.encode(dto.getSenha()));

        try {
            TipoUsuario tipo = TipoUsuario.valueOf(dto.getTipo().toUpperCase());
            usuario.setTipo(tipo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo inválido: " + dto.getTipo());
        }

        if (dto.getNome() != null || dto.getCargo() != null) {
            PerfilProfissional perfil = new PerfilProfissional();
            perfil.setUsuario(usuario);
            perfil.setNome(dto.getNome());
            perfil.setCargo(dto.getCargo());
            perfil.setAreaAtuacao(dto.getAreaAtuacao());
            perfil.setCompetencias(dto.getCompetencias());
            perfil.setBio(dto.getBio());
            usuario.setPerfilProfissional(perfil);
        }

        return usuarioRepo.save(usuario);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepo.findById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepo.findByEmail(email);
    }

    public List<Usuario> listarTodosExceto(Long id) {
        return usuarioRepo.findByIdNot(id);
    }

    public List<Usuario> buscarPorNomeOuEmail(String termo) {
        return usuarioRepo.findByPerfilProfissional_NomeContainingIgnoreCaseOrEmailContainingIgnoreCase(termo, termo);
    }

    public List<Usuario> buscarPorTipo(TipoUsuario tipo) {
        return usuarioRepo.findByTipo(tipo);
    }

    public boolean deletarUsuario(Long id) {
        if (usuarioRepo.existsById(id)) {
            usuarioRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public UsuarioDTO toUsuarioDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setEmail(usuario.getEmail());
        dto.setTipo(usuario.getTipo().name());

        if (usuario.getPerfilProfissional() != null) {
            PerfilProfissional perfil = usuario.getPerfilProfissional();
            dto.setNome(perfil.getNome());
            dto.setCargo(perfil.getCargo());
            dto.setAreaAtuacao(perfil.getAreaAtuacao());
            dto.setCompetencias(perfil.getCompetencias());
            dto.setBio(perfil.getBio());
        }

        return dto;
    }

    public UsuarioComPerfilDTO toUsuarioComPerfilDTO(Usuario usuario) {
        UsuarioComPerfilDTO dto = new UsuarioComPerfilDTO();
        dto.setId(usuario.getId());
        dto.setEmail(usuario.getEmail());
        dto.setTipo(usuario.getTipo().name());

        if (usuario.getPerfilProfissional() != null) {
            PerfilProfissional perfil = usuario.getPerfilProfissional();
            dto.setNome(perfil.getNome());
            dto.setCargo(perfil.getCargo());
            dto.setAreaAtuacao(perfil.getAreaAtuacao());
            dto.setCompetencias(perfil.getCompetencias());
            dto.setBio(perfil.getBio());
        }

        return dto;
    }
}