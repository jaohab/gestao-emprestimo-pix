package com.java.gestaoemprestimopix.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.gestaoemprestimopix.dto.conta.ContaCreateRequest;
import com.java.gestaoemprestimopix.dto.conta.ContaUpdateRequest;
import com.java.gestaoemprestimopix.dto.conta.ContaResponse;
import com.java.gestaoemprestimopix.service.ContaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ContaResponse criarConta(@RequestBody @Valid ContaCreateRequest request) {
        final ContaResponse response = contaService.criarConta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response).getBody();
    }
    
    @GetMapping
    public List<ContaResponse> buscarTodasAsContas() {
        final List<ContaResponse> contas = contaService.buscarTodasAsContas();
        return ResponseEntity.status(HttpStatus.OK).body(contas).getBody();
    }

    @GetMapping("/{idConta}")
    public ContaResponse buscarContaPorId(@PathVariable String idConta) {
        final ContaResponse response = contaService.buscarIdConta(idConta);
        return ResponseEntity.status(HttpStatus.OK).body(response).getBody();
    }

    @PatchMapping("/{idConta}")
    public ContaResponse atualizarConta(@PathVariable String idConta, @RequestBody @Valid ContaUpdateRequest request) {
        final ContaResponse response = contaService.atualizarConta(idConta, request);
        return ResponseEntity.status(HttpStatus.OK).body(response).getBody();
    }

    @PatchMapping("/desativar/{idConta}")
    public ContaResponse desativarConta(@PathVariable String idConta) {
        final ContaResponse response = contaService.desativarConta(idConta);
        return ResponseEntity.status(HttpStatus.OK).body(response).getBody();
    }

    @PatchMapping("/reativar/{idConta}")
    public ContaResponse reativarConta(@PathVariable String idConta) {
        final ContaResponse response = contaService.reativarConta(idConta);
        return ResponseEntity.status(HttpStatus.OK).body(response).getBody();
    }

}
