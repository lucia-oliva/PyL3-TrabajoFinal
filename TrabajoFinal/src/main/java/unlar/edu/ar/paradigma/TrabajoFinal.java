package unlar.edu.ar.paradigma;

import unlar.edu.ar.paradigma.gui.Principal;
import java.sql.Connection;
import java.sql.SQLException;

import unlar.edu.ar.paradigma.controladores.SetConexion;

public class TrabajoFinal {

    public static void main(String[] args) throws SQLException {
        
        Principal principal = new Principal();
        principal.setVisible(true);

        try {
            Connection connection = SetConexion.getConnection();
            if (connection != null) {
                System.out.println("Conexion exitosa a la base de datos.");
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error en la creación del accidente: " + e.getMessage());
        }

        // Pueden poner todo el código quieran acá

    }
}
