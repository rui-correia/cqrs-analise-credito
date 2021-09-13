package br.com.braz.rui.analisecreditocqrs.application.usecase;

import br.com.braz.rui.analisecreditocqrs.application.read.queries.PropostaQuery;

import java.util.Set;

public interface ListarPropostaUseCase {
    default Set<PropostaQuery> handle(Long idUsuario){
        return null;
    }
}
