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
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
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

    @Override
    @Transactional
    public ClienteResponseDto guardarCliente(ClienteRequestDto clienteRequestDto) {
        Cliente clienteToSave = clienteMapper.requestDtoToEntity(clienteRequestDto);
        clienteToSave.setContrasenia(passwordEncoder.encode(clienteRequestDto.getContrasenia()));
        clienteToSave.setEstado(ConstantesCliente.ESTADO_ACT_NUMERICO);
        Cliente clienteCreado = clienteRepository.save(clienteToSave);
        return clienteMapper.entityToResponseDto(clienteCreado);
    }

    @Override
    public ClienteResponseDto editarDatosCliente(Integer id, ClienteRequestDto clienteRequestDto) {
        //todo implementar excepcion personalizada
        Cliente buscarCliente = clienteRepository.findClienteByIdAndAndEstadoIsTrue(id).orElseThrow( () ->
                new EntityNotFoundException(ConstantesCliente.CLIENTE_NO_ENCONTRADO));
//        Optional<Cliente> clienteBase = clienteRepository.findClienteByIdAndAndEstadoIsTrue(id);
//        if (clienteBase.isEmpty()){
//            throw new EntityNotFoundException(ConstantesCliente.CLIENTE_NO_ENCONTRADO+ id);
//        }
        //Cliente clienteObj = clienteMapper.requestDtoToEntity(clienteRequestDto);
        boolean cambiosDetectados = Boolean.FALSE;
        if (clienteRequestDto.getContrasenia() != null){
            String hashedPassword = passwordEncoder.encode(clienteRequestDto.getContrasenia());
            Boolean comprobarPassword = BCrypt.checkpw(clienteRequestDto.getContrasenia(), buscarCliente.getContrasenia());
            if (Boolean.FALSE.equals(comprobarPassword)){
                cambiosDetectados = Boolean.TRUE;
                buscarCliente.setContrasenia(hashedPassword);
            }
        }
        if (clienteRequestDto.getEdad() != null && !clienteRequestDto.getEdad().equals(buscarCliente.getEdad())){
            buscarCliente.setEdad(clienteRequestDto.getEdad());
        }
        buscarCliente.setNombre(clienteRequestDto.getNombre());
        buscarCliente.setGenero(clienteRequestDto.getGenero());
        if (clienteRequestDto.getIdentificacion() != null && !clienteRequestDto.getIdentificacion().equals(buscarCliente.getIdentificacion())){
            if (Boolean.TRUE.equals(clienteRepository.existsClienteByIdentificacionAndEstadoTrue(clienteRequestDto.getIdentificacion()))){
                throw new EntityNotFoundException("La identificacion ingresada ya se encuentra registrada en el sistema");
            }
        }
        buscarCliente.setIdentificacion(clienteRequestDto.getIdentificacion());
        buscarCliente.setDireccion(clienteRequestDto.getDireccion());
        buscarCliente.setTelefono(clienteRequestDto.getTelefono());
        buscarCliente.setEstado(clienteRequestDto.getEstado());

        Cliente clienteAct = clienteRepository.save(buscarCliente);

        return clienteMapper.entityToResponseDto(clienteAct);
    }

    @Transactional
    @Override
    public void inactivarCliente(Integer id) {
        Cliente clienteExistente = clienteRepository.findClienteByIdAndAndEstadoIsTrue(id).orElseThrow( () ->
                new EntityNotFoundException(ConstantesCliente.CLIENTE_NO_ENCONTRADO));
        clienteRepository.inactivarCliente(ConstantesCliente.ESTADO_INC_NUMERICO, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClienteResponseDto> clientes() {
        return clienteMapper.entityListToResponseDtoList(clienteRepository.findAllByEstadoTrue());
    }

    @Transactional(readOnly = true)
    @Override
    public ClienteResponseDto buscarPorId(Integer id) {
        return clienteRepository.findClienteByIdAndAndEstadoIsTrue(id).map(clienteMapper::entityToResponseDto).orElseThrow( () ->
                new EntityNotFoundException(ConstantesCliente.CLIENTE_NO_ENCONTRADO));
    }
}
