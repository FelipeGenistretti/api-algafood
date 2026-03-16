package com.jpa_exemplo.jpa_exemplo.domain.exception;

public class FormaDePagamentoException extends RuntimeException {
    public FormaDePagamentoException(String message) {
        super(message);
    }

    public FormaDePagamentoException(Long formaPagamentoId){
        this(String.format("Não existe um cadastro de forma de pagamento com código %d", formaPagamentoId));
    }
}


