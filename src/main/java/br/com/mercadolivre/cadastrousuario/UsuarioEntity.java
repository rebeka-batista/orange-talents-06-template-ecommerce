package br.com.mercadolivre.cadastrousuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity(name = "usuario")
public class UsuarioEntity implements UserDetails {

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

    @NotNull
    @PastOrPresent
    private LocalDateTime instanteCriacao;

    public UsuarioEntity(@NotBlank @Email String email,
                         @NotNull @Valid SenhaLimpa sl) {
        Assert.isTrue(StringUtils.hasLength(email), "O e-mail não pode ser em branco!");
        Assert.notNull(sl, "A senha não pode ser nula");
        // Assert.isTrue(senha.length() >= 6, "A senha deve ter pelo menos 6 caracteres");
        this.email = email;
        this.senha = sl.hash();
        this.instanteCriacao = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "Cadastro Usuário: " +
                "\nId: " + id +
                ", \nEmail: " + email +
                ", \nSenha: " + senha +
                ", \nData/Hora de criação: " + instanteCriacao;
    }

    public UsuarioEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
