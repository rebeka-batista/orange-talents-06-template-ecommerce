package br.com.mercadolivre.novoproduto;

import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import br.com.mercadolivre.novacategoria.CategoriaEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "descricao", nullable = false, length = 1000)
    private String descricao;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @NotNull
    @Valid
    @ManyToOne
    private CategoriaEntity categoria;

    @NotNull
    @Valid
    @ManyToOne
    private UsuarioEntity donoProduto;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();


    public Produto(@NotBlank String nomeProduto,
                   @NotNull @Positive Integer quantidade,
                   @NotBlank @Length(max = 1000) String descricao,
                   @NotNull @Positive BigDecimal preco,
                   @NotNull @Valid CategoriaEntity categoria,
                   @NotNull @Valid UsuarioEntity donoProduto,
                   @Size(min = 3) @Valid Collection<NovaCaracteristicaDto> caracteristicas) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.donoProduto = donoProduto;
        Set<CaracteristicaProduto> novasCaracteristicas = caracteristicas.stream()
                .map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet());

        this.caracteristicas.addAll(novasCaracteristicas);
        Assert.isTrue(this.caracteristicas.size() >= 3, "O produto precisa ter pelo menos 3 características");
    }


    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens =
                links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());
        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(UsuarioEntity donoProduto) {
        return this.donoProduto.equals(donoProduto);
    }

    @Override
    public String toString() {
        return "Produto: " +
                "\nId: " + id +
                "\nNome do Produto: " + nomeProduto +
                "\nQuantidade: " + quantidade +
                "\nDescricao: " + descricao +
                "\nPreco: " + preco +
                "\nCategoria: " + categoria +
                "\nDono do Produto: " + donoProduto +
                "\nCaracteristicas: " + caracteristicas +
                "\n" + imagens;
    }


    public Produto() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return nomeProduto.equals(produto.nomeProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeProduto);
    }


}
