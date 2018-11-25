package br.com.crud.gweb.service;

import br.com.crud.gweb.model.Documento;
import br.com.crud.gweb.model.Usuario;
import br.com.crud.gweb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Jhonata Ribeiro
 * @date: 23/11/18
 */
@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> obterUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario editarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario exlcuirUsuario(Long id) {
        Usuario usuario = obterUsuarioPeloId(id);
        usuarioRepository.delete(usuario);
        return null;
    }

    public Usuario obterUsuarioPeloId(Long id){
        return usuarioRepository.findAById(id);
    }

    public List<Documento> obterDocumentsPorRangeData(Long idUsuario, Date dataInicio, Date dataFim){
        Usuario usuario = usuarioRepository.findAById(idUsuario);
        return usuario.getDocumentos().parallelStream().filter(documento -> {
            return documento.getDataCriacao().after(dataInicio) && documento.getDataCriacao().before(dataFim);
        }).collect(Collectors.toList());
    }

}
