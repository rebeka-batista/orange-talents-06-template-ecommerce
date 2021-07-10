package br.com.mercadolivre.autenticausuario;

public class UsuarioDto {

    private String tipo, token;

    public UsuarioDto(String token) {
        this.tipo = "JWT";
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public String getToken() {
        return token;
    }

}
