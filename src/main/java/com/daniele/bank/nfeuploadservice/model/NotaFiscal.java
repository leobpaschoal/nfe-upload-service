package com.daniele.bank.nfeuploadservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotaFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nomeArquivoOriginal;
    private String arquivoId;
    private String chave;
    private int numero;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd MM:hh:ss")
    private LocalDateTime dhRegistro;

    private String nomeEmitente;
    private String nomeDestinatario;
    private BigDecimal valorNota;

    @OneToMany
    @JoinColumn(name = "nota_fiscal_id")
    private List<NotaFiscalDuplicata> duplicatas;

    @OneToOne
    private NotaFiscalStatus status;

}
