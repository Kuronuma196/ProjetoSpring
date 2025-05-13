package org.example.entities;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
    private Long proId;

    @Column(name = "PRO_NOME")
    private String proNome;

    @Column(name = "PRO_PRECO_CUSTO", precision = 10, scale = 2)
    private BigDecimal proPrecoCusto;

    @Column(name = "PRO_PRECO_VENDA", precision = 10, scale = 2)
    private BigDecimal proPrecoVenda;

    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque; // Ex: 120

    @Column(length = 100, nullable = false)
    private String categoria; // Ex: "Bebidas"

    @Column(name = "codigo_barras", unique = true, nullable = false, length = 20)
    private String codigoBarras; // Ex: "7894900011517"

    @Column(length = 50, nullable = false)
    private String marca; // Ex: "Coca-Cola"

    @Column(name = "unidade_medida", length = 20)
    private String unidadeMedida; // Ex: "Litro"

    @Column(length = 5)
    private String ativo; // Ex: true — OBS: use `Boolean` se quiser armazenar como booleano

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    public Produto() {
    }

    public Produto(Long proId, String proNome, BigDecimal proPrecoCusto, BigDecimal proPrecoVenda, Integer quantidadeEstoque, String categoria, String codigoBarras, String marca, String unidadeMedida, String ativo, LocalDateTime dataCadastro, LocalDateTime dataAtualizacao) {
        this.proId = proId;
        this.proNome = proNome;
        this.proPrecoCusto = proPrecoCusto;
        this.proPrecoVenda = proPrecoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
        this.codigoBarras = codigoBarras;
        this.marca = marca;
        this.unidadeMedida = unidadeMedida;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProNome() {
        return proNome;
    }

    public void setProNome(String proNome) {
        this.proNome = proNome;
    }

    public BigDecimal getProPrecoCusto() {
        return proPrecoCusto;
    }

    public void setProPrecoCusto(BigDecimal proPrecoCusto) {
        this.proPrecoCusto = proPrecoCusto;
    }

    public BigDecimal getProPrecoVenda() {
        return proPrecoVenda;
    }

    public void setProPrecoVenda(BigDecimal proPrecoVenda) {
        this.proPrecoVenda = proPrecoVenda;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}