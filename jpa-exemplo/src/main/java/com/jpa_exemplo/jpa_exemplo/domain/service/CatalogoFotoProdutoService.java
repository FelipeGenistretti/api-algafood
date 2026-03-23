package com.jpa_exemplo.jpa_exemplo.domain.service;

import com.jpa_exemplo.jpa_exemplo.domain.model.FotoProduto;
import com.jpa_exemplo.jpa_exemplo.domain.repository.ProdutoRepositoryInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepositoryInterface produtoRepository;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();

        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

        if(fotoExistente.isPresent()) {
            produtoRepository.delete(fotoExistente.get());
        }

        FotoStorageService.NovaFoto novaFoto = FotoStorageService.NovaFoto.builder().nomeArquivo(foto.getNomeArquivo()).inputStream(dadosArquivo).build();

        fotoStorageService.armazenar(novaFoto);
        return produtoRepository.save(foto);
    }
}
