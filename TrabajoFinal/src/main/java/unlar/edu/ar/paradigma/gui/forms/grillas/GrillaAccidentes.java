package unlar.edu.ar.paradigma.gui.forms.grillas;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import unlar.edu.ar.paradigma.objetos.AccidenteDTO;

public class GrillaAccidentes extends DefaultTableModel {

    private ArrayList<AccidenteDTO> datos = new ArrayList<>();
    private String[] columnas = { "Numero", "Fecha", "Ubicacion", "Legajo del empleado", "Motivo (id)",
            "Tipo de accidente (ID)",
            "id_zona" };

    public GrillaAccidentes() {
        this.datos = new ArrayList<>();
    }

    public GrillaAccidentes(ArrayList<AccidenteDTO> datos) {
        setDatos(datos);
    }

    public void setDatos(ArrayList<AccidenteDTO> datos) {
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
        AccidenteDTO accidente = datos.get(rowIndex);
        if (datos == null || rowIndex >= datos.size()) {
        return null; // Evitar acceso a un índice fuera de los límites si datos es null
    }

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
    
    public AccidenteDTO getEmpleadoByID(int numero) {
    for (AccidenteDTO accidente : datos) {
        if (accidente.getNumero() == numero) {
            return accidente;
        }
    }
    return null;
    
}


}
