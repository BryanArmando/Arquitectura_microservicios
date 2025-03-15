package com.transaccion.cuenta.controller;

import com.transaccion.cuenta.dto.CuentaRequestDto;
import com.transaccion.cuenta.dto.CuentaResponseDto;
import com.transaccion.cuenta.dto.CuentaUpdateRequestDto;
import com.transaccion.cuenta.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clientes")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping("/save")
    public ResponseEntity<CuentaResponseDto> crearCuenta(@RequestBody @Valid CuentaRequestDto cuentaRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.crearCuenta(cuentaRequestDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<CuentaResponseDto> editarCuenta(@PathVariable("id") Integer id, @RequestBody @Valid CuentaUpdateRequestDto cuentaUpdateRequestDto){
        return ResponseEntity.ok(cuentaService.editarCuenta(id, cuentaUpdateRequestDto));
    }

    @PostMapping("/eliminar/{id}")
    public ResponseEntity<String> incativarCuenta(@PathVariable("id") Integer id){
        cuentaService.inactivarCuenta(id);
        return ResponseEntity.ok().body("Cuenta desactivada");
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<CuentaResponseDto> buscarCuentaPorId(@PathVariable("id") Integer id){
        CuentaResponseDto cuentaResponseDto = cuentaService.validarExistenciaCuenta(id);
        return ResponseEntity.ok(cuentaService.validarExistenciaCuenta(id));
    }
}
