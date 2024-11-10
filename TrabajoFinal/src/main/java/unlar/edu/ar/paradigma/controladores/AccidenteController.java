package unlar.edu.ar.paradigma.controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import unlar.edu.ar.paradigma.objetos.AccidenteDTO;

public class AccidenteController implements IABMController<Integer, AccidenteDTO> {

    private Connection connection;

    @Override
    public boolean setConexion(Connection connection) {
        this.connection = connection;
        return this.connection != null;
    }

    @Override
    public List<AccidenteDTO> extraerTodo() {
        List<AccidenteDTO> listaAccidentes = new ArrayList<>();
        String query = "SELECT a.numero, a.fecha_del_accidente, " +
                "a.ubicacion, a.legajo, a.codigo_motivo, " +
                "a.codigo_tipo_accidente, " +
                "zc.id_zona, pc.codigo " +
                "FROM Accidente a " +
                "INNER JOIN accidentezonacuerpo azc ON azc.numero_accidente = a.numero " +
                "INNER JOIN ZonaCuerpo zc ON zc.id_zona = azc.id_zona " +
                "INNER JOIN ParteCuerpo pc ON pc.codigo = zc.codigo";

        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                do {
                    AccidenteDTO accidente = new AccidenteDTO(
                            rs.getInt("numero"),
                            rs.getDate("fecha_del_accidente"),
                            rs.getString("ubicacion"),
                            rs.getInt("legajo"),
                            rs.getInt("codigo_motivo"),
                            rs.getInt("codigo_tipo_accidente"),
                            rs.getInt("id_zona"),
                            rs.getInt("codigo"));
                    listaAccidentes.add(accidente);
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer todos los accidentes: " + e.getMessage());
            return new ArrayList<>();
        }
        return listaAccidentes;
    }

    @Override
    public AccidenteDTO extraer(Integer id) {
        String query = "SELECT a.numero, a.fecha_del_accidente, " +
                "a.ubicacion, a.legajo, a.codigo_motivo, " +
                "a.codigo_tipo_accidente, " +
                "zc.id_zona, pc.codigo " +
                "FROM Accidente a " +
                "INNER JOIN accidentezonacuerpo azc ON azc.numero_accidente = a.numero " +
                "INNER JOIN ZonaCuerpo zc ON zc.id_zona = azc.id_zona " +
                "INNER JOIN ParteCuerpo pc ON pc.codigo = zc.codigo " +
                "WHERE a.numero = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new AccidenteDTO(
                        rs.getInt("numero"),
                        rs.getDate("fecha_del_accidente"),
                        rs.getString("ubicacion"),
                        rs.getInt("legajo"),
                        rs.getInt("codigo_motivo"),
                        rs.getInt("codigo_tipo_accidente"),
                        rs.getInt("id_zona"),
                        rs.getInt("codigo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer accidente con ID " + id + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean modificar(AccidenteDTO entidad) {
        String updateAccidente = "UPDATE Accidente SET " +
                "fecha_del_accidente = ?, " +
                "ubicacion = ?, " +
                "legajo = ?, " +
                "codigo_motivo = ?, " +
                "codigo_tipo_accidente = ? " +
                "WHERE numero = ?";

        String deleteAccidenteZonaCuerpo = "DELETE FROM AccidenteZonaCuerpo " +
                "WHERE numero_accidente = ? AND id_zona = " +
                "(SELECT id_zona FROM ZonaCuerpo WHERE codigo = ? AND id_zona = ?)";

        String insertAccidenteZonaCuerpo = "INSERT INTO AccidenteZonaCuerpo (numero_accidente, id_zona) " +
                "VALUES (?, (SELECT id_zona FROM ZonaCuerpo WHERE codigo = ? AND id_zona = ?))";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement pstmtAccidente = connection.prepareStatement(updateAccidente)) {
                pstmtAccidente.setDate(1, new java.sql.Date(entidad.getFecha_del_accidente().getTime()));
                pstmtAccidente.setString(2, entidad.getUbicacion());
                pstmtAccidente.setInt(3, entidad.getLegajo());
                pstmtAccidente.setInt(4, entidad.getCodigo_motivo());
                pstmtAccidente.setInt(5, entidad.getCodigo_tipo_accidente());
                pstmtAccidente.setInt(6, entidad.getNumero());
                pstmtAccidente.executeUpdate();
            }

            try (PreparedStatement pstmtDeleteAccidenteZonaCuerpo = connection
                    .prepareStatement(deleteAccidenteZonaCuerpo)) {
                pstmtDeleteAccidenteZonaCuerpo.setInt(1, entidad.getNumero());
                pstmtDeleteAccidenteZonaCuerpo.setInt(2, entidad.getCodigo());
                pstmtDeleteAccidenteZonaCuerpo.setInt(3, entidad.getId_zona());
                pstmtDeleteAccidenteZonaCuerpo.executeUpdate();
            }

            try (PreparedStatement pstmtInsertAccidenteZonaCuerpo = connection
                    .prepareStatement(insertAccidenteZonaCuerpo)) {
                pstmtInsertAccidenteZonaCuerpo.setInt(1, entidad.getNumero());
                pstmtInsertAccidenteZonaCuerpo.setInt(2, entidad.getCodigo());
                pstmtInsertAccidenteZonaCuerpo.setInt(3, entidad.getId_zona());
                pstmtInsertAccidenteZonaCuerpo.executeUpdate();
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: " + ex.getMessage());
            }
            System.err.println("Error al modificar accidente: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error al restablecer el auto-commit: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean eliminar(AccidenteDTO accidente) {
        String deleteAccidenteZonaCuerpo = "DELETE FROM AccidenteZonaCuerpo WHERE numero_accidente = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteAccidenteZonaCuerpo)) {
            statement.setInt(1, accidente.getNumero());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar registros dependientes: " + e.getMessage());
            return false;
        }

        String query = "DELETE FROM Accidente WHERE numero = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, accidente.getNumero());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar accidente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean crear(AccidenteDTO accidente) {
        String insertAccidenteQuery = "INSERT INTO Accidente (numero, fecha_del_accidente, ubicacion, legajo, codigo_motivo, codigo_tipo_accidente) VALUES (?, ?, ?, ?, ?, ?)";
        String insertAccidenteZonaCuerpoQuery = "INSERT INTO AccidenteZonaCuerpo (numero_accidente, id_zona) VALUES (?, ?)";

        try (PreparedStatement insertAccidenteStmt = connection.prepareStatement(insertAccidenteQuery);
                PreparedStatement insertAccidenteZonaCuerpoStmt = connection
                        .prepareStatement(insertAccidenteZonaCuerpoQuery)) {

            insertAccidenteStmt.setInt(1, accidente.getNumero());
            insertAccidenteStmt.setDate(2, accidente.getFecha_del_accidente());
            insertAccidenteStmt.setString(3, accidente.getUbicacion());
            insertAccidenteStmt.setInt(4, accidente.getLegajo());
            insertAccidenteStmt.setInt(5, accidente.getCodigo_motivo());
            insertAccidenteStmt.setInt(6, accidente.getCodigo_tipo_accidente());

            int rowsAccidente = insertAccidenteStmt.executeUpdate();

            if (rowsAccidente <= 0) {
                System.err.println("Error al insertar el accidente.");
                return false;
            }

            insertAccidenteZonaCuerpoStmt.setInt(1, accidente.getNumero());
            insertAccidenteZonaCuerpoStmt.setInt(2, accidente.getId_zona());
            int rowsZonaCuerpo = insertAccidenteZonaCuerpoStmt.executeUpdate();

            if (rowsZonaCuerpo <= 0) {
                System.err.println("Error al insertar la zona asociada al accidente.");
                return false;
            }

            return true;

        } catch (SQLException e) {
            System.err.println("Error al crear el accidente: " + e.getMessage());
            return false;
        }
    }

}
