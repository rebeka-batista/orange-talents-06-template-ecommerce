package br.com.mercadolivre.cadastracompra;

import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import br.com.mercadolivre.novoproduto.Produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity(name = "compra")
public class CompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Valid
    @ManyToOne
    private Produto produtoASerComprado;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    @Valid
    @ManyToOne
    private UsuarioEntity consumidor;

    @Enumerated
    @NotNull
    private GatewayPagamento gateway;

    public CompraEntity(@NotNull @Valid Produto produtoASerComprado,
                        @NotNull @Positive Integer quantidade,
                        @NotNull @Valid UsuarioEntity consumidor,
                        @NotNull GatewayPagamento gateway) {
        this.produtoASerComprado = produtoASerComprado;
        this.quantidade = quantidade;
        this.consumidor = consumidor;
        this.gateway = gateway;
    }

    @Override
    public String toString() {
        return "Compra: " +
                "\nId: " + id +
                "\nProduto a ser comprado: " + produtoASerComprado +
                "\nQuantidade: " + quantidade +
                "\nConsumidor: " + consumidor +
                "\nGateway Pagamento: " + gateway;
    }

    public Long getId() {
        return id;
    }
}
