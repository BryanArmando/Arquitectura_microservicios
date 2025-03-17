package com.transaccion.cuenta.controller;

import com.transaccion.cuenta.dto.CuentaRequestDto;
import com.transaccion.cuenta.dto.CuentaResponseDto;
import com.transaccion.cuenta.dto.CuentaUpdateRequestDto;
import com.transaccion.cuenta.service.CuentaService;
import com.transaccion.cuenta.utils.ObtencionIpUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase controller para entidad Cuenta
 * @author BryanArmando
 */
@RestController
@RequestMapping("api/cuenta")
@Tag(name = "Cuenta", description = "Cuenta API")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Operation(summary = "Realiza el guardado de una cuenta",
            description = "Servicio que realiza el registro una cuenta asociada a un clienteId.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto Dto de tipo `Cuenta`. "
                    + "<br>El campo `clienteId` debe ser un cliente activo que lo valida el microservicio cliente ",
                    required = true
            )
    )
    @PostMapping("/save")
    public ResponseEntity<CuentaResponseDto> crearCuenta(@RequestBody @Valid CuentaRequestDto cuentaRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.crearCuenta(cuentaRequestDto));
    }

    @Operation(summary = "Realiza la edición de una cuenta",
            description = "Servicio que realiza la edición de una cuenta.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto Dto de tipo `Cuenta`. ",
                    required = true
            ),
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Código único de cuenta.",
                            required = true,
                            example = "1"
                    )
            }
    )
    @PostMapping("/{id}")
    public ResponseEntity<CuentaResponseDto> editarCuenta(@PathVariable("id") Integer id, @RequestBody @Valid CuentaUpdateRequestDto cuentaUpdateRequestDto){
        return ResponseEntity.ok(cuentaService.editarCuenta(id, cuentaUpdateRequestDto));
    }

    @Operation(summary = "Realiza una eliminación lógica de una cuenta activo",
            description = "Servicio que realiza una eliminación lógica de cuenta cambiando su estado activo (1) a inactivo (0).",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Código único de cuenta a eliminar.",
                            required = true,
                            example = "1"
                    )
            }
    )
    @PostMapping("/eliminar/{id}")
    public ResponseEntity<String> incativarCuenta(@PathVariable("id") Integer id){
        cuentaService.inactivarCuenta(id, ObtencionIpUtils.getIp());
        return ResponseEntity.ok().body("Cuenta desactivada");
    }

    @Operation(summary = "Realiza la búsqueda de una cuenta",
            description = "Servicio que realiza una búsqueda de cuenta mediante si id.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Código único de cuenta a buscar.",
                            required = true,
                            example = "1"
                    )
            }
    )
    @GetMapping("/buscar/{id}")
    public ResponseEntity<CuentaResponseDto> buscarCuentaPorId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(cuentaService.validarExistenciaCuenta(id));
    }
}
