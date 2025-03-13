package com.transaccion.cuenta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public abstract class DatosAuditoria {

    @Column(
            name = "FECHA_MODIFICACION",
            length = 50
    )
    protected Date fechaModificacion;

    @Column(name = "IP_ACTUALIZACION")
    protected String ipActualizacion;


}
