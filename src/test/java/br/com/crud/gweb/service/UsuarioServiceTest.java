package br.com.crud.gweb.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.javafaker.Faker;

import br.com.crud.gweb.model.Usuario;
import br.com.crud.gweb.repository.UsuarioRepository;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioServiceTest  {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Before
	public void cadastrarUsuarios() {
		java.util.List<Usuario> usuarios = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Usuario usuario = new Usuario();
			Faker faker = new Faker();
			usuario.setNome(faker.name().fullName());
			usuarioRepository.save(usuario);
		}

	}

	@Test
	public void deveObterUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		Assert.assertEquals(10, usuarios.size());
	}

}
