package com.transaccion.cuenta.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO de tipo cuenta para respuestas
 */
@Valid
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaUpdateRequestDto {

    private Integer numeroCuenta;

    private String tipoCuenta;

    private Double saldoInicial;
}
