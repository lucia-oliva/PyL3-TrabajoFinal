package unlar.edu.ar.paradigma.controladores;

import java.sql.Connection;
import java.util.List;

public interface IABMController <ID, Entidad>{
    
    boolean setConexion(Connection connection);

    public List<Entidad> extraerTodo();
    public Entidad extraer(ID id);
    public boolean crear(Entidad entidad);
    public boolean modificar(Entidad entidad);
    public boolean eliminar(Entidad entidad);
}
