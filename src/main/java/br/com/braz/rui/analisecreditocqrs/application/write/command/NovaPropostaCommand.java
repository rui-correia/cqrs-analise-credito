package br.com.braz.rui.analisecreditocqrs.application.write.command;

import br.com.braz.rui.analisecreditocqrs.application.domain.Proposta;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaCommand {

    @NotBlank
    private String nome;
    @NotBlank
    @CPF
    private String cpf;
    @NotNull
    @Positive
    @Min(1000)
    private BigDecimal renda;

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public Proposta paraProposta(){
        return new Proposta(this.nome, this.cpf, this.renda);
    }

}
