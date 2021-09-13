package br.com.braz.rui.analisecreditocqrs.application.read.handler;

import br.com.braz.rui.analisecreditocqrs.application.config.database.DatabaseConfig;
import br.com.braz.rui.analisecreditocqrs.application.domain.Cargo;
import br.com.braz.rui.analisecreditocqrs.application.domain.Usuario;
import javassist.NotFoundException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BuscaUsuarioHandler {

    public boolean isAnalista(Long id) throws NotFoundException {
        Usuario usuario = handle(id);
        if (usuario == null){
            throw new NotFoundException("Usuario " + id + " nao encontrado.");
        }
        return usuario.getCargo().equals(Cargo.ANALISTA) ? true : false;
    }

    public Usuario handle(Long id) {
        String sqlQuery = "SELECT * FROM " + Usuario.class.getSimpleName() + " WHERE ID = ?";
        Connection connection = DatabaseConfig.openConnection();
        Usuario usuario = new Usuario();

        try {
            connection.setReadOnly(true);
            PreparedStatement pstm = connection.prepareStatement(sqlQuery);
            pstm.setLong(1, id);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                int iId, iNome, iEmail, iCpf, iCargo;
                iId = resultSet.findColumn("id");
                iNome = resultSet.findColumn("nome");
                iEmail = resultSet.findColumn("email");
                iCpf = resultSet.findColumn("cpf");
                iCargo = resultSet.findColumn("cargo");
                usuario.setId(resultSet.getLong(iId));
                usuario.setNome(resultSet.getString(iNome));
                usuario.setEmail(resultSet.getString(iEmail));
                usuario.setCpf(resultSet.getString(iCpf));
                usuario.setCargo(Cargo.valueOf(resultSet.getString(iCargo)));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu uma exception: " + e.getMessage());
        }
        return null;
    }
}
