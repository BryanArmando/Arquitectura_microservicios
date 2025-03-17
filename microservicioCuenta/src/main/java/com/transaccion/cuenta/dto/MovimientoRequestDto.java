package com.transaccion.cuenta.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Clase DTO de tipo Movimiento para petición de datos.
 */
@Valid
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoRequestDto {

    @NotNull(message = "Identificador de cuenta es requerido")
    private Integer idCuenta;

    @NotNull(message = "El valor a registrar es requerido")
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "El valor ingresado debe tener como máximo 2 decimales")
    private BigDecimal valor;

}
