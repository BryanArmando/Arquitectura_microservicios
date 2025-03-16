package com.transaccion.cuenta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoResponseDto {
    private Timestamp fechaCreacion;

    private String tipoMovimiento;

    private Double valor;

    private Double saldoInicial;

    private Double saldoDisponible;

    private Integer idCuenta;

}
