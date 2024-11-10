package unlar.edu.ar.paradigma.controladores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import unlar.edu.ar.paradigma.objetos.Motivo;

public class MotivoController implements IABMController<Integer, Motivo> {

    private Connection connection;

    @Override
    public boolean setConexion(Connection connection) {
        this.connection = connection;
        return this.connection != null;
    }

    @Override
    public List<Motivo> extraerTodo() {
        List<Motivo> listaMotivos = new ArrayList<>();
        String query = "SELECT * FROM Motivo";

        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Motivo motivo = new Motivo(
                        rs.getInt("codigo"),
                        rs.getString("motivo"));
                listaMotivos.add(motivo);
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer motivos: " + e.getMessage());
        }
        return listaMotivos;
    }

    @Override
    public Motivo extraer(Integer ID) {
        String query = "SELECT * FROM Motivo WHERE codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Motivo(
                        rs.getInt("codigo"),
                        rs.getString("motivo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer motivo con ID " + ID + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean modificar(Motivo entidad) {
        String query = "UPDATE Motivo SET motivo = ? WHERE codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entidad.getMotivo());
            statement.setInt(2, entidad.getCodigo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar motivo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Motivo motivo) {
        String query = "DELETE FROM Motivo WHERE codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, motivo.getCodigo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar motivo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean crear(Motivo motivo) {
        String query = "INSERT INTO Motivo (codigo, motivo) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, motivo.getCodigo());
            statement.setString(2, motivo.getMotivo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear motivo: " + e.getMessage());
            return false;
        }
    }
}
