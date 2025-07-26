package org.example.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PesquisaResponse {

    @JsonProperty("resposta")
    private String resposta;

    @JsonProperty("abstract")
    private String abstractText;

    public PesquisaResponse() {}

    public PesquisaResponse(String resposta, String abstractText) {
        this.resposta = resposta;
        this.abstractText = abstractText;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }
}
