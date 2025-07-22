package org.example.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PesquisaResponse {
    @JsonProperty("Abstract")
    private String abstractText;
    private String Answer;


        private String answer;

        // Construtor padrão
        public PesquisaResponse() {}

        // Construtor com argumentos
        public PesquisaResponse(String answer, String abstractText) {
            this.answer = answer;
            this.abstractText = abstractText;
        }

        // getters e setters
        // ...

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}


