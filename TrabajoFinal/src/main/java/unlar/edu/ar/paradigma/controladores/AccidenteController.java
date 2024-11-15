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
    public AccidenteDTO extraer(Integer ID) {
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
            statement.setInt(1, ID);
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
            System.err.println("Error al extraer accidente con ID " + ID + ": " + e.getMessage());
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

    public List<String> obtenerEmpleados() {
        List<String> empleados = new ArrayList<>();
        String query = "SELECT apellido_nombre FROM empleado";
        
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                empleados.add(rs.getString("apellido_nombre"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener empleados: " + e.getMessage());
        }
        return empleados;
    }

    // Método para llenar comboBox con motivos de accidente
    public List<String> obtenerMotivos() {
        List<String> motivos = new ArrayList<>();
        String query = "SELECT motivo FROM motivo";
        
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                motivos.add(rs.getString("motivo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener motivos: " + e.getMessage());
        }
        return motivos;
    }

    // Método para llenar comboBox con partes del cuerpo involucradas en accidentes
    public List<String> obtenerPartesCuerpo() {
        List<String> partesCuerpo = new ArrayList<>();
        String query = "SELECT parte FROM partecuerpo";
        
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                partesCuerpo.add(rs.getString("parte"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener partes del cuerpo: " + e.getMessage());
        }
        return partesCuerpo;
    }


    public int obtenerIdEmpleado(String empleado) {
        int idEmpleado = -1; // Valor por defecto si no se encuentra
    
        String query = "SELECT legajo FROM empleado WHERE apellido_nombre = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, empleado);
            ResultSet rs = statement.executeQuery();
    
            if (rs.next()) {
                idEmpleado = rs.getInt("legajo");  // Supongo que 'legajo' es el identificador único
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el ID del empleado: " + e.getMessage());
        }
    
        return idEmpleado;
    }
    
    public int obtenerIdMotivo(String motivo) {
        int idMotivo = -1; // Valor por defecto si no se encuentra
        String query = "SELECT codigo FROM Motivo WHERE motivo = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, motivo);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                idMotivo = rs.getInt("codigo");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el ID del motivo: " + e.getMessage());
        }
        return idMotivo;
    }

    public int obtenerIdParteCuerpo(String parteCuerpo) {
        int idParteCuerpo = -1; // Valor por defecto si no se encuentra
        String query = "SELECT codigo FROM ParteCuerpo WHERE parte = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, parteCuerpo);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                idParteCuerpo = rs.getInt("codigo");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el ID de la parte del cuerpo: " + e.getMessage());
        }
        return idParteCuerpo;
    }
        

    public int obtenerNuevaId() {
        int nuevoNumero = -1; // Valor por defecto si no se encuentra un valor válido
    
        String query = "SELECT MAX(numero) + 1 FROM Accidente";
    
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                nuevoNumero = rs.getInt(1);
                // Si el MAX(numero) es NULL (es decir, si no hay registros previos), empezamos desde 1
                if (nuevoNumero == 0) {
                    nuevoNumero = 1;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el nuevo número de accidente: " + e.getMessage());
        }
    
        return nuevoNumero;
    }

    public List<String> obtenerTiposAccidente() {
        List<String> tiposAccidente = new ArrayList<>();
        String query = "SELECT tipo FROM tipoaccidente";
        
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                tiposAccidente.add(rs.getString("tipo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tipos de accidente: " + e.getMessage());
        }
        return tiposAccidente;
    }
    
    public int obtenerIdTipoAccidente(String tipoAccidente) {
        int idTipoAccidente = -1; // Valor por defecto si no se encuentra
        String query = "SELECT ta.codigo FROM tipoaccidente ta WHERE ta.tipo = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tipoAccidente);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                idTipoAccidente = rs.getInt("codigo");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el ID del tipo de accidente: " + e.getMessage());
        }
        
        return idTipoAccidente;
    }

    public List<String> obtenerPartesCuerpoPorZona(String zonaCuerpo) {
        List<String> partesCuerpo = new ArrayList<>();
        
        // Determinar el valor de izquierdaDerecha según la zona del cuerpo
        int izquierdaDerecha = "Izquierda".equalsIgnoreCase(zonaCuerpo) ? 0 : 
                               "Derecha".equalsIgnoreCase(zonaCuerpo) ? 1 : -1;
    
        // Validar el valor
        if (izquierdaDerecha == -1) {
            System.err.println("Zona del cuerpo no válida: " + zonaCuerpo);
            return partesCuerpo; // Retornar lista vacía si el valor no es válido
        }
    
        // Consulta SQL para obtener las partes del cuerpo correspondientes a la zona
        String query = "SELECT DISTINCT pc.parte FROM ParteCuerpo pc " +
                       "INNER JOIN ZonaCuerpo zc ON zc.codigo = pc.codigo " +
                       "WHERE zc.izqder = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, izquierdaDerecha); // Pasar el valor calculado
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                partesCuerpo.add(rs.getString("parte"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener partes del cuerpo para la zona: " + e.getMessage());
        }
    
        return partesCuerpo;
    }

    public int obtenerIdZonaCuerpo(String parteCuerpo, int zonaCuerpo) {
        int idZonaCuerpo = -1; // Valor por defecto si no se encuentra
        
        // Verificar si los parámetros recibidos son válidos
        if (parteCuerpo == null || parteCuerpo.isEmpty()) {
            System.err.println("Parte de cuerpo inválida: " + parteCuerpo);
            return idZonaCuerpo;
        }
        
        // Realizar la consulta para obtener el id_zona usando parteCuerpo y zonaCuerpo
        String query = "SELECT zc.id_zona " +
                       "FROM ZonaCuerpo zc " +
                       "INNER JOIN ParteCuerpo pc ON zc.codigo = pc.codigo " +
                       "WHERE pc.parte = ? AND zc.izqder = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, parteCuerpo);  // La parte del cuerpo (e.g., "Pierna")
            statement.setInt(2, zonaCuerpo);      // Izquierda (0) o Derecha (1)
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                idZonaCuerpo = rs.getInt("id_zona");
            } else {
                System.err.println("No se encontró la zona de cuerpo para la parte: " + parteCuerpo + " y zona: " + zonaCuerpo);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el ID de la zona de cuerpo: " + e.getMessage());
        }
        
        return idZonaCuerpo;
    }

    
}
