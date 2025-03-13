package com.transaccion.cliente.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad cliente
 * @author BryanArmando
 */
@Entity
@Table(name = "CLIENTE")
@Data
public class Cliente extends Persona{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteId;

    @Column(name = "CONTRASENIA")
    private Integer contrasenia;

    @Column(name = "ESTADO")
    private String estado;

}
