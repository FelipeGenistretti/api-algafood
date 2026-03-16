package com.jpa_exemplo.jpa_exemplo.domain.repository;

import com.jpa_exemplo.jpa_exemplo.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CidadeRepositoryInterface extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    boolean existsById(Long id);

}
