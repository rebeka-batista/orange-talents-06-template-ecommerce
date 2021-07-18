package br.com.mercadolivre.cadastracompra;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(), "A compra não foi concluida com sucesso " + compra);
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> dto = Map.of("idCompra", compra.getId(), "idComprador", compra.getConsumidor().getId());
        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", dto, String.class);
    }

}
