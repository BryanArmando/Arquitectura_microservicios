package com.transaccion.cliente.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad cliente
 * @author BryanArmando
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENTE")
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona{

    @Column(name = "CONTRASENIA")
    private String contrasenia;

    @Column(name = "ESTADO")
    private String estado;

    public Cliente(String nombre, String genero, Integer edad, String identificacion, String direccion, String telefono, String contrasenia, String estado) {
        super(nombre, genero, edad, identificacion, direccion, telefono);
        this.contrasenia = contrasenia;
        this.estado = estado;
    }
}
