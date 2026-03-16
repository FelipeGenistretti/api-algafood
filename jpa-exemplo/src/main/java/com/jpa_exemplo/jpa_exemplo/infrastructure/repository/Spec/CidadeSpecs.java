package com.jpa_exemplo.jpa_exemplo.infrastructure.repository.Spec;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public class CidadeSpecs {
    public static Specification<Cidade> nomeIgualIgnoreCase(String nome) {
        return (root, query, cb) ->
                cb.equal(
                        cb.lower(root.get("nome")),
                        nome.toLowerCase()
                );
    }

    public static Specification<Cidade> idDiferente(Long id) {
        return (root, query, cb) ->
                cb.notEqual(root.get("id"), id);
    }
}
