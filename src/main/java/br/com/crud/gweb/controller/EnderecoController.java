package br.com.crud.gweb.controller;

import br.com.crud.gweb.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Jhonata Ribeiro
 * @date: 28/11/18
 */
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listar(){
        return new ResponseEntity<>(enderecoService.listar(), HttpStatus.OK);
    }
}
