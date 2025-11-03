package com.java.gestaoemprestimopix.entity;
import com.java.gestaoemprestimopix.enums.StatusCliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String cpf;

    private String endereco;

    private StatusCliente statusCliente;

    private LocalDate dataNascimento;

    private LocalDateTime dataCadastro;

    private BigDecimal rendaMensal;

    private LocalDate dataAtualizacao;

    @OneToMany(mappedBy = "cliente")
    private list<Emprestimo> emprestimos;

    @OneToMany(mappedBy = "cliente")
    private List<Simulacao> simulacoes;

    @OneToOne(mappedBy = "cliente")
    private Login login;

    @OneToOne(mappedBy = "cliente")
    private Conta conta;
}
