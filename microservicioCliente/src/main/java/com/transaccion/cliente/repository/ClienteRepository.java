package com.transaccion.cliente.repository;

import com.transaccion.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio con metodos query y commands para entidad Cliente
 * @author BryanArmando
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /**
     * sentencia que inactiva (0) a cliente activo (1)
     * @param estado 1
     * @param id id único de cliente
     */
    @Modifying
    @Query("update Cliente c set c.estado = :estado WHERE c.id = :id")
    void inactivarCliente(@Param("estado") String estado, @Param("id") Integer id);

    /**
     * @param id identificador unico de cliente
     * @return
     */
    Optional<Cliente> findClienteByIdAndAndEstadoIsTrue(Integer id);

    /**
     * Sentencia que verifica si existe un cliente por campo identificación
     * @param identificacion campo identificacion de Cliente
     * @return true/false
     */
    Boolean existsClienteByIdentificacionAndEstadoTrue (String identificacion);

    /**
     * Sentencia que busca todos los clientes activos (1)
     * @return lista de clientes activos
     */
    List<Cliente> findAllByEstadoTrue();

}
