package br.com.crud.gweb;

import br.com.crud.gweb.model.Role;
import br.com.crud.gweb.model.Usuario;
import br.com.crud.gweb.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class GwebApplication implements CommandLineRunner {

	@Autowired
	UsuarioService userService;

	public static void main(String[] args) {
		SpringApplication.run(GwebApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");

		config.addAllowedMethod("POST");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Override
	public void run(String... params) throws Exception {
		Usuario admin = new Usuario();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setEmail("admin@email.com");
		admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

		userService.signup(admin);

		Usuario client = new Usuario();
		client.setUsername("client");
		client.setPassword("client");
		client.setEmail("client@email.com");
		client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

		userService.signup(client);
	}
}
