package com.transaccion.cuenta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.sql.Timestamp;

@MappedSuperclass
@Data
public abstract class DatosAuditoria {

    @Column(
            name = "FECHA_MODIFICACION",
            length = 50
    )
    protected Timestamp fechaModificacion;

    @Column(name = "IP_ACTUALIZACION")
    protected String ipActualizacion;


}
