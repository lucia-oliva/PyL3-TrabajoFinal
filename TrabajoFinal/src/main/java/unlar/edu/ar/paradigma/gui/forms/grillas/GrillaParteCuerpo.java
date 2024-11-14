package unlar.edu.ar.paradigma.gui.forms.grillas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import unlar.edu.ar.paradigma.objetos.ParteCuerpo;

public class GrillaParteCuerpo extends DefaultTableModel {

    private ArrayList<ParteCuerpo> datos = new ArrayList<>();
    private String[] columnas = { "Codigo", "Parte" };

    public GrillaParteCuerpo() {
        this.datos = new ArrayList<>();
    }

    public GrillaParteCuerpo(ArrayList<ParteCuerpo> datos) {
        setDatos(datos);
    }

    public void setDatos(ArrayList<ParteCuerpo> datos) {
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
        
        ParteCuerpo parteCuerpo = datos.get(rowIndex);
        if (datos == null || rowIndex >= datos.size()) {
            return null;
        }
        switch (columnIndex) {
            case 0:
                return parteCuerpo.getCodigo();
            case 1:
                return parteCuerpo.getParte();
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
                datos.add(new ParteCuerpo()); // Agrega instancias vacías si rowCount es mayor
            }
        }
        fireTableDataChanged(); // Actualiza la vista de la tabla
    }

    // Método para obtener un Motivo por su código
    public ParteCuerpo getMotivoByCodigo(int codigo) {
        for (ParteCuerpo parteCuerpo : datos) {
            if (parteCuerpo.getCodigo() == codigo) {
                return parteCuerpo;
            }
        }
        return null;
    }
}
    
