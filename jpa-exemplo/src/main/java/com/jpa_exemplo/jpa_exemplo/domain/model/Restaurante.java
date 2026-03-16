package com.jpa_exemplo.jpa_exemplo.domain.model;

import com.jpa_exemplo.jpa_exemplo.Groups;
import com.jpa_exemplo.jpa_exemplo.core.validation.Multiplo;
import com.jpa_exemplo.jpa_exemplo.domain.exception.NegocioException;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private OffsetDateTime dataAtualizacao;

    @NotNull
    @Column(name = "taxa_frete")
    @PositiveOrZero
    @Multiplo(numero = 5)
    private BigDecimal taxaFrete;

    @Column(nullable = false)
    private Boolean ativo = Boolean.TRUE;

    @Column(nullable = false)
    private Boolean aberto = Boolean.TRUE;

    @Valid
    @ConvertGroup(from = Default.class, to = Groups.CadastroRestaurante.class)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")
    )
    private Set<FormaDePagamento> formasPagamento = new HashSet<>();

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @Embedded
    private Endereco endereco;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name= "restaurante_usuario_responsavel",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios = new ArrayList<>();

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public void adicionarFormaPagamento(FormaDePagamento formaPagamento) {
        this.formasPagamento.add(formaPagamento);
    }

    public void removerFormaPagamento(FormaDePagamento formaPagamento) {
        this.formasPagamento.remove(formaPagamento);
    }

    public void definirFormaPagamento(Collection<FormaDePagamento> formasPagamento) {
        this.formasPagamento.clear();
        this.formasPagamento.addAll(formasPagamento);
    }

    public boolean aceitaFormaPagamento(FormaDePagamento formaPagamento) {
        return this.formasPagamento.contains(formaPagamento);
    }

    public boolean naoAceitaFormaPagamento(FormaDePagamento formaPagamento) {
        return !aceitaFormaPagamento(formaPagamento);
    }

    public Set<FormaDePagamento> getFormasPagamento() {
        return Collections.unmodifiableSet(formasPagamento);
    }

    public void adicionarProduto(Produto produto)
    {
        Objects.requireNonNull(produto);
        if(produto.getId() != null && this.produtos.stream().anyMatch(p -> p.getId().equals(produto.getId()))){
            throw new NegocioException("Produto já associado ao restaurante");
        }

        this.produtos.add(produto);
        produto.definirRestaurante(this);
    }

    public void ativarProduto(Produto produto)
    {
        if (produto == null || !this.produtos.contains(produto)) {
            throw new NegocioException("Produto não pertence a este restaurante.");
        }

        produto.ativar();
    }

    public void desativarProduto(Produto produto) {
        if (produto == null || !this.produtos.contains(produto)) {
            throw new NegocioException("O produto não pertence a este restaurante.");
        }
        produto.desativar();
    }

    public List<Produto> listarProdutosRestaurante()
    {
        return this.getProdutos();
    }

    public void fecharRestaurante()
    {
        if(!this.aberto){
            throw new NegocioException("Este restaurante já esta fechado");
        }
        this.aberto = false;
    }

    public void abrirRestaurante() {
        if (this.aberto) {
            throw new NegocioException("Este restaurante já está aberto");
        }

        this.aberto = true;
    }

    public void adicionarResponsavel(Usuario usuario) {
        if(!this.usuarios.contains(usuario)){
            this.usuarios.add(usuario);
        }
    }

    public void removerResponsavel(Usuario usuario) {
        if(this.usuarios.contains(usuario)){
            this.usuarios.remove(usuario);
        }
    }


}