package com.daw.finalProject.payload;

import lombok.Data;

/**
 * Clase para manejar las solicitudes de registro de usuarios.
 */
@Data
public class AuthResponse {

    private String token; // Token JWT generado para el usuario autenticado

    /**
     * Constructor para AuthResponse.
     *
     * @param token Token JWT generado
     */
    public AuthResponse(String token) {
        this.token = token;
    }

}