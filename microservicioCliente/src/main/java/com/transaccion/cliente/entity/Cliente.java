package com.transaccion.cliente.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidad cliente
 * @author BryanArmando
 */
@Entity
@Table(name = "CLIENTE")
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona{

    @Column(name = "CONTRASENIA")
    private String contrasenia;

    @Column(name = "ESTADO")
    private String estado;

}
