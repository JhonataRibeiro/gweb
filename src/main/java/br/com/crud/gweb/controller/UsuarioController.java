package br.com.crud.gweb.controller;

import br.com.crud.gweb.model.Usuario;
import br.com.crud.gweb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Jhonata Ribeiro
 * @date: 23/11/18
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listar(){
        return new ResponseEntity(usuarioService.obterUsuarios(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity inserir(@RequestBody Usuario usuario){
        return new ResponseEntity(usuarioService.salvarUsuario(usuario), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity editar(@RequestBody Usuario usuario){
        return new ResponseEntity(usuarioService.editarUsuario(usuario), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletar(@RequestParam Usuario usuario){
        return new ResponseEntity(usuarioService.exlcuirUsuario(usuario), HttpStatus.OK);
    }
}
