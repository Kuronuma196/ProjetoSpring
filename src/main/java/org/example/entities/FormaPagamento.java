package org.example.entities;

import javax.persistence.*;
import java.io.Serializable;

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
    public FormaPagamento() {
    }

    public FormaPagamento(Long fpgId, String fpgDescricao, String fpgNome, String fpgPrice) {
        this.fpgId = fpgId;
        this.fpgDescricao = fpgDescricao;
        this.fpgNome = fpgNome;
        this.fpgPrice = fpgPrice;
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
}