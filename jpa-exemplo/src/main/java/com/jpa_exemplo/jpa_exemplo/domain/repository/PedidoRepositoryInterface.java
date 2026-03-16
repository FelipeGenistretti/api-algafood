package com.jpa_exemplo.jpa_exemplo.domain.repository;

import com.jpa_exemplo.jpa_exemplo.domain.model.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepositoryInterface extends JpaRepository<Pedido, Long> {

    @Override
    @EntityGraph(attributePaths = {
            "itensPedido",
            "itensPedido.produto",
            "cliente",
            "restaurante",
            "formaPagamento",
            "enderecoEntrega.cidade"
    })
    List<Pedido> findAll();

    @Query("""
    select distinct p
    from Pedido p
    left join fetch p.itensPedido ip
    left join fetch ip.produto
""")
    List<Pedido> buscarTodosComItens();
}
