package br.com.mercadolivre.cadastrousuario;

import br.com.mercadolivre.validator.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioDto {

    @Email
    @NotBlank
    @JsonProperty("email")
    @UniqueValue(domainClass = UsuarioEntity.class, fieldName = "email")
    private String email;

    @NotBlank
    @Length(min = 6)
    @JsonProperty("senha")
    private String senha;

    public NovoUsuarioDto(@Email @NotBlank String email,
                          @NotBlank @Length(min = 6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsuarioEntity toModel() {
        return new UsuarioEntity(this.email, new SenhaLimpa(senha));
    }
}
