package org.example.services;

import dto.MensagemDTO;
import org.example.entities.Mensagem;
import org.example.entities.Usuario;
import org.example.repositories.MensagemRepository;
import org.example.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public MensagemService(MensagemRepository mensagemRepository,
                         UsuarioRepository usuarioRepository) {
        this.mensagemRepository = mensagemRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Page<Mensagem> getConversation(Long userId1, Long userId2, Pageable pageable) {
        return mensagemRepository.findMessagesBetweenUsers(userId1, userId2, pageable);
    }

    public Page<Mensagem> listarMensagensRecebidas(Long destinatarioId, Pageable pageable) {
        return mensagemRepository.findByDestinatario_Id(destinatarioId, pageable);
    }

    public Mensagem enviarMensagem(MensagemDTO dto) {
        Usuario remetente = usuarioRepository.findById(dto.getRemetenteId())
                .orElseThrow(() -> new IllegalArgumentException("Remetente não encontrado com ID: " + dto.getRemetenteId()));
        
        Usuario destinatario = usuarioRepository.findById(dto.getDestinatarioId())
                .orElseThrow(() -> new IllegalArgumentException("Destinatário não encontrado com ID: " + dto.getDestinatarioId()));
        
        Mensagem mensagem = new Mensagem(
            remetente,
            destinatario,
            dto.getConteudo(),
            LocalDateTime.now()
        );
        
        return mensagemRepository.save(mensagem);
    }

    public void marcarComoLida(Long mensagemId) {
        mensagemRepository.findById(mensagemId).ifPresent(mensagem -> {
            mensagem.setLida(true);
            mensagemRepository.save(mensagem);
        });
    }

    public Optional<Mensagem> getMessage(Long messageId) {
        return mensagemRepository.findById(messageId);
    }

    public List<Mensagem> buscarMensagensEntreUsuarios(Long remetenteId, Long destinatarioId) {
        return mensagemRepository.findMessagesBetweenUsers(remetenteId, destinatarioId, Pageable.unpaged()).getContent();
    }
    
    public Mensagem enviarMensagem(Mensagem mensagem) {
        if (mensagem.getDataEnvio() == null) {
            mensagem.setDataEnvio(LocalDateTime.now());
        }
        if (mensagem.getDataHora() == null) {
            mensagem.setDataHora(LocalDateTime.now());
        }
        return mensagemRepository.save(mensagem);
    }
}