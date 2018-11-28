package br.com.crud.gweb.repository;

import br.com.crud.gweb.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Jhonata Ribeiro
 * @date: 28/11/18
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    Endereco findById(Long id);
}
