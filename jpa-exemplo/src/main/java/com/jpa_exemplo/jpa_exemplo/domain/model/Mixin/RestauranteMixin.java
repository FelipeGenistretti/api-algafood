package com.jpa_exemplo.jpa_exemplo.domain.model.Mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jpa_exemplo.jpa_exemplo.Groups;
import com.jpa_exemplo.jpa_exemplo.domain.model.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {


    //@JsonIgnore
    private OffsetDateTime dataCadastro;

    //@JsonIgnore
    private OffsetDateTime dataAtualizacao;

    @JsonIgnoreProperties(value="nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private List<FormaDePagamento> formasPagamento = new ArrayList<>();


    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    @JsonIgnore
    private Cidade cidade;

    @JsonIgnore
    private Endereco endereco;
}
