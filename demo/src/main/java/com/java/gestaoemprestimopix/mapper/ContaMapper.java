package com.java.gestaoemprestimopix.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.java.gestaoemprestimopix.dto.conta.ContaCreateRequest;
import com.java.gestaoemprestimopix.dto.conta.ContaResponse;
import com.java.gestaoemprestimopix.entity.Conta;

public class ContaMapper {

    public static Conta map(final ContaCreateRequest request) {
        return new Conta(
                null,
                request.getNumeroConta(),
                request.getAgencia(),
                request.getSaldo(),
                request.getDataCriacao(),
                request.isStatusConta());
    }

    public static ContaResponse toResponse(final Conta conta) {
        return new ContaResponse(
                conta.getIdConta().toString(),
                conta.getNumeroConta(),
                conta.getAgencia(),
                conta.getSaldo(),
                conta.getDataCriacao(),
                conta.isStatusConta());
    }

    public static List<ContaResponse> toResponseList(final List<Conta> contas) {
        return contas.stream()
                .map(ContaMapper::toResponse)
                .collect(Collectors.toList());
    }

}
