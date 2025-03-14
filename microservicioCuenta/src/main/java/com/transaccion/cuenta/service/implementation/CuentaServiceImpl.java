package com.transaccion.cuenta.service.implementation;

import com.transaccion.cuenta.commons.ConstantesMsCuenta;
import com.transaccion.cuenta.dto.CuentaRequestDto;
import com.transaccion.cuenta.dto.CuentaResponseDto;
import com.transaccion.cuenta.dto.CuentaUpdateRequestDto;
import com.transaccion.cuenta.entity.Cuenta;
import com.transaccion.cuenta.exception.EntityNotFoundException;
import com.transaccion.cuenta.mapper.CuentaMapper;
import com.transaccion.cuenta.repository.CuentaRepository;
import com.transaccion.cuenta.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Cuenta servicio Implementacion de interfaz
 * @author BryanArmando
 */
@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private CuentaMapper cuentaMapper;

    @Override
    public CuentaResponseDto crearCuenta(CuentaRequestDto cuentaRequestDto) {
        if (Boolean.TRUE.equals(cuentaRepository.existsCuentaByNumeroCuenta(cuentaRequestDto.getNumeroCuenta()))){
            throw new EntityNotFoundException("El número de cuenta ya se encuentra registrado para otro usuario");
        }
        //todo crear producer kafka para poder identificar si existe el cliente
        //ClienteResponseDTO clienteEncontrado = producer.esperarRespuesta(cuentaRequestDto.getClienteId());

        Cuenta cuentaGuardar = cuentaMapper.requestDtoToEntity(cuentaRequestDto);
        cuentaGuardar.setEstado(ConstantesMsCuenta.ESTADO_ACT_NUMERICO);
//        cuentaGuardar.setClienteId(delproducer);
        Cuenta cuentaCreada = cuentaRepository.save(cuentaGuardar);
        return cuentaMapper.entityToResponseDto(cuentaCreada);
    }

    /**
     * Metodo para actualizar datos de cuenta incluido el saldo si se envia ese dato
     * @param id
     * @param cuentaUpdateRequestDto
     * @return
     */
    @Transactional
    @Override
    public CuentaResponseDto editarCuenta(Integer id, CuentaUpdateRequestDto cuentaUpdateRequestDto) {
        Cuenta cuentaExistente = cuentaRepository.findCuentaByIdCuentaAndEstadoIsTrue(id).orElseThrow(() ->
                new EntityNotFoundException("La cuenta no pudo ser encontrada o está inactiva"));
        if (cuentaUpdateRequestDto.getNumeroCuenta() != null ){
            if (Boolean.TRUE.equals(cuentaRepository.existsCuentaByNumeroCuenta(cuentaUpdateRequestDto.getNumeroCuenta()))){
                throw new EntityNotFoundException("El número de cuenta ya se encuentra registrado para otro usuario");
            }
            cuentaExistente.setNumeroCuenta(cuentaUpdateRequestDto.getNumeroCuenta());
        }
        if (cuentaUpdateRequestDto.getTipoCuenta() != null){
            cuentaExistente.setTipoCuenta(cuentaUpdateRequestDto.getTipoCuenta());
        }
        if (cuentaUpdateRequestDto.getSaldoInicial() != null){
            cuentaExistente.setSaldoInicial(cuentaUpdateRequestDto.getSaldoInicial());
        }
        Cuenta cuentaCreada = cuentaRepository.save(cuentaExistente);

        return cuentaMapper.entityToResponseDto(cuentaCreada);
    }

    @Override
    public void inactivarCuenta(Integer id) {
        Optional<Cuenta> cuenta = cuentaRepository.findCuentaByIdCuentaAndEstadoIsTrue(id);
        if (cuenta.isEmpty()){
            throw new EntityNotFoundException("La cuenta ingresada no existe o esta inactiva");
        }
        cuentaRepository.inactivarCuenta(ConstantesMsCuenta.ESTADO_INC_NUMERICO, cuenta.get().getIdCuenta());
    }

    @Override
    public List<CuentaResponseDto> cuentasCliente(Integer clienteID) {
        return cuentaMapper.listEntityToResponseDto(cuentaRepository.findAllByClienteId(clienteID));
    }

    @Override
    public CuentaResponseDto validarExistenciaCuenta(Integer cuentaId) {
        return cuentaRepository.findCuentaByIdCuentaAndEstadoIsTrue(cuentaId).map(cuentaMapper::entityToResponseDto).orElse(null);
    }
}
