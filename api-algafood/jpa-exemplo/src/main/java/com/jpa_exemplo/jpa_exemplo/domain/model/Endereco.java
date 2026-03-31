package com.jpa_exemplo.jpa_exemplo.domain.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Endereco {

    @Column(name = "endereco_cep")
    public String cep;

    @Column(name = "endereco_logradouro")
    public String logradouro;

    @Column(name = "endereco_numero")
    public String numero;

    @Column(name = "endereco_name")
    public String complemento;

    @Column(name = "endereco_bairro")
    public String bairro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="endereco_cidade_id")
    public Cidade cidade;

}
