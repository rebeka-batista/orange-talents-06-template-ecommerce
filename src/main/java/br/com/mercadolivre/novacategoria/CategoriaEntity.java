package br.com.mercadolivre.novacategoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "categoria")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome_categoria", nullable = false)
    private String nomeCategoria;

    @ManyToOne
    private CategoriaEntity categoriaMae;


    public CategoriaEntity() {
    }

    @Override
    public String toString() {
        return "\n\nCategoria: " +
                "\nId: " + id +
                "\nNome da Categoria: " + nomeCategoria +
                "\nCategoria Mãe: " + categoriaMae;
    }

    public CategoriaEntity(@NotBlank String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public void setCategoriaMae(CategoriaEntity categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}
