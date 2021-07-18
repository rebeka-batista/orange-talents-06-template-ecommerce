package br.com.mercadolivre.cadastracompra;

import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import br.com.mercadolivre.novoproduto.Produto;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "compra")
public class Compra {

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    public Compra() {
    }

    public Compra(@NotNull @Valid Produto produtoASerComprado,
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
                "\nGateway Pagamento: " + gateway +
                "\n " + transacoes;
    }

    public UsuarioEntity getDonoProduto() {
        return produtoASerComprado.getDonoProduto();
    }

    public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
        return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
    }

    public void adicionaTransacao(@Valid RetornoGatwayPagamento processamentoDto) {
        Transacao novaTransacao = processamentoDto.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao),
                "Já existe uma transacao igual a essa sendo processada!");

        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra foi concluida com sucesso");
        this.transacoes.add(processamentoDto.toTransacao(this));
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso =
                this.transacoes.stream().filter(Transacao::concluidoComSucesso).collect(Collectors.toSet());
        return transacoesConcluidasComSucesso;
    }

    public Boolean processadaComSucesso() {
        Assert.isTrue(transacoesConcluidasComSucesso().size() <= 1, "Tem mais de uma transação concluida com sucesso na compra");
        return !transacoesConcluidasComSucesso().isEmpty();
    }

    public Long getId() {
        return id;
    }

    public UsuarioEntity getConsumidor() {
        return consumidor;
    }

}
