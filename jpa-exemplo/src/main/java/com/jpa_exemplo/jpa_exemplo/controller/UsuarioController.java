package com.jpa_exemplo.jpa_exemplo.controller;

import com.jpa_exemplo.jpa_exemplo.domain.exception.UsuarioNaoEncontradoException;
import com.jpa_exemplo.jpa_exemplo.domain.model.Grupo;
import com.jpa_exemplo.jpa_exemplo.domain.model.Usuario;
import com.jpa_exemplo.jpa_exemplo.domain.repository.UsuarioRepositoryInterface;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroUsuarioService;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.GrupoResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.ListGroupResponseDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.*;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.grupo.ListGroupsMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.usuario.CreateUsuarioMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.usuario.ListUsuariosMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.usuario.UpdateUsuarioMapper;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController
{
    @Autowired
    private UsuarioRepositoryInterface usuarioRepository;

    @Autowired
    private ListUsuariosMapper usuariosMapper;

    @Autowired
    private CreateUsuarioMapper createUsuarioMapper;

    @Autowired
    private UpdateUsuarioMapper updateUsuarioMapper;

    @Autowired
    private CadastroUsuarioService usuarioService;

    @Autowired
    private ListGroupsMapper grupoMapper;

    @GetMapping
    public ResponseEntity<List<ListUsuariosResponseDTO>> listarTodos()
    {
        List<Usuario> usuario = usuarioRepository.findAll();
        return ResponseEntity.ok(usuariosMapper.toCollectionResponse(usuario));
    }

    @PostMapping
    public ResponseEntity<CreateUsuarioResponseDTO> criarUsuario(@RequestBody @Valid CreateUsuarioRequestDTO dto)
    {
        Usuario usuario = createUsuarioMapper.toEntity(dto);
        usuario = usuarioService.criaUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(createUsuarioMapper.toResponse(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdadeUsuarioResponseDTO> updateUsuario(@RequestBody @Valid UpdateUsuarioRequestDTO dto, @PathVariable Long id)
    {
        Usuario usuario = usuarioService.updateUsuario(dto, id);
        return ResponseEntity.ok(updateUsuarioMapper.toResponse(usuario));
    }

    @PutMapping("/{id}/senha")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid UpdatePasswordUserRequestDTO dto ,@PathVariable Long id)
    {
        usuarioService.updatePassword(dto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario( @PathVariable Long id)
    {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListUsuariosResponseDTO> usuarioPorId(@PathVariable Long id )
    {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new UsuarioNaoEncontradoException("Não foi encontrado nenhum usuario com este id"));
        return ResponseEntity.ok(usuariosMapper.toResponse(usuario));
    }

    @PutMapping("/{userId}/grupos/{grupoId}")
    public ResponseEntity<Void> associarUserGroup(@PathVariable Long userId, @PathVariable Long grupoId) {
        usuarioService.associarUserGroup(userId, grupoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/grupos/{grupoId}")
    public ResponseEntity<Void> desassociarUserGroup(@PathVariable Long userId, @PathVariable Long grupoId)
    {
        usuarioService.desassociarUserGroup(userId, grupoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/grupos")
    public ResponseEntity<List<GrupoResponseDTO>> gruporPorUsuario(@PathVariable Long userId){
        List<Grupo> grupos = usuarioService.gruposPorUsuarios(userId);
        return ResponseEntity.ok(grupoMapper.toCollectionResponse(grupos));
    }



}
