package com.jpa_exemplo.jpa_exemplo.domain.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }

    public UsuarioNaoEncontradoException(Long usuarioId){
        this(String.format("Não existe um cadastro de usuario com código %d", usuarioId));
    }
}
