package com.transaccion.cliente.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponseDto {

    private Integer id;
    private Integer nombre;
    private String genero;
    private String edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String estado;
}
