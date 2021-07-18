package br.com.mercadolivre.cadastracompra;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PaypalDto implements RetornoGatwayPagamento {

    @Min(0)
    @Max(1)
    @JsonProperty("status")
    private Integer status;

    @NotBlank
    @JsonProperty("id_transacao")
    private String idTransacao;


    public Transacao toTransacao(Compra compra) {
        StatusTransacao transacao = this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso;
        return new Transacao(transacao, idTransacao, compra);
    }

}
