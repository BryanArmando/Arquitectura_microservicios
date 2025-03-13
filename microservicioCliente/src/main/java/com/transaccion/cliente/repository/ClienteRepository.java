package com.transaccion.cliente.repository;

import com.transaccion.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio con metodos query y commands para entidad Cliente
 * @author BryanArmando
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /**
     * @param id identificador unico de cliente
     * @return
     */
    Optional<Cliente> findClienteByIdAndAndEstadoIsTrue(Integer id);
}
