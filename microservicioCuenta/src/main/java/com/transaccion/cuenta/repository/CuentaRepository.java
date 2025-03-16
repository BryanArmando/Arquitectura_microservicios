package com.transaccion.cuenta.repository;

import com.transaccion.cuenta.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    Boolean existsCuentaByNumeroCuenta(Integer numeroCuenta);

    Optional<Cuenta> findCuentaByIdCuentaAndEstadoIsTrue(Integer idCuenta);

    @Modifying
    @Query("update Cuenta c set c.estado = :estado WHERE c.idCuenta = :id")
    void inactivarCuenta(@Param("estado") String estado, @Param("id") Integer id);

    List<Cuenta> findAllByClienteId(Integer clienteId);

    @Query("""
            select c from Cuenta c left join fetch c.movimientos movimientos
            where c.clienteId = ?1 and movimientos.fechaCreacion between ?2 and ?3 or movimientos is null""")
    List<Cuenta> findByClienteIdAndMovimientosFechaCreacionBetween (Integer clienteId, Timestamp fechaInicio, Timestamp fechaFin);
}
