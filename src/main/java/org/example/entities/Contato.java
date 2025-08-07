package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CON_ID")
    private Long conId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id") // chave estrangeira no banco
    private Cliente conCliente;

    @JsonIgnore
     @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CON_FOR_ID")
    private Fornecedor fornecedor;


    @Column(name = "CON_CELULAR", length = 14)
    private String conCelular;

    @Column(name = "CON_TELEFONE_COMERCIAL", length = 14)
    private String conTelefoneComercial;

    @Column(length = 55, name = "CON_EMAIL")
    private String conEmail;


    public Contato(Long conId, Fornecedor fornecedor, String conCelular, String conTelefoneComercial, String conEmail) {
        this.conId = conId;
        this.fornecedor = fornecedor;
        this.conCelular = conCelular;
        this.conTelefoneComercial = conTelefoneComercial;
        this.conEmail = conEmail;
    }

    public Contato(Long conId, Cliente conCliente, String conCelular, String conTelefoneComercial, String conEmail) {
        this.conId = conId;
        this.conCliente = conCliente;
        this.conCelular = conCelular;
        this.conTelefoneComercial = conTelefoneComercial;
        this.conEmail = conEmail;
    }

    public Long getConId() {
        return conId;
    }

    public void setConId(Long conId) {
        this.conId = conId;
    }

    public Cliente getConCliente() {
        return conCliente;
    }

    public void setConCliente(Cliente conCliente) {
        this.conCliente = conCliente;
    }

    public Fornecedor getConFornecedor() {
        return fornecedor;
    }
    @Transient  // Mark as transient since it's not a real database column
public Long getFornecedorId() {
    return fornecedor != null ? fornecedor.getForId() : null;
}

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getConCelular() {
        return conCelular;
    }

    public void setConCelular(String conCelular) {
        this.conCelular = conCelular;
    }

    public String getConTelefoneComercial() {
        return conTelefoneComercial;
    }

    public void setConTelefoneComercial(String conTelefoneComercial) {
        this.conTelefoneComercial = conTelefoneComercial;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }
}
