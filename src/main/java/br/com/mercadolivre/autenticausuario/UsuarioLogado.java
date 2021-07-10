package br.com.mercadolivre.autenticausuario;

import br.com.mercadolivre.cadastrousuario.SenhaLimpa;
import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;

public class UsuarioLogado {

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String senha;

    @Deprecated
    public UsuarioLogado() {
    }

    public UsuarioLogado(@NotBlank @NotNull String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public UsuarioEntity converter(UsuarioRepository usuarioRepository) {
        return new UsuarioEntity(email, new SenhaLimpa(senha));
    }
}
