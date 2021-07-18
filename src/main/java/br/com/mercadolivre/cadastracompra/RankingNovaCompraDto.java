package br.com.mercadolivre.cadastracompra;

import javax.validation.constraints.NotNull;

public class RankingNovaCompraDto {

    @NotNull
    private Long idCompra;

    @NotNull
    private Long idDonoProduto;

    public RankingNovaCompraDto(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idDonoProduto = idComprador;
    }

    @Override
    public String toString() {
        return "Ranking da Compra: " +
                "\nId da Compra: " + idCompra +
                "\nId do Dono do Produto: " + idDonoProduto;
    }

}
