package com.daw.finalProject.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.finalProject.model.Usuario;
import com.daw.finalProject.payload.AuthResponse;
import com.daw.finalProject.payload.LoginRequest;
import com.daw.finalProject.payload.RegistroRequest;
import com.daw.finalProject.security.JwtUtil;
import com.daw.finalProject.service.UsuarioService;

import jakarta.validation.Valid;

/**
 * Controlador que maneja las solicitudes de autenticación y registro.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Endpoint para autenticar a un usuario y generar un token JWT.
     *
     * @param loginRequest Objeto que contiene las credenciales de login
     * @return Respuesta que contiene el token JWT o un mensaje de error
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // validamos el cuerpo de la peticion y devolvemos el token
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            String token = jwtUtil.generateToken(loginRequest.getEmail());

            return ResponseEntity.ok(new AuthResponse(token));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("credenciales no válidas");
        }

    }

    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * @param registroRequest Objeto que contiene los datos de registro
     * @return Respuesta que indica el éxito o error del registro
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistroRequest registroRequest) {

        // Verificamos que el mail no existe en base de datos
        if (usuarioService.validarExistenciaUsuarioByEmail(registroRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: El email ya existe!");
        }

        // Creamos el usuario con los datos proporcionados
        Usuario usuario = new Usuario();
        usuario.setNombre(registroRequest.getNombre());
        usuario.setApellidos(registroRequest.getApellidos());
        usuario.setEmail(registroRequest.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroRequest.getPassword()));
        usuario.setRol("USER");

        usuarioService.guardarUsuario(usuario);

        return ResponseEntity.ok("Usuario registrado correctamente!");
    }

    /**
     * Endpoint protegido para pruebas que requiere autenticación.
     *
     * @return Mensaje confirmando el acceso al recurso protegido
     */
    @GetMapping("/protected")
    public ResponseEntity<?> getProtectedResource() {

        return ResponseEntity.ok(Collections.singletonMap("message", "Estas viendo contenido restringido!"));
    }
}
