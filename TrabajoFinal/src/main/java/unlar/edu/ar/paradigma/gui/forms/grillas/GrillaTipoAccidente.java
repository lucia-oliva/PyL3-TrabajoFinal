package unlar.edu.ar.paradigma.gui.forms.grillas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import unlar.edu.ar.paradigma.objetos.TipoAccidente;

public class GrillaTipoAccidente extends AbstractTableModel {

    private ArrayList<TipoAccidente> datos = new ArrayList<>();
    private String[] columnas = { "Codigo", "Tipo" };

    public GrillaTipoAccidente() {
    }

    public GrillaTipoAccidente(ArrayList<TipoAccidente> datos) {
        setDatos(datos);
    }

    public void setDatos(ArrayList<TipoAccidente> datos) {
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
        TipoAccidente tipoAccidente = datos.get(rowIndex);
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
}
