package unlar.edu.ar.paradigma.gui.forms.grillas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import unlar.edu.ar.paradigma.objetos.Motivo;

public class GrillaMotivo extends DefaultTableModel {

    private ArrayList<Motivo> datos = new ArrayList<>();
    private String[] columnas = { "Codigo", "Motivo" };

    public GrillaMotivo() {
        this.datos = new ArrayList<>();
    }

    public GrillaMotivo(ArrayList<Motivo> datos) {
        setDatos(datos);
    }

    public void setDatos(ArrayList<Motivo> datos) {
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
        
        Motivo motivo = datos.get(rowIndex);
        if (datos == null || rowIndex >= datos.size()) {
            return null;
        }
        
        switch (columnIndex) {
            case 0:
                return motivo.getCodigo();
            case 1:
                return motivo.getMotivo();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    // Agrega el método para establecer el número de filas en la tabla
    public void setRowCount(int rowCount) {
        if (rowCount < datos.size()) {
            datos.subList(rowCount, datos.size()).clear(); // Elimina filas extras si rowCount es menor
        } else {
            for (int i = datos.size(); i < rowCount; i++) {
                datos.add(new Motivo()); // Agrega instancias vacías si rowCount es mayor
            }
        }
        fireTableDataChanged(); // Actualiza la vista de la tabla
    }

    // Método para obtener un Motivo por su código
    public Motivo getMotivoByCodigo(int codigo) {
        for (Motivo motivo : datos) {
            if (motivo.getCodigo() == codigo) {
                return motivo;
            }
        }
        return null;
    }
}