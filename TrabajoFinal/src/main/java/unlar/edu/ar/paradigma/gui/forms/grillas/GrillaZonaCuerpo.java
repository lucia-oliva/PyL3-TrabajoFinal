package unlar.edu.ar.paradigma.gui.forms.grillas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import unlar.edu.ar.paradigma.objetos.ZonaCuerpo;

public class GrillaZonaCuerpo extends DefaultTableModel {

    private ArrayList<ZonaCuerpo> datos = new ArrayList<>();
    private String[] columnas = { "ID Zona", "Codigo Parte Cuerpo", "Izquierdo o Derecho" };

    public GrillaZonaCuerpo() {
    }

    public GrillaZonaCuerpo(ArrayList<ZonaCuerpo> datos) {
        setDatos(datos);
    }

    public void setDatos(ArrayList<ZonaCuerpo> datos) {
        this.datos = datos;
    }

    @Override
    public int getRowCount() {
        if(datos == null) {
            return 0;
        }
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ZonaCuerpo zonaCuerpo = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return zonaCuerpo.getId_zona();
            case 1:
                return zonaCuerpo.getCodigo();
            case 2:
                return zonaCuerpo.getIzqder();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
