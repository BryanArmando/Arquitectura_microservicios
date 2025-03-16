package com.transaccion.cuenta.service;

import com.transaccion.cuenta.dto.CuentaRequestDto;
import com.transaccion.cuenta.dto.CuentaResponseDto;
import com.transaccion.cuenta.dto.CuentaUpdateRequestDto;

import java.util.List;

public interface CuentaService {

    CuentaResponseDto crearCuenta(CuentaRequestDto cuentaRequestDto);

    CuentaResponseDto editarCuenta(Integer id, CuentaUpdateRequestDto cuentaUpdateRequestDto);

    void inactivarCuenta(Integer id, String ip);

    List<CuentaResponseDto> cuentasCliente(Integer clienteID);

    CuentaResponseDto validarExistenciaCuenta(Integer cuentaId);
}
