package com.transaccion.cuenta.mapper;

import com.transaccion.cuenta.dto.MovimientoResponseDto;
import com.transaccion.cuenta.dto.reportes.EstadoCuentaMovimientosDto;
import com.transaccion.cuenta.entity.Movimientos;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovimientoMapper {
    MovimientoResponseDto entityToResponseDto(Movimientos movimientos);

    List<MovimientoResponseDto> listEntityToRespondeDtoList(List<Movimientos> movimientos);

    Collection<EstadoCuentaMovimientosDto> listEntityToEstadoCuentaList(Collection<Movimientos> movimientosList);
}
