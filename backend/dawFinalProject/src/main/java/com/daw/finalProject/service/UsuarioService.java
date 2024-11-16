package com.daw.finalProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.finalProject.exception.ResourceNotFoundException;
import com.daw.finalProject.model.Usuario;
import com.daw.finalProject.repository.IUsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    // Verificar que no existe el usuario
    public Boolean validarExistenciaUsuarioByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    // Guardar usuario en base de datos
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // retornar lista de usuarios disponibles
    public List<Usuario> obtenerListaUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario actualizarUsuarioPorLogin(String login, Usuario nuevoUsuario) {
        Usuario usuario = usuarioRepository.findByEmail(login)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con login : " + login + " no existe"));
        return usuario;
    }
}
