package com.transaccion.cliente.exception;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 10L;

    /**
     * EntityNotFoundException message constructor.
     *
     * @param message Message o exception
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}
