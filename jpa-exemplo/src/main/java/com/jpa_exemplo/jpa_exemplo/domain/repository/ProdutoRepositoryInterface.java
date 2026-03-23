package com.jpa_exemplo.jpa_exemplo.domain.repository;

import com.jpa_exemplo.jpa_exemplo.domain.model.FotoProduto;
import com.jpa_exemplo.jpa_exemplo.domain.model.Pedido;
import com.jpa_exemplo.jpa_exemplo.domain.model.Produto;
import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositoryInterface extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries {
    boolean existsByNome(String nome);

    Optional<Produto> findProdutoByIdAndRestauranteId(Long produtoId, Long restauranteId);

    @Query("from Produto p where p.restaurante = :restaurante and p.ativo = true")
    List<Produto> findAtivosByRestaurante(Restaurante restaurante);


    List<Produto> findAllByRestaurante(Restaurante restaurante);

    @Query("select f from FotoProduto f join f.produto p where p.restaurante.id = :restauranteId f.produtoId = :produtoId")
    Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);

}
