package br.com.mercadolivre.novacategoria;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class NovaCategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/cadastro")
    @Transactional
    public String cadastrarCategoria(@RequestBody @Valid NovaCategoriaDto categoria) {
        CategoriaEntity c = categoria.toModel(manager);
        manager.persist(c);
        return categoria.toString();
    }

}
