package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.restaurante;

import com.jpa_exemplo.jpa_exemplo.domain.model.Endereco;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.AtualizarRestauranteRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Restaurante.ResumoEnderecoRequestDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T22:14:29-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class UpdateRestauranteMapperImpl implements UpdateRestauranteMapper {

    @Override
    public void updateEntity(AtualizarRestauranteRequestDTO request, Restaurante restaurante) {
        if ( request == null ) {
            return;
        }

        restaurante.setNome( request.nome() );
        restaurante.setTaxaFrete( request.taxaFrete() );
        if ( request.endereco() != null ) {
            if ( restaurante.getEndereco() == null ) {
                restaurante.setEndereco( new Endereco() );
            }
            resumoEnderecoRequestDTOToEndereco( request.endereco(), restaurante.getEndereco() );
        }
        else {
            restaurante.setEndereco( null );
        }
    }

    protected void resumoEnderecoRequestDTOToEndereco(ResumoEnderecoRequestDTO resumoEnderecoRequestDTO, Endereco mappingTarget) {
        if ( resumoEnderecoRequestDTO == null ) {
            return;
        }

        mappingTarget.setCep( resumoEnderecoRequestDTO.cep() );
        mappingTarget.setLogradouro( resumoEnderecoRequestDTO.logradouro() );
        mappingTarget.setNumero( resumoEnderecoRequestDTO.numero() );
        mappingTarget.setComplemento( resumoEnderecoRequestDTO.complemento() );
        mappingTarget.setBairro( resumoEnderecoRequestDTO.bairro() );
    }
}
