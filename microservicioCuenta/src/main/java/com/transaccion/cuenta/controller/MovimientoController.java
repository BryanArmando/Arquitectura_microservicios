package com.transaccion.cuenta.controller;

import com.transaccion.cuenta.dto.*;
import com.transaccion.cuenta.service.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @PostMapping("/save")
    public ResponseEntity<MovimientoResponseDto> registrarMovimiento(@RequestBody @Valid MovimientoRequestDto movimientoRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoService.registrarMovimiento(movimientoRequestDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<List<MovimientoResponseDto>> obtenerMovimientosCuenta(@PathVariable("id") Integer id){
        return ResponseEntity.ok(movimientoService.listadoMovimiento(id));
    }
}
