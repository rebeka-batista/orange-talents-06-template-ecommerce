package br.com.mercadolivre.detalheproduto;

import br.com.mercadolivre.novoproduto.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/produto")
public class DetalheProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/{id}")
    public DetalheProdutoView mostrarDetalheProduto(@PathVariable("id") Long id) {
        Produto produtoEscolhido = manager.find(Produto.class, id);
        return new DetalheProdutoView(produtoEscolhido);
    }


}
