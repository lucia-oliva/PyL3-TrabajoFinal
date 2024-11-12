package unlar.edu.ar.paradigma.gui.forms.grillas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import unlar.edu.ar.paradigma.objetos.Empleado;

public class GrillaEmpleado extends DefaultTableModel {

    private ArrayList<Empleado> datos = new ArrayList<>();
    private String[] columnas = { "Legajo", "Apellido y Nombre" };

    public GrillaEmpleado() {
        this.datos = new ArrayList<>();
    }

    public GrillaEmpleado(ArrayList<Empleado> datos) {
        setDatos(datos);
    }

    public void setDatos(ArrayList<Empleado> datos) {
        this.datos = datos;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
         if (datos == null) {
        return 0; // Si datos es null, no hay filas en la tabla
    }
    return datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empleado empleado = datos.get(rowIndex);
 if (datos == null || rowIndex >= datos.size()) {
        return null; // Evitar acceso a un índice fuera de los límites si datos es null
    }
    switch (columnIndex) {
        case 0:
            return empleado.getLegajo();
        case 1:
            return empleado.getApellido_nombre();
        default:
            return "";
    }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
