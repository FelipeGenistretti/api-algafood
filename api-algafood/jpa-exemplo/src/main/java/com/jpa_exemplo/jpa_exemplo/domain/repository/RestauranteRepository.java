package com.jpa_exemplo.jpa_exemplo.domain.repository;

import com.jpa_exemplo.jpa_exemplo.domain.model.Restaurante;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryCustom, JpaSpecificationExecutor<Restaurante> {
    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    @EntityGraph(attributePaths = {
            "cozinha",
            "endereco.cidade",
            "endereco.cidade.estado"
    })
    @Query("""
   select r
   from Restaurante r
""")
    List<Restaurante> listarComCozinha();




    List<Restaurante> findByNomeContainingAndTaxaFreteBetween(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);

    @Query("from Restaurante where nome like %:nome% and cozinha.id = :cozinha")
    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

    Optional<Restaurante> findFirstByNomeContaining(String nome);
    Optional<Restaurante> findTop5ByNomeContaining(String nome);

    int countByCozinhaId(Long cozinhaId);

    List<Restaurante> findRestauranteByAtivoTrue();

}
