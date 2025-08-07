package org.example.entities;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLI_ID")
    private Long id;

    @OneToMany(mappedBy = "endCliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "conCliente", cascade = CascadeType.ALL)
    private List<Contato> contatos = new ArrayList<>();

    @NotBlank(message = "Nome é obrigatório!")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres!")
    @Column(name = "CLI_NOME", nullable = false, length = 100)
    private String cliNome;

    @NotBlank(message = "CPF é obrigatório!")
    @CPF(message = "CPF inválido")
    @Column(name = "CLI_CPF", nullable = false, unique = true, length = 11)
    private String cliCpf;

    // Relacionamento com Usuario
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Usuario usuario;

    public Cliente() {
    }

    public Cliente(Long cliId, String cliNome, String cliCpf) {
        this.id = cliId;
        this.cliNome = cliNome;
        this.cliCpf = cliCpf;
    }

    public Long getCliId() {
        return id;
    }

    public void setCliId(Long cliId) {
        this.id = cliId;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> conetatos) {
        this.contatos = conetatos;
    }

    public String getCliNome() {
        return cliNome;
    }

    public void setCliNome(String cliNome) {
        this.cliNome = cliNome;
    }

    public String getCliCpf() {
        return cliCpf;
    }

    public void setCliCpf(String cliCpf) {
        this.cliCpf = cliCpf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
