package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.restaurante;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import com.jpa_exemplo.jpa_exemplo.domain.model.Cozinha;
import com.jpa_exemplo.jpa_exemplo.domain.model.Endereco;
import com.jpa_exemplo.jpa_exemplo.domain.model.Estado;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.ListCidadeRestauranteResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.ListCozinhaRestauranteResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.ListEnderecoRestauranteResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.ListEstadoRestauranteResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.ListRestaurantesResponseDTO;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-19T22:41:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class ListRestauranteMapperImpl implements ListRestauranteMapper {

    @Override
    public ListRestaurantesResponseDTO toResponse(Restaurante restaurante) {
        if ( restaurante == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        BigDecimal taxaFrete = null;
        ListCozinhaRestauranteResponseDTO cozinha = null;
        OffsetDateTime dataCadastro = null;
        OffsetDateTime dataAtualizacao = null;
        boolean ativo = false;
        boolean aberto = false;
        ListEnderecoRestauranteResponseDTO endereco = null;

        id = restaurante.getId();
        nome = restaurante.getNome();
        taxaFrete = restaurante.getTaxaFrete();
        cozinha = cozinhaToListCozinhaRestauranteResponseDTO( restaurante.getCozinha() );
        dataCadastro = restaurante.getDataCadastro();
        dataAtualizacao = restaurante.getDataAtualizacao();
        if ( restaurante.getAtivo() != null ) {
            ativo = restaurante.getAtivo();
        }
        if ( restaurante.getAberto() != null ) {
            aberto = restaurante.getAberto();
        }
        endereco = enderecoToListEnderecoRestauranteResponseDTO( restaurante.getEndereco() );

        ListRestaurantesResponseDTO listRestaurantesResponseDTO = new ListRestaurantesResponseDTO( id, nome, taxaFrete, cozinha, dataCadastro, dataAtualizacao, ativo, aberto, endereco );

        return listRestaurantesResponseDTO;
    }

    @Override
    public List<ListRestaurantesResponseDTO> toCollectionResponse(List<Restaurante> restaurantes) {
        if ( restaurantes == null ) {
            return null;
        }

        List<ListRestaurantesResponseDTO> list = new ArrayList<ListRestaurantesResponseDTO>( restaurantes.size() );
        for ( Restaurante restaurante : restaurantes ) {
            list.add( toResponse( restaurante ) );
        }

        return list;
    }

    protected ListCozinhaRestauranteResponseDTO cozinhaToListCozinhaRestauranteResponseDTO(Cozinha cozinha) {
        if ( cozinha == null ) {
            return null;
        }

        Long id = null;
        String nome = null;

        id = cozinha.getId();
        nome = cozinha.getNome();

        ListCozinhaRestauranteResponseDTO listCozinhaRestauranteResponseDTO = new ListCozinhaRestauranteResponseDTO( id, nome );

        return listCozinhaRestauranteResponseDTO;
    }

    protected ListEstadoRestauranteResponseDTO estadoToListEstadoRestauranteResponseDTO(Estado estado) {
        if ( estado == null ) {
            return null;
        }

        Long id = null;
        String nome = null;

        id = estado.getId();
        nome = estado.getNome();

        ListEstadoRestauranteResponseDTO listEstadoRestauranteResponseDTO = new ListEstadoRestauranteResponseDTO( id, nome );

        return listEstadoRestauranteResponseDTO;
    }

    protected ListCidadeRestauranteResponseDTO cidadeToListCidadeRestauranteResponseDTO(Cidade cidade) {
        if ( cidade == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        ListEstadoRestauranteResponseDTO estado = null;

        id = cidade.getId();
        nome = cidade.getNome();
        estado = estadoToListEstadoRestauranteResponseDTO( cidade.getEstado() );

        ListCidadeRestauranteResponseDTO listCidadeRestauranteResponseDTO = new ListCidadeRestauranteResponseDTO( id, nome, estado );

        return listCidadeRestauranteResponseDTO;
    }

    protected ListEnderecoRestauranteResponseDTO enderecoToListEnderecoRestauranteResponseDTO(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        String cep = null;
        String logradouro = null;
        String numero = null;
        String complemento = null;
        String bairro = null;
        ListCidadeRestauranteResponseDTO cidade = null;

        cep = endereco.getCep();
        logradouro = endereco.getLogradouro();
        numero = endereco.getNumero();
        complemento = endereco.getComplemento();
        bairro = endereco.getBairro();
        cidade = cidadeToListCidadeRestauranteResponseDTO( endereco.getCidade() );

        ListEnderecoRestauranteResponseDTO listEnderecoRestauranteResponseDTO = new ListEnderecoRestauranteResponseDTO( cep, logradouro, numero, complemento, bairro, cidade );

        return listEnderecoRestauranteResponseDTO;
    }
}
