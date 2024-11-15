package unlar.edu.ar.paradigma;

import java.sql.Connection;
import java.sql.SQLException;
import unlar.edu.ar.paradigma.controladores.SetConexion;
import unlar.edu.ar.paradigma.gui.Principal;

public class TrabajoFinal {

    public static void main(String[] args) {
        
        
        Principal principal = new Principal();
        principal.setVisible(true);
        
        try {
            Connection connection = SetConexion.getConnection();
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("Error al conectar a la base de datos.");
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
    }
}
