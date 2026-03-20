package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.restaurante;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import com.jpa_exemplo.jpa_exemplo.domain.model.Cozinha;
import com.jpa_exemplo.jpa_exemplo.domain.model.Endereco;
import com.jpa_exemplo.jpa_exemplo.domain.model.Estado;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Endereco.Response.CidadeResumoResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Endereco.Response.EnderecoResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.CriarRestauranteCozinhaResponse;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.CriarRestauranteRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.CriarRestauranteResponse;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-19T22:41:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class CriarRestauranteMapperImpl implements CriarRestauranteMapper {

    @Override
    public CriarRestauranteResponse toResponse(Restaurante restaurante) {
        if ( restaurante == null ) {
            return null;
        }

        EnderecoResponseDTO endereco = null;
        Long id = null;
        String nome = null;
        BigDecimal taxaFrete = null;
        CriarRestauranteCozinhaResponse cozinha = null;
        Boolean ativo = null;

        endereco = enderecoToEnderecoResponseDTO( restaurante.getEndereco() );
        id = restaurante.getId();
        nome = restaurante.getNome();
        taxaFrete = restaurante.getTaxaFrete();
        cozinha = cozinhaToCriarRestauranteCozinhaResponse( restaurante.getCozinha() );
        ativo = restaurante.getAtivo();

        CriarRestauranteResponse criarRestauranteResponse = new CriarRestauranteResponse( id, nome, taxaFrete, cozinha, ativo, endereco );

        return criarRestauranteResponse;
    }

    @Override
    public Restaurante toEntity(CriarRestauranteRequestDTO request) {
        if ( request == null ) {
            return null;
        }

        Restaurante restaurante = new Restaurante();

        restaurante.setNome( request.nome() );
        restaurante.setTaxaFrete( request.taxaFrete() );

        return restaurante;
    }

    @Override
    public void updateEntity(CriarRestauranteRequestDTO dto, Restaurante restaurante) {
        if ( dto == null ) {
            return;
        }

        restaurante.setNome( dto.nome() );
        restaurante.setTaxaFrete( dto.taxaFrete() );
    }

    private String cidadeEstadoNome(Cidade cidade) {
        if ( cidade == null ) {
            return null;
        }
        Estado estado = cidade.getEstado();
        if ( estado == null ) {
            return null;
        }
        String nome = estado.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }

    protected CidadeResumoResponseDTO cidadeToCidadeResumoResponseDTO(Cidade cidade) {
        if ( cidade == null ) {
            return null;
        }

        String estado = null;
        Long id = null;
        String nome = null;

        estado = cidadeEstadoNome( cidade );
        id = cidade.getId();
        nome = cidade.getNome();

        CidadeResumoResponseDTO cidadeResumoResponseDTO = new CidadeResumoResponseDTO( id, nome, estado );

        return cidadeResumoResponseDTO;
    }

    protected EnderecoResponseDTO enderecoToEnderecoResponseDTO(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        CidadeResumoResponseDTO cidade = null;
        String cep = null;
        String logradouro = null;
        String numero = null;
        String complemento = null;
        String bairro = null;

        cidade = cidadeToCidadeResumoResponseDTO( endereco.getCidade() );
        cep = endereco.getCep();
        logradouro = endereco.getLogradouro();
        numero = endereco.getNumero();
        complemento = endereco.getComplemento();
        bairro = endereco.getBairro();

        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO( cep, logradouro, numero, complemento, bairro, cidade );

        return enderecoResponseDTO;
    }

    protected CriarRestauranteCozinhaResponse cozinhaToCriarRestauranteCozinhaResponse(Cozinha cozinha) {
        if ( cozinha == null ) {
            return null;
        }

        Long id = null;
        String nome = null;

        id = cozinha.getId();
        nome = cozinha.getNome();

        CriarRestauranteCozinhaResponse criarRestauranteCozinhaResponse = new CriarRestauranteCozinhaResponse( id, nome );

        return criarRestauranteCozinhaResponse;
    }
}
