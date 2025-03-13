package com.transaccion.cliente.service.implementation;

import com.transaccion.cliente.dto.ClienteRequestDto;
import com.transaccion.cliente.dto.ClienteResponseDto;
import com.transaccion.cliente.entity.Cliente;
import com.transaccion.cliente.repository.ClienteRepository;
import com.transaccion.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author @BryanArmando
 */
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ClienteResponseDto guardarCliente(ClienteRequestDto clienteRequestDto) {
        Cliente clienteToSave = new Cliente();
        clienteToSave.setContrasenia(passwordEncoder.encode(clienteRequestDto.getContrasenia()));
        clienteToSave.setEstado(Boolean.TRUE.toString());
        Cliente clienteCreacion = clienteRepository.save(clienteToSave);
        //todo implementar mapstruct de entidad a dto request y response
        return new ClienteResponseDto();
    }

    @Override
    public ClienteResponseDto editarDatosCliente(Integer id, ClienteRequestDto clienteRequestDto) {
        //todo implementar excepcion personalizada
        Cliente buscarCliente = clienteRepository.findClienteByIdAndAndEstadoIsTrue(id).orElseThrow( () -> new RuntimeException());
        return null;
    }

    @Override
    public void inactivarCliente(Integer id) {

    }

    @Override
    public List<ClienteResponseDto> clientes() {
        return null;
    }
}
