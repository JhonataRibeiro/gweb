package br.com.crud.gweb.service;

import br.com.crud.gweb.dto.UsuarioDTO;
import br.com.crud.gweb.model.Documento;
import br.com.crud.gweb.model.Usuario;
import br.com.crud.gweb.repository.UsuarioRepository;
import br.com.crud.gweb.utils.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: Jhonata Ribeiro
 * @date: 23/11/18
 */
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    DocumentoService documentoService;

    List<Usuario> usuarios = new ArrayList<>();

    public List<UsuarioDTO> obterUsuarios(String argumentoPesquisa) {

        List<Usuario> usuarios = new ArrayList<>();

        if(Objects.nonNull(argumentoPesquisa)){
            usuarios = usuarioRepository.findByNomeContainingIgnoreCase(argumentoPesquisa);
        }else{
            usuarios = usuarioRepository.findAll();
        }

        //Aqui eu extrairia para outro metodo, mas o tempo.. 
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();
        usuarios.parallelStream().forEach(usuario -> {
           usuarioDTOS.add((UsuarioDTO) DTOUtils.convertClassAToClassBDTO(Usuario.class, UsuarioDTO.class, usuario));
        });
        return  usuarioDTOS;
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
            return documento.getValidade().after(dataInicio) || documento.getValidade().equals(dataInicio)  && documento.getValidade().before(dataFim) || documento.getValidade().equals(dataFim);
        }).collect(Collectors.toList());
    }

    public Usuario excluirDocumento(Long idUsuario, Long idDocumento){
        Usuario usuario = usuarioRepository.findAById(idUsuario);
        usuario.setDocumentos(usuario.getDocumentos().parallelStream().filter(documento -> documento.getId() != idDocumento).collect(Collectors.toList()));
        documentoService.excluirDocumento(idDocumento);
        return usuarioRepository.save(usuario);



    }

}
