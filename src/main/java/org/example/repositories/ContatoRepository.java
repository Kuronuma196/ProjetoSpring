package org.example.repositories;

import org.example.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    // Busca todos os contatos de um cliente específico
    List<Contato> findByconCliente_Id(Long clienteId);

    // Busca todos os contatos de um fornecedor específico
    // Correct naming:
     @Query("SELECT c FROM Contato c WHERE c.fornecedor.id = :fornecedorId")
    List<Contato> findByFornecedorId(@Param("fornecedorId") Long fornecedorId);

    // Se você tiver um campo nome na entidade Contato (ex: conNome), ajuste conforme nome correto
    // Exemplo genérico, supondo que exista campo 'conNome' ou 'nome'
    List<Contato> findByConEmailContainingIgnoreCase(String email);
}
