package br.com.mercadolivre.cadastrousuario;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/cadastro")
    @Transactional
    public String cadastrarUsuario(@RequestBody @Valid NovoUsuarioDto usuario) {
        UsuarioEntity u = usuario.toModel();
        manager.persist(u);
        return u.toString();
    }

}
