package com.jpa_exemplo.jpa_exemplo.infrastructure.repository.Spec;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cozinha;
import org.springframework.data.jpa.domain.Specification;

public class CozinhaSpecs {
    public static Specification<Cozinha> cozinhaPorNomeLike(String nome){
        return(root, query, builder)->builder.like(root.get("nome"), "%" + nome + "%");
    }
}
