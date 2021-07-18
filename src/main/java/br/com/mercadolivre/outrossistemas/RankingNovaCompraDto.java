package br.com.mercadolivre.outrossistemas;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class RankingNovaCompraDto {

    @NotNull
    @JsonProperty("id_compra")
    private Long idCompra;

    @NotNull
    @JsonProperty("id_dono_produto")
    private Long idDonoProduto;

    public RankingNovaCompraDto(@NotNull Long idCompra, @NotNull Long idDonoProduto) {
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }

    @Override
    public String toString() {
        return "Ranking: " +
                "\nId Compra: " + idCompra +
                "\nId Dono do Produto: " + idDonoProduto;
    }
}
