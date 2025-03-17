package com.transaccion.cuenta.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO de tipo Cliente para respuestas kafka
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
