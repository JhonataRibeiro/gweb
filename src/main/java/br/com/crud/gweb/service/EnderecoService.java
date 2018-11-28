package br.com.crud.gweb.service;

import br.com.crud.gweb.model.Endereco;
import br.com.crud.gweb.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Jhonata Ribeiro
 * @date: 28/11/18
 */
@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    protected Endereco salvar(Endereco endereco){
        return this.enderecoRepository.save(endereco);
    }
}
