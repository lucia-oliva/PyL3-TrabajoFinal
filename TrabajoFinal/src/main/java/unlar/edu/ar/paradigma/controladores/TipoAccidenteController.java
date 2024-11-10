package unlar.edu.ar.paradigma.controladores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import unlar.edu.ar.paradigma.objetos.TipoAccidente;

public class TipoAccidenteController implements IABMController<Integer, TipoAccidente> {

    private Connection connection;

    @Override
    public boolean setConexion(Connection connection) {
        this.connection = connection;
        return this.connection != null;
    }

    @Override
    public List<TipoAccidente> extraerTodo() {
        List<TipoAccidente> listaTipoAccidentes = new ArrayList<>();
        String query = "SELECT * FROM TipoAccidente";

        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                TipoAccidente tipoAccidente = new TipoAccidente(
                        rs.getInt("codigo"),
                        rs.getString("tipo"));
                listaTipoAccidentes.add(tipoAccidente);
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer tipos de accidente: " + e.getMessage());
        }
        return listaTipoAccidentes;
    }

    @Override
    public TipoAccidente extraer(Integer id) {
        String query = "SELECT * FROM TipoAccidente WHERE codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new TipoAccidente(
                        rs.getInt("codigo"),
                        rs.getString("tipo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer tipo de accidente con ID " + id + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean modificar(TipoAccidente entidad) {
        String query = "UPDATE TipoAccidente SET tipo = ? WHERE codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entidad.getTipo());
            statement.setInt(2, entidad.getCodigo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar tipo de accidente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(TipoAccidente tipoAccidente) {
        String query = "DELETE FROM TipoAccidente WHERE codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tipoAccidente.getCodigo());
            statement.setString(2, tipoAccidente.getTipo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar tipo de accidente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean crear(TipoAccidente tipoAccidente) {
        String query = "INSERT INTO TipoAccidente (codigo, tipo) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tipoAccidente.getCodigo());
            statement.setString(2, tipoAccidente.getTipo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear tipo de accidente: " + e.getMessage());
            return false;
        }
    }
}
