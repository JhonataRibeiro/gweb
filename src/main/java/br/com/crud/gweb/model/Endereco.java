package br.com.crud.gweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author: Jhonata Ribeiro
 * @date: 27/11/18
 */
@Entity
@Data
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String complemento;
    private String rua;
    private int cep;
    @OneToOne(mappedBy = "endereco")
    @JoinColumn(name = "fk_usuario")
    @JsonManagedReference
    private Usuario usuario;

    public void Endereco(String complemento){
        this.complemento = complemento;
    }
}
