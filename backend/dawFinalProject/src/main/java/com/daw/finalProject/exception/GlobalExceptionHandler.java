package com.daw.finalProject.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para capturar y manejar excepciones en toda
 * la aplicación.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja las excepciones de credenciales inválidas.
     *
     * @param ex Excepción BadCredentialsException
     * @return Respuesta HTTP con estado 401 y mensaje de error
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales no válidas");
    }

    /**
     * Maneja las excepciones cuando un usuario no es encontrado.
     *
     * @param ex Excepción UsernameNotFoundException
     * @return Respuesta HTTP con estado 404 y mensaje de error
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Maneja las excepciones de validación con parametros de entrada en peticiones HTTP
     *
     * @param ex Excepción MethodArgumentNotValidException
     * @return Respuesta HTTP con estado 400 y detalles de los errores de validación
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        // Puede haber más de un parametro de entrada con errores asique iteramos todos y los agregamos al map
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Maneja todas las demás excepciones no específicas.
     *
     * @param ex Excepción Exception
     * @return Respuesta HTTP con estado 500 y mensaje de error genérico
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        ex.printStackTrace(); // Imprime la traza de la excepción para depuración
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
    }
}
