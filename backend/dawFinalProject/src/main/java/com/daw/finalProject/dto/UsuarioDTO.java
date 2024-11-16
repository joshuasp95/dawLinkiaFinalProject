package com.daw.finalProject.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String rol;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

}
