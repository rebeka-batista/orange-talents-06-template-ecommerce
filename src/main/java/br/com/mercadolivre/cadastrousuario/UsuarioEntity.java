package br.com.mercadolivre.cadastrousuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank
    @Length(min = 6)
    @Column(name = "senha", nullable = false)
    private String senha;

    public UsuarioEntity(@NotBlank @Email String email,
                         @NotNull @Valid SenhaLimpa sl) {
        Assert.isTrue(StringUtils.hasLength(email), "O e-mail não pode ser em branco!");
        Assert.notNull(sl, "A senha não pode ser nula");
        // Assert.isTrue(senha.length() >= 6, "A senha deve ter pelo menos 6 caracteres");
        this.email = email;
        this.senha = sl.hash();
    }


    @Override
    public String toString() {
        return "Cadastro Usuário: " +
                "\nId: " + id +
                ", \nEmail: " + email +
                ", \nSenha: " + senha;
    }
}
