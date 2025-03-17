package com.transaccion.cuenta.commons;

/**
 * Constantes para microservicio
 * @author BryanArmando
 */
public final class ConstantesMsCuenta {

    public static final String ESTADO_ACT_NUMERICO = "1";
    public static final String ESTADO_INC_NUMERICO = "0";

    public static final String CUENTA_NO_ENCONTRADA = "La cuenta ingresada no fue encontrado o esta inactiva ";

    public static final String RETIRO = "RETIRO";
    public static final String DEPOSITO = "DEPOSITO";

    public static final String TIMESTAMP = "timestamp";
    public static final String STATUS = "status";

    private ConstantesMsCuenta(){
        super();
    }
}
