package org.example.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conteudo;
    private LocalDateTime dataHora;
    private boolean lida = false;

    @ManyToOne
    @JoinColumn(name = "remetente_id")
    private Usuario remetente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Usuario destinatario;

    @Column(nullable = false, length = 1000)
    private String texto;

    private LocalDateTime dataEnvio;

    public Mensagem() {}

    public Mensagem(Usuario remetente, Usuario destinatario, String texto, LocalDateTime dataHora) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.texto = texto;
        this.dataHora = dataHora;
        this.dataEnvio = LocalDateTime.now();
    }

    public Mensagem(Usuario remetente, Usuario destinatario, String texto, LocalDateTime dataEnvio, LocalDateTime dataHora) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.texto = texto;
        this.dataEnvio = dataEnvio;
        this.dataHora = dataHora;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    // Helper method to get remetente ID
    public Long getRemetenteId() {
        return remetente != null ? remetente.getId() : null;
    }

    // Helper method to get destinatario ID
    public Long getDestinatarioId() {
        return destinatario != null ? destinatario.getId() : null;
    }
}