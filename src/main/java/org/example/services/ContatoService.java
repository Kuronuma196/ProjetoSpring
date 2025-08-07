package org.example.services;

import org.example.entities.Contato;
import org.example.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public List<Contato> listarContatosPorFornecedorId(Long fornecedorId) {
        return contatoRepository.findByFornecedorId(fornecedorId);
    }
}
