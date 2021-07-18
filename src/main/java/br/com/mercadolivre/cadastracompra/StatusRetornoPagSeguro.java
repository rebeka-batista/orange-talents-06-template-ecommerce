package br.com.mercadolivre.cadastracompra;

public enum StatusRetornoPagSeguro {

    sucesso, erro;

    public StatusTransacao normaliza() {
        if (this.equals(sucesso)) {
            return StatusTransacao.sucesso;
        }
        return StatusTransacao.erro;
    }

}
