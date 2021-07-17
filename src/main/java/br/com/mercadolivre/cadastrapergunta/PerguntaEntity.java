package br.com.mercadolivre.cadastrapergunta;

import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import br.com.mercadolivre.novoproduto.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "pergunta")
public class PerguntaEntity implements Comparable<PerguntaEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotNull
    @ManyToOne
    private UsuarioEntity interessado;

    @NotNull
    @ManyToOne
    private Produto produto;

    private LocalDateTime instante = LocalDateTime.now();


    public PerguntaEntity() {
    }

    public PerguntaEntity(String titulo, UsuarioEntity curioso, Produto produto) {
        this.titulo = titulo;
        this.interessado = curioso;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public UsuarioEntity getInteressado() {
        return interessado;
    }

    public UsuarioEntity getDonoProduto() {
        return produto.getDonoProduto();
    }


    @Override
    public int compareTo(PerguntaEntity o) {
        return this.titulo.compareTo(o.titulo);
    }

    @Override
    public String toString() {
        return "Pergunta: " +
                "\nId: " + id +
                "\nTitulo: " + titulo +
                "\n " + interessado +
                "\n" + produto +
                "\nInstante do cadastro: " + instante;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PerguntaEntity)) return false;
        PerguntaEntity that = (PerguntaEntity) o;
        return getTitulo().equals(that.getTitulo()) && getInteressado().equals(that.getInteressado()) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitulo(), getInteressado(), produto);
    }

}
