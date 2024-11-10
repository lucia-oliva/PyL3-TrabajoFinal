package unlar.edu.ar.paradigma.controladores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import unlar.edu.ar.paradigma.objetos.Empleado;

public class EmpleadoController implements IABMController<Integer, Empleado> {

    private Connection connection;

    @Override
    public boolean setConexion(Connection connection) {
        this.connection = connection;
        return this.connection != null;
    }

    @Override
    public List<Empleado> extraerTodo() {
        List<Empleado> listaEmpleados = new ArrayList<>();
        String query = "SELECT * FROM Empleado";

        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getInt("legajo"),
                        rs.getString("apellido_nombre"));
                listaEmpleados.add(empleado);
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer empleados: " + e.getMessage());
        }
        return listaEmpleados;
    }

    @Override
    public Empleado extraer(Integer ID) {
        String query = "SELECT * FROM Empleado WHERE legajo = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Empleado(
                        rs.getInt("legajo"),
                        rs.getString("apellido_nombre"));
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer empleado con ID " + ID + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean modificar(Empleado entidad) {
        String query = "UPDATE Empleado SET apellido_nombre = ? WHERE legajo = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entidad.getApellido_nombre());
            statement.setInt(2, entidad.getLegajo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar empleado: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Empleado empleado) {
        String deleteAccidenteZonaCuerpoQuery = "DELETE FROM AccidenteZonaCuerpo WHERE numero_accidente IN (SELECT numero FROM Accidente WHERE legajo = ?)";
        try (PreparedStatement statement = connection.prepareStatement(deleteAccidenteZonaCuerpoQuery)) {
            statement.setInt(1, empleado.getLegajo());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar registros dependientes en AccidenteZonaCuerpo: " + e.getMessage());
            return false;
        }

        String deleteAccidenteQuery = "DELETE FROM Accidente WHERE legajo = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteAccidenteQuery)) {
            statement.setInt(1, empleado.getLegajo());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar accidentes dependientes: " + e.getMessage());
            return false;
        }

        String deleteEmpleadoQuery = "DELETE FROM Empleado WHERE legajo = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteEmpleadoQuery)) {
            statement.setInt(1, empleado.getLegajo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar empleado: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean crear(Empleado empleado) {
        String query = "INSERT INTO Empleado (legajo, apellido_nombre) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, empleado.getLegajo());
            statement.setString(2, empleado.getApellido_nombre());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear empleado: " + e.getMessage());
            return false;
        }
    }
}
