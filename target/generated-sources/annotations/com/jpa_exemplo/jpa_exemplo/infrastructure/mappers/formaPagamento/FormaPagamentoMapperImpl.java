package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.formaPagamento;

import com.jpa_exemplo.jpa_exemplo.domain.model.FormaDePagamento;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FormaDePagamento.Request.FormaDePagamentoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FormaDePagamento.Response.FormaDePagamentoResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FormaDePagamento.Response.RestauranteResumoResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-16T20:32:58-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class FormaPagamentoMapperImpl implements FormaPagamentoMapper {

    @Override
    public FormaDePagamento toEntity(FormaDePagamentoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FormaDePagamento formaDePagamento = new FormaDePagamento();

        formaDePagamento.setDescricao( dto.descricao() );

        return formaDePagamento;
    }

    @Override
    public FormaDePagamentoResponseDTO toResponse(FormaDePagamento formaPagamento) {
        if ( formaPagamento == null ) {
            return null;
        }

        Long id = null;
        String descricao = null;
        List<RestauranteResumoResponse> restaurantes = null;

        id = formaPagamento.getId();
        descricao = formaPagamento.getDescricao();
        restaurantes = restauranteListToRestauranteResumoResponseList( formaPagamento.getRestaurantes() );

        FormaDePagamentoResponseDTO formaDePagamentoResponseDTO = new FormaDePagamentoResponseDTO( id, descricao, restaurantes );

        return formaDePagamentoResponseDTO;
    }

    @Override
    public void updateEntity(FormaDePagamentoRequestDTO dto, FormaDePagamento formaPagamento) {
        if ( dto == null ) {
            return;
        }

        formaPagamento.setDescricao( dto.descricao() );
    }

    @Override
    public Set<FormaDePagamentoResponseDTO> toColletionResponse(Collection<FormaDePagamento> entidades) {
        if ( entidades == null ) {
            return null;
        }

        Set<FormaDePagamentoResponseDTO> set = new LinkedHashSet<FormaDePagamentoResponseDTO>( Math.max( (int) ( entidades.size() / .75f ) + 1, 16 ) );
        for ( FormaDePagamento formaDePagamento : entidades ) {
            set.add( toResponse( formaDePagamento ) );
        }

        return set;
    }

    protected RestauranteResumoResponse restauranteToRestauranteResumoResponse(Restaurante restaurante) {
        if ( restaurante == null ) {
            return null;
        }

        Long id = null;
        String nome = null;

        id = restaurante.getId();
        nome = restaurante.getNome();

        RestauranteResumoResponse restauranteResumoResponse = new RestauranteResumoResponse( id, nome );

        return restauranteResumoResponse;
    }

    protected List<RestauranteResumoResponse> restauranteListToRestauranteResumoResponseList(List<Restaurante> list) {
        if ( list == null ) {
            return null;
        }

        List<RestauranteResumoResponse> list1 = new ArrayList<RestauranteResumoResponse>( list.size() );
        for ( Restaurante restaurante : list ) {
            list1.add( restauranteToRestauranteResumoResponse( restaurante ) );
        }

        return list1;
    }
}
