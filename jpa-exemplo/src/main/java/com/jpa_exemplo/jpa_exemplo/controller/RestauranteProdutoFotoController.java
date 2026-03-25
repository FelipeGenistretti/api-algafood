package com.jpa_exemplo.jpa_exemplo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.jpa_exemplo.jpa_exemplo.domain.exception.EntidadeNaoEncontradaException;
import com.jpa_exemplo.jpa_exemplo.domain.model.FotoProduto;
import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroProdutoService;
import com.jpa_exemplo.jpa_exemplo.domain.service.CatalogoFotoProdutoService;
import com.jpa_exemplo.jpa_exemplo.domain.service.FotoStorageService;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FotoProduto.FotoProdutoResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.fotoProduto.FotoProdutoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FotoProduto.FotoProdutoRequestDTO;

import jakarta.validation.Valid;

import javax.print.attribute.standard.Media;


@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private FotoProdutoMapper fotoProdutoMapper;

    @Autowired
    private FotoStorageService fotoStorageService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoResponseDTO atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestParam MultipartFile arquivo, @Valid @ModelAttribute FotoProdutoRequestDTO dto ) throws IOException {
        Produto produto = cadastroProdutoService.buscarOuFalhar(produtoId);

        MultipartFile arquivoDto = dto.arquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(dto.descricao());
        foto.setContentType(arquivoDto.getContentType());
        foto.setTamanho(arquivoDto.getSize());
        foto.setNomeArquivo(arquivoDto.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto, arquivo.getInputStream());

        return fotoProdutoMapper.toResponse(fotoSalva);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoResponseDTO buscar(@PathVariable Long restauranteId,
                                   @PathVariable Long produtoId) {
        FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);

        return fotoProdutoMapper.toResponse(fotoProduto);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestHeader(name="accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException
    {
        try {
            FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypeAceitas = MediaType.parseMediaTypes(acceptHeader);
            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypeAceitas);

            InputStream inputStream = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(inputStream));
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFotoProduto(@PathVariable Long restauranteId, @PathVariable Long produtoId)
    {
        catalogoFotoProdutoService.excluir(restauranteId, produtoId);

    }

    private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypeAceitas) throws HttpMediaTypeNotAcceptableException
    {
        boolean compativel = mediaTypeAceitas.stream().anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));

        if(!compativel) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypeAceitas);
        }
    }

}
