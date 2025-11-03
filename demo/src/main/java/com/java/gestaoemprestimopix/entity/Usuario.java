package com.java.gestaoemprestimopix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
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

    @NotBlank(message = "O cpf do cliente é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres")
    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    @NotBlank(message = "A descrição não pode ser vazia.")
    @Size(max = 150, message = "A descrição pode ter no máximo 150 caracteres.")
    private String descricao;

    @NotNull(message = "O campo ativo é obrigatório.")
    private Boolean ativo;

    @NotNull(message = "A data de criação é obrigatória.")
    private java.time.LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status é obrigatório.")
    private StatusEntity status;
}
