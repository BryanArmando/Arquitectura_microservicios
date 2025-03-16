package com.transaccion.cliente.dto;

import jakarta.validation.Valid;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDto {

    private Integer id;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String estado;
}
