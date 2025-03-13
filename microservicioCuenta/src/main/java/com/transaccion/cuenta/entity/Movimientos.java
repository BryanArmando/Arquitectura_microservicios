package com.transaccion.cuenta.entity;

import jakarta.persistence.*;
import lombok.Data;

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
    private Date fechaCreacion;

    @Column(name = "TIPO_MOVIMIENTO")
    private String tipoMovimiento;

    @Column(name = "VALOR")
    private Double valor;

    @Column(name = "SALDO")
    private Double saldo;

}
