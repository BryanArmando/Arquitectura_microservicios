package com.transaccion.cuenta.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Entidad movimiento
 * @author BryanArmando
 */
@Entity
@Table(name = "MOVIMIENTOS")
@Data
public class Movimientos extends DatosAuditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MOVIMIENTO")
    private Integer idMovimiento;

    @Column(name = "FECHA_CREACION")
    private Timestamp fechaCreacion;

    @Column(name = "TIPO_MOVIMIENTO")
    private String tipoMovimiento;

    @Column(name = "VALOR")
    private Double valor;

    @Column(name = "SALDO_INICIAL")
    private Double saldoInicial;

    @Column(name = "ID_CUENTA")
    private Integer idCuenta;

    @Column(name = "SALDO_DISPONIBLE")
    private Double saldoDisponible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CUENTA", referencedColumnName = "ID_CUENTA", insertable = false, updatable = false)
    private Cuenta cuenta;

}
