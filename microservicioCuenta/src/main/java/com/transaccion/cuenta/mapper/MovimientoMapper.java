package com.transaccion.cuenta.mapper;

import com.transaccion.cuenta.dto.MovimientoResponseDto;
import com.transaccion.cuenta.entity.Movimientos;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovimientoMapper {
    MovimientoResponseDto entityToResponseDto(Movimientos movimientos);

    List<MovimientoResponseDto> listEntityToRespondeDtoList(List<Movimientos> movimientos);
}
