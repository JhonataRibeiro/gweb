package br.com.crud.gweb.repository;

import br.com.crud.gweb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Jhonata Ribeiro
 * @date: 23/11/18
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findAById(Integer id);
}
