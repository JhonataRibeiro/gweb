package br.com.crud.gweb.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Jhonata Ribeiro
 * @date: 23/11/18
 */
@Entity
@Data
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

    @Column
    private Genero genero;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Documento> documentos = new ArrayList<>();

    @Transient
    private boolean temDocumentos(){
        return this.documentos.size() > 0;
    }

    @CreatedDate
    private Date dataCriacao;

    @LastModifiedDate
    private Date dataAtualizacao;

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }

    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }

    public Usuario(String nome) {
        this.nome = nome;
    }
}
