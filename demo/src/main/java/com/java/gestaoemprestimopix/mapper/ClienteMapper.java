package com.java.gestaoemprestimopix.mapper;
import com.java.gestaoemprestimopix.dto.Entity1.Request.ClienteRequestDTO;
import com.java.gestaoemprestimopix.dto.Entity1.Response.ClienteResponseDTO;
import com.java.gestaoemprestimopix.entity.Cliente;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequestDTO dto){

        Cliente cliente = new Cliente();
        cliente.setCpf(dto.cpf());
        cliente.setDataCadastro(dto.dataCadastro());
        cliente.setEndereco(dto.endereco());
        cliente.setNome(dto.nome());
        cliente.setDataAtualizacao(LocalDate.now());
        cliente.setStatusCliente(dto.statusCliente());
        cliente.setDataNascimento(dto.dataNascimento());

        return cliente;
    }

    public ClienteResponseDTO toResponseDTO(Cliente cliente){
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEndereco(),
                cliente.getStatusCliente(),
                cliente.getDataCadastro(),
                cliente.getRendaMensal(),
                cliente.getDataAtualizacao()
        );
    }
}
