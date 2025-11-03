package com.java.gestaoemprestimopix.dto.Response;

import com.java.gestaoemprestimopix.enums.StatusUsuario;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UsuarioResponse {

    private UUID id;
    private String cpf;
    private String email;
    private LocalDate dataCadastro;
    private StatusUsuario status;
}
