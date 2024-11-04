package com.daw.finalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.daw.finalProject.model.Usuario;
import com.daw.finalProject.service.UsuarioService;

import lombok.extern.java.Log;

@SpringBootApplication
@Log
public class DawFinalProjectApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DawFinalProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (!usuarioService.validarExistenciaUsuarioByEmail("joshuasainz95@gmail.com")) {
			Usuario admin = new Usuario();
			admin.setNombre("Administrador");
			admin.setApellidos("");
			admin.setEmail("joshuasainz95@gmail.com");
			// Codificamos la pass por seguridad
			admin.setPassword(passwordEncoder.encode("linkia.2024"));
			admin.setRol("ADMIN");
			usuarioService.guardarUsuario(admin);
			log.info("Usuario " + admin.getNombre() + " guardado correctamente en base de datos");

		}

	}
}
