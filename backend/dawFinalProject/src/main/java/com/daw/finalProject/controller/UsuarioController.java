package com.daw.finalProject.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.finalProject.dto.UsuarioDTO;
import com.daw.finalProject.exception.ResourceNotFoundException;
import com.daw.finalProject.mapper.UsuarioMapper;
import com.daw.finalProject.model.Usuario;
import com.daw.finalProject.responseEntity.Response;
import com.daw.finalProject.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/users")
@Log
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

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

}
