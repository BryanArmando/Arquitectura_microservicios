package com.transaccion.cliente.mapper;

import com.transaccion.cliente.dto.ClienteRequestDto;
import com.transaccion.cliente.dto.ClienteResponseDto;
import com.transaccion.cliente.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Interfaz para uso de MapStruct y conversión de entidad a dto
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {
    Cliente responseDtoToEntity(ClienteResponseDto clienteResponseDto);
    Cliente requestDtoToEntity(ClienteRequestDto clienteRequestDto);

    ClienteRequestDto entityToRequestDto(Cliente cliente);

    ClienteResponseDto entityToResponseDto(Cliente cliente);

    List<ClienteResponseDto> entityListToResponseDtoList(List<Cliente> clientes);
}
