package com.jpa_exemplo.jpa_exemplo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jpa_exemplo.jpa_exemplo.domain.exception.EntidadeNaoEncontradaException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.RestauranteNaoEncontradoException;
import com.jpa_exemplo.jpa_exemplo.domain.model.Endereco;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.domain.model.Usuario;
import com.jpa_exemplo.jpa_exemplo.domain.model.view.RestauranteView;
import com.jpa_exemplo.jpa_exemplo.domain.repository.RestauranteRepository;
import com.jpa_exemplo.jpa_exemplo.domain.repository.UsuarioRepositoryInterface;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroRestauranteService;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroUsuarioService;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Endereco.Request.EnderecoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.*;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.ListUsuariosResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.endereco.EnderecoMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.restaurante.CriarRestauranteMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.restaurante.ListRestauranteMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.restaurante.UpdateRestauranteMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.usuario.ListUsuariosMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.repository.Spec.RestauranteSpecs;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(maxAge = 10)
@RestController
@RequestMapping(value="/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CriarRestauranteMapper restauranteMapper;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private ListRestauranteMapper listRestauranteMapper;

    @Autowired
    private UpdateRestauranteMapper updateRestauranteMapper;

    @Autowired
    private ListUsuariosMapper listUsuariosMapper;

    @GetMapping
    public MappingJacksonValue todos(@RequestParam(required = false) String projecao ) {
        List<Restaurante> restaurantes = restauranteRepository.listarComCozinha();
        List<ListRestaurantesResponseDTO> restauranteModel = listRestauranteMapper.toCollectionResponse(restaurantes);

        MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restauranteModel);
        restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);

        if(Objects.equals(projecao, "apenas-nome")){
            restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
        } else if (Objects.equals(projecao, "completo")) {
            restaurantesWrapper.setSerializationView(null);
        }

        return restaurantesWrapper;
    }


    @GetMapping("/cozinhas")
    public ResponseEntity<List<ListRestaurantesResponseDTO>> todos() {
        var restaurantes = restauranteRepository.listarComCozinha();
        return ResponseEntity.ok(listRestauranteMapper.toCollectionResponse(restaurantes));
    }

// @CrossOrigin(origins = "http://localhost:8000")
// @GetMapping("/cozinhas")
// public ResponseEntity<?> todos() {
//     System.out.println("Recebendo request /cozinhas"); // LOG
//     try {
//         var restaurantes = restauranteRepository.listarComCozinha();
//         var dto = listRestauranteMapper.toCollectionResponse(restaurantes);
//         System.out.println("DTO gerado: " + dto.size()); // LOG
//         return ResponseEntity.ok(dto);
//     } catch (Exception e) {
//         e.printStackTrace();
//         return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
//     }
// }

    // @JsonView(RestauranteView.Resumo.class)
    // @GetMapping(params = "projecao=resumo")
    // public ResponseEntity<List<ListRestaurantesResponseDTO>> listarResumido() {
    //     return todos();
    // }

    // @JsonView(RestauranteView.ApenasNome.class)
    // @GetMapping(params = "projecao=apenas-nome")
    // public ResponseEntity<List<ListRestaurantesResponseDTO>> listarApenasNome() {
    //     return todos();
    // }



    @GetMapping("/{restauranteId}")
    public ResponseEntity<ListRestaurantesResponseDTO> buscarPorId(@PathVariable Long restauranteId){
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        return ResponseEntity.ok(listRestauranteMapper.toResponse(restaurante));
    }

    @PostMapping
    public ResponseEntity<CriarRestauranteResponse> inserir(
            @RequestBody @Valid CriarRestauranteRequestDTO dto) {

        Restaurante restaurante = restauranteMapper.toEntity(dto);

        restaurante = cadastroRestauranteService
                .inserirRestaurante(restaurante, dto.cozinhaId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restauranteMapper.toResponse(restaurante));
    }


    @PutMapping("/{restauranteId}")
    public ResponseEntity<CriarRestauranteResponse> atualizar(
            @PathVariable Long restauranteId,
            @RequestBody @Valid AtualizarRestauranteRequestDTO dto) {

        try {
            Restaurante restauranteAtualizado =
                    cadastroRestauranteService.atualizarRestaurante(restauranteId, dto);

            return ResponseEntity.ok(restauranteMapper.toResponse(restauranteAtualizado));

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> remover(@PathVariable Long restauranteId){
        try{
            cadastroRestauranteService.deletarRestaurante(restauranteId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> atualizarParcialmente(
            @PathVariable Long restauranteId,
            @RequestBody Map<String, Object> camposOrigem) {

        Restaurante restauranteAtualizado = cadastroRestauranteService.atualizarParcial(restauranteId, camposOrigem);

        return ResponseEntity.ok(restauranteAtualizado);
    }

    @GetMapping("/restaurante-taxa-frete")
    public ResponseEntity<List<Restaurante>> restauranteTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
        List<Restaurante> restaurantes = restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
        if(restaurantes.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/restaurante-nome-por-cozinha")
    public ResponseEntity<List<Restaurante>> restauranteNomePorCozinha(String nome, Long cozinhaId){
        List<Restaurante> restaurantes = restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
        if(restaurantes.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/por-nome-e-frete")
    public ResponseEntity<List<Restaurante>> restaurantePorNomeFrete(String nome, BigDecimal freteInicial, BigDecimal freteFinal){
        List<Restaurante> restaurantes = restauranteRepository.findByNomeContainingAndTaxaFreteBetween(nome, freteInicial, freteFinal);
        if(restaurantes.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restaurantes);
    }


    @GetMapping("/com-frete-gratis")
    public ResponseEntity<List<Restaurante>> restauranteComFreteGratis(String nome){


        var resultado = restauranteRepository.findAll(
                RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comNomeSemelhante(nome))
        );

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/primeiro")
    public ResponseEntity<List<Restaurante>> restaurantePrimeiro(String nome){


        var resultado = restauranteRepository.findAll(
                RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comNomeSemelhante(nome))
        );

        return ResponseEntity.ok(resultado);
    }

    //@GetMapping("/primeiro")
    //public ResponseEntity<Restaurante> buscarPrimeiro() {
    //    Restaurante restaurante = restauranteRepository.buscarPrimeiro()
    //            .orElse(null);
    //    return ResponseEntity.ok(restaurante);
    //}

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId){
        cadastroRestauranteService.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativar(@PathVariable Long restauranteId){
        cadastroRestauranteService.inativar(restauranteId);
    }


    @PutMapping("/{restauranteId}/endereco")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarEndereco(@PathVariable Long restauranteId, @RequestBody EnderecoRequestDTO dto){
        Endereco endereco = enderecoMapper.toEntity(dto);
        cadastroRestauranteService.adicionarEndereco(restauranteId, endereco);
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fecharRestaurante(@PathVariable Long restauranteId)
    {
        cadastroRestauranteService.fecharRestaurante(restauranteId);
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrirRestaurante(@PathVariable Long restauranteId) {
        cadastroRestauranteService.abrirRestaurante(restauranteId);
    }

    @PutMapping("/{restauranteId}/usuarios/{usuarioId}")
    public ResponseEntity<Void> associarUsuarioRestaurante(@PathVariable Long restauranteId, @PathVariable Long usuarioId)
    {
        cadastroRestauranteService.associarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{restauranteId}/usuarios/{usuarioId}")
    public ResponseEntity<Void> desassociarUsuarioRestaurante(@PathVariable Long restauranteId, @PathVariable Long usuarioId)
    {
        cadastroRestauranteService.desassociarResponsavel(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{restauranteId}/responsaveis")
    public ResponseEntity<List<ListUsuariosResponseDTO>> listarResponsaveisRestaurante(@PathVariable Long restauranteId)
    {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        List<Usuario> usuarios = restaurante.getUsuarios();
        return ResponseEntity.ok(listUsuariosMapper.toCollectionResponse(usuarios));

    }

    @PutMapping("ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds){
        try{
            cadastroRestauranteService.ativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativarMultiplos(@RequestBody List<Long> restauranteIds){
        try{
            cadastroRestauranteService.desativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }









}
