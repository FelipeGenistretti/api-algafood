package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.grupo;

import com.jpa_exemplo.jpa_exemplo.domain.model.Grupo;
import com.jpa_exemplo.jpa_exemplo.domain.model.Permissao;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.GrupoResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.ListGroupsPermissaoResponseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-07T12:36:30-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class ListGroupsMapperImpl implements ListGroupsMapper {

    @Override
    public GrupoResponseDTO toResponse(Grupo grupo) {
        if ( grupo == null ) {
            return null;
        }

        Long id = null;
        String nome = null;

        id = grupo.getId();
        nome = grupo.getNome();

        GrupoResponseDTO grupoResponseDTO = new GrupoResponseDTO( id, nome );

        return grupoResponseDTO;
    }

    @Override
    public List<GrupoResponseDTO> toCollectionResponse(List<Grupo> entidades) {
        if ( entidades == null ) {
            return null;
        }

        List<GrupoResponseDTO> list = new ArrayList<GrupoResponseDTO>( entidades.size() );
        for ( Grupo grupo : entidades ) {
            list.add( toResponse( grupo ) );
        }

        return list;
    }

    @Override
    public List<ListGroupsPermissaoResponseDTO> toCollectionResponsePermitions(List<Permissao> entidades) {
        if ( entidades == null ) {
            return null;
        }

        List<ListGroupsPermissaoResponseDTO> list = new ArrayList<ListGroupsPermissaoResponseDTO>( entidades.size() );
        for ( Permissao permissao : entidades ) {
            list.add( permissaoToListGroupsPermissaoResponseDTO( permissao ) );
        }

        return list;
    }

    protected ListGroupsPermissaoResponseDTO permissaoToListGroupsPermissaoResponseDTO(Permissao permissao) {
        if ( permissao == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        String descricao = null;

        id = permissao.getId();
        nome = permissao.getNome();
        descricao = permissao.getDescricao();

        ListGroupsPermissaoResponseDTO listGroupsPermissaoResponseDTO = new ListGroupsPermissaoResponseDTO( id, nome, descricao );

        return listGroupsPermissaoResponseDTO;
    }
}
