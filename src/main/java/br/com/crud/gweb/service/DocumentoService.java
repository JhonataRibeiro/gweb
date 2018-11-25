package br.com.crud.gweb.service;

import br.com.crud.gweb.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jribeiro
 * @date 25/11/18
 */
@Service
public class DocumentoService {

    @Autowired
    DocumentoRepository documentoRepository;

    protected void excluirDocumento(Long idDocumento) {
        documentoRepository.delete(documentoRepository.findById(idDocumento));
    }
}
