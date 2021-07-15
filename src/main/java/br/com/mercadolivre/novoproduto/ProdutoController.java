package br.com.mercadolivre.novoproduto;

import br.com.mercadolivre.autenticausuario.UsuarioRepository;
import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicaIgualValidator());
    }

    @PostMapping("/cadastro")
    @Transactional
    public String cadastrarProduto(@RequestBody @Valid NovoProdutoDto novoProduto) {
        UsuarioEntity dp = usuarioRepository.findByEmail("rebekabatista@outlook.com").get();
        Produto produto = novoProduto.toModel(manager, dp);
        manager.persist(produto);
        return novoProduto.toString();
    }

}
