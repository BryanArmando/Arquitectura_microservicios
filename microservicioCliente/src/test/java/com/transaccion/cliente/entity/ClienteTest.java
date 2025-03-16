package com.transaccion.cliente.entity;

import com.transaccion.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ClienteTest {
    private static final SecureRandom random = new SecureRandom();

    private Cliente clienteTest;
    @Mock
    ClienteRepository clienteRepository;

    @BeforeEach
    void setClienteTest(){
        clienteTest = new Cliente();
        clienteTest.setId(random.nextInt());
        clienteTest.setEstado("1");
        clienteTest.setIdentificacion("123456789");
        clienteTest.setDireccion("Quito-Ecuador");
        clienteTest.setTelefono("0987654321");
        clienteTest.setContrasenia("contra1234");
        clienteTest.setGenero("M");
        clienteTest.setEdad(random.nextInt());
    }
    @Test
    void testGettersHeredados(){
        assertNotNull(clienteTest.getEdad());
        assertEquals("1", clienteTest.getEstado());
        assertEquals("123456789", clienteTest.getIdentificacion());
        assertEquals("Quito-Ecuador", clienteTest.getDireccion());
        assertEquals("0987654321", clienteTest.getTelefono());
        assertEquals("contra1234", clienteTest.getContrasenia());
        assertEquals("M", clienteTest.getGenero());
    }

    @Test
    void verClientes(){
        when(clienteRepository.findAllByEstadoTrue()).thenReturn(Collections.singletonList(clienteTest));
        List<Cliente> clientes = clienteRepository.findAllByEstadoTrue();
        assertNotNull(clientes);
        assertFalse(clientes.isEmpty(), "La lista de clientes no está vacía");
        assertEquals(1, clientes.size(), "La lista de clientes tiene un solo elemento");
        assertEquals(clienteTest, clientes.get(0), "El cliente retornado es el esperado");

    }

}
