package br.com.mercadolivre.novacategoria;

import br.com.mercadolivre.validator.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class NovaCategoriaDto {

    @NotBlank
    @JsonProperty("nome_categoria")
    @UniqueValue(domainClass = CategoriaEntity.class, fieldName = "nomeCategoria")
    private String nomeCategoria;

    @Positive
    @JsonProperty("id_categoria_mae")
    private Long categoriaMae;


    public CategoriaEntity toModel(EntityManager manager) {
        CategoriaEntity categoria = new CategoriaEntity(nomeCategoria);
        if (categoriaMae != null) {
            CategoriaEntity categoriaMae = manager.find(CategoriaEntity.class, this.categoriaMae);
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }

    @Override
    public String toString() {
        return "Categoria: " +
                "\nNome: " + nomeCategoria +
                ", \nId da Categoria Mãe: " + categoriaMae;
    }

    public NovaCategoriaDto() {
    }


    public void setCategoriaMae(Long categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

}
