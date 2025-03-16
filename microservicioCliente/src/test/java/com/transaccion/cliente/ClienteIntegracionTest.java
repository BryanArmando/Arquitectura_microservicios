package com.transaccion.cliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaccion.cliente.entity.Cliente;
import com.transaccion.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.security.SecureRandom;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteIntegracionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente;
    private static SecureRandom random = new SecureRandom();
    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
        cliente = new Cliente();
        cliente.setId(random.nextInt());
        cliente.setEstado("1");
        cliente.setIdentificacion("123456789");
        cliente.setDireccion("Quito-Ecuador");
        cliente.setTelefono("0987654321");
        cliente.setContrasenia("contra1234");
        cliente.setGenero("M");
        cliente.setEdad(random.nextInt());
        cliente = clienteRepository.save(cliente);
    }

    @Test
    void testGetAllClientes() throws Exception {
        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.size()").value(1))
                .andExpect((ResultMatcher) jsonPath("$[0].nombre").value(cliente.getNombre()));
    }

    @Test
    void testGetClienteById() throws Exception {
        mockMvc.perform(get("/clientes/{id}", cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.nombre").value(cliente.getNombre()));
    }

    @Test
    void testCreateCliente() throws Exception {
        Cliente newCliente = new Cliente();
        newCliente.setNombre("Jane Doe");

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCliente)))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.nombre").value("Jane Doe"));

        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(2);
    }

    @Test
    void testDeleteCliente() throws Exception {
        mockMvc.perform(delete("/clientes/{id}", cliente.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).isEmpty();
    }
}