package modelo;

/**
 *
 * @author never
 */
public class Token {

    private String tipoDeToken;
    private String valorDelToken;

    public Token(String tipoDeToken, String valorDelToken) {
        this.tipoDeToken = tipoDeToken;
        this.valorDelToken = valorDelToken;
    }

    public String getTipoDeToken() {
        return tipoDeToken;
    }

    public String getValorDelToken() {
        return valorDelToken;
    }
}
