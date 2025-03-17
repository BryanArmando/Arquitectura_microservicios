package com.transaccion.cliente.service.implementation;

import com.transaccion.cliente.commons.ConstantesCliente;
import com.transaccion.cliente.dto.ClienteRequestDto;
import com.transaccion.cliente.dto.ClienteResponseDto;
import com.transaccion.cliente.entity.Cliente;
import com.transaccion.cliente.exception.EntityNotFoundException;
import com.transaccion.cliente.mapper.ClienteMapper;
import com.transaccion.cliente.repository.ClienteRepository;
import com.transaccion.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase servicio que implementa funciones para procesos de cliente
 * @author @BryanArmando
 */
@Service
@RequiredArgsConstructor
@Log
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ClienteResponseDto guardarCliente(ClienteRequestDto clienteRequestDto) {
        Cliente clienteToSave = clienteMapper.requestDtoToEntity(clienteRequestDto);
        clienteToSave.setContrasenia(passwordEncoder.encode(clienteRequestDto.getContrasenia()));
        clienteToSave.setEstado(ConstantesCliente.ESTADO_ACT_NUMERICO);
        Cliente clienteCreado = clienteRepository.save(clienteToSave);
        return clienteMapper.entityToResponseDto(clienteCreado);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClienteResponseDto editarDatosCliente(Integer id, ClienteRequestDto clienteRequestDto) {
        Cliente buscarCliente = clienteRepository.findClienteByIdAndAndEstadoIsTrue(id).orElseThrow( () ->
                new EntityNotFoundException(ConstantesCliente.CLIENTE_NO_ENCONTRADO));
        if (clienteRequestDto.getContrasenia() != null){
            String hashedPassword = passwordEncoder.encode(clienteRequestDto.getContrasenia());
            Boolean comprobarPassword = BCrypt.checkpw(clienteRequestDto.getContrasenia(), buscarCliente.getContrasenia());
            if (Boolean.FALSE.equals(comprobarPassword)){
                buscarCliente.setContrasenia(hashedPassword);
            }
        }
        if (clienteRequestDto.getEdad() != null && !clienteRequestDto.getEdad().equals(buscarCliente.getEdad())){
            buscarCliente.setEdad(clienteRequestDto.getEdad());
        }
        buscarCliente.setNombre(clienteRequestDto.getNombre());
        buscarCliente.setGenero(clienteRequestDto.getGenero());
        if (clienteRequestDto.getIdentificacion() != null && !clienteRequestDto.getIdentificacion().equals(buscarCliente.getIdentificacion())
                && Boolean.TRUE.equals(clienteRepository.existsClienteByIdentificacionAndEstadoTrue(clienteRequestDto.getIdentificacion()))){
                throw new EntityNotFoundException("La identificacion ingresada ya se encuentra registrada en el sistema");
            }

        buscarCliente.setIdentificacion(clienteRequestDto.getIdentificacion());
        buscarCliente.setDireccion(clienteRequestDto.getDireccion());
        buscarCliente.setTelefono(clienteRequestDto.getTelefono());
        buscarCliente.setEstado(clienteRequestDto.getEstado());

        Cliente clienteAct = clienteRepository.save(buscarCliente);

        return clienteMapper.entityToResponseDto(clienteAct);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void inactivarCliente(Integer id) {
        clienteRepository.findClienteByIdAndAndEstadoIsTrue(id).orElseThrow( () ->
                new EntityNotFoundException(ConstantesCliente.CLIENTE_NO_ENCONTRADO));
        clienteRepository.inactivarCliente(ConstantesCliente.ESTADO_INC_NUMERICO, id);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<ClienteResponseDto> clientes() {
        return clienteMapper.entityListToResponseDtoList(clienteRepository.findAllByEstadoTrue());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public ClienteResponseDto buscarPorId(Integer id) {
        return clienteRepository.findClienteByIdAndAndEstadoIsTrue(id).map(clienteMapper::entityToResponseDto).orElseThrow( () ->
                new EntityNotFoundException(ConstantesCliente.CLIENTE_NO_ENCONTRADO));
    }
}
