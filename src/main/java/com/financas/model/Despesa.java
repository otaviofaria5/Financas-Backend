package com.financas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "despesas")
@Getter @Setter @NoArgsConstructor
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A descrição é obrigatória")
    @Column(nullable = false)
    private String descricao;

    @NotNull(message = "A categoria é obrigatória")
    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotNull(message = "O valor total é obrigatório")
    @Positive(message = "O valor total deve ser maior que zero")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @NotBlank
    @Pattern(regexp = "^(0[1-9]|1[0-2])/\\d{4}$", message = "Use o formato MM/AAAA")
    @Column(nullable = false, length = 7)
    private String mesReferencia;

    @NotNull(message = "O pagador é obrigatório")
    @ManyToOne(optional = false)
    @JoinColumn(name = "pagador_id")
    private Pessoa pagador;

    @ManyToOne
    @JoinColumn(name = "participante_id")
    private Pessoa participante;

    @NotNull
    @DecimalMin("0.00") @DecimalMax("100.00")
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal percentualPagador = new BigDecimal("100.00");

    @NotNull
    @DecimalMin("0.00") @DecimalMax("100.00")
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal percentualParticipante = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal valorPagador;

    @Column(precision = 12, scale = 2)
    private BigDecimal valorParticipante;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDespesa status = StatusDespesa.PENDENTE;
}