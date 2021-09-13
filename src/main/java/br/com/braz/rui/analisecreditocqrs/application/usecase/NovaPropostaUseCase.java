package br.com.braz.rui.analisecreditocqrs.application.usecase;

import br.com.braz.rui.analisecreditocqrs.application.write.command.NovaPropostaCommand;

public interface NovaPropostaUseCase {
    default void handle(NovaPropostaCommand command){}
}
