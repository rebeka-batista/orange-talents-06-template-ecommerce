package br.com.mercadolivre.cadastrapergunta;

import br.com.mercadolivre.cadastracompra.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class Email {

    @Autowired
    private Mailer mailer;

    @NotNull
    @Valid
    private PerguntaEntity pergunta;

    public void novaPergunta(@NotNull @Valid PerguntaEntity pergunta) {
        mailer.enviar("<html>...</html>", "Nova Pergunta", pergunta.getInteressado().getEmail(),
                "np@mercadolivre.com", pergunta.getDonoProduto().getEmail());
    }

    public void novaCompra(Compra novaCompra) {
        mailer.enviar("nova compra..." + novaCompra, "Você tem uma nova compra", novaCompra.getConsumidor().getEmail(),
                "compras@mercadolivre.com", novaCompra.getDonoProduto().getEmail());
    }


}
