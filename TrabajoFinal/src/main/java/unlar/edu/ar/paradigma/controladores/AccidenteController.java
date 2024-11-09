package unlar.edu.ar.paradigma.controladores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import unlar.edu.ar.paradigma.objetos.Accidente;
import java.util.ArrayList;



public class AccidenteController implements IABMController {

       private Connection connection;
    
    @Override
    public boolean setConexion(Connection connection) {
       
        this.connection = connection;
        return this.connection !=null;
    }

    @Override
    
    public List<Accidente> extraerTodo() {
        
        List<Accidente> accidentes = new ArrayList<>();
        String query = "SELECT * FROM accidente";
     
         try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Integer numero = resultSet.getInt("numero");
                String fecha = resultSet.getString("fecha_del_accidente");
                String ubicacion = resultSet.getString("ubicacion");
                Integer legajo = resultSet.getInt("legajo");
                Integer codigoMotivo = resultSet.getInt("codigo_motivo");
                Integer codigoTipoAccidente = resultSet.getInt("codigo_tipo_accidente");
                Integer izqDer = resultSet.getInt("izqDer");

                
                accidentes.add(new Accidente(numero, fecha, ubicacion, legajo, codigoMotivo, codigoTipoAccidente, izqDer));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accidentes;
    }

    @Override
    public Object extraer(Object id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'extraer'");
    }

    @Override
    public boolean crear(Object entidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crear'");
    }

    @Override
    public boolean modificar(Object entidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public boolean eliminar(Object entidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

}
