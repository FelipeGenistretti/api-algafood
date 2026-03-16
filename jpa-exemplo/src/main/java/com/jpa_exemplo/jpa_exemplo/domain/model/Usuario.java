package com.jpa_exemplo.jpa_exemplo.domain.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    private String senha;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private OffsetDateTime dataCadastro;

    @ManyToMany
    @JoinTable(name = "restaurante_grupos", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private List<Grupo> grupos = new ArrayList<>();

    @ManyToMany(mappedBy = "usuarios")
    private List<Restaurante> restaurantes;

    public void adicionarGrupoUsuario(Grupo grupo) {
        if (!this.grupos.contains(grupo)) {
            this.grupos.add(grupo);
        }
    }

    public void removerGrupoUsuario(Grupo grupo) {
        if (this.grupos.contains(grupo)) {
            this.grupos.remove(grupo);
        }
    }
}
