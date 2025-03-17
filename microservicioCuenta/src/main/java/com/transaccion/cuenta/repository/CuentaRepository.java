package com.transaccion.cuenta.repository;

import com.transaccion.cuenta.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio con metodos query y commands para entidad Cuenta
 * @author BryanArmando
 */
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    /**
     * Valida la existencia de una cuenta
     * @param numeroCuenta
     * @return true/false
     */
    Boolean existsCuentaByNumeroCuenta(Integer numeroCuenta);

    /**
     * Busca una cuenta activa
     * @param idCuenta id cuenta
     * @return Cuenta
     */
    Optional<Cuenta> findCuentaByIdCuentaAndEstadoIsTrue(Integer idCuenta);

    /**
     * Eliminación lógica de cuenta
     * @param estado 1
     * @param id idCuenta
     */
    @Modifying
    @Query("update Cuenta c set c.estado = :estado WHERE c.idCuenta = :id")
    void inactivarCuenta(@Param("estado") String estado, @Param("id") Integer id);

    /**
     * Busca todas las cuentas por id de cliente
     * @param clienteId id de cliente
     * @return lista de cuentas
     */
    List<Cuenta> findAllByClienteId(Integer clienteId);

    /**
     * Busca cuentas y movimientos de un cliente en un rango de fechas
     * @param clienteId clienteId
     * @param fechaInicio rango inicio de fecha
     * @param fechaFin rango final de fecha
     * @return lista de cuentas con movimientos
     */
    @Query("""
            select c from Cuenta c left join fetch c.movimientos movimientos
            where c.clienteId = ?1 and movimientos.fechaCreacion between ?2 and ?3 or movimientos is null""")
    List<Cuenta> findByClienteIdAndMovimientosFechaCreacionBetween (Integer clienteId, Timestamp fechaInicio, Timestamp fechaFin);
}
