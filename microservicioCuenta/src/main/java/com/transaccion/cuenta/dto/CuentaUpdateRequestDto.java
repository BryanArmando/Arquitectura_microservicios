package com.transaccion.cuenta.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
