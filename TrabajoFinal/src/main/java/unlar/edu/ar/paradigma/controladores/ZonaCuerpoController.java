package unlar.edu.ar.paradigma.controladores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import unlar.edu.ar.paradigma.objetos.ZonaCuerpo;

public class ZonaCuerpoController implements IABMController<Integer, ZonaCuerpo> {

    private Connection connection;

    @Override
    public boolean setConexion(Connection connection) {
        this.connection = connection;
        return this.connection != null;
    }

    @Override
    public List<ZonaCuerpo> extraerTodo() {
        List<ZonaCuerpo> listaZonasCuerpo = new ArrayList<>();
        String query = "SELECT * FROM ZonaCuerpo";

        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                ZonaCuerpo zonaCuerpo = new ZonaCuerpo(
                        rs.getInt("id_zona"),
                        rs.getInt("codigo"),
                        rs.getInt("izqDer"));
                listaZonasCuerpo.add(zonaCuerpo);
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer zonas de cuerpo: " + e.getMessage());
        }
        return listaZonasCuerpo;
    }

    @Override
    public ZonaCuerpo extraer(Integer id) {
        String query = "SELECT * FROM ZonaCuerpo WHERE id_zona = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new ZonaCuerpo(
                        rs.getInt("id_zona"),
                        rs.getInt("codigo"),
                        rs.getInt("izqDer"));
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer zona de cuerpo con ID " + id + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean modificar(ZonaCuerpo entidad) {
        String query = "UPDATE ZonaCuerpo SET codigo = ?, izqDer = ? WHERE id_zona = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entidad.getCodigo());
            statement.setInt(2, entidad.getIzqder());
            statement.setInt(3, entidad.getId_zona());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar zona de cuerpo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(ZonaCuerpo zonaCuerpo) {
        String deleteAccidenteZonaCuerpo = "DELETE FROM AccidenteZonaCuerpo WHERE id_zona = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteAccidenteZonaCuerpo)) {
            statement.setInt(1, zonaCuerpo.getId_zona());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar registros dependientes en AccidenteZonaCuerpo: " + e.getMessage());
            return false;
        }

        String query = "DELETE FROM ZonaCuerpo WHERE id_zona = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, zonaCuerpo.getId_zona());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar zona de cuerpo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean crear(ZonaCuerpo zonaCuerpo) {
        String query = "INSERT INTO ZonaCuerpo (id_zona, codigo, izqDer) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, zonaCuerpo.getId_zona());
            statement.setInt(2, zonaCuerpo.getCodigo());
            statement.setInt(3, zonaCuerpo.getIzqder());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected <= 0) {
                System.err.println("Error al insertar la zona de cuerpo.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error al crear zona de cuerpo: " + e.getMessage());
            return false;
        }

        return true;
    }
}
