package br.com.mercadolivre.cadastraopiniao;

import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import br.com.mercadolivre.novoproduto.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity(name = "opniao")
public class OpiniaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(name = "nota", nullable = false)
    private Integer nota;

    @NotBlank
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotBlank
    @Size(max = 500)
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull
    @Valid
    @ManyToOne
    private Produto produto;

    @NotNull
    @Valid
    @ManyToOne
    private UsuarioEntity usuarioConsumidor;

    public OpiniaoEntity() {
    }

    public OpiniaoEntity(Integer nota, String titulo, String descricao, Produto produto, UsuarioEntity usuarioConsumidor) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuarioConsumidor = usuarioConsumidor;
    }

    @Override
    public String toString() {
        return "Opniao: " +
                "\nId: " + id +
                "\nNota: " + nota +
                "\nTitulo: " + titulo +
                "\nDescricao: " + descricao +
                "\nProduto: " + produto +
                "\nUsuario Consumidor: " + usuarioConsumidor;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpiniaoEntity)) return false;
        OpiniaoEntity that = (OpiniaoEntity) o;
        return Objects.equals(nota, that.nota) && Objects.equals(titulo, that.titulo)
                && Objects.equals(descricao, that.descricao) && Objects.equals(produto, that.produto)
                && Objects.equals(usuarioConsumidor, that.usuarioConsumidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nota, titulo, descricao, produto, usuarioConsumidor);
    }
}
