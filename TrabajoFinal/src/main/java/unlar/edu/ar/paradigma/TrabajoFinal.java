package unlar.edu.ar.paradigma;

import java.sql.Connection;
import java.sql.SQLException;

import unlar.edu.ar.paradigma.controladores.SetConexion;

public class TrabajoFinal {

    public static void main(String[] args) throws SQLException {

        try {
            // Obtener la conexión usando la clase SetConexion
            Connection connection = SetConexion.getConnection();
            
            // Si la conexión es exitosa, muestra un mensaje
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            }
        } catch (SQLException e) {
            // Si ocurre un error, muestra el mensaje de error
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
