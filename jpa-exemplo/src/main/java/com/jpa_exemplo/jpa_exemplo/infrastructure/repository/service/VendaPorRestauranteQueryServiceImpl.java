package com.jpa_exemplo.jpa_exemplo.infrastructure.repository.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import java.sql.Date;

import org.springframework.stereotype.Repository;

import com.jpa_exemplo.jpa_exemplo.core.validation.enums.StatusPedido;
import com.jpa_exemplo.jpa_exemplo.domain.model.Pedido;
import com.jpa_exemplo.jpa_exemplo.domain.model.dto.VendaRankingRestaurante;
import com.jpa_exemplo.jpa_exemplo.domain.repository.filter.VendaPorRestauranteFilter;
import com.jpa_exemplo.jpa_exemplo.domain.service.VendaPorRestauranteQueryService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class VendaPorRestauranteQueryServiceImpl implements VendaPorRestauranteQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaRankingRestaurante> consultarVendaPorRestaurante(VendaPorRestauranteFilter filter, String offset) {

        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaRankingRestaurante.class);
        var root = query.from(Pedido.class);
        var restaurante = root.join("restaurante");

        var totalFaturado = builder.sum(root.get("valorTotal")).as(BigDecimal.class);
        var totalPedidos = builder.count(root.get("id"));

        var functionConvertTzDataCriacao = builder.function(
            "convert_tz",
            Date.class,
            root.get("dataCriacao"),
            builder.literal("+00:00"),
            builder.literal(offset)
        );

        var functionDateDataCriacao =
            builder.function("date", Date.class, functionConvertTzDataCriacao);

        var predicates = new ArrayList<Predicate>();
        var havingPredicates = new ArrayList<Predicate>();

        var selection = builder.construct(
            VendaRankingRestaurante.class, 
            restaurante.get("nome"),
            totalPedidos,
            functionDateDataCriacao,
            totalFaturado
        );
        
        query.select(selection);

        if (filter.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(
                root.get("dataCriacao"),
                filter.getDataCriacaoInicio()
            ));
        }

        if (filter.getDataCriacaoFim() != null ) {
            predicates.add(builder.lessThanOrEqualTo(
                root.get("dataCriacao"),
                filter.getDataCriacaoFim()
            ));
        }

        if (filter.getRestauranteId() != null ) {
            predicates.add(builder.equal(
                restaurante.get("id"),
                filter.getRestauranteId()
            ));
        }

        predicates.add(
            root.get("status").in(
                StatusPedido.CONFIRMADO,
                StatusPedido.ENTREGUE
            )
        );

        if (filter.getQuantidadeMinima() != null ) {
            havingPredicates.add(
                builder.greaterThanOrEqualTo(
                    totalPedidos,
                    filter.getQuantidadeMinima()
                )
            );
        }

        if (filter.getFaturamentoMin() != null ) {
            havingPredicates.add(
                builder.greaterThanOrEqualTo(
                    totalFaturado,
                    filter.getFaturamentoMin()
                )
            );
        }

        if (!predicates.isEmpty()){
            query.where(predicates.toArray(new Predicate[0]));
        }

        if (!havingPredicates.isEmpty()) {
            query.having(havingPredicates.toArray(new Predicate[0]));
        }

        query.groupBy(
            restaurante.get("id"),
            restaurante.get("nome"),
            functionDateDataCriacao
        );

        query.orderBy(
            builder.desc(totalFaturado)
        );

        return manager.createQuery(query).getResultList();
    }
}