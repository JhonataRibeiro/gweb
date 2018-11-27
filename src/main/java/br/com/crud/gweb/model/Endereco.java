package br.com.crud.gweb.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: Jhonata Ribeiro
 * @date: 27/11/18
 */
@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String complemento;
    private String rua;
    private int cep;
}
