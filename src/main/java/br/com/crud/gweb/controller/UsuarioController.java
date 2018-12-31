package br.com.crud.gweb.controller;

import br.com.crud.gweb.dto.UserDataDTO;
import br.com.crud.gweb.dto.UserResponseDTO;
import br.com.crud.gweb.model.Usuario;
import br.com.crud.gweb.service.UsuarioService;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.Date;

/**
 * @author: Jhonata Ribeiro
 * @date: 23/11/18
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listar(@PathParam("argumentopesquisa") String argumentopesquisa) {
        try {
            return new ResponseEntity<>(usuarioService.obterUsuarios(argumentopesquisa), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}/documentos", method = RequestMethod.GET)
    public ResponseEntity obteResponseEntity(@PathVariable("id") Long id, @PathParam("dataInicio") Date dataInicio, @PathParam("dataInicio") Date dataFim) {
        try {
            return new ResponseEntity<>(usuarioService.obterDocumentsPorRangeData(id, dataInicio, dataFim), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity inserir(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.salvarUsuario(usuario), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity editar(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.editarUsuario(usuario), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(usuarioService.exlcuirUsuario(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}/documento/{idDocumento}", method = RequestMethod.DELETE)
    public ResponseEntity deletar(@PathVariable("id") Long id, @PathVariable("idDocumento") Long idDocumento) {
        try {
            return new ResponseEntity<>(usuarioService.excluirDocumento(id, idDocumento), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/signin")
    public String login(//
                        @ApiParam("Username") @RequestParam String username, //
                        @ApiParam("Password") @RequestParam String password) {
        return usuarioService.signin(username, password);
    }

    @PostMapping("/signup")
    public String signup(@ApiParam("Signup User") @RequestBody UserDataDTO user) {
        return usuarioService.signup(modelMapper.map(user, Usuario.class));
    }

    @GetMapping(value = "/me")
    public UserResponseDTO whoami(HttpServletRequest req) {
        return modelMapper.map(usuarioService.whoami(req), UserResponseDTO.class);
    }
}
