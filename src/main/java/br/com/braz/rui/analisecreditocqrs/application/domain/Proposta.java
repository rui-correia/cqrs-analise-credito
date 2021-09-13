package br.com.braz.rui.analisecreditocqrs.application.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private BigDecimal renda;
    private Date dataCriacao;
    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;
    private boolean finalizada;

    public Proposta(String nome, String cpf, BigDecimal renda) {
        this.nome = nome;
        this.cpf = cpf;
        this.renda = renda;
        this.dataCriacao = Date.valueOf(LocalDate.now());
        this.statusProposta = StatusProposta.EM_ANALISE;
        this.finalizada = false;
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

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public boolean isFinalizada() {
        return finalizada;
    }
}
