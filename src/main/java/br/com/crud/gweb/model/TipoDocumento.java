package br.com.crud.gweb.model;

/**
 * @author: Jhonata Ribeiro
 * @date: 23/11/18
 */
public enum TipoDocumento {
    IDENTIDADE("Identidade"), CARTEIRA_TRABALHO("Carteira de trabalho");

    public String valorDoEnum;

    TipoDocumento(String value) {
        this.valorDoEnum = value;
    }

    public String obterValor() {
        return valorDoEnum;
    }
}
