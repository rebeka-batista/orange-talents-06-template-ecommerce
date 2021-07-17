package br.com.mercadolivre.detalheproduto;

import br.com.mercadolivre.novoproduto.Opinioes;
import br.com.mercadolivre.novoproduto.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class DetalheProdutoView {

    @NotBlank
    @Size(max = 500)
    @JsonProperty("descricao")
    private String descricao;

    @NotBlank
    @JsonProperty("nome")
    private String nome;

    @NotNull
    @Positive
    @JsonProperty("preco")
    private BigDecimal preco;

    @NotNull
    @Valid
    private Produto produto;

    private Set<DetalheProdutoCaracteristica> caracteristicas;
    private Set<String> linksImagens;
    private SortedSet<String> perguntas;
    private Set<Map<String, String>> opinioes;
    private Double mediaNotas;
    private Integer total;

    public DetalheProdutoView(Produto produto) {
        this.descricao = produto.getDescricao();
        this.nome = produto.getNomeProduto();
        this.preco = produto.getPreco();
        this.caracteristicas = produto.mapCaracteristicas(DetalheProdutoCaracteristica::new);
        this.linksImagens = produto.mapImagens(imagem -> imagem.getLink());
        this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());

        Opinioes opinioes = produto.getOpinioes();
        this.mediaNotas = opinioes.media();
        this.opinioes = opinioes.mapOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
        });
        this.mediaNotas = opinioes.media();
        this.total = opinioes.total();

    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getTotal() {
        return total;
    }
}
