package com.transaccion.cuenta.dto.reportes;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase general para construcción de reportes de estado de cuenta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCuentaCabeceraReporteDto {

    private String nombre;
    private String identificacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    List<EstadoCuentaTipoCuentaDto> estadoCuentaTipoCuentaDtoList;

    @Transient
    private String observacion;



}
