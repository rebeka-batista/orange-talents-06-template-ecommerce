package br.com.mercadolivre.cadastraopniao;

import br.com.mercadolivre.cadastrousuario.UsuarioEntity;
import br.com.mercadolivre.novoproduto.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class NovaOpiniaoDto {

    @Min(1)
    @Max(5)
    @JsonProperty("nota")
    private Integer nota;

    @NotBlank
    @JsonProperty("titulo")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    @JsonProperty("descricao")
    private String descricao;

    public OpniaoEntity toModel(@NotNull @Valid Produto produto, @NotNull @Valid UsuarioEntity usuarioConsumidor) {
        return new OpniaoEntity(nota, titulo, descricao, produto, usuarioConsumidor);
    }

    NovaOpiniaoDto() {
    }

    public NovaOpiniaoDto(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }


}
