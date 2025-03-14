package com.transaccion.cliente.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Entidad persona
 * @author BryanArmando
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERSONA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "EDAD")
    private Integer edad;

    @Column(name = "IDENTIFICACION", unique = true)
    private String identificacion;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TELEFONO")
    private String telefono;

    public Persona(String nombre, String genero, Integer edad, String identificacion, String direccion, String telefono) {
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
    }

}
