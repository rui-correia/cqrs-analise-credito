package br.com.braz.rui.analisecreditocqrs.application.write.handler;

import br.com.braz.rui.analisecreditocqrs.application.domain.Proposta;
import br.com.braz.rui.analisecreditocqrs.application.usecase.NovaPropostaUseCase;
import br.com.braz.rui.analisecreditocqrs.application.write.command.NovaPropostaCommand;
import org.springframework.stereotype.Component;

import java.sql.*;

import static br.com.braz.rui.analisecreditocqrs.application.config.database.DatabaseConfig.openConnection;

@Component
public class NovaPropostaHandler implements NovaPropostaUseCase {

    private Long id;
    private String sqlInsert = "INSERT INTO " + Proposta.class.getSimpleName() + " (nome, cpf, renda, data_criacao, status_proposta, finalizada) "
            + "VALUES(?,?,?,?,?,?)";

    public void handle(NovaPropostaCommand command) {

        Proposta proposta = command.paraProposta();

        try {
            Connection connection = openConnection();
            PreparedStatement pstmt = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, proposta.getNome());
            pstmt.setString(2, proposta.getCpf());
            pstmt.setBigDecimal(3, proposta.getRenda());
            pstmt.setDate(4, proposta.getDataCriacao());
            pstmt.setString(5, proposta.getStatusProposta().toString());
            pstmt.setBoolean(6, proposta.isFinalizada());

            System.out.println("Inserindo nova proposta");
            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try {
                    ResultSet resultSet = pstmt.getGeneratedKeys();
                    if (resultSet.next()) {
                        id = resultSet.getLong(1);
                        System.out.println("Proposta id: " + id + " salva com sucesso.");
                    }
                    resultSet.close();
                    pstmt.close();
                    connection.close();

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
