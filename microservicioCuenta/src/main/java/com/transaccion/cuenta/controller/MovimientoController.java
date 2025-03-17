package com.transaccion.cuenta.controller;

import com.transaccion.cuenta.dto.*;
import com.transaccion.cuenta.dto.reportes.EstadoCuentaCabeceraReporteDto;
import com.transaccion.cuenta.service.MovimientoService;
import com.transaccion.cuenta.service.reportes.EstadoCuentaService;
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
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private EstadoCuentaService estadoCuentaService;

    @PostMapping("/save")
    public ResponseEntity<MovimientoResponseDto> registrarMovimiento(@RequestBody @Valid MovimientoRequestDto movimientoRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoService.registrarMovimiento(movimientoRequestDto));
    }

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
    @GetMapping("/reportes")
    public ResponseEntity<EstadoCuentaCabeceraReporteDto> obtenerEstadoDeCuenta(
            @RequestParam("clienteId") Integer clienteId,
            @RequestParam("fechaInicio") LocalDate fechaInicio,
            @RequestParam("fechaFin") LocalDate fechaFin
    ){
        return ResponseEntity.ok(estadoCuentaService.obtenerReporteEstadoCuenta(clienteId, fechaInicio, fechaFin));
    }
}
