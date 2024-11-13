package unlar.edu.ar.paradigma.gui.forms.grillas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import unlar.edu.ar.paradigma.objetos.TipoAccidente;

public class GrillaTipoAccidente extends DefaultTableModel {

    private ArrayList<TipoAccidente> datos = new ArrayList<>();
    private String[] columnas = { "Codigo", "Tipo" };

    public GrillaTipoAccidente() {
        this.datos = new ArrayList<>();
    }

    public GrillaTipoAccidente(ArrayList<TipoAccidente> datos) {
        setDatos(datos);
    }

    public void setDatos(ArrayList<TipoAccidente> datos) {
        this.datos = datos;
        fireTableDataChanged(); // Notifica a la tabla que los datos han cambiado
    }

    @Override
    public int getRowCount() {
        return datos == null ? 0 : datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        TipoAccidente tipoAccidente = datos.get(rowIndex);
        if (datos == null || rowIndex >= datos.size()) {
            return null;
        }
        switch (columnIndex) {
            case 0:
                return tipoAccidente.getCodigo();
            case 1:
                return tipoAccidente.getTipo();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
    
    
    @Override
    public void setRowCount(int rowCount) {
        if (rowCount < datos.size()) {
            datos.subList(rowCount, datos.size()).clear(); // Elimina filas extras si rowCount es menor
        } else {
            for (int i = datos.size(); i < rowCount; i++) {
                datos.add(new TipoAccidente()); // Agrega instancias vacías si rowCount es mayor
            }
        }
        fireTableDataChanged(); // Actualiza la vista de la tabla
    }

    // Método para obtener un Motivo por su código
    public TipoAccidente getMotivoByCodigo(int codigo) {
        for (TipoAccidente tipoAccidente : datos) {
            if (tipoAccidente.getCodigo() == codigo) {
                return tipoAccidente;
            }
        }
        return null;
    }
}
    
