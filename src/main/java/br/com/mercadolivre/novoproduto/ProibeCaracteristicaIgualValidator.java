package br.com.mercadolivre.novoproduto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeCaracteristicaIgualValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NovoProdutoDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        NovoProdutoDto request = (NovoProdutoDto) target;
        Set<String> nomesIguais = request.buscaCaracteristicasIguais();
        if (!nomesIguais.isEmpty()) {
            errors.rejectValue("caracteristicas", null, "Essa característica já foi cadastrada!" + nomesIguais);
        }
    }
}
