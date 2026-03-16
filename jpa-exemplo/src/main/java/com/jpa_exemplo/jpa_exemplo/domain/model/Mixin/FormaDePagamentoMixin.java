package com.jpa_exemplo.jpa_exemplo.domain.model.Mixin;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class FormaDePagamentoMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes;
}
