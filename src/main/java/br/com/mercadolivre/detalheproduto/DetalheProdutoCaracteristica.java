package br.com.mercadolivre.detalheproduto;

import br.com.mercadolivre.novoproduto.CaracteristicaProduto;

import javax.validation.constraints.NotBlank;

public class DetalheProdutoCaracteristica {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public DetalheProdutoCaracteristica() {
    }

    public DetalheProdutoCaracteristica(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
