package org.example.entities;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
    private Long proId;


    @NotBlank(message = "Nome é obrigatório!")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres!")
    @Column(name = "PRO_NOME", nullable = false, length = 100)
    private String proNome;

    @NotBlank(message = "Preço de custo é obrigatório!")
    @Column(name = "PRO_PRECO_CUSTO", precision = 10, scale = 2, nullable = false)
    private BigDecimal proPrecoCusto;


    @NotBlank(message = "Preço de venda é obrigatório!")
    @Column(name = "PRO_PRECO_VENDA", precision = 10, scale = 2, nullable = false)
    private BigDecimal proPrecoVenda;


    @NotBlank(message = "Quantidade de estoque é obrigatório!")
    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque; // Ex: 120

    @NotBlank(message = "Categoria é obrigatório!")
    @Size(max = 100, message = "Categoria deve ter no máximo 100 caracteres!")
    @Column(name = "categoria", nullable = false,length = 100)
    private String categoria; // Ex: "Bebidas"

    @NotBlank(message = "Código_barras é obrigatório!")
    @Size(max = 20, message = "Código_barras deve ter no máximo 20 caracteres!")
    @Column(name = "codigo_barras", nullable = false, unique = true, length = 20)
    private String codigoBarras; // Ex: "7894900011517"

    @NotBlank(message = "Marca é obrigatório!")
    @Size(max = 50, message = "Marca deve ter no máximo 50 caracteres!")
    @Column(name = "marca", nullable = false, length = 50)
    private String marca; // Ex: "Coca-Cola"

    @NotBlank(message = "Unidade de medida é obrigatório!")
    @Size(max = 20, message = "Unidade de medida deve ter no máximo 20 caracteres!")
    @Column(name = "unidade_medida", length = 20)
    private String unidadeMedida; // Ex: "Litro"

    @NotBlank(message = "Estar Ativo é obrigatório!")
    @Size(max = 5, message = "Nome deve ter no máximo 5 caracteres!")
    @Column(name = "PRO_ATIVO", nullable = false, unique = true, length = 5)
    private String ativo; // Ex: "true" (ideal seria Boolean, veja observação abaixo)

    @NotBlank(message = "Data de Cadastro é obrigatório!")
    @Column(name = "data_cadastro", nullable = false, unique = true, updatable = false)
    private LocalDateTime dataCadastro;

    @NotBlank(message = "Data de atualização é obrigatório!")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres!")
    @Column(name = "data_atualizacao", nullable = false, unique = true, length = 100)
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