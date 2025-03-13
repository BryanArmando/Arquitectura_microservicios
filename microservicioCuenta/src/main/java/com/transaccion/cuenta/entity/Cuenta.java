package com.transaccion.cuenta.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad cuenta
 * @author BryanArmando
 */
@Entity
@Table(name = "CUENTA")
@Data
public class Cuenta extends DatosAuditoria{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CUENTA")
    private Integer idCuenta;

    @Column(
            name = "NUMERO_CUENTA",
            nullable = false
    )
    private Integer numeroCuenta;

    @Column(
            name = "TIPO_CUENTA",
            nullable = false
    )
    private String tipoCuenta;

    @Column(
            name = "SALDO_INICIAL",
            nullable = false
    )
    private Double saldoInicial;

    @Column(
            name = "ESTADO",
            nullable = false
    )
    private String estado;
}
