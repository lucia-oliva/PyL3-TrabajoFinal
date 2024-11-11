package unlar.edu.ar.paradigma.controladores;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SetConexion {

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {

        Dotenv dotenv = Dotenv.load();
        String POSTGRES_USER = dotenv.get("POSTGRES_USER");
        String POSTGRES_PASSWORD = dotenv.get("POSTGRES_PASSWORD");
        String POSTGRES_HOST = dotenv.get("POSTGRES_HOST");
        String POSTGRES_PORT = dotenv.get("POSTGRES_PORT");
        String POSTGRES_DB = dotenv.get("POSTGRES_DB");

        URL = "jdbc:postgresql://" + POSTGRES_HOST + ":" + POSTGRES_PORT + "/" + POSTGRES_DB;
        USER = POSTGRES_USER;
        PASSWORD = POSTGRES_PASSWORD;

    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver no encontrado", e);
        }
    }
}
