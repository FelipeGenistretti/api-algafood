package com.jpa_exemplo.jpa_exemplo.domain.model;


import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Grupo {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany
    @JoinTable(name="grupo_permissao", joinColumns = @JoinColumn(name = "grupo_id"), inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private List<Permissao> permissao = new ArrayList<>();


    @ManyToMany(mappedBy = "grupos")
    private List<Usuario> usuarios = new ArrayList<>();

    public List<Permissao> listarPermissoes()
    {
        return this.permissao;
    }

    public void adicionarPermissao(Permissao permissao) {
        this.permissao.add(permissao);
    }

    public void removerPermissao(Permissao permissao) {
        this.permissao.remove(permissao);
    }
}
