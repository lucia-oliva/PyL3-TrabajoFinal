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
                probarConexion();
                probarExtraer(accidenteController);
                probarExtraerTodo(accidenteController);
                // probarModificar(accidenteController);
                probarEliminar(accidenteController);
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error en la creación del accidente: " + e.getMessage());
        }
    }

    private static void probarConexion() {
        System.out.println("Conexión exitosa a la base de datos.");
    }

    private static void probarExtraer(AccidenteController accidenteController) {
        int idAccidente = 1;
        AccidenteDTO accidente = accidenteController.extraer(idAccidente);
        if (accidente != null) {
            System.out.println("\nAccidente con ID " + idAccidente + ":");
            System.out.println(accidente);
        } else {
            System.out.println("No se encontró un accidente con el ID " + idAccidente);
        }
    }

    private static void probarExtraerTodo(AccidenteController accidenteController) {
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

    private static void probarModificar(AccidenteController accidenteController) {
        AccidenteDTO accidenteAModificar = accidenteController.extraer(1);
        if (accidenteAModificar != null) {
            accidenteAModificar.setUbicacion("Nueva ubicación");
            accidenteAModificar.setFecha_del_accidente(new java.sql.Date(System.currentTimeMillis()));
            accidenteAModificar.setLegajo(1);
            accidenteAModificar.setCodigo_motivo(2);
            accidenteAModificar.setCodigo_tipo_accidente(2);
            accidenteAModificar.setIzqder(1);
            accidenteAModificar.setCodigo(3);

            if (accidenteController.modificar(accidenteAModificar)) {
                System.out.println("Accidente modificado con éxito.");
            } else {
                System.out.println("Error al modificar accidente.");
            }
        } else {
            System.out.println("No se encontró el accidente a modificar.");
        }
    }

    private static void probarEliminar(AccidenteController accidenteController) {
        // Supongamos que el accidente a eliminar tiene el número 1
        int idAccidente = 3;

        // Primero, verifica si el accidente existe
        AccidenteDTO accidenteAEliminar = accidenteController.extraer(idAccidente);
        if (accidenteAEliminar != null) {
            // Intenta eliminar el accidente
            if (accidenteController.eliminar(accidenteAEliminar)) {
                System.out.println("Accidente eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar accidente.");
            }
        } else {
            System.out.println("No se encontró el accidente con ID " + idAccidente + " para eliminar.");
        }
    }

}