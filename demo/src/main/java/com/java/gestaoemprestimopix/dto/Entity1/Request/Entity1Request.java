package com.java.gestaoemprestimopix.dto.Entity1.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO responsável por representar os dados de entrada (Request)
 * ao criar ou atualizar uma Entity1.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity1Request {

    @NotBlank(message = "O campo 'name' não pode estar vazio.")
    @Size(max = 50, message = "O campo 'name' deve ter no máximo 50 caracteres.")
    private String name;

    @NotNull(message = "O campo 'value' não pode ser nulo.")
    private Double value;
}
