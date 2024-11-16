package com.daw.finalProject.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.daw.finalProject.model.Usuario;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    // servicio para gestión de usuarios
    @Autowired
    private UsuarioService usuarioService;

    /**
     * Obtiene las autoridades (roles) del usuario.
     *
     * @param usuario Objeto Usuario
     * @return Colección de GrantedAuthority que representan los roles del usuario
     */
    private Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {

        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
    }

    /**
     * Carga los detalles del usuario por su nombre de usuario (email).
     *
     * @param username Nombre de usuario (email) del usuario
     * @return Objeto UserDetails que contiene los detalles del usuario
     * @throws UsernameNotFoundException Si el usuario no se encuentra en la base de
     *                                   datos
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario con mail: " + username + " no se encuentra en la base de datos"));

        return new User(usuario.getEmail(), usuario.getPassword(), getAuthorities(usuario));
    }

}
