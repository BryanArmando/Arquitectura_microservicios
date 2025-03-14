package com.transaccion.cuenta.mapper;

import com.transaccion.cuenta.dto.CuentaRequestDto;
import com.transaccion.cuenta.dto.CuentaResponseDto;
import com.transaccion.cuenta.entity.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CuentaMapper {

    Cuenta requestDtoToEntity(CuentaRequestDto cuentaRequestDto);

    CuentaResponseDto entityToResponseDto(Cuenta cuenta);

    List<CuentaResponseDto> listEntityToResponseDto(List<Cuenta> cuentas);

}
