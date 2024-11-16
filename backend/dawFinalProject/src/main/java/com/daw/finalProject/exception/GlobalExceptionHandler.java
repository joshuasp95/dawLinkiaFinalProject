package com.daw.finalProject.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.daw.finalProject.responseEntity.Response;

/**
 * Manejador global de excepciones para capturar y manejar excepciones en toda
 * la aplicación.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Maneja las excepciones de credenciales inválidas.
     *
     * @param ex Excepción BadCredentialsException
     * @return Respuesta HTTP con estado 401 y mensaje de error
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex) {
        logger.error("BadCredentialsException: ", ex);
        Response<String> response = new Response<>(false, null, "Credenciales no válidas");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Maneja las excepciones cuando un usuario no es encontrado.
     *
     * @param ex Excepción UsernameNotFoundException
     * @return Respuesta HTTP con estado 404 y mensaje de error
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        logger.error("UsernameNotFoundException: ", ex);
        Response<String> response = new Response<>(false, null, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja las excepciones de validación con parametros de entrada en peticiones
     * HTTP
     *
     * @param ex Excepción MethodArgumentNotValidException
     * @return Respuesta HTTP con estado 400 y detalles de los errores de validación
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        Response<Map<String, String>> response = new Response<>(false, errors, "Errores de validación");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja las excepciones cuando un recurso no se encuentra
     * HTTP
     *
     * @param ex Excepción MethodArgumentNotValidException
     * @return Respuesta HTTP con estado 400 y detalles de los errores de validación
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.error("ResourceNotFoundException: ", ex);
        Response<String> response = new Response<>(false, null, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja todas las demás excepciones no específicas.
     *
     * @param ex Excepción Exception
     * @return Respuesta HTTP con estado 500 y mensaje de error genérico
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        logger.error("Exception: ", ex);
        Response<String> response = new Response<>(false, null, "Ocurrió un error inesperado");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
