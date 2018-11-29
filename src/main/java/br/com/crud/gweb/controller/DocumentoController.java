package br.com.crud.gweb.controller;

import br.com.crud.gweb.model.Usuario;
import br.com.crud.gweb.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Jhonata Ribeiro
 * @date: 29/11/18
 */
@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    @Autowired
    DocumentoService documentoService;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listar() {
        try {
            return new ResponseEntity<>(documentoService.listar(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
