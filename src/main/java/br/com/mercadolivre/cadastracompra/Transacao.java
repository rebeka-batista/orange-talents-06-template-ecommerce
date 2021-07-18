package br.com.mercadolivre.cadastracompra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private StatusTransacao status;

    @NotBlank
    private String idTransacaoGateway;

    @NotNull
    private LocalDateTime instante;

    @ManyToOne
    @NotNull
    @Valid
    private Compra compra;

    public Transacao() {
    }

    public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacaoGateway,
                     @NotNull @Valid Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.instante = LocalDateTime.now();
        this.compra = compra;
    }

    public Boolean concluidoComSucesso() {
        return this.status.equals(StatusTransacao.sucesso);
    }


    @Override
    public String toString() {
        return "Transacao: " +
                "\nId: " + id +
                "\nStatus" + status +
                "\nId Transacao Gateway: " + idTransacaoGateway +
                "\nInstante: " + instante;
                //"\nCompra: " + compra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transacao)) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(id, transacao.id) && status == transacao.status
                && Objects.equals(idTransacaoGateway, transacao.idTransacaoGateway)
                && Objects.equals(compra, transacao.compra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, compra);
    }
}
