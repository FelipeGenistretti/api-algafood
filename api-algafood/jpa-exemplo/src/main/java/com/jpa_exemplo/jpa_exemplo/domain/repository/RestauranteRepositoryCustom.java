package com.jpa_exemplo.jpa_exemplo.domain.repository;

import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryCustom {
    List<Restaurante> findComFreteGratis(String nome);

}
