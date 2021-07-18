package br.com.mercadolivre.cadastracompra;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagSeguroDto implements RetornoGatwayPagamento {

    @NotBlank
    @JsonProperty("id_transacao")
    private String idTransacao;

    @NotNull
    @JsonProperty("status")
    private StatusRetornoPagSeguro status;

    public PagSeguroDto(@NotBlank String idTransacao, @NotNull StatusRetornoPagSeguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PagSeguro: " +
                "\nId Transacao: " + idTransacao +
                "\nStatus: " + status;
    }

    public Transacao toTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }

}
