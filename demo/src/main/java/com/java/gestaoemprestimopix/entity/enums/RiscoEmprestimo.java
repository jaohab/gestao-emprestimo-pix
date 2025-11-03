package com.java.gestaoemprestimopix.entity.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum RiscoEmprestimo {

    BAIXO(new BigDecimal("0.015"), new BigDecimal("0.10")),
    MEDIO(new BigDecimal("0.030"), new BigDecimal("0.20")),
    ALTO(new BigDecimal("0.050"), new BigDecimal("0.30"));

    private final BigDecimal taxaJuros;
    private final BigDecimal comprometimentoMaximo;

    RiscoEmprestimo(BigDecimal taxaJuros, BigDecimal comprometimentoMaximo) {
        this.taxaJuros = taxaJuros;
        this.comprometimentoMaximo = comprometimentoMaximo;
    }
}


