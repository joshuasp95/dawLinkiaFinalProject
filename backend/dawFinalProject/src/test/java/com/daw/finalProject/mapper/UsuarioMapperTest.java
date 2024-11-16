package com.daw.finalProject.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.daw.finalProject.dto.UsuarioDTO;
import com.daw.finalProject.model.Usuario;

@SpringBootTest
public class UsuarioMapperTest {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Test
    public void toUsuarioDTO() {
        Usuario usuario = new Usuario();

        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setApellidos("Pérez");
        usuario.setEmail("juan.perez@example.com");
        usuario.setRol("USER");
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setFechaModificacion(LocalDateTime.now());

        UsuarioDTO usuarioDTO = usuarioMapper.toUsuarioDTO(usuario);

        assertNotNull(usuarioDTO);
        assertEquals(usuario.getId(), usuarioDTO.getId());
        assertEquals(usuario.getNombre(), usuarioDTO.getNombre());
        assertEquals(usuario.getApellidos(), usuarioDTO.getApellidos());
        assertEquals(usuario.getEmail(), usuarioDTO.getEmail());
        assertEquals(usuario.getRol(), usuarioDTO.getRol());
        assertEquals(usuario.getFechaCreacion(), usuarioDTO.getFechaCreacion());
        assertEquals(usuario.getFechaModificacion(), usuarioDTO.getFechaModificacion());
    }
}
