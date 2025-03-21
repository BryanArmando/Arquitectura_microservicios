package com.transaccion.cuenta.dto.reportes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Clase para construccion de estado de cuenta abarca movimientos
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCuentaMovimientosDto {

    private Timestamp fechaCreacion;

    private Double saldoInicial;

    private Double valor;

    private String tipoMovimiento;

    private String saldoDisponible;
}
