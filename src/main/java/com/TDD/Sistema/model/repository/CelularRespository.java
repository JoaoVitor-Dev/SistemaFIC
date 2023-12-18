package com.TDD.Sistema.model.repository;

import com.TDD.Sistema.model.entity.Celular;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CelularRespository extends JpaRepository<Celular, Long> {
}
