package org.example.dto;

import java.io.Serializable;

public class FornecedorDTO implements Serializable {

    private Long forId;
    private String forNome;
    private String forNomeFantasia;
    private String forCnpj;
    private String forRazaoSocial;

    private String endRua;
    private String endNumero;
    private String endCidade;
    private String endCep;
    private String endEstado;

    private String conCelular;
    private String conTelefoneComercial;
    private String conEmail;

    public FornecedorDTO() {

    }
}