package unlar.edu.ar.paradigma.gui.forms.grillas;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import unlar.edu.ar.paradigma.objetos.AccidenteDTO;

public class GrillaAccidentes extends AbstractTableModel {

    private ArrayList<AccidenteDTO> datos = new ArrayList<>();
    private String[] columnas = { "Numero", "Fecha", "Ubicacion", "Legajo del empleado", "Motivo (id)",
            "Tipo de accidente (ID)",
            "izquierdo o Derecho" };

    public GrillaAccidentes() {
    }

    public GrillaAccidentes(ArrayList<AccidenteDTO> datos) {
        setDatos(datos);
    }

    public void setDatos(ArrayList<AccidenteDTO> datos) {
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
        AccidenteDTO accidente = datos.get(rowIndex);

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
                return accidente.getId_zona();
            case 7:
                return accidente.getCodigo();
            default:
                return "";
        }

    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

}
