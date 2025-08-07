package org.example.repositories;

import org.example.entities.PerfilProfissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilProfissionalRepository extends JpaRepository<PerfilProfissional, Long> {
    
    // Busca o perfil profissional associado a um usuário
    Optional<PerfilProfissional> findByUsuarioId(Long usuarioId);
}
