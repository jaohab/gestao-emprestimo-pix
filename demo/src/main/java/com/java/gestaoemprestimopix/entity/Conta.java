package com.java.gestaoemprestimopix.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idConta;
    @Column(unique = true, nullable = false)
    private Long numeroConta;
    @Column(nullable = false)
    private String agencia;
    private BigDecimal saldo;
    private LocalDateTime dataCriacao;
    private boolean statusConta;

    // Constructors
    public Conta() {
    }

    public Conta(UUID idConta, Long numeroConta, String agencia, BigDecimal saldo, LocalDateTime dataCriacao,
            boolean statusConta) {
        this.idConta = idConta;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = saldo;
        this.dataCriacao = dataCriacao;
        this.statusConta = statusConta;
    }

    // Getters and Setters
    public UUID getIdConta() {
        return idConta;
    }

    public void setIdConta(UUID idConta) {
        this.idConta = idConta;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isStatusConta() {
        return statusConta;
    }

    public void setStatusConta(boolean statusConta) {
        this.statusConta = statusConta;
    }

}
