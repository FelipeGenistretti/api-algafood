package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.usuario;

import com.jpa_exemplo.jpa_exemplo.domain.model.Usuario;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.ListUsuariosResponseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-19T22:41:42-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class ListUsuariosMapperImpl implements ListUsuariosMapper {

    @Override
    public Usuario toEntity(ListUsuariosResponseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( dto.id() );
        usuario.setNome( dto.nome() );
        usuario.setEmail( dto.email() );

        return usuario;
    }

    @Override
    public ListUsuariosResponseDTO toResponse(Usuario entidade) {
        if ( entidade == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        String email = null;

        id = entidade.getId();
        nome = entidade.getNome();
        email = entidade.getEmail();

        ListUsuariosResponseDTO listUsuariosResponseDTO = new ListUsuariosResponseDTO( id, nome, email );

        return listUsuariosResponseDTO;
    }

    @Override
    public List<ListUsuariosResponseDTO> toCollectionResponse(List<Usuario> entidades) {
        if ( entidades == null ) {
            return null;
        }

        List<ListUsuariosResponseDTO> list = new ArrayList<ListUsuariosResponseDTO>( entidades.size() );
        for ( Usuario usuario : entidades ) {
            list.add( toResponse( usuario ) );
        }

        return list;
    }
}
