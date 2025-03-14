package com.transaccion.cliente.controller;

import com.transaccion.cliente.dto.ClienteRequestDto;
import com.transaccion.cliente.dto.ClienteResponseDto;
import com.transaccion.cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cliente")
@CrossOrigin(value = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> buscarClientesActivos(){
        return ResponseEntity.ok(clienteService.clientes());
    }

    @PostMapping("/save")
    public ResponseEntity<ClienteResponseDto> crearCliente(@RequestBody @Valid ClienteRequestDto clienteRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.guardarCliente(clienteRequestDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> editarCliente(@PathVariable("id") Integer id, @RequestBody @Valid ClienteRequestDto clienteRequestDto){
        return ResponseEntity.ok(clienteService.editarDatosCliente(id, clienteRequestDto));
    }

    @PostMapping("/eliminar/{id}")
    public ResponseEntity<String> inactivarCliente(@PathVariable("id") Integer id){
        clienteService.inactivarCliente(id);
        return ResponseEntity.ok().body("Cliente desactivado");
    }

}
