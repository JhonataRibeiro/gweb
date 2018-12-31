package br.com.crud.gweb.service;

import br.com.crud.gweb.dto.UsuarioDTO;
import br.com.crud.gweb.exception.CustomException;
import br.com.crud.gweb.model.Documento;
import br.com.crud.gweb.model.Usuario;
import br.com.crud.gweb.repository.UsuarioRepository;
import br.com.crud.gweb.security.JwtTokenProvider;
import br.com.crud.gweb.utils.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Autowired
    private AuthenticationManager authenticationManager;

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

    public String signup(Usuario user) {
        if (!usuarioRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usuarioRepository.save(user);
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, usuarioRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public Usuario whoami(HttpServletRequest req) {
        return usuarioRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }
}
