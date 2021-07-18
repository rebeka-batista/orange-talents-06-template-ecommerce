package br.com.mercadolivre.cadastracompra;

public interface RetornoGatwayPagamento {
    Transacao toTransacao(Compra compra);
}
