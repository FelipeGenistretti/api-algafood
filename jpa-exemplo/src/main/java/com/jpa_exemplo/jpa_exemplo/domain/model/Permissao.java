package com.jpa_exemplo.jpa_exemplo.domain.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Permissao {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nome;

    String descricao;

    @ManyToMany(mappedBy = "permissao")
    private List<Grupo> grupos = new ArrayList<>();


}
