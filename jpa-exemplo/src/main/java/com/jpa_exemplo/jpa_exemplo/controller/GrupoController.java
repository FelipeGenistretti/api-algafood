package com.jpa_exemplo.jpa_exemplo.controller;

import com.jpa_exemplo.jpa_exemplo.domain.exception.GrupoNaoEncontrado;
import com.jpa_exemplo.jpa_exemplo.domain.model.Grupo;
import com.jpa_exemplo.jpa_exemplo.domain.model.Permissao;
import com.jpa_exemplo.jpa_exemplo.domain.repository.GrupoRepositoryInterface;
import com.jpa_exemplo.jpa_exemplo.domain.service.CadastroGrupoService;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.*;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.grupo.CreateGroupMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.grupo.ListGroupsMapper;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.grupo.UpdateGrupoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepositoryInterface grupoRepository;

    @Autowired
    private CadastroGrupoService grupoService;

    @Autowired
    private ListGroupsMapper listGroupMapper;

    @Autowired
    private CreateGroupMapper createGroupMapper;

    @Autowired
    private UpdateGrupoMapper updateGrupoMapper;

    @GetMapping
    public ResponseEntity<List<GrupoResponseDTO>> listarTodos()
    {
        List<Grupo> grupos = grupoRepository.findAll();
        return ResponseEntity.ok(listGroupMapper.toCollectionResponse(grupos));
    }

    @PostMapping
    public ResponseEntity<CreateGrupoResponseDTO> criarGrupo(@RequestBody @Valid CreateGroupRequestDTO dto)
    {
       Grupo grupo = createGroupMapper.toEntity(dto);
       grupo = grupoService.criarGrupo(grupo);
       return ResponseEntity.status(HttpStatus.CREATED).body(createGroupMapper.toResponse(grupo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoResponseDTO> grupoPorId(@PathVariable Long id)
    {
        Grupo grupo = grupoRepository.findById(id).orElseThrow(()-> new GrupoNaoEncontrado("Grupo nao encontrado"));
        return ResponseEntity.ok(listGroupMapper.toResponse(grupo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGrupo(@PathVariable Long id) {
        try {
            grupoService.deletarGrupo(id);
            return ResponseEntity.noContent().build();
        } catch (GrupoNaoEncontrado e) {
            return ResponseEntity.notFound().build();
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateGrupoResponseDTO> updateGrupo(
            @RequestBody @Valid UpdateGrupoRequestDTO dto,
            @PathVariable Long id) {

        Grupo grupoAtualizado = grupoService.atualizarGrupo(id, dto);

        return ResponseEntity.ok(updateGrupoMapper.toResponse(grupoAtualizado));
    }

    @GetMapping("/{grupoId}/permissoes")
    public ResponseEntity<List<ListGroupsPermissaoResponseDTO>> listarGruposPermissoes(@PathVariable Long grupoId) {
        Grupo grupo = this.grupoService.buscarOuFalhar(grupoId);
        List<Permissao> permissoes = grupo.listarPermissoes();

        var dtoList = listGroupMapper.toCollectionResponsePermitions(permissoes);
        return ResponseEntity.ok(dtoList);
    }

    @PutMapping("/{grupoId}/permissoes/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarPermissao(@PathVariable Long grupoId,
                                  @PathVariable Long permissaoId) {

        grupoService.associarPermissaoGrupo(grupoId, permissaoId);
    }

    @DeleteMapping("/{grupoId}/permissoes/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarPermissao(@PathVariable Long grupoId,
                                     @PathVariable Long permissaoId) {

        grupoService.desassociarPermissaoGrupo(grupoId, permissaoId);
    }


}
