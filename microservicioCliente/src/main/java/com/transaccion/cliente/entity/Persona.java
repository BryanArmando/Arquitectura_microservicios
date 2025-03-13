package com.transaccion.cliente.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad persona
 * @author BryanArmando
 */
@Entity
@Table(name = "PERSONA")
@Data
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOMBRE")
    private Integer nombre;
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "EDAD")
    private String edad;
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "TELEFONO")
    private String telefono;

}
