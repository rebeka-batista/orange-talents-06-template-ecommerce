package br.com.mercadolivre.cadastrapergunta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface Mailer {

    /*
    body - corpo do email
    subject - assunto do email
    nameFrom - nome para aparecer no provedor do email
    from - email de origem
    to - email de destino
     */

    void enviar(@NotBlank String body, @NotBlank String subject, @NotBlank String nameFrom,
                @NotBlank @Email String from, @NotBlank @Email String to);
}
