package org.example.entities;

import javax.persistence.*;
import java.io.Serializable;

public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="NOME")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "FORNECEDOR", nullable = false)
    private Fornecedor fornecedor;

    public Produto() {
    }

    public Produto(Long id, String nome, Fornecedor fornecedor) {
        this.id = id;
        this.nome = nome;
        this.fornecedor = fornecedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}