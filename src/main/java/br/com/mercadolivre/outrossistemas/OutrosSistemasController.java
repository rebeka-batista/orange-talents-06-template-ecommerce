package br.com.mercadolivre.outrossistemas;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {

    @PostMapping(value = "/notas-fiscais")
    public void criaNota(@Valid @RequestBody NovaCompraNFDto nfDto) throws InterruptedException {
        System.out.println("criando nota " + nfDto);
        Thread.sleep(150);
    }

    @PostMapping(value = "/ranking")
    public void ranking(@Valid @RequestBody RankingNovaCompraDto rankingDto) throws InterruptedException {
        System.out.println("criando ranking" + rankingDto);
        Thread.sleep(150);
    }

}
