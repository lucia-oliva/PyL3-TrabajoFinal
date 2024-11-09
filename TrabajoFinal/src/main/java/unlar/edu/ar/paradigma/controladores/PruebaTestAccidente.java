/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unlar.edu.ar.paradigma.controladores;

import unlar.edu.ar.paradigma.objetos.Accidente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author lucia
 */
public class PruebaTestAccidente {
    public static void main(String[] args) {
        try {
            // Establecer la conexión
            Connection connection = SetConexion.getConnection();
            
            // Crear una instancia de AccidenteController y establecer la conexión
            AccidenteController controller = new AccidenteController();
            controller.setConexion(connection);
            
            // Llamar al método extraerTodo y obtener la lista de accidentes
            List<Accidente> accidentes = controller.extraerTodo();
            
            // Mostrar los accidentes en consola
            if (accidentes.isEmpty()) {
                System.out.println("No se encontraron accidentes.");
            } else {
                for (Accidente accidente : accidentes) {
                    System.out.println("Número: " + accidente.getNumero() +
                            ", Fecha: " + accidente.getFecha_del_accidente() +
                            ", Ubicación: " + accidente.getUbicacion());
                }
            }
            
        } catch (SQLException e) {
        }
    }
}

