package org.example.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class FormaPagamento  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FPG_ID")
    private Long fpgId;

    @NotBlank(message = "Descrição é obrigatório!")
    @Size(max = 100, message = "Descrição deve ter no máximo 100 caracteres!")
    @Column(name = "FPG_DESCRICAO", nullable = false, length = 100)
    private static String fpgDescricao;

    @NotBlank(message = "Nome é obrigatório!")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres!")
    @Column(name = "FPG_NOME", nullable = false, length = 100)
    private static String fpgNome;

    @NotBlank(message = "Preço é obrigatório!")
    @Column(name = "FPG_PRICE", nullable = false)
    private static String fpgPrice;

    @NotBlank(message = "Estar Ativo é obrigatório!")
    @Size(max = 5, message = "Nome deve ter no máximo 5 caracteres!")
    @Column(name = "FPG_ATIVO", nullable = false, unique = true, length = 5)
    private String ativo; // Ex: "true" (ideal seria Boolean, veja observação abaixo)

    @NotBlank(message = "Permitir parcelamento é obrigatório!")
    @Size(max = 3, message = "Nome deve ter no máximo 3 caracteres!")
    @Column(name = "FPG_PermitirParcelamento", nullable = false, length = 3)
    private Boolean permiteParcelamento; // Ex: true


    @NotBlank(message = "Número de parcelas é obrigatório!")
    @Column(name = "numero_maximo_parcelas", nullable = false)
    private Integer numeroMaximoParcelas; // Ex: 12


    @NotBlank(message = "Taxa Adicional é obrigatório!")
    @Column(name = "taxa_adicional", precision = 5, scale = 2, nullable = false)
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