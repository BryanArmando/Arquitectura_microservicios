package com.transaccion.cliente.exception;

import java.io.Serial;

/**
 * Excepcion personalizada
 * @author BryanArmando
 */
public class EntityNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 10L;

    /**
     * EntityNotFoundException constructor
     *
     * @param message Message
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}
