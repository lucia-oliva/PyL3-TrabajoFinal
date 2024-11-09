package unlar.edu.ar.paradigma.controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import unlar.edu.ar.paradigma.objetos.Accidente;

public class AccidenteController implements IABMController<Integer, Accidente> {

    private Connection connection;

    @Override
    public boolean setConexion(Connection connection) {
        this.connection = connection;
        return this.connection != null;
    }

    @Override
    public List<Accidente> extraerTodo() {
        List<Accidente> listaAccidentes = new ArrayList<>();
        String query = "SELECT * FROM Accidente";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Accidente accidente = new Accidente(
                        rs.getInt("numero"),
                        rs.getDate("fecha_del_accidente"),
                        rs.getString("ubicacion"),
                        rs.getInt("legajo"),
                        rs.getInt("codigo_motivo"),
                        rs.getInt("codigo_tipo_accidente"),
                        rs.getInt("izqDer"));
                listaAccidentes.add(accidente);
            }
        } catch (SQLException e) {
        }
        return listaAccidentes;
    }

    @Override
    public Accidente extraer(Integer id) {
        String query = "SELECT * FROM Accidente WHERE numero = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Accidente(
                        rs.getInt("numero"),
                        rs.getDate("fecha_del_accidente"),
                        rs.getString("ubicacion"),
                        rs.getInt("legajo"),
                        rs.getInt("codigo_motivo"),
                        rs.getInt("codigo_tipo_accidente"),
                        rs.getInt("izqDer"));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public boolean crear(Accidente accidente) {
        String query = "INSERT INTO Accidente (numero, fecha_del_accidente, ubicacion, legajo, codigo_motivo, codigo_tipo_accidente, izqDer) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accidente.getNumero());
            statement.setDate(2, accidente.getFecha_del_accidente());
            statement.setString(3, accidente.getUbicacion());
            statement.setInt(4, accidente.getLegajo());
            statement.setInt(5, accidente.getCodigo_motivo());
            statement.setInt(6, accidente.getCodigo_tipo_accidente());
            statement.setInt(7, accidente.getIzqDer());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean modificar(Accidente accidente) {
        String query = "UPDATE Accidente SET fecha_del_accidente = ?, ubicacion = ?, legajo = ?, codigo_motivo = ?, codigo_tipo_accidente = ?, izqDer = ? WHERE numero = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, accidente.getFecha_del_accidente());
            statement.setString(2, accidente.getUbicacion());
            statement.setInt(3, accidente.getLegajo());
            statement.setInt(4, accidente.getCodigo_motivo());
            statement.setInt(5, accidente.getCodigo_tipo_accidente());
            statement.setInt(6, accidente.getIzqDer());
            statement.setInt(7, accidente.getNumero());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(Accidente accidente) {
        String query = "DELETE FROM Accidente WHERE numero = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accidente.getNumero());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
