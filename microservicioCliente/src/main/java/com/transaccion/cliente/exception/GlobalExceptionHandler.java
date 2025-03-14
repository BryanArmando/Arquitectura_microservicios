package com.transaccion.cliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleValidationException(
            EntityNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", "Conflict");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).substring(4));

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        /*BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessages = new ArrayList<>();
        String defaultMesagge = "Error interno";

        for (FieldError fieldError : fieldErrors) {
            String errorMessage = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            errorMessages.add(errorMessage);
            defaultMesagge = fieldError.getDefaultMessage();
        }

        BaseResponseVo response = BaseResponseVo.builder()
                .code(1)
                .data(null)
                .message(defaultMesagge)
                .build();

        return ResponseEntity.ok(response);*/
    }

    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(
            MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessages = new ArrayList<>();
        String defaultMesagge = "Error interno";

        for (FieldError fieldError : fieldErrors) {
            String errorMessage = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            errorMessages.add(errorMessage);
            defaultMesagge = fieldError.getDefaultMessage();
        }

        BaseResponseVo response = BaseResponseVo.builder()
                .code(1)
                .data(null)
                .message(defaultMesagge)
                .build();

        return ResponseEntity.ok(response);
    }*/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
