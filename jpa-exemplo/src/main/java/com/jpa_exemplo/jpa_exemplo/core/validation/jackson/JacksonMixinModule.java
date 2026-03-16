package com.jpa_exemplo.jpa_exemplo.core.validation.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import com.jpa_exemplo.jpa_exemplo.domain.model.FormaDePagamento;
import com.jpa_exemplo.jpa_exemplo.domain.model.Mixin.CidadeMixin;
import com.jpa_exemplo.jpa_exemplo.domain.model.Mixin.RestauranteMixin;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule(){
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(FormaDePagamento.class, FormaDePagamento.class);
    }
}
