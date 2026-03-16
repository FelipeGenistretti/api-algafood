package com.jpa_exemplo.jpa_exemplo.domain.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }

    public ProdutoNaoEncontradoException(Long produtoId){
        this(String.format("Não existe um produto com código %d", produtoId));
    }
}
