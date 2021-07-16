package br.com.mercadolivre.cadastraopniao;

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
public class OpniaoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/{id}/opniao")
    @Transactional
    public String cadastrarOpinao(@RequestBody @Valid NovaOpiniaoDto novaOpiniao,
                                  @PathVariable("id") Long id) {
        Produto produto = manager.find(Produto.class, id);
        UsuarioEntity usuarioConsumidor = usuarioRepository.findByEmail("rebekabatista@outlook.com").get();
        OpniaoEntity opniaoEntity = novaOpiniao.toModel(produto, usuarioConsumidor);
        manager.persist(opniaoEntity);
        return opniaoEntity.toString();
    }

}
