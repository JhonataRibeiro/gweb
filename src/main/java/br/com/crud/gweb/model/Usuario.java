package br.com.crud.gweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Jhonata Ribeiro
 * @date: 23/11/18
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private Integer idade;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Document> documents  = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }
}
