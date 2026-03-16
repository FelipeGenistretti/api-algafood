package com.jpa_exemplo.jpa_exemplo.domain.repository;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> , JpaSpecificationExecutor<Cozinha> {
        List<Cozinha> findByNomeContaining(String nome);
        Optional<Cozinha> findByNome(String nome);
        boolean existsByNome(String nome);
        boolean existsByNomeAndIdNot(String nome, Long id);



}
