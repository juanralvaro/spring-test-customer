package com.certidevs;

import com.certidevs.model.Usuario;
import com.certidevs.repository.UsuarioRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {

		var context = SpringApplication.run(Main.class, args);

		var usuarioRepository = context.getBean(UsuarioRepository.class);
		if (usuarioRepository.count() == 0) {
			var usuario1 = Usuario.builder()
					.nombre("Juan").apellido("García").email("juan@game.com").edad(43).build();
			var usuario2 = Usuario.builder()
					.nombre("María").apellido("Rodríguez").email("maria@game.com").edad(25).build();

			usuarioRepository.saveAll(List.of(usuario1, usuario2));
		}
	}

}
