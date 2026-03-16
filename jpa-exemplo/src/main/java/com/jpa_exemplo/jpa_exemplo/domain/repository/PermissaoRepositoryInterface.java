package com.jpa_exemplo.jpa_exemplo.domain.repository;

import com.jpa_exemplo.jpa_exemplo.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepositoryInterface extends JpaRepository<Permissao,Long> {
}
