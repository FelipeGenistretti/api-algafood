package com.jpa_exemplo.jpa_exemplo.domain.service;


import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

public interface FotoStorageService {

    void armazenar(NovaFoto novaFoto);

    @Data
    @Builder
    class NovaFoto {
        private String nomeArquivo;
        private InputStream inputStream;
    }
}
