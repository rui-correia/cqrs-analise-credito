package br.com.braz.rui.analisecreditocqrs.application.write.handler;

import br.com.braz.rui.analisecreditocqrs.application.config.database.DatabaseConfig;
import br.com.braz.rui.analisecreditocqrs.application.domain.Proposta;
import br.com.braz.rui.analisecreditocqrs.application.domain.StatusProposta;
import br.com.braz.rui.analisecreditocqrs.application.read.handler.BuscaUsuarioHandler;
import br.com.braz.rui.analisecreditocqrs.application.usecase.AprovarPropostaUseCase;
import br.com.braz.rui.analisecreditocqrs.application.write.command.AprovarPropostaCommand;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class AprovarPropostaHandler implements AprovarPropostaUseCase {

    private BuscaUsuarioHandler usuarioHandler;
    private String sqlString = "UPDATE " + Proposta.class.getSimpleName() + " SET STATUS_PROPOSTA = '" + StatusProposta.APROVADA + "' WHERE ID = ?";

    @Override
    public void handle(AprovarPropostaCommand command) {



        Connection connection = DatabaseConfig.openConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(sqlString);
            pstm.setLong(1, command.getIdProposta());
            System.out.println("Aprovando proposta " + command.getIdProposta());
            pstm.executeQuery();
            System.out.println("Proposta " + command.getIdProposta() + " aprovada");
        }catch (SQLException e){
            System.out.println("Ocorreu o seguinte problema: " + e.getMessage());
        }

    }
}
