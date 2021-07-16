package br.com.mercadolivre.cadastraopniao;

import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import br.com.mercadolivre.novoproduto.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity(name = "opniao")
public class OpniaoEntity {

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

    public OpniaoEntity(Integer nota, String titulo, String descricao, Produto produto, UsuarioEntity usuarioConsumidor) {
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
}
