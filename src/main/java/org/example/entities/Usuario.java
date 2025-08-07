package org.example.entities;

import org.example.security.TipoUsuario;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipo; // FUNCIONARIO, CLIENTE, ALUNO, FORNECEDOR

    // Relacionamento com Cliente
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cli_id")
    private Cliente cliente;

    public Usuario() {
    }

    public Usuario(Long id, String email, String senha, TipoUsuario tipo) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private PerfilProfissional perfilProfissional;

public PerfilProfissional getPerfilProfissional() {
    return perfilProfissional;
}

public void setPerfilProfissional(PerfilProfissional perfilProfissional) {
    this.perfilProfissional = perfilProfissional;
}

}
