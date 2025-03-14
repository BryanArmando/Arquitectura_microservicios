package com.transaccion.cuenta.exception;

import java.io.Serial;

/**
 * Excepcion personalizada
 * @author BryanArmando
 */
public class MonetaryFundsException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 10L;

    /**
     * EntityNotFoundException constructor
     *
     * @param message Message
     */
    public MonetaryFundsException(String message) {
        super(message);
    }
}
