package com.transaccion.cuenta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO para envio de datos de tipo cuenta
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaResponseDto {

    private Integer idCuenta;

    private Integer numeroCuenta;

    private String tipoCuenta;

    private Double saldoInicial;

    private Integer clienteId;
}
