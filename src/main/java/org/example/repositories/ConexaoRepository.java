package org.example.repositories;

import org.example.entities.Conexao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConexaoRepository extends JpaRepository<Conexao, Long> {

    // Lista conexões de um usuário
    List<Conexao> findByUsuario_Id(Long usuarioId);

    // Lista favoritos de um usuário
    List<Conexao> findByUsuario_IdAndFavoritoTrue(Long usuarioId);

    // Verifica se já existe conexão entre dois usuários
    boolean existsByUsuario_IdAndContato_Id(Long usuarioId, Long contatoId);
}
