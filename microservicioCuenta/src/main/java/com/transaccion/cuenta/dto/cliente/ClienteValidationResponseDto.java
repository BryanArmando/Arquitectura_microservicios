package com.transaccion.cuenta.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO de tipo cliente para respuesta a kafka
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteValidationResponseDto {
    private String requestId;
    private ClienteResponseDto clienteResponseDto;
    private Integer clienteId;
    private String errorMessage;
}
