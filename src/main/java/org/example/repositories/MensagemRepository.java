package org.example.repositories;

import org.example.entities.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    // Busca mensagens enviadas de um usuário para outro em ordem crescente (conversa)
    List<Mensagem> findByRemetenteIdAndDestinatarioIdOrderByDataHoraAsc(Long remetenteId, Long destinatarioId);

    // Busca todas as mensagens recebidas por um usuário em ordem decrescente (mais recentes primeiro)
    List<Mensagem> findByDestinatarioIdOrderByDataHoraDesc(Long destinatarioId);
}
