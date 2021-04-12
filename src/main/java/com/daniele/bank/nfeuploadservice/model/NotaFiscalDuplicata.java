package com.daniele.bank.nfeuploadservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotaFiscalDuplicata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int parcela;
    private BigDecimal valor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate vencimento;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "nota_fiscal_id", insertable = false, updatable = false)
    private NotaFiscal notaFiscal;
}