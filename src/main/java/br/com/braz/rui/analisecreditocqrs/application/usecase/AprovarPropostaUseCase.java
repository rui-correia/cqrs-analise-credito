package br.com.braz.rui.analisecreditocqrs.application.usecase;

import br.com.braz.rui.analisecreditocqrs.application.write.command.AprovarPropostaCommand;

public interface AprovarPropostaUseCase {
    default void handle(AprovarPropostaCommand command){}
}
