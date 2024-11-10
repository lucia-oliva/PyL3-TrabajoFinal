package unlar.edu.ar.paradigma.gui.forms.grillas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import unlar.edu.ar.paradigma.objetos.ParteCuerpo;

public class GrillaParteCuerpo extends AbstractTableModel {

    private ArrayList<ParteCuerpo> datos = new ArrayList<>();
    private String[] columnas = { "Codigo", "Parte" };

    public GrillaParteCuerpo() {
    }

    public GrillaParteCuerpo(ArrayList<ParteCuerpo> datos) {
        setDatos(datos);
    }

    public void setDatos(ArrayList<ParteCuerpo> datos) {
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
        ParteCuerpo parteCuerpo = datos.get(rowIndex);
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
}
