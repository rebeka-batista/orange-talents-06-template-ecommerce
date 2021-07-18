package br.com.mercadolivre.outrossistemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class NovaCompraNFDto {

    @NotNull
    @JsonProperty("id_compra")
    private Long idCompra;

    @NotNull
    @JsonProperty("id_comprador")
    private Long idComprador;

    public NovaCompraNFDto(@NotNull Long idCompra, @NotNull Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "NF Compra: " +
                "\nId Compra: " + idCompra +
                "\nId Comprador: " + idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }

}