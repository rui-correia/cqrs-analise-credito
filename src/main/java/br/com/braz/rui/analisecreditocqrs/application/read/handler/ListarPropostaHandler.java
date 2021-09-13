package br.com.braz.rui.analisecreditocqrs.application.read.handler;

import br.com.braz.rui.analisecreditocqrs.application.domain.Proposta;
import br.com.braz.rui.analisecreditocqrs.application.domain.StatusProposta;
import br.com.braz.rui.analisecreditocqrs.application.read.queries.PropostaQuery;
import br.com.braz.rui.analisecreditocqrs.application.usecase.ListarPropostaUseCase;
import javassist.NotFoundException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static br.com.braz.rui.analisecreditocqrs.application.config.database.DatabaseConfig.openConnection;
import static br.com.braz.rui.analisecreditocqrs.application.domain.StatusProposta.*;

@Component
public class ListarPropostaHandler implements ListarPropostaUseCase {

    private BuscaUsuarioHandler usuarioHandler;
    private Set<PropostaQuery> propostas = new HashSet<>();
    private String sqlQuery = "SELECT ID, NOME, CPF, RENDA FROM " + Proposta.class.getSimpleName() + " WHERE STATUS_PROPOSTA = ANY (?)";


    public ListarPropostaHandler(BuscaUsuarioHandler usuarioHandler) {
        this.usuarioHandler = usuarioHandler;
    }

    @Override
    public Set<PropostaQuery> handle(Long idUsuario) {
        String[] status = new String[] {APROVADA.toString(), NEGADA.toString()};
        propostas.clear();
        try {
            Connection connection = openConnection();
            connection.setReadOnly(true);
            PreparedStatement pstm = connection.prepareStatement(sqlQuery);

            if (usuarioHandler.isAnalista(idUsuario)){
                status = new String[]{EM_ANALISE.toString()};
                pstm.setArray(1, connection.createArrayOf("text", status));
            } else {
                pstm.setArray(1, connection.createArrayOf("text", status));
            }

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                PropostaQuery proposta = new PropostaQuery();
                proposta.setId(resultSet.getLong(1));
                proposta.setNome(resultSet.getString(2));
                proposta.setCpf(resultSet.getString(3));
                proposta.setRenda(resultSet.getBigDecimal(4));
                propostas.add(proposta);
            }
            resultSet.close();
            System.out.println("Limpando parametros da pstm");
            pstm.clearParameters();
            pstm.close();
            connection.close();
        } catch (SQLException | NotFoundException e) {
            e.printStackTrace();
        }
        return propostas;
    }
}
