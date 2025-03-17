package com.transaccion.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO de tipo Cliente para petición de datos en kafka
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteValidationRequestDto {

    private String requestId;

    private Integer clienteId;
}
