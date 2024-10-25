package com.certidevs;

import com.certidevs.model.Usuario;
import com.certidevs.repository.UsuarioRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {

		var context = SpringApplication.run(Main.class, args);

		var usuarioRepository = context.getBean(UsuarioRepository.class);
		if (usuarioRepository.count() == 0) {
			var usuario1 = Usuario.builder().nombreUsuario("Juan").password("1234").nombre("Juan").direccion("Calle 1").CP(15300).DNI("12345678O").fechaCreacion(Date.from(Instant.now())).build();
			var usuario2 = Usuario.builder().nombreUsuario("Pedro").password("1234").nombre("Pedro").direccion("Calle 2").CP(12334).DNI("19797477M").fechaCreacion(Date.from(Instant.now())).build();
			var usuario3 = Usuario.builder().nombreUsuario("Carlos").password("1234").nombre("Carlos").direccion("Calle 3").CP(44147).DNI("13464497M").fechaCreacion(Date.from(Instant.now())).build();

			usuarioRepository.saveAll(List.of(usuario1, usuario2));
		}
	}

}
