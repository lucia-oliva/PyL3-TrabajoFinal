package unlar.edu.ar.paradigma.gui.forms.grillas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import unlar.edu.ar.paradigma.objetos.Empleado;

public class GrillaEmpleado extends AbstractTableModel {

    private ArrayList<Empleado> datos = new ArrayList<>();
    private String[] columnas = { "Legajo", "Apellido y Nombre" };

    public GrillaEmpleado() {
    }

    public GrillaEmpleado(ArrayList<Empleado> datos) {
        setDatos(datos);
    }

    public void setDatos(ArrayList<Empleado> datos) {
        this.datos = datos;
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empleado empleado = datos.get(rowIndex);
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
