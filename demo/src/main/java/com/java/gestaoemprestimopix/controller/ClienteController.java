package com.java.gestaoemprestimopix.controller;

import com.java.gestaoemprestimopix.dto.Entity1.Request.ClienteRequestDTO;
import com.java.gestaoemprestimopix.dto.Entity1.Response.ClienteResponseDTO;
import com.java.gestaoemprestimopix.enums.StatusCliente;
import com.java.gestaoemprestimopix.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> getAll(){
        List<ClienteResponseDTO> clientes = clienteService.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable UUID uuid){
        ClienteResponseDTO dto = clienteService.buscarCliente(uuid);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> updateClient(@PathVariable UUID uuid,@RequestBody ClienteRequestDTO dto){
        ClienteResponseDTO responseDTO = clienteService.atualizarCadastro(uuid,dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> createClient(@RequestBody ClienteRequestDTO dto){
        ClienteResponseDTO responseDTO = clienteService.cadastrarCliente(dto);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStatus(@RequestBody StatusCliente status,@PathVariable UUID uuid){
        clienteService.atualizarStatus(uuid,status);
        return new ResponseEntity<String>("Status do cliente atualizado com sucesso",HttpStatus.OK);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<StatusCliente> verifyStatus(@PathVariable UUID uuid){
        StatusCliente statusCliente = clienteService.verificarStatus(uuid);
        return new ResponseEntity<>(statusCliente,HttpStatus.OK);
    }


}
