package com.jpa_exemplo.jpa_exemplo.domain.model.Mixin;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jpa_exemplo.jpa_exemplo.domain.model.Estado;


public class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
