package com.jpa_exemplo.jpa_exemplo.domain.repository;

import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepositoryInterface extends JpaRepository<Produto, Long> {
    boolean existsByNome(String nome);

    Optional<Produto> findProdutoByIdAndRestauranteId(Long produtoId, Long restauranteId);
}
