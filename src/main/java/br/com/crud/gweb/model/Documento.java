package br.com.crud.gweb.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Jhonata Ribeiro
 * @date: 23/11/18
 */
@Entity
@Data
@NoArgsConstructor
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private TipoDocumento tipo;

    @Column
    private Date validade;

    @Column
    private Integer numeroDocumento;

    @Column
    private String observacao;

    private Date dataCriacao;

    private Date dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private
    Usuario usuario;

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

}
