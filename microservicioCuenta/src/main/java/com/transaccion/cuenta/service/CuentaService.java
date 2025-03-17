package com.transaccion.cuenta.service;

import com.transaccion.cuenta.dto.CuentaRequestDto;
import com.transaccion.cuenta.dto.CuentaResponseDto;
import com.transaccion.cuenta.dto.CuentaUpdateRequestDto;

import java.util.List;

/**
 * Interfaz de servicio para metodos relacionados a cuenta
 * @autor BryanArmando
 */
public interface CuentaService {

    /**
     * Crea cuenta asociada a un cliente
     * @param cuentaRequestDto datos
     * @return cuenta creada
     */
    CuentaResponseDto crearCuenta(CuentaRequestDto cuentaRequestDto);

    /**
     * Modifica datos de cuenta por id
     * @param id identificador de cuenta
     * @param cuentaUpdateRequestDto datos a modificar
     * @return cuenta modificada
     */
    CuentaResponseDto editarCuenta(Integer id, CuentaUpdateRequestDto cuentaUpdateRequestDto);

    /**
     * Elimina de manera lógica una cuenta
     * @param id id de cuenta
     * @param ip ip a registrar
     */
    void inactivarCuenta(Integer id, String ip);

    /**
     * Busca cuentas asociadas a un cliente
     * @param clienteID clienteID
     * @return lista de cuentas
     */
    List<CuentaResponseDto> cuentasCliente(Integer clienteID);

    /**
     * Valida si existe una cuenta
     * @param cuentaId cuentaId
     * @return cuenta
     */
    CuentaResponseDto validarExistenciaCuenta(Integer cuentaId);
}
