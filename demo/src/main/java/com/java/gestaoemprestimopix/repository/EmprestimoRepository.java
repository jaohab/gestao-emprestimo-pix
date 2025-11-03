package com.java.gestaoemprestimopix.repository;

import com.java.gestaoemprestimopix.entity.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
