package com.jpa_exemplo.jpa_exemplo.controller;

import java.nio.file.Path;
import java.util.UUID;

import com.jpa_exemplo.jpa_exemplo.domain.model.FotoProduto;
import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroProdutoService;
import com.jpa_exemplo.jpa_exemplo.domain.service.CatalogoFotoProdutoService;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FotoProduto.FotoProdutoResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.fotoProduto.FotoProdutoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FotoProduto.FotoProdutoRequestDTO;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private FotoProdutoMapper fotoProdutoMapper;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoResponseDTO atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestParam MultipartFile arquivo, @Valid @ModelAttribute FotoProdutoRequestDTO dto ) {
        Produto produto = cadastroProdutoService.buscarOuFalhar(produtoId);

        MultipartFile arquivoDto = dto.arquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(dto.descricao());
        foto.setContentType(arquivoDto.getContentType());
        foto.setTamanho(arquivoDto.getSize());
        foto.setNomeArquivo(arquivoDto.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto);

        return fotoProdutoMapper.toResponse(fotoSalva);
    }

}
