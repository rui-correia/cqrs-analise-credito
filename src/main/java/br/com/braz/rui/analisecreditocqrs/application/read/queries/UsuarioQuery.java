package br.com.braz.rui.analisecreditocqrs.application.read.queries;

import br.com.braz.rui.analisecreditocqrs.application.domain.Cargo;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UsuarioQuery {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

}
