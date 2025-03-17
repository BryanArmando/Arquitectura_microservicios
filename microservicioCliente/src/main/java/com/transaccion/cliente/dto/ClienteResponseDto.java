package com.transaccion.cliente.dto;

import lombok.*;

/**
 * Clase DTO de tipo cliente para envio de datos
 */
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
