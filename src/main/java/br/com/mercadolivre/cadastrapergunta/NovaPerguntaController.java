package br.com.mercadolivre.cadastrapergunta;

import br.com.mercadolivre.autenticausuario.UsuarioRepository;
import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import br.com.mercadolivre.novoproduto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class NovaPerguntaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Email email;

    @PostMapping("/{id}/pergunta")
    @Transactional
    public String cadastraPergunta(@PathVariable("id") Long id,
                                   @RequestBody @Valid NovaPerguntaDto novaPergunta) {
        Produto produto = manager.find(Produto.class, id);
        UsuarioEntity interessado = usuarioRepository.findByEmail("pergunta@email.com").get();
        PerguntaEntity pergunta = novaPergunta.toModel(interessado, produto);
        manager.persist(pergunta);
        email.novaPergunta(pergunta);
        return pergunta.toString();
    }

}
