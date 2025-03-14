package com.transaccion.cliente.service;

import com.transaccion.cliente.dto.ClienteRequestDto;
import com.transaccion.cliente.dto.ClienteResponseDto;

import java.util.List;

public interface ClienteService {

    ClienteResponseDto guardarCliente(ClienteRequestDto clienteRequestDto);

    ClienteResponseDto editarDatosCliente(Integer id, ClienteRequestDto clienteRequestDto);

    void inactivarCliente(Integer id);

    List<ClienteResponseDto> clientes();

    ClienteResponseDto buscarPorId(Integer id);
}
