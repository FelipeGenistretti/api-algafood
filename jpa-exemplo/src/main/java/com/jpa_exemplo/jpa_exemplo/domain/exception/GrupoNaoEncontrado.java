package com.jpa_exemplo.jpa_exemplo.domain.exception;

public class GrupoNaoEncontrado extends RuntimeException {
    public GrupoNaoEncontrado(String message) {
        super(message);
    }

    public GrupoNaoEncontrado(Long grupoId){
        this(String.format("Não existe um cadastro de grupo com código %d", grupoId));
    }
}
