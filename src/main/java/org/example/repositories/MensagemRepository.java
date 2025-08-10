package org.example.repositories;

import org.example.entities.Mensagem;
import org.example.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    // Paginated conversation between two users
    @Query("SELECT m FROM Mensagem m WHERE " +
           "(m.remetente.id = :usuario1Id AND m.destinatario.id = :usuario2Id) OR " +
           "(m.remetente.id = :usuario2Id AND m.destinatario.id = :usuario1Id) " +
           "ORDER BY m.dataEnvio DESC")
    Page<Mensagem> findMessagesBetweenUsers(@Param("usuario1Id") Long usuario1Id, 
                                          @Param("usuario2Id") Long usuario2Id,
                                          Pageable pageable);

    // Find all received messages for a user, ordered by date
    List<Mensagem> findByDestinatario_IdOrderByDataHoraDesc(Long destinatarioId);
    
    // Find conversation between two users (using User objects instead of IDs)
    @Query("SELECT m FROM Mensagem m WHERE " +
           "(m.remetente = :remetente AND m.destinatario = :destinatario) OR " +
           "(m.remetente = :destinatario AND m.destinatario = :remetente) " +
           "ORDER BY m.dataEnvio ASC")
    List<Mensagem> findConversationBetweenUsers(
        @Param("remetente") Usuario remetente,
        @Param("destinatario") Usuario destinatario
    );

    Page<Mensagem> findByDestinatario_Id(Long destinatarioId, Pageable pageable);
}