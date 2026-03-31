package com.jpa_exemplo.jpa_exemplo.domain.service;

import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import com.jpa_exemplo.jpa_exemplo.domain.exception.UsuarioNaoEncontradoException;
import com.jpa_exemplo.jpa_exemplo.domain.model.Grupo;
import com.jpa_exemplo.jpa_exemplo.domain.model.Usuario;
import com.jpa_exemplo.jpa_exemplo.domain.repository.UsuarioRepositoryInterface;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.UpdatePasswordUserRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.Usuario.UpdateUsuarioRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.usuario.UpdateUsuarioMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroUsuarioService
{

    @Autowired
    private UsuarioRepositoryInterface usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UpdateUsuarioMapper updateUsuarioMapper;

    @Autowired
    private CadastroGrupoService grupoService;



    @Transactional
    public Usuario criaUsuario(Usuario usuario)
    {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario))
        {
            throw new NegocioException("Já existe um usuario com este email");
        }

        if(usuarioRepository.existsByNome(usuario.getNome())){
            throw new NegocioException("Já existe um usuario com este nome");
        }


        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario updateUsuario(UpdateUsuarioRequestDTO dto, Long id)
    {
        Usuario usuario = this.buscarOuFalhar(id);

        updateUsuarioMapper.updateEntity(dto, usuario);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void updatePassword(UpdatePasswordUserRequestDTO dto, Long id)
    {
        Usuario usuario = this.buscarOuFalhar(id);

        if (!passwordEncoder.matches(dto.senhaAtual(), usuario.getSenha())) {
            throw new NegocioException("Senha atual incorreta");
        }

        usuario.setSenha(passwordEncoder.encode(dto.novaSenha()));

    }

    @Transactional
    public void deleteUsuario(Long id)
    {
        Usuario usuario = this.buscarOuFalhar(id);
        usuarioRepository.delete(usuario);
    }

    @Transactional
    public void associarUserGroup(Long userId, Long groupId) {
        Usuario usuario = this.buscarOuFalhar(userId);
        Grupo grupo = grupoService.buscarOuFalhar(groupId);

        usuario.adicionarGrupoUsuario(grupo);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void desassociarUserGroup(Long userId, Long groupId) {
        Usuario usuario = this.buscarOuFalhar(userId);
        Grupo grupo = grupoService.buscarOuFalhar(groupId);

        usuario.removerGrupoUsuario(grupo);
        usuarioRepository.save(usuario);
    }

    public List<Grupo> gruposPorUsuarios(Long userId) {
        Usuario usuario = this.buscarOuFalhar(userId);
        List<Grupo> grupos = usuario.getGrupos();
        return grupos;

    }


    public Usuario buscarOuFalhar(Long id)
    {
        return usuarioRepository.findById(id).orElseThrow(()-> new UsuarioNaoEncontradoException("Não existe um usuario com este id"));
    }

}
