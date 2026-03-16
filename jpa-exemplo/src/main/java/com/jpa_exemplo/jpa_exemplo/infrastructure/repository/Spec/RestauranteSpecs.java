package com.jpa_exemplo.jpa_exemplo.infrastructure.repository.Spec;

import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Locale;

public class RestauranteSpecs {

    public static Specification<Restaurante> comFreteGratis(){
        return (root, query, builder)-> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome) {
        return (root, query, builder) -> {
            if (nome == null || nome.isBlank()) {
                return builder.conjunction();
            }
            return builder.like(root.get("nome"), "%" + nome + "%");
        };
    }

    public static Specification<Restaurante> ativo() {
        return (root, query, builder) ->
                builder.isTrue(root.get("ativo"));
    }

    public static Specification<Restaurante> ordenarPorCriacao() {
        return (root, query, builder) -> {
            query.orderBy(builder.asc(root.get("dataCriacao")));
            return builder.conjunction();
        };
    }

    public static Specification<Restaurante> nomeIgualIgnoreCase(String nome)
    {
        return (root, query, cb) -> cb.equal(
                cb.lower(root.get("nome")),
                nome.toLowerCase()
        );
    }
}
