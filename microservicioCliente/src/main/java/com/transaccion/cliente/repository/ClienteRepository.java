package com.transaccion.cliente.repository;

import com.transaccion.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio con metodos query y commands para entidad Cliente
 * @author BryanArmando
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Modifying
    @Query("update Cliente c set c.estado = :estado WHERE c.id = :id")
    void inactivarCliente(@Param("estado") String estado, @Param("id") Integer id);

    /**
     * @param id identificador unico de cliente
     * @return
     */
    Optional<Cliente> findClienteByIdAndAndEstadoIsTrue(Integer id);

    Boolean existsClienteByIdentificacionAndEstadoTrue (String identificacion);
    //findDistinctByIdentificacionExists

    List<Cliente> findAllByEstadoTrue();

}
