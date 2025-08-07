package org.example.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Conexao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Dono da conexão (quem salvou o contato)
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Pessoa conectada
    @ManyToOne
    @JoinColumn(name = "contato_id")
    private Usuario contato;

    // Data em que foi adicionada à rede
    private LocalDateTime dataAdicao;

    // Rótulo ou categoria (ex: fornecedor, aluno, professor)
    private String tipoRelacionamento;

    // Se o usuário marcou como favorito
    private boolean favorito = false;

    public Conexao() {
        this.dataAdicao = LocalDateTime.now();
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getContato() {
        return contato;
    }

    public void setContato(Usuario contato) {
        this.contato = contato;
    }

    public LocalDateTime getDataAdicao() {
        return dataAdicao;
    }

    public void setDataAdicao(LocalDateTime dataAdicao) {
        this.dataAdicao = dataAdicao;
    }

    public String getTipoRelacionamento() {
        return tipoRelacionamento;
    }

    public void setTipoRelacionamento(String tipoRelacionamento) {
        this.tipoRelacionamento = tipoRelacionamento;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
