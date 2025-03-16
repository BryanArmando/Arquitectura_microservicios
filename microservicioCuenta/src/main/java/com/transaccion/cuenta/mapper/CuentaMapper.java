package com.transaccion.cuenta.mapper;

import com.transaccion.cuenta.dto.CuentaRequestDto;
import com.transaccion.cuenta.dto.CuentaResponseDto;
import com.transaccion.cuenta.dto.reportes.EstadoCuentaMovimientosDto;
import com.transaccion.cuenta.dto.reportes.EstadoCuentaTipoCuentaDto;
import com.transaccion.cuenta.entity.Cuenta;
import com.transaccion.cuenta.entity.Movimientos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CuentaMapper {

    Cuenta requestDtoToEntity(CuentaRequestDto cuentaRequestDto);

    CuentaResponseDto entityToResponseDto(Cuenta cuenta);

    List<CuentaResponseDto> listEntityToResponseDto(List<Cuenta> cuentas);

    @Mapping(source = "saldoInicial", target = "saldoDisponible")
    EstadoCuentaTipoCuentaDto cuentaToEstadoCuentaTipoCuentaDto(Cuenta cuenta);

    List<EstadoCuentaTipoCuentaDto> entityToEstadoCuenta(List<Cuenta> cuentas);

    List<EstadoCuentaMovimientosDto> entityToReporteMov(List<Movimientos> movimientos);

}
