package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.usuario;

import com.jpa_exemplo.jpa_exemplo.domain.model.Usuario;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.UpdadeUsuarioResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.UpdateUsuarioRequestDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-16T20:32:58-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class UpdateUsuarioMapperImpl implements UpdateUsuarioMapper {

    @Override
    public Usuario updateEntity(UpdateUsuarioRequestDTO dto, Usuario usuario) {
        if ( dto == null ) {
            return usuario;
        }

        usuario.setNome( dto.nome() );
        usuario.setEmail( dto.email() );

        return usuario;
    }

    @Override
    public UpdadeUsuarioResponseDTO toResponse(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        String email = null;

        id = usuario.getId();
        nome = usuario.getNome();
        email = usuario.getEmail();

        UpdadeUsuarioResponseDTO updadeUsuarioResponseDTO = new UpdadeUsuarioResponseDTO( id, nome, email );

        return updadeUsuarioResponseDTO;
    }
}
