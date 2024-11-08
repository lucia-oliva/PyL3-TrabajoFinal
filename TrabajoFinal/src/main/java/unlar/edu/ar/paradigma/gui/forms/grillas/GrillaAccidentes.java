package unlar.edu.ar.paradigma.gui.forms.grillas;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import unlar.edu.ar.paradigma.objetos.Accidente;

public class GrillaAccidentes extends AbstractTableModel {

    private ArrayList<Accidente> datos = new ArrayList<Accidente>();
    private String[] columnas = { "Numero", "fecha", "ubicacion", "legajo", "codigo_motivo", "codigo_tipo_accidente",
            "izquierdo o Derecho" };

    public GrillaAccidentes() {
    }

    public GrillaAccidentes(ArrayList<Accidente> datos) {
        setDatos(datos);
    }

    public void setDatos(ArrayList<Accidente> datos) {
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
        Accidente accidente = datos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return accidente.getNumero();
            case 1:
                return accidente.getFecha_del_accidente();
            case 2:
                return accidente.getUbicacion();
            case 3:
                return accidente.getLegajo();
            case 4:
                return accidente.getCodigo_motivo();
            case 5:
                return accidente.getCodigo_tipo_accidente();
            case 6:
                return accidente.getIzqDer();
            default:
                return "";
        }

    }

    @Override // Devuelvo el nombre de la columna
    public String getColumnName(int column) {
        return columnas[column]; // Generated from
                                 // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
