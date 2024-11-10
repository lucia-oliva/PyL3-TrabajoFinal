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
        String query = "SELECT a.numero, a.fecha_del_accidente, a.ubicacion, a.legajo, " +
                "a.codigo_motivo, a.codigo_tipo_accidente, a.izqDer, " +
                "z.zona_cuerpo, p.parte_cuerpo " +
                "FROM accidente a " +
                "INNER JOIN zonacuerpo z ON a.codigo_zona_cuerpo = z.codigo " +
                "INNER JOIN partecuerpo p ON a.codigo_parte_cuerpo = p.codigo";

        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                AccidenteDTO accidente = new AccidenteDTO(
                        rs.getInt("numero"),
                        rs.getDate("fecha_del_accidente"),
                        rs.getString("ubicacion"),
                        rs.getInt("legajo"),
                        rs.getInt("codigo_motivo"),
                        rs.getInt("codigo_tipo_accidente"),
                        rs.getInt("izqder"),
                        rs.getInt("codigo"));
                listaAccidentes.add(accidente);
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer todos los accidentes: " + e.getMessage());
        }
        return listaAccidentes;
    }

    @Override
    public AccidenteDTO extraer(Integer id) {
        String query = "SELECT a.numero, a.fecha_del_accidente, " +
                "a.ubicacion, a.legajo, a.codigo_motivo, " +
                "a.codigo_tipo_accidente, " +
                "zc.izqder, pc.codigo " +
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
                        rs.getInt("izqder"),
                        rs.getInt("codigo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer accidente con ID " + id + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean crear(AccidenteDTO entidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crear'");
    }

    @Override
    public boolean modificar(AccidenteDTO entidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public boolean eliminar(AccidenteDTO entidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

}
