package com.transaccion.cliente.service;

import com.transaccion.cliente.dto.ClienteRequestDto;
import com.transaccion.cliente.dto.ClienteResponseDto;

import java.util.List;

/**
 * Interfaz de servicio para metodos relacionados a cliente
 * @autor BryanArmando
 */
public interface ClienteService {

    /**
     * Metodo que guarda un cliente
     * @param clienteRequestDto dto de cliente a guardar
     * @return dto cliente guardado
     */
    ClienteResponseDto guardarCliente(ClienteRequestDto clienteRequestDto);

    /**
     * Método que actualiza datos de cliente
     * @param id id único de cliente
     * @param clienteRequestDto dto con campos a editar
     * @return dto cliente actualizado
     */
    ClienteResponseDto editarDatosCliente(Integer id, ClienteRequestDto clienteRequestDto);

    /**
     * Método que realiza eliminacion lógica de cliente
     * @param id id único de cliente
     */
    void inactivarCliente(Integer id);

    /**
     * Método que busca todos los clientes activos del sistema
     * @return dto de lista de clientes
     */
    List<ClienteResponseDto> clientes();

    /**
     * Método que busca cliente por id
     * @param id id único de cliente
     * @return dto con cliente de id
     */
    ClienteResponseDto buscarPorId(Integer id);
}
