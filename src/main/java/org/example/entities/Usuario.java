package org.example.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Usuario implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(length = 55, name = "NOME", nullable = false)
    private String nome;

    @Column(name = "IDADE", nullable = false)
    private Integer idade;

    //Construtor vazio
    public Usuario() {
    }

    //Construtor com argumentos
    public Usuario(Long id, String nome, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    //Getters e Setters
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
