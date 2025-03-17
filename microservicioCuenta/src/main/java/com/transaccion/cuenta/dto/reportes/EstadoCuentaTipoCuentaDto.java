package com.transaccion.cuenta.dto.reportes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Clase para construcción de estado de cuenta que contiene cuenta y sus movimientos
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCuentaTipoCuentaDto {
    private BigDecimal saldoDisponible;
    private Integer numeroCuenta;
    private String tipoCuenta;

    private List<EstadoCuentaMovimientosDto> movimientos;

}
