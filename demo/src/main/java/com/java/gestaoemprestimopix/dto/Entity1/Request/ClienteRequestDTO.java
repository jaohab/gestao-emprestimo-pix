package com.java.gestaoemprestimopix.dto.Entity1.Request;

import com.java.gestaoemprestimopix.enums.StatusCliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClienteRequestDTO(@NotBlank(message = "Nome é obrigatório") String nome,
                                @NotBlank(message = "cpf é obrigatório")  String cpf,
                                @NotBlank(message = "endereco é obrigatório")String endereco,
                                @NotBlank(message = "Status do cliente é obrigatório") StatusCliente statusCliente,
                                @NotNull(message = "dt.nascimento é obrigatória") LocalDate dataNascimento,
                                @NotNull(message = "dt.cadastro é obrigatória") LocalDateTime dataCadastro,
                                @NotNull(message = "renda mensal é obrigatória") BigDecimal rendaMensal
                                ) {
}
