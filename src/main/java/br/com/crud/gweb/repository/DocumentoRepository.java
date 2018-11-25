package br.com.crud.gweb.repository;

import br.com.crud.gweb.model.Documento;
import br.com.crud.gweb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jribeiro
 * @date 25/11/18
 */
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
    Documento findById(Long idDocumento);
}
