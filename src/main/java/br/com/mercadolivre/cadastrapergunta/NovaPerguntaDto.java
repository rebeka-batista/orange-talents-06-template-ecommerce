package br.com.mercadolivre.cadastrapergunta;

import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import br.com.mercadolivre.novoproduto.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaPerguntaDto {

    @NotBlank
    @JsonProperty("titulo")
    private String titulo;

    NovaPerguntaDto() {
    }

    public NovaPerguntaDto(String titulo) {
        this.titulo = titulo;
    }

    public PerguntaEntity toModel(@NotNull @Valid UsuarioEntity interessado, @NotNull @Valid Produto produto) {
        return new PerguntaEntity(titulo, interessado, produto);
    }
}
