package com.daw.finalProject.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.finalProject.dto.UsuarioDTO;
import com.daw.finalProject.dto.UsuarioRecepcionDTO;
import com.daw.finalProject.exception.ResourceNotFoundException;
import com.daw.finalProject.mapper.UsuarioMapper;
import com.daw.finalProject.model.Usuario;
import com.daw.finalProject.responseEntity.Response;
import com.daw.finalProject.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
@Log
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Endpoint para obtener la lista de todos los usuarios.
     * Solo accesible por usuarios con rol ADMIN.
     *
     * @return Lista de UsuarioDTO encapsulada en una respuesta normalizada
     */

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obtiene la lista de todos los usuarios", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Response<List<UsuarioDTO>>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerListaUsuarios();

        if (usuarios.isEmpty()) {
            throw new ResourceNotFoundException("No se han encontrado usuarios");
        }

        List<UsuarioDTO> usuarioDTOs = usuarios.stream().map(usuarioMapper::toUsuarioDTO).collect(Collectors.toList());

        Response<List<UsuarioDTO>> response = new Response<>(true, usuarioDTOs, "Usuarios obtenidos correctamente");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para crear un nuevo usuario.
     *
     * @param usuario Objeto que contiene los datos de usuario
     * @return Respuesta que indica el éxito o error del registro
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obtiene la lista de todos los usuarios", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> registerUser(@Valid @RequestBody UsuarioRecepcionDTO usuario) {

        // Verificamos que el mail no existe en base de datos
        if (usuarioService.validarExistenciaUsuarioByEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new Response<>(false, null, "Error: El email ya existe!"));
        }

        // Creamos el usuario con los datos proporcionados
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuario.getNombre());
        nuevoUsuario.setApellidos(usuario.getApellidos());
        nuevoUsuario.setEmail(usuario.getEmail());
        nuevoUsuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        nuevoUsuario.setRol("USER");

        usuarioService.guardarUsuario(nuevoUsuario);

        Response<String> response = new Response<>(true, null, "Usuario registrado correctamente!");
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para actualizar usuario
     * 
     * @param id el id del usuario a actualizar
     * @return respuesta que indica el éxito o error de la actualización
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response<UsuarioDTO>> actualizarUsuario(@PathVariable Long id,
            @Valid @RequestBody UsuarioRecepcionDTO usuario) {

        Usuario nuevoUsuario = usuarioService.actualizarUsuarioPorId(id, usuario);

        UsuarioDTO usuarioDTO = usuarioMapper.toUsuarioDTO(nuevoUsuario);

        Response<UsuarioDTO> response = new Response<>(true, usuarioDTO, "Usuario actualizado correctamente");

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
