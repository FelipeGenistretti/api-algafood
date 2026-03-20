package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.usuario;

import com.jpa_exemplo.jpa_exemplo.domain.model.Usuario;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.CreateUsuarioRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.CreateUsuarioResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-19T22:41:43-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class CreateUsuarioMapperImpl implements CreateUsuarioMapper {

    @Override
    public Usuario toEntity(CreateUsuarioRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNome( dto.nome() );
        usuario.setEmail( dto.email() );
        usuario.setSenha( dto.senha() );

        return usuario;
    }

    @Override
    public CreateUsuarioResponseDTO toResponse(Usuario entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        String email = null;

        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();

        CreateUsuarioResponseDTO createUsuarioResponseDTO = new CreateUsuarioResponseDTO( id, nome, email );

        return createUsuarioResponseDTO;
    }
}
