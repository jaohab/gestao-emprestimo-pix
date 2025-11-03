package com.java.gestaoemprestimopix.entity;

import com.java.gestaoemprestimopix.enums.StatusUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O CPF do cliente é obrigatório.")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres.")
    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    @Email(message = "O e-mail informado é inválido.")
    @Column(unique = true, nullable = true)
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    @NotNull(message = "A data de cadastro é obrigatória.")
    private LocalDate dataCadastro;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status é obrigatório.")
    private StatusUsuario status;
}
