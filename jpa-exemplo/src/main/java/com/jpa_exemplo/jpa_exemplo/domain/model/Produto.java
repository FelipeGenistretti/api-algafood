package com.jpa_exemplo.jpa_exemplo.domain.model;


import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    public void definirRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public void ativar(){
        if(this.ativo){
            throw new NegocioException("Este produto já esta ativo");
        }
        this.ativo = true;
    }

    public void desativar(){
        if(!this.ativo){
            throw new NegocioException("Este produto já esta inativo");
        }
        this.ativo = false;
    }



}
