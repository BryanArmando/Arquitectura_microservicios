package com.transaccion.cuenta.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO de tipo cliente para peticiones
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteValidationRequestDto {

    private String requestId;

    private Integer clienteId;
}
