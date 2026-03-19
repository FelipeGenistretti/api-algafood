package com.jpa_exemplo.jpa_exemplo.infrastructure.repository.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Repository;

import com.jpa_exemplo.jpa_exemplo.core.validation.enums.StatusPedido;
import com.jpa_exemplo.jpa_exemplo.domain.model.Pedido;
import com.jpa_exemplo.jpa_exemplo.domain.model.dto.VendaDiaria;
import com.jpa_exemplo.jpa_exemplo.domain.repository.filter.VendaDiariaFilter;
import com.jpa_exemplo.jpa_exemplo.domain.service.VendaQueryService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {

        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);

        var predicates = new ArrayList<Predicate>();

        var functionConvertTzDataCriacao = builder.function("convert_tz", Date.class,root.get("dataCriacao") ,builder.literal("+00:00"), builder.literal(timeOffset));

        var functionDateDataCriacao =
                builder.function("date", Date.class, functionConvertTzDataCriacao);

        var selection = builder.construct(
                VendaDiaria.class,
                functionDateDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal"))
        );

        query.select(selection);

        if (filtro.getRestauranteId() != null) {
            predicates.add(
                builder.equal(root.get("restaurante").get("id"), filtro.getRestauranteId())
            );
        }

        if (filtro.getDataCriacaoInicio() != null) {
            predicates.add(
                builder.greaterThanOrEqualTo(
                    root.get("dataCriacao"),
                    filtro.getDataCriacaoInicio()
                )
            );
        }

        if (filtro.getDataCriacaoFim() != null) {
            predicates.add(
                builder.lessThanOrEqualTo(
                    root.get("dataCriacao"),
                    filtro.getDataCriacaoFim()
                )
            );
        }

        predicates.add(
            root.get("status").in(
                StatusPedido.CONFIRMADO,
                StatusPedido.ENTREGUE
            )
        );

        if (!predicates.isEmpty()) {
            query.where(predicates.toArray(new Predicate[0]));
        }

        query.groupBy(functionDateDataCriacao);

        return manager.createQuery(query).getResultList();
    }
}