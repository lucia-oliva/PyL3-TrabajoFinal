/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package unlar.edu.ar.paradigma.gui.forms;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import unlar.edu.ar.paradigma.controladores.MotivoController;
import unlar.edu.ar.paradigma.controladores.SetConexion;
import unlar.edu.ar.paradigma.gui.forms.abm.AddMotivo;
import unlar.edu.ar.paradigma.gui.forms.abm.ModMotivo;
import unlar.edu.ar.paradigma.gui.forms.grillas.GrillaMotivo;
import unlar.edu.ar.paradigma.objetos.Motivo;

/**
 *
 * @author lucia
 */
public class FormularioMotivo extends javax.swing.JPanel {

        private MotivoController motivoController;
    /**
     * Creates new form FormularioMotivo
     */
    public FormularioMotivo() {
        
        initComponents();
        initCustomComponents();
        motivoController = new MotivoController();
        try {
            Connection connection = SetConexion.getConnection();
            motivoController.setConexion(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        actualizarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grillaMotivo = new unlar.edu.ar.paradigma.gui.forms.grillas.GrillaMotivo();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jTable1.setModel(grillaMotivo);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(54, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AddMotivo registrarMotivo = new AddMotivo(this);
        registrarMotivo.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        // Obtener la fila seleccionada en la tabla
    int selectedRow = jTable1.getSelectedRow();
    
    if (selectedRow >= 0) {
        // Obtener el c�digo del motivo desde la tabla
        Integer codigo = (Integer) jTable1.getValueAt(selectedRow, 0);
        
        // Confirmar la eliminaci�n
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Estas seguro de que deseas eliminar el motivo con codigo " + codigo + "?",
                "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Llamar al m�todo en MotivoController para eliminar el motivo
            Motivo motivo = motivoController.extraer(codigo);
            if (motivo != null) {
                boolean eliminado = motivoController.eliminar(motivo);
                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Motivo eliminado con exito", "exito", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTabla(); // Actualizar la tabla para reflejar los cambios
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el motivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione un motivo para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //redirigir a nuevo panel nashe
        int selectedRow = jTable1.getSelectedRow();
    if (selectedRow >= 0) {
        // Obtener el legajo del empleado seleccionado
        Integer codigo = (Integer) jTable1.getValueAt(selectedRow, 0);
        // Llamar al metodo del controlador para obtener el empleado por legajo
        Motivo motivo = motivoController.extraer(codigo);
        if (motivo != null) {
            // Crear el formulario de modificación y pasarle el empleado
            ModMotivo modificarMotivo = new ModMotivo(motivo,this);
            modificarMotivo.setVisible(true);
             actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Motivo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un motivo", "Warning", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private unlar.edu.ar.paradigma.gui.forms.grillas.GrillaMotivo grillaMotivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    
    public void actualizarTabla() {
        List<Motivo> motivos = motivoController.extraerTodo();
        if (motivos != null && !motivos.isEmpty()) {
            cargarMotivos(motivos);
        } else {
            System.out.println("No hay motivos para mostrar");
        }
    }
    
        private void cargarMotivos(List<Motivo> motivos) {
        GrillaMotivo model = new GrillaMotivo();
        jTable1.setModel(model);
        model.setRowCount(0);
        model.setDatos(new ArrayList<>(motivos));
    }
    
    private void initCustomComponents() {
        jButton1.setText("Agregar");
        jButton2.setText("Modificar");
        jButton3.setText("Eliminar");
        jButton4.setText("Cerrar");
   
        
        revalidate();
        repaint();
    }
}
