package br.com.mercadolivre.cadastracompra;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraDto {

    @NotNull
    @Positive
    @JsonProperty("quantidade")
    private Integer quantidade;

    @NotNull
    @JsonProperty("id_produto")
    private Long idProduto;

    @NotNull
    @JsonProperty("gateway")
    private GatewayPagamento gateway;

    public NovaCompraDto() {
    }

    public NovaCompraDto(@NotNull @Positive Integer quantidade, @NotNull Long idProduto, @NotNull GatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }
}
