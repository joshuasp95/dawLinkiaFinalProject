package com.daw.finalProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.daw.finalProject.model.Usuario;

public class CustomerUserDetailServiceTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private CustomerUserDetailService customerUserDetailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loadUserByUsername_UserExists_ReturnsUserDetails() {
        // Configurar el comportamiento del mock
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setPassword("password");
        usuario.setRol("USER");

        when(usuarioService.obtenerUsuarioPorEmail("test@example.com")).thenReturn(Optional.of(usuario));

        // Llamar al método a probar
        UserDetails userDetails = customerUserDetailService.loadUserByUsername("test@example.com");

        // Verificar el resultado
        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
    }

    @Test
    public void loadUserByUsername_UserDoesNotExist_ThrowsException() {
        // Configurar el comportamiento del mock
        when(usuarioService.obtenerUsuarioPorEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Llamar al método a probar y esperar una excepción
        assertThrows(UsernameNotFoundException.class, () -> {
            customerUserDetailService.loadUserByUsername("nonexistent@example.com");
        });
    }
}
