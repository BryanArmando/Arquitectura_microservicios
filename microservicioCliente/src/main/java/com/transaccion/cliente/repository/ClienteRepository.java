package com.transaccion.cliente.repository;

import com.transaccion.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio con metodos query y commands para entidad Cliente
 * @author BryanArmando
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
