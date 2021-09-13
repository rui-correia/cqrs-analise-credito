package br.com.braz.rui.analisecreditocqrs.application.read.queries;

import java.math.BigDecimal;

public class PropostaQuery {

    private Long id;
    private String nome;
    private String cpf;
    private BigDecimal renda;


    public PropostaQuery() {
    }

    public PropostaQuery(Long id, String nome, String cpf, BigDecimal renda) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.renda = renda;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public BigDecimal getRenda() {
        return renda;
    }
}
