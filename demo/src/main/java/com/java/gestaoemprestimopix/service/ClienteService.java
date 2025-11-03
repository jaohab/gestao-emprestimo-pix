package com.java.gestaoemprestimopix.service;

import com.java.gestaoemprestimopix.dto.Entity1.Request.ClienteRequestDTO;
import com.java.gestaoemprestimopix.dto.Entity1.Response.ClienteResponseDTO;
import com.java.gestaoemprestimopix.entity.Cliente;
import com.java.gestaoemprestimopix.enums.StatusCliente;
import com.java.gestaoemprestimopix.exception.ClientStatusNotFound;
import com.java.gestaoemprestimopix.exception.ClienteNotFound;
import com.java.gestaoemprestimopix.mapper.ClienteMapper;
import com.java.gestaoemprestimopix.repository.ClienteRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClienteService {

    ClienteMapper clienteMapper;
    ClienteRepository clienteRepository;

    public List<ClienteResponseDTO> listarClientes(){
        return clienteRepository.findAll().stream().map(cliente -> clienteMapper.toResponseDTO(cliente)).toList();
    }

    public ClienteResponseDTO buscarCliente(UUID uuid){
        Cliente cliente =  clienteRepository.findById(uuid).orElseThrow(() -> new ClienteNotFound("Cliene não encontrado"));
        return clienteMapper.toResponseDTO(cliente);
    }

    public ClienteResponseDTO cadastrarCliente(@Valid ClienteRequestDTO dto){

        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente saved = clienteRepository.save(cliente);

        return clienteMapper.toResponseDTO(saved);
    }

    public ClienteResponseDTO atualizarCadastro(UUID uuid, ClienteRequestDTO dto){

        Cliente cliente = clienteRepository.findById(uuid).orElseThrow(() -> new ClienteNotFound("Cliente não encontrado"));
        cliente.setCpf(dto.cpf());
        cliente.setDataCadastro(dto.dataCadastro());
        cliente.setEndereco(dto.endereco());
        cliente.setNome(dto.nome());
        cliente.setDataAtualizacao(LocalDate.now());
        cliente.setStatusCliente(dto.statusCliente());
        cliente.setDataNascimento(dto.dataNascimento());

        clienteRepository.save(cliente);

        return clienteMapper.toResponseDTO(cliente);
    }

    public void atualizarStatus(UUID uuid, StatusCliente statusCliente){
        Cliente cliente = clienteRepository.findById(uuid).orElseThrow(() -> new ClienteNotFound("Cliente não encontrado"));
        List<StatusCliente> listStatus = Arrays.stream(StatusCliente.values()).toList();
        if (!listStatus.contains(statusCliente)){
            throw new ClientStatusNotFound("Status inválido");
        }
        cliente.setStatusCliente(statusCliente);
        clienteRepository.save(cliente);
    }

    public StatusCliente verificarStatus(UUID uuid){
        Cliente cliente = clienteRepository.findById(uuid).orElseThrow(()-> new ClienteNotFound("Cliente não encontrado"));
        return cliente.getStatusCliente();
    }

}
