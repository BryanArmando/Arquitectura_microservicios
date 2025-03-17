package com.transaccion.cliente.controller;

import com.transaccion.cliente.dto.ClienteRequestDto;
import com.transaccion.cliente.dto.ClienteResponseDto;
import com.transaccion.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase controlador para entidad Cliente
 * @author BryanArmando
 */
@RestController
@RequestMapping("api/cliente")
@CrossOrigin(value = "*")
@Tag(name = "Cliente", description = "Cliente API")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtiene una lista de clientes activos",
            description = "Servicio que obtiene una listado de clientes activos"
    )
    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> buscarClientesActivos(){
        return ResponseEntity.ok(clienteService.clientes());
    }

    @Operation(summary = "Realiza el guardado de clientes",
            description = "Servicio que guarda un cliente y retorna los datos del cliente guardado",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto Dto de tipo `Cliente`, "
                    + "con datos de cliente a guardar.<br>",
                    required = true
            )
    )
    @PostMapping("/save")
    public ResponseEntity<ClienteResponseDto> crearCliente(@RequestBody @Valid ClienteRequestDto clienteRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.guardarCliente(clienteRequestDto));
    }

    @Operation(summary = "Realiza una edicion de un cliente activo",
            description = "Servicio que realiza la edición de un cliente mediante un id y sus respectivos datos a editar.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Código único de cliente a editar.",
                            required = true,
                            example = "1"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto Dto de tipo `Cliente`, "
                    + "con los datos a editar.<br>",
                    required = true
            )
    )
    @PostMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> editarCliente(@PathVariable("id") Integer id, @RequestBody @Valid ClienteRequestDto clienteRequestDto){
        return ResponseEntity.ok(clienteService.editarDatosCliente(id, clienteRequestDto));
    }

    @Operation(summary = "Realiza una eliminación lógica de un cliente activo",
            description = "Servicio que realiza una eliminación lógica del cliente cambiando su estado activo (1) a inactivo (0).",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Código único de cliente a eliminar.",
                            required = true,
                            example = "1"
                    )
            }
    )
    @PostMapping("/eliminar/{id}")
    public ResponseEntity<String> inactivarCliente(@PathVariable("id") Integer id){
        clienteService.inactivarCliente(id);
        return ResponseEntity.ok().body("Cliente desactivado");
    }

}
