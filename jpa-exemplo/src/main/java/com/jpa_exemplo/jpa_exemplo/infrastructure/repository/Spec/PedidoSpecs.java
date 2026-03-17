package com.jpa_exemplo.jpa_exemplo.infrastructure.repository.Spec;

import com.jpa_exemplo.jpa_exemplo.domain.model.Pedido;
import com.jpa_exemplo.jpa_exemplo.domain.repository.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;

public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
        return (root, query, builder) -> {
            root.fetch("restaurante").fetch("cozinha");
            root.fetch("cliente");

            var predicates = new ArrayList<Predicate>();

            if (filtro.getClienteId() != null) {
                predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
            }

            if (filtro.getRestauranteId() != null) {
                predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
            }

            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }

            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}