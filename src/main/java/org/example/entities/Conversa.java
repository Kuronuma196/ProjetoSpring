package org.example.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Conversa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario1_id")
    private Usuario usuario1;

    @ManyToOne
    @JoinColumn(name = "usuario2_id")
    private Usuario usuario2;

    private LocalDateTime ultimaMensagem;

    public Conversa() {}

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }

    public LocalDateTime getUltimaMensagem() {
        return ultimaMensagem;
    }

    public void setUltimaMensagem(LocalDateTime ultimaMensagem) {
        this.ultimaMensagem = ultimaMensagem;
    }
}
