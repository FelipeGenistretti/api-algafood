package com.jpa_exemplo.jpa_exemplo.domain.repository;

import com.jpa_exemplo.jpa_exemplo.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositoryInterface extends JpaRepository<Usuario, Long> {

    boolean existsByNome(String nome);


    Optional<Usuario> findByEmail(String email);

}
