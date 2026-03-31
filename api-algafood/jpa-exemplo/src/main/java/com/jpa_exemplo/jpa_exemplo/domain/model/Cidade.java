package com.jpa_exemplo.jpa_exemplo.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jpa_exemplo.jpa_exemplo.Groups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @NotBlank
    @Column(nullable = false)
    String  nome;

    @Valid
    @ConvertGroup(from = Default.class, to = Groups.CadastroCidade.class)
    @ManyToOne
    @JoinColumn(name="estado_id", nullable=false)
    private Estado estado;

}
