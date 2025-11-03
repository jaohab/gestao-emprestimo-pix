package com.java.gestaoemprestimopix.repository;

import com.java.gestaoemprestimopix.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    // Busca usuário pelo CPF
    Optional<Usuario> findByCpf(String cpf);

    // Busca usuário pelo e-mail (opcional)
    Optional<Usuario> findByEmail(String email);

    // Verifica se já existe um CPF cadastrado
    boolean existsByCpf(String cpf);

    // Verifica se já existe um e-mail cadastrado
    boolean existsByEmail(String email);
}