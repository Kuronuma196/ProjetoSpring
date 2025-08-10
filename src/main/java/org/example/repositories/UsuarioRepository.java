package org.example.repositories;

import org.example.entities.Usuario;
import org.example.security.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca um usuário pelo email
    Optional<Usuario> findByEmail(String email);

    // REMOVE THIS METHOD: Optional<Usuario> findByUsername(String username);
    
    // Add this method if you need username lookup by email
    default Optional<Usuario> findByUsername(String username) {
        return findByEmail(username);
    }

    // Lista todos os usuários exceto o com o ID fornecido (ex: usuário logado)
    List<Usuario> findByIdNot(Long id);

    // Lista usuários por tipo (ex: ALUNO, FUNCIONARIO, CLIENTE)
    List<Usuario> findByTipo(TipoUsuario tipo);

    // Filtro de busca por nome (do perfil profissional) ou email (case-insensitive)
    List<Usuario> findByPerfilProfissional_NomeContainingIgnoreCaseOrEmailContainingIgnoreCase(String nome, String email);
}