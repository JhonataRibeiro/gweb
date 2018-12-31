package br.com.crud.gweb.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)

    private String username;
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

    @Column
    private Integer idade;

    @Column(unique = true, nullable = false)
    private String email;

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
