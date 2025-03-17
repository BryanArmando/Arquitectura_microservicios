package com.transaccion.cuenta.repository;

import com.transaccion.cuenta.entity.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio con metodos query y commands para entidad Cuenta
 * @author BryanArmando
 */
public interface MovimientoRepository extends JpaRepository<Movimientos, Integer> {

    /**
     * Busca todos los movimientos por id de cuenta
     * @param idCuenta idCuenta
     * @return lista de movimientos
     */
    List<Movimientos> findAllByIdCuenta(Integer idCuenta);
}
