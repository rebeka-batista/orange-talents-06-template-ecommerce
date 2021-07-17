package br.com.mercadolivre.cadastracompra;

import br.com.mercadolivre.autenticausuario.UsuarioRepository;
import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import br.com.mercadolivre.novoproduto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compra")
public class FechaCompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/produto")
    @Transactional
    public String castrarCompra(@RequestBody @Valid NovaCompraDto compraDto,
                                UriComponentsBuilder uriComponentsBuilder) throws BindException {

        Produto produtoASerComprado = manager.find(Produto.class, compraDto.getIdProduto());
        Integer quantidade = compraDto.getQuantidade();
        Boolean abateu = produtoASerComprado.abateEstoque(quantidade);

        if (abateu) {
            UsuarioEntity consumidor = usuarioRepository.findByEmail("rebekabatista@outlook.com").get();
            GatewayPagamento gateway = compraDto.getGateway();
            CompraEntity novaCompra = new CompraEntity(produtoASerComprado, quantidade, consumidor, gateway);
            manager.persist(novaCompra);

            if (gateway.equals(GatewayPagamento.pagseguro)) {
                String urlRetornoPagSeguro =
                        uriComponentsBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(novaCompra.getId()).toString();
                return "pagseguro.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPagSeguro;
            }
            else if (gateway.equals(GatewayPagamento.paypal)) {
                String urlRetornoPayPall =
                        uriComponentsBuilder.path("/retorno-paypal/{id}").buildAndExpand(novaCompra.getId()).toString();
                return "paypal.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPayPall;
            }
        }

        BindException problemaComEstoque = new BindException(compraDto, "novaCompraDto");
        problemaComEstoque.reject(null, "Não é possível realizar a compra devido o estoque.");
        throw problemaComEstoque;
    }


}
