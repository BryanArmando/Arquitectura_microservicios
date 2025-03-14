package com.transaccion.cuenta.repository;

import com.transaccion.cuenta.entity.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimientos, Integer> {
}
