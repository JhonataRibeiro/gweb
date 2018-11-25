package br.com.crud.gweb.dto;

import br.com.crud.gweb.model.Documento;
import br.com.crud.gweb.model.Genero;
import br.com.crud.gweb.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jribeiro
 * @date 25/11/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
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

    @CreatedDate
    private Date dataCriacao;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.idade = usuario.getIdade();
        this.genero = usuario.getGenero();
        this.documentos = usuario.getDocumentos();
        this.dataCriacao = usuario.getDataCriacao();
    }
}
