package br.com.braz.rui.analisecreditocqrs.application.config.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {


    private static String url = "jdbc:postgresql://host.docker.internal:5433/analisecreditodb";
    private static String databaseName = "analisecreditodb";

    public static Connection openConnection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            System.out.println("Conectado a " + databaseName);
        } catch (Exception exception) {
            throw new Error("Problema: ", exception);
        }
        return connection;
    }

}
