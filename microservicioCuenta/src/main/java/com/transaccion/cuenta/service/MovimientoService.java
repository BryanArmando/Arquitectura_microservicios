package com.transaccion.cuenta.service;

import com.transaccion.cuenta.dto.MovimientoRequestDto;
import com.transaccion.cuenta.dto.MovimientoResponseDto;

import java.util.List;

/**
 * Interfaz de servicio para metodos relacionados a Movimiento
 * @autor BryanArmando
 */
public interface MovimientoService {

    MovimientoResponseDto registrarMovimiento(MovimientoRequestDto movimientoRequestDto);

    List<MovimientoResponseDto> listadoMovimiento(Integer idCuenta);



}
