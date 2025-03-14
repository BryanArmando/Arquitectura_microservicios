package com.transaccion.cuenta.repository;

import com.transaccion.cuenta.entity.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimientos, Integer> {

    List<Movimientos> findAllByIdCuenta(Integer idCuenta);
}
