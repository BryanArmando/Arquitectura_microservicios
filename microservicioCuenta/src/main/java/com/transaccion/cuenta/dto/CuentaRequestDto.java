package com.transaccion.cuenta.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO de tipo Cuenta para recibir datos
 */
@Valid
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaRequestDto {

    @NotNull(message = "Identificado de cliente es requerido")
    private Integer clienteId;

    @NotNull(message = "Número de cuenta es requerido")
    private Integer numeroCuenta;

    @NotBlank(message = "Debe especificar el tipo de cuenta")
    private String tipoCuenta;

    @NotNull(message = "Saldo inicial es requerido")
    private Double saldoInicial;

}
