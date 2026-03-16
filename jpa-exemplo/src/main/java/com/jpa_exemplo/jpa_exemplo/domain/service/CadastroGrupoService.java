package com.jpa_exemplo.jpa_exemplo.domain.service;

import com.jpa_exemplo.jpa_exemplo.domain.exception.GrupoNaoEncontrado;
import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import com.jpa_exemplo.jpa_exemplo.domain.model.Grupo;
import com.jpa_exemplo.jpa_exemplo.domain.model.Permissao;
import com.jpa_exemplo.jpa_exemplo.domain.repository.GrupoRepositoryInterface;
import com.jpa_exemplo.jpa_exemplo.domain.repository.PermissaoRepositoryInterface;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Grupo.UpdateGrupoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.grupo.UpdateGrupoMapper;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroGrupoService
{
    @Autowired
    private GrupoRepositoryInterface grupoRepository;

    @Autowired
    private UpdateGrupoMapper updateGrupoMapper;

    @Autowired
    private PermissaoRepositoryInterface permissaoRepository;

    @Transactional
    public Grupo criarGrupo(Grupo grupo)
    {
        if(grupoRepository.existsByNome(grupo.getNome())){
            throw new NegocioException("Já existe um grupo com este nome");
        }

        return grupoRepository.save(grupo);
    }

    @Transactional
    public void deletarGrupo(Long id)
    {
        Grupo grupo = this.buscarOuFalhar(id);
        grupoRepository.delete(grupo);
        grupoRepository.flush();
    }

    @Transactional
    public Grupo atualizarGrupo(Long id, UpdateGrupoRequestDTO dto) {

        Grupo grupo = buscarOuFalhar(id);

        updateGrupoMapper.updateEntity(dto, grupo);

        return grupoRepository.save(grupo);
    }

    public Grupo buscarOuFalhar(Long id)
    {
        Grupo grupo = grupoRepository.findById(id).orElseThrow(()-> new GrupoNaoEncontrado("Grupo não encontrado"));
        return grupo;
    }

    @Transactional
    public void associarPermissaoGrupo(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new NegocioException("Nenhuma permissão encontrada com este id"));


        grupo.adicionarPermissao(permissao);
    }

    @Transactional
    public void desassociarPermissaoGrupo(Long grupoId, Long permissaoId) {

        Grupo grupo = buscarOuFalhar(grupoId);

        Permissao permissao = permissaoRepository.findById(permissaoId)
                .orElseThrow(() ->
                        new NegocioException("Nenhuma permissão encontrada com este id"));

        grupo.removerPermissao(permissao);
    }



}
