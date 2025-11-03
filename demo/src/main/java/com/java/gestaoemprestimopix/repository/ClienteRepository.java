package com.java.gestaoemprestimopix.repository;


import com.java.gestaoemprestimopix.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
