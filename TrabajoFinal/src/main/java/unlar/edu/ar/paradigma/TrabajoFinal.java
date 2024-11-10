package unlar.edu.ar.paradigma;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import unlar.edu.ar.paradigma.controladores.AccidenteController;
import unlar.edu.ar.paradigma.controladores.SetConexion;
import unlar.edu.ar.paradigma.gui.Principal;
import unlar.edu.ar.paradigma.objetos.AccidenteDTO;

public class TrabajoFinal {

    public static void main(String[] args) throws SQLException {

        Principal principal = new Principal();
        principal.setVisible(true);

        try {
            Connection connection = SetConexion.getConnection();

            AccidenteController accidenteController = new AccidenteController();
            accidenteController.setConexion(connection);

            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
                int idAccidente = 1;
                AccidenteDTO accidente = accidenteController.extraer(idAccidente);
                if (accidente != null) {
                    System.out.println("\nAccidente con ID " + idAccidente + ":");
                    System.out.println(accidente);
                } else {
                    System.out.println("No se encontró un accidente con el ID " + idAccidente);
                }

                // Probar extraerTodo()
                List<AccidenteDTO> listaAccidentes = accidenteController.extraerTodo();
                if (!listaAccidentes.isEmpty()) {
                    System.out.println("\nLista de accidentes:");
                    for (AccidenteDTO accident : listaAccidentes) {
                        System.out.println(accident);
                    }
                } else {
                    System.out.println("No se encontraron accidentes.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error en la creación del accidente: " + e.getMessage());
        }
    }
}