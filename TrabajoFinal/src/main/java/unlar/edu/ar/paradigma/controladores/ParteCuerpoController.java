package unlar.edu.ar.paradigma.controladores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import unlar.edu.ar.paradigma.objetos.ParteCuerpo;

public class ParteCuerpoController implements IABMController<Integer, ParteCuerpo> {

    private Connection connection;

    @Override
    public boolean setConexion(Connection connection) {
        this.connection = connection;
        return this.connection != null;
    }

    @Override
    public List<ParteCuerpo> extraerTodo() {
        List<ParteCuerpo> listaPartesCuerpo = new ArrayList<>();
        String query = "SELECT * FROM ParteCuerpo";

        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                ParteCuerpo parteCuerpo = new ParteCuerpo(
                        rs.getInt("codigo"),
                        rs.getString("parte"));
                listaPartesCuerpo.add(parteCuerpo);
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer partes del cuerpo: " + e.getMessage());
        }
        return listaPartesCuerpo;
    }

    @Override
    public ParteCuerpo extraer(Integer ID) {
        String query = "SELECT * FROM ParteCuerpo WHERE codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new ParteCuerpo(
                        rs.getInt("codigo"),
                        rs.getString("parte"));
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer parte de cuerpo con ID " + ID + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean modificar(ParteCuerpo entidad) {
        String query = "UPDATE ParteCuerpo SET parte = ? WHERE codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entidad.getParte());
            statement.setInt(2, entidad.getCodigo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar parte del cuerpo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(ParteCuerpo parteCuerpo) {
        String query = "DELETE FROM ParteCuerpo WHERE codigo = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, parteCuerpo.getCodigo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar parte del cuerpo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean crear(ParteCuerpo parteCuerpo) {
        String query = "INSERT INTO ParteCuerpo (codigo, parte) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, parteCuerpo.getCodigo());
            statement.setString(2, parteCuerpo.getParte());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear parte del cuerpo: " + e.getMessage());
            return false;
        }
    }
}
