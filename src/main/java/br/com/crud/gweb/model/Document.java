package br.com.crud.gweb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author: Jhonata Ribeiro
 * @date: 23/11/18
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private TipoDocumento tipo;

    @Column
    private String observacao;

}
