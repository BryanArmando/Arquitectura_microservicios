package com.transaccion.cliente.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Valid
@Getter
@Setter
public class ClienteRequestDto {
    @NotBlank(message = "El nombre del cliente es requerido")
    private Integer nombre;
    @NotBlank(message = "El género del cliente es requerido")
    private String genero;
    @NotBlank(message = "La edad del cliente es requerida")
    @Size(min = 0, message = "Su edad no puede ser negativa")
    @Digits(integer = 3, fraction = 0, message = "Su edad no puede contener decimales")
    private Integer edad;
    @Size(min = 10, max = 15, message = "La identificacion debe tener entre 10 y 15 caracteres")
    @NotBlank(message = "La identificacion del cliente es requerida")
    private String identificacion;
    @NotBlank(message = "Dirección del cliente es requerida")
    private String direccion;
    @NotBlank(message = "Número de teléfono es requerido")
    private String telefono;
    @NotBlank(message = "Contraseña de cliente es requerida")
    private String contrasenia;
}
