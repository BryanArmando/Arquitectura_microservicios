package com.transaccion.cuenta.exception;

import com.transaccion.cuenta.commons.ConstantesMsCuenta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase para manejo de excepciones
 * @author BryanArmando
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Validacion de excepcion personalizada
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleValidationException(
            EntityNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put(ConstantesMsCuenta.TIMESTAMP, LocalDateTime.now());
        body.put(ConstantesMsCuenta.STATUS, HttpStatus.CONFLICT.value());
        body.put("error", "Conflict");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).substring(4));

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Validacion y envío de excepción por validaciones de campos
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put(ConstantesMsCuenta.TIMESTAMP, LocalDateTime.now());
        body.put(ConstantesMsCuenta.STATUS, HttpStatus.BAD_REQUEST.value());

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MonetaryFundsException.class)
    public ResponseEntity<Object> handleValidationExceptionFunds(
            MonetaryFundsException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put(ConstantesMsCuenta.TIMESTAMP, LocalDateTime.now());
        body.put(ConstantesMsCuenta.STATUS, HttpStatus.BAD_REQUEST.value());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
