package org.example.dto;

import java.io.Serializable;

public class ClienteDTO implements Serializable {

    private Long cliId;
    private String cliNome;
    private String cliCpf;
    private String cliEmail;
    private String cliTelefone;

    private String endRua;
    private String endNumero;
    private String endCidade;
    private String endCep;
    private String endEstado;

    private String conCelular;
    private String conTelefoneComercial;
    private String conEmail;

    public ClienteDTO() {
    }

}
