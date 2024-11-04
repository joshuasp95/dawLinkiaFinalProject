package com.daw.finalProject.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Clase para manejar las solicitudes de login de usuarios.
 */
@Data // Genera getters y setters automáticamente
public class LoginRequest {

    // Email del usuario, debe ser válido y no puede estar vacío
    @NotBlank(message = "El email es obligatorio")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El email debe ser válido")
    private String email;

    // Contraseña del usuario, no puede estar vacía
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}
