package unlar.edu.ar.paradigma.gui.forms.grillas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import unlar.edu.ar.paradigma.objetos.Motivo;

public class GrillaMotivo extends AbstractTableModel {

    private ArrayList<Motivo> datos = new ArrayList<>();
    private String[] columnas = { "Codigo", "Motivo" };

    public GrillaMotivo() {
    }

    public GrillaMotivo(ArrayList<Motivo> datos) {
        setDatos(datos);
    }

    public void setDatos(ArrayList<Motivo> datos) {
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
        Motivo motivo = datos.get(rowIndex);
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
}
