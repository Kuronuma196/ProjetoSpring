package org.example.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class FormaPagamento  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FPG_ID")
    private Long fpgId;

    @Column(name = "FPG_DESCRICAO")
    private static String fpgDescricao;

    @Column(name = "FPG_NOME")
    private static String fpgNome;
    @Column(name = "FPG_PRICE")
    private static String fpgPrice;

    @Column(length = 5)
    private String ativo; // Ex: "true" (ideal seria Boolean, veja observação abaixo)

    @Column(nullable = false)
    private Boolean permiteParcelamento; // Ex: true

    @Column(name = "numero_maximo_parcelas")
    private Integer numeroMaximoParcelas; // Ex: 12

    @Column(name = "taxa_adicional", precision = 5, scale = 2)
    private BigDecimal taxaAdicional; // Ex: new BigDecimal("1.99") = 1,99%
    public FormaPagamento() {
    }

    public FormaPagamento(Long fpgId, String fpgDescricao, String fpgNome, String fpgPrice,String ativo,Boolean permiteParcelamento,Integer numeroMaximoParcelas,BigDecimal taxaAdicional) {
        this.fpgId = fpgId;
        this.fpgDescricao = fpgDescricao;
        this.fpgNome = fpgNome;
        this.fpgPrice = fpgPrice;
        this.ativo= ativo;
        this.permiteParcelamento= permiteParcelamento;
        this.numeroMaximoParcelas= numeroMaximoParcelas;
        this.taxaAdicional= taxaAdicional;


    }

    public Long getFpgId() {
        return fpgId;
    }

    public void setFpgId(Long fpgId) {
        this.fpgId = fpgId;
    }

    public static String getFpgDescricao() {
        return fpgDescricao;
    }

    public void setFpgDescricao(String fpgDescricao) {
        this.fpgDescricao = fpgDescricao;
    }

    public static String getFpgNome() {
        return fpgNome;
    }

    public static void setFpgNome(String fpgNome) {
        FormaPagamento.fpgNome = fpgNome;
    }

    public static String getFpgPrice() {
        return fpgPrice;
    }

    public static void setFpgPrice(String fpgPrice) {
        FormaPagamento.fpgPrice = fpgPrice;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public Boolean getPermiteParcelamento() {
        return permiteParcelamento;
    }

    public void setPermiteParcelamento(Boolean permiteParcelamento) {
        this.permiteParcelamento = permiteParcelamento;
    }

    public Integer getNumeroMaximoParcelas() {
        return numeroMaximoParcelas;
    }

    public void setNumeroMaximoParcelas(Integer numeroMaximoParcelas) {
        this.numeroMaximoParcelas = numeroMaximoParcelas;
    }

    public BigDecimal getTaxaAdicional() {
        return taxaAdicional;
    }

    public void setTaxaAdicional(BigDecimal taxaAdicional) {
        this.taxaAdicional = taxaAdicional;
    }
}