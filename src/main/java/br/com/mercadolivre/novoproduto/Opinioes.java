package br.com.mercadolivre.novoproduto;

import br.com.mercadolivre.cadastraopiniao.OpiniaoEntity;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinioes {

    private Set<OpiniaoEntity> opinioes;

    public <T> Set<T> mapOpinioes(Function<OpiniaoEntity, T> funcaoMapeadora) {
        return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public Opinioes(Set<OpiniaoEntity> opinioes) {
        this.opinioes = opinioes;
    }

    public Double media() {
        Set<Integer> notas = mapOpinioes(opiniao -> opiniao.getNota());
        OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
        return possivelMedia.orElse(0.0);
    }

    public Integer total() {
        return this.opinioes.size();
    }

}
