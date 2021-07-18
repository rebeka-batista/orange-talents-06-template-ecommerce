package br.com.mercadolivre.cadastracompra;

import br.com.mercadolivre.autenticausuario.UsuarioRepository;
import br.com.mercadolivre.cadastrapergunta.Email;
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
public class CadastroCompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Email email;

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

            Compra novaCompra = new Compra(produtoASerComprado, quantidade, consumidor, gateway);
            manager.persist(novaCompra);
            email.novaCompra(novaCompra);

            return novaCompra.urlRedirecionamento(uriComponentsBuilder);

        }

        BindException problemaComEstoque = new BindException(compraDto, "novaCompraDto");
        problemaComEstoque.reject(null, "Não é possível realizar a compra devido o estoque.");
        throw problemaComEstoque;

    }


}
