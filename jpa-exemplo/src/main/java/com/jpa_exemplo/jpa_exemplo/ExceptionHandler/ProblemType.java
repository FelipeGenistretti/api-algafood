package com.jpa_exemplo.jpa_exemplo.ExceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    NEGOCIO_EXCEPTION("/negocio-excption", "Negócio exception"),
    ENTIDADE_EM_USO_EXCEPTION("/entidade-em-uso", "Entidade em uso"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivel"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos");

    private String title;
    private String uri;

    ProblemType(String path, String title)
    {
        this.uri = "https://algafood.com.br/" + path;
        this.title = title;
    }
}
