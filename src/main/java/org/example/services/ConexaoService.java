package org.example.services;

import org.example.entities.Conexao;
import org.example.repositories.ConexaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConexaoService {

    @Autowired
    private ConexaoRepository conexaoRepository;

    public Conexao adicionarConexao(Conexao conexao) {
        conexao.setDataAdicao(LocalDateTime.now());
        return conexaoRepository.save(conexao);
    }

    public void removerConexao(Long id) {
        conexaoRepository.deleteById(id);
    }

    public List<Conexao> listarConexoesPorUsuario(Long usuarioId) {
        return conexaoRepository.findByUsuario_Id(usuarioId);
    }

    public List<Conexao> listarFavoritos(Long usuarioId) {
        return conexaoRepository.findByUsuario_IdAndFavoritoTrue(usuarioId);
    }

    public boolean existeConexaoEntre(Long usuarioId, Long contatoId) {
        return conexaoRepository.existsByUsuario_IdAndContato_Id(usuarioId, contatoId);
    }
}
