package br.com.mercadolivre.autenticausuario;

import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import br.com.mercadolivre.security.GeraToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AutenticaUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GeraToken geraToken;

    public AutenticaUsuarioController(UsuarioRepository usuarioRepository, GeraToken geraToken) {
        this.usuarioRepository = usuarioRepository;
        this.geraToken = geraToken;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> autenticaUsuario(@RequestBody @Valid UsuarioLogado usuarioLogado) {
        Optional<UsuarioEntity> usuarioLogar = usuarioRepository.findByEmail(usuarioLogado.getEmail());
        UsuarioEntity usuario = usuarioLogado.converter(usuarioRepository);
        if (usuarioLogar.isPresent()) {
            String token = geraToken.geraToken(usuarioLogar);
            System.out.println(token);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
