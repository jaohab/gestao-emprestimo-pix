package com.java.gestaoemprestimopix.dto.Entity1.Response;

import com.java.gestaoemprestimopix.enums.StatusCliente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


public record ClienteResponseDTO(UUID id,
                                 String nome,
                                 String cpf,
                                 String endereco,
                                 StatusCliente statusCliente,
                                 LocalDateTime dataCadastro,
                                 BigDecimal rendaMensal,
                                 LocalDate dataAtualizacao
                                 ) {


}
