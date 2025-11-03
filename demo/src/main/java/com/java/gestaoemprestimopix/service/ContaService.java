package com.java.gestaoemprestimopix.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.java.gestaoemprestimopix.dto.conta.ContaCreateRequest;
import com.java.gestaoemprestimopix.dto.conta.ContaUpdateRequest;
import com.java.gestaoemprestimopix.dto.conta.ContaResponse;
import com.java.gestaoemprestimopix.entity.Conta;
import com.java.gestaoemprestimopix.repository.ContaRepository;
import com.java.gestaoemprestimopix.mapper.ContaMapper;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }
    
    public ContaResponse criarConta(ContaCreateRequest request) {
        Conta conta = ContaMapper.map(request);
        if (request.getAgencia().isBlank()) {
            conta.setAgencia("0000");
        }
        if (request.getSaldo() == null) {
            conta.setSaldo(new BigDecimal(0));
        }
        conta.setDataCriacao(LocalDateTime.now());
        conta.setStatusConta(true);
        Conta saved = contaRepository.save(conta);
        return contaResponse(saved);
    }

    public ContaResponse buscarIdConta(String idConta) {
        return contaResponse(encontrarContaPorId(idConta));
    }

    public List<ContaResponse> buscarTodasAsContas() {
        List<Conta> contas = contaRepository.findAll();
        return ContaMapper.toResponseList(contas);
    }

    public ContaResponse atualizarConta(String idConta, ContaUpdateRequest request) {
        Conta conta = encontrarContaPorId(idConta);
        conta.setSaldo(new BigDecimal(request.getSaldo().longValue()));
        Conta saved = contaRepository.save(conta);
        return contaResponse(saved);
    }

    public ContaResponse desativarConta(String idConta) {
        Conta conta = encontrarContaPorId(idConta);
        conta.setStatusConta(false);
        Conta saved = contaRepository.save(conta);
        return contaResponse(saved);
    }

    public ContaResponse reativarConta(String idConta) {
        Conta conta = encontrarContaPorId(idConta);
        conta.setStatusConta(true);
        Conta saved = contaRepository.save(conta);
        return contaResponse(saved);
    }

    private Conta encontrarContaPorId(final String idConta) {
        Conta conta = contaRepository.findById(UUID.fromString(idConta))
                .orElseThrow(() -> new RuntimeException("Conta não encontrada."));
        return conta;
    }

    private ContaResponse contaResponse(final Conta conta) {
        return new ContaResponse(
            conta.getIdConta().toString(),
            conta.getNumeroConta(),
            conta.getAgencia(),
            conta.getSaldo(),
            conta.getDataCriacao(),
            conta.isStatusConta()
        );
    }

}
