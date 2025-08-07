package org.example.services;

import org.example.entities.Mensagem;
import org.example.repositories.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository mensagemRepository;

    public Mensagem enviarMensagem(Mensagem mensagem) {
        mensagem.setDataHora(LocalDateTime.now());
        return mensagemRepository.save(mensagem);
    }

    public List<Mensagem> listarMensagensEntreUsuarios(Long idRemetente, Long idDestinatario) {
        return mensagemRepository.findByRemetenteIdAndDestinatarioIdOrderByDataHoraAsc(idRemetente, idDestinatario);
    }

    public List<Mensagem> listarMensagensRecebidas(Long idDestinatario) {
        return mensagemRepository.findByDestinatarioIdOrderByDataHoraDesc(idDestinatario);
    }

    public void marcarComoLida(Long mensagemId) {
        mensagemRepository.findById(mensagemId).ifPresent(mensagem -> {
            mensagem.setLida(true);
            mensagemRepository.save(mensagem);
        });
    }
}
