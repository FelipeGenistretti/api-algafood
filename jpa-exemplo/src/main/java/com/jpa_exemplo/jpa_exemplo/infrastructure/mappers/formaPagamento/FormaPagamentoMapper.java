package com.jpa_exemplo.jpa_exemplo.infrastructure.mappers.formaPagamento;

import com.jpa_exemplo.jpa_exemplo.domain.model.FormaDePagamento;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FormaDePagamento.Request.FormaDePagamentoRequestDTO;
import com.jpa_exemplo.jpa_exemplo.infrastructure.dtos.FormaDePagamento.Response.FormaDePagamentoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface FormaPagamentoMapper {

    FormaDePagamento toEntity(FormaDePagamentoRequestDTO dto);

    FormaDePagamentoResponseDTO toResponse(FormaDePagamento formaPagamento);

    void updateEntity(FormaDePagamentoRequestDTO dto, @MappingTarget FormaDePagamento formaPagamento);

    Set<FormaDePagamentoResponseDTO> toColletionResponse(Collection<FormaDePagamento> entidades);
}