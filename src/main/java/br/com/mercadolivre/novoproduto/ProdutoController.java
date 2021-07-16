package br.com.mercadolivre.novoproduto;

import br.com.mercadolivre.autenticausuario.UsuarioRepository;
import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Uploader uploaderFake;


    @InitBinder(value = "novoProdutoDto")
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


    @PostMapping("/{id}/imagem")
    @Transactional
    public String cadastrarImagem(@PathVariable("id") Long id, @Valid NovasImagensDto novaImagem) {
        UsuarioEntity donoProduto = usuarioRepository.findByEmail("rebekabatista@outlook.com").get();
        Produto produto = manager.find(Produto.class, id);

        if (!produto.pertenceAoUsuario(donoProduto)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploaderFake.enviaImagem(novaImagem.getImagens());
        produto.associaImagens(links);
        manager.merge(produto);
        return produto.toString();
    }


}
