package com.transaccion.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteValidationResponseDto {
    private String requestId;
    private ClienteResponseDto clienteResponseDto;
    private Integer clienteId;
    private String errorMessage;
}
