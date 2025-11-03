package com.java.gestaoemprestimopix.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.gestaoemprestimopix.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, UUID> {

}
