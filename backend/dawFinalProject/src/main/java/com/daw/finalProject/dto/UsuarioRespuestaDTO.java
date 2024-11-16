package com.daw.finalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRespuestaDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String rol;

}
