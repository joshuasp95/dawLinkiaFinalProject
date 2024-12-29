package com.daw.finalProject.service;

import java.util.List;
import java.util.Optional;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.finalProject.dto.UsuarioRecepcionDTO;
import com.daw.finalProject.exception.ResourceNotFoundException;
import com.daw.finalProject.kafka.producer.KafkaProducerUtil;
import com.daw.finalProject.model.LoginAttempt;
import com.daw.finalProject.model.Usuario;
import com.daw.finalProject.repository.IUsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // Verificar que no existe el usuario
    public Boolean validarExistenciaUsuarioByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    // Verificar que no existe el usuario
    public Boolean validarExistenciaUsuarioById(Long id) {
        return usuarioRepository.existsById(id);
    }

    // Guardar usuario en base de datos
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario obtenerUsuarioPorIdOLanzarError(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con id: " + id + " no encontrado"));
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

    public Usuario actualizarUsuarioPorId(Long id, UsuarioRecepcionDTO usuarioActual) {
        Usuario usuario = this.obtenerUsuarioPorIdOLanzarError(id);

        usuario.setNombre(usuarioActual.getNombre());
        usuario.setApellidos(usuarioActual.getApellidos());
        usuario.setEmail(usuarioActual.getEmail());

        return usuarioRepository.save(usuario);
    }

    public void accesoCorrectoPorKafka(String login, String status) {
        // enviar intento de acceso a kafka
        LoginAttempt loginAttempt = new LoginAttempt(login, status);
        try {
            String message = objectMapper.writeValueAsString(loginAttempt);
            KafkaProducerUtil.getProducer().send(new ProducerRecord<String, String>("login-attempts", login, message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void accesoIncorrectoPorKafka(String login, String status) {
        // enviar intento de acceso a kafka
        LoginAttempt loginAttempt = new LoginAttempt(login, status);
        try {
            String message = objectMapper.writeValueAsString(loginAttempt);
            KafkaProducerUtil.getProducer().send(new ProducerRecord<String, String>("login-attempts", login, message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
