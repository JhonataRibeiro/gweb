package br.com.crud.gweb.repository;

import br.com.crud.gweb.dto.UsuarioDTO;
import br.com.crud.gweb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: Jhonata Ribeiro
 * @date: 23/11/18
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.id = ?1")
    Usuario findAById(Long id);

    List<Usuario> findByNomeContainingIgnoreCase(String argumentoPesquisa);
}
