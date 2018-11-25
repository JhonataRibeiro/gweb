package br.com.crud.gweb.model;

/**
 * @author jribeiro
 * @date 25/11/18
 */
public enum Genero {
    MASCULINO("Masculino"), FEMININO("Feminino"), DESCONHECIDO("Desconhecido");

    public String valorDoEnum;

    Genero(String value) {
        this.valorDoEnum = value;
    }

    public String obterValor() {
        return valorDoEnum;
    }
}
