package com.java.gestaoemprestimopix.entity.model;

import com.java.gestaoemprestimopix.entity.enums.RiscoEmprestimo;
import com.java.gestaoemprestimopix.entity.enums.StatusEmprestimo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "emprestimos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)

public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long idEmprestimo;

    @EqualsAndHashCode.Include
    @Column(name = "uuid", updatable = false, nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "valor_emprestimo", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorEmprestimo;

    @Column(name = "numero_parcelas", nullable = false)
    private Integer numeroParcelas;

    @Column(name = "valor_total_emprestimo", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotalEmprestimo;

    @Column(name = "risco_emprestimo", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private RiscoEmprestimo riscoEmprestimo;

    @Column(name = "status_emprestimo", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private StatusEmprestimo statusEmprestimo;

    @CreatedDate
    @Column(name = "data_contrato_emprestimo", nullable = false, updatable = false)
    private LocalDate dataContratoEmprestimo;

    @Column(name = "taxa_juros", nullable = false, precision = 5, scale = 4)
    private BigDecimal taxaJuros;

    @Column(name = "valor_parcela", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorParcela;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    @ToString.Exclude
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conta", nullable = false)
    @ToString.Exclude
    private Conta conta;

    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AgendamentoPix> agendamento = new ArrayList<>();


    public void adicionarAgendamento(AgendamentoPix agendamento) {
        if (this.agendamento == null){
            this.agendamento = new ArrayList<>();
        }
        this.agendamento.add(agendamento);
        agendamento.setEmprestimo(this);
    }

    @PrePersist
    public void prePersist() {
        if (uuid == null) { uuid = UUID.randomUUID();}
    }

    public void setStatusEmprestimo(StatusEmprestimo statusEmprestimo) {
        this.statusEmprestimo = statusEmprestimo;
    }

    public void atribuirCliente(Cliente cliente){
        this.cliente = cliente;
    }
}
