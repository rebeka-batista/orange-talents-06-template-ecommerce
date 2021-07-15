package br.com.mercadolivre.novoproduto;

import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import br.com.mercadolivre.novacategoria.CategoriaEntity;
import br.com.mercadolivre.validator.ExistsId;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NovoProdutoDto {

    @NotBlank
    @JsonProperty("nome_produto")
    private String nomeProduto;

    @NotNull
    @Positive
    @JsonProperty("quantidade")
    private Integer quantidade;

    @NotNull
    @Positive
    @JsonProperty("preco")
    private BigDecimal preco;

    @NotBlank
    @Length(max = 1000)
    @JsonProperty("descricao")
    private String descricao;

    @NotNull
    @ExistsId(domainClass = CategoriaEntity.class, fieldName = "id")
    @JsonProperty("id_categoria")
    private Long idCategoria;

    @Size(min = 3)
    @Valid
    private List<NovaCaracteristicaDto> caracteristicas = new ArrayList<>();


    public NovoProdutoDto() {
    }

    public NovoProdutoDto(@NotBlank String nomeProduto,
                          @NotNull @Positive Integer quantidade,
                          @NotNull @Positive BigDecimal preco,
                          @NotBlank @Length(max = 1000) String descricao,
                          @NotNull Long idCategoria,
                          @Size(min = 3) @Valid List<NovaCaracteristicaDto> caracteristicas) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    @Override
    public String toString() {
        return "Cadastro de Produto: " +
                "\nNome do Produto: " + nomeProduto +
                "\nQuantidade: " + quantidade +
                "\nPreco: " + preco +
                "\nDescricao: " + descricao +
                "\nId da Categoria: " + idCategoria +
                "\n" + caracteristicas;
    }


    public Produto toModel(EntityManager manager, UsuarioEntity donoProduto) {
        CategoriaEntity categoria = manager.find(CategoriaEntity.class, idCategoria);
        return new Produto(nomeProduto, quantidade, descricao, preco, categoria, donoProduto, caracteristicas);
    }

    public List<NovaCaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<NovaCaracteristicaDto> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Set<String> buscaCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();

        for (NovaCaracteristicaDto nc : caracteristicas) {
            String nome = nc.getNome();
            if (!nomesIguais.add(nc.getNome())) {
                resultados.add(nome);
            }
        }
        return resultados;
    }
}
