package com.transaccion.cuenta.dto.Cliente;

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
