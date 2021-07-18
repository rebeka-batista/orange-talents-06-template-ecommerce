package br.com.mercadolivre.cadastracompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ProcessamentoPagamentoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPag(@PathVariable("id") Long idCompra, @RequestBody @Valid PagSeguroDto retornoGatway) {
        return processa(idCompra, retornoGatway);
    }


    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public String processamentoPag(@PathVariable("id") Long idCompra, @RequestBody @Valid PaypalDto retornoGatway) {
        return processa(idCompra, retornoGatway);
    }

    private String processa(Long idCompra, RetornoGatwayPagamento retornoGatway) {
        Compra compra = manager.find(Compra.class, idCompra);
        compra.adicionaTransacao(retornoGatway);
        manager.merge(compra);
        eventosNovaCompra.processa(compra);
        return compra.toString();
    }

}
