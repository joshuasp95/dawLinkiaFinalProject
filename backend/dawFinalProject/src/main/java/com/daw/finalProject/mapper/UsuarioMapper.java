package com.daw.finalProject.mapper;

import org.mapstruct.Mapper;

import com.daw.finalProject.dto.UsuarioDTO;
import com.daw.finalProject.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toUsuarioDTO(Usuario usuario);

    Usuario toUsuario(UsuarioDTO usuarioDTO);

}
