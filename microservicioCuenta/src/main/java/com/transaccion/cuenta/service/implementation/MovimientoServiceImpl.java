package com.transaccion.cuenta.service.implementation;

import com.transaccion.cuenta.commons.ConstantesMsCuenta;
import com.transaccion.cuenta.dto.CuentaResponseDto;
import com.transaccion.cuenta.dto.CuentaUpdateRequestDto;
import com.transaccion.cuenta.dto.MovimientoRequestDto;
import com.transaccion.cuenta.dto.MovimientoResponseDto;
import com.transaccion.cuenta.entity.Movimientos;
import com.transaccion.cuenta.exception.EntityNotFoundException;
import com.transaccion.cuenta.exception.MonetaryFundsException;
import com.transaccion.cuenta.mapper.MovimientoMapper;
import com.transaccion.cuenta.repository.MovimientoRepository;
import com.transaccion.cuenta.service.CuentaService;
import com.transaccion.cuenta.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Clase servicio Implementacion de interfaz Movimiento
 * @author BryanArmando
 */
@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientoMapper movimientoMapper;

    @Override
    public MovimientoResponseDto registrarMovimiento(MovimientoRequestDto movimientoRequestDto) {
        if (movimientoRequestDto.getValor().compareTo(BigDecimal.ZERO) ==0) {
            throw new MonetaryFundsException("Debe ingresar un valor diferente de cero");
        }
        CuentaResponseDto cuentaTransaccion = cuentaService.validarExistenciaCuenta(movimientoRequestDto.getIdCuenta());
        Movimientos movimientos = new Movimientos();

        BigDecimal valorTemporal = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
        if (cuentaTransaccion != null){
            valorTemporal = BigDecimal.valueOf(cuentaTransaccion.getSaldoInicial());
            movimientos.setIdCuenta(cuentaTransaccion.getIdCuenta());
            movimientos.setFechaCreacion(new Timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
        } else {
            throw new EntityNotFoundException("La cuenta ingresada no existe o está inactiva.");
        }

        BigDecimal saldoRestante = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
        if (movimientoRequestDto.getValor().compareTo(BigDecimal.ZERO) < 0 ){
            saldoRestante = valorTemporal.add(movimientoRequestDto.getValor());
            if (saldoRestante.compareTo(BigDecimal.ZERO)< 0){
                throw new MonetaryFundsException("No tiene los fondos necesarios para realizar el RETIRO de: " + movimientoRequestDto.getValor() + "$");
            }
            movimientos.setTipoMovimiento(ConstantesMsCuenta.RETIRO);
        } else {
            saldoRestante = valorTemporal.add(movimientoRequestDto.getValor());
            movimientos.setTipoMovimiento(ConstantesMsCuenta.DEPOSITO);
        }
        movimientos.setValor(movimientoRequestDto.getValor().doubleValue());
        movimientos.setSaldoInicial(cuentaTransaccion.getSaldoInicial());
        movimientos.setSaldoDisponible(saldoRestante.doubleValue());
        Movimientos movimientoGuardado = movimientoRepository.save(movimientos);
        CuentaUpdateRequestDto cuentaUpdateValueRequestDto = new CuentaUpdateRequestDto();
        cuentaUpdateValueRequestDto.setSaldoInicial(saldoRestante.doubleValue());
        cuentaService.editarCuenta(cuentaTransaccion.getIdCuenta(), cuentaUpdateValueRequestDto);
        return movimientoMapper.entityToResponseDto(movimientoGuardado);
    }

    @Override
    public List<MovimientoResponseDto> listadoMovimiento(Integer idCuenta) {
        return movimientoMapper.listEntityToRespondeDtoList(movimientoRepository.findAllByIdCuenta(idCuenta));
    }
}
