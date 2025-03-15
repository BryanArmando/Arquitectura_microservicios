package com.transaccion.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteValidationRequestDto {

    private String requestId;

    private Integer clienteId;
}
