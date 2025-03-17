package com.transaccion.cuenta.controller;

import com.transaccion.cuenta.dto.*;
import com.transaccion.cuenta.dto.reportes.EstadoCuentaCabeceraReporteDto;
import com.transaccion.cuenta.service.MovimientoService;
import com.transaccion.cuenta.service.reportes.EstadoCuentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase controller para entidad Movimientos
 * @author BryanArmando
 */
@RestController
@RequestMapping("api/movimientos")
@Tag(name = "Movimientos", description = "Movimientos API")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private EstadoCuentaService estadoCuentaService;

    @Operation(summary = "Realiza el guardado de movimientos",
            description = "Servicio que realiza el registro de un movimiento relacionado a una cuenta.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto Dto de tipo `Movimiento`, "
                    + "con datos del movimiento(`idCuenta`, `valor`) a guardar.<br>"
                    + "El campo `valor` al ser negativo se considera un RETIRO y al ser positivo es un DEPOSITO ",
                    required = true
            )
    )
    @PostMapping("/save")
    public ResponseEntity<MovimientoResponseDto> registrarMovimiento(@RequestBody @Valid MovimientoRequestDto movimientoRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoService.registrarMovimiento(movimientoRequestDto));
    }

    @Operation(summary = "Realiza búsqueda de movimientos",
            description = "Servicio que realiza la búsqueda de los movimientos realizados en la cuenta.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Código único de cuenta.",
                            required = true,
                            example = "1"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<List<MovimientoResponseDto>> obtenerMovimientosCuenta(@PathVariable("id") Integer id){
        return ResponseEntity.ok(movimientoService.listadoMovimiento(id));
    }

    /**
     * Metodo que permite obtener el estado de cuenta del usuario en un rango de fechas
     * @param clienteId
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    @Operation(summary = "Realiza la generación de reportes para estado de cuenta",
            description = "Servicio que realiza la generacion de un estado de cuenta de un cliente en un rango de fechas.",
            parameters = {
                    @Parameter(
                            name = "clienteId",
                            description = "Código único de cliente.",
                            required = true,
                            example = "1"
                    ),
                    @Parameter(
                            name = "fechaInicio",
                            description = "Fecha inicial de filtro.",
                            required = true,
                            example = "2025-03-01"
                    ),
                    @Parameter(
                            name = "fechaFin",
                            description = "Fecha final de filtro.",
                            required = true,
                            example = "2025-04-01"
                    )
            }
    )
    @GetMapping("/reportes")
    public ResponseEntity<EstadoCuentaCabeceraReporteDto> obtenerEstadoDeCuenta(
            @RequestParam("clienteId") Integer clienteId,
            @RequestParam("fechaInicio") LocalDate fechaInicio,
            @RequestParam("fechaFin") LocalDate fechaFin
    ){
        return ResponseEntity.ok(estadoCuentaService.obtenerReporteEstadoCuenta(clienteId, fechaInicio, fechaFin));
    }
}
