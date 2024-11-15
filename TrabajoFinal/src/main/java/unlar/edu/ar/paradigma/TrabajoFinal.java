package unlar.edu.ar.paradigma;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unlar.edu.ar.paradigma.controladores.AccidenteController;
import unlar.edu.ar.paradigma.controladores.SetConexion;
import unlar.edu.ar.paradigma.gui.Principal;

public class TrabajoFinal {

    public static void main(String[] args) {
        
        
        Principal principal = new Principal();
        principal.setVisible(true);
        try {
            // Establecer la conexión
            Connection connection = SetConexion.getConnection();
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("Error al conectar a la base de datos.");
                return; // Salir si no hay conexión
            }

            // Crear una instancia del controlador con la conexión
            AccidenteController accidenteController = new AccidenteController();
            accidenteController.setConexion(connection);

            // Probar el método obtenerPartesCuerpoPorZona
            System.out.println("Prueba con la zona 'Izquierda':");
            List<String> partesIzquierda = accidenteController.obtenerPartesCuerpoPorZona("Izquierda");
            partesIzquierda.forEach(System.out::println);

            System.out.println("\nPrueba con la zona 'Derecha':");
            List<String> partesDerecha = accidenteController.obtenerPartesCuerpoPorZona("Derecha");
            partesDerecha.forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }
    }
}
