/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package unlar.edu.ar.paradigma.gui.forms.abm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import unlar.edu.ar.paradigma.controladores.SetConexion;
import unlar.edu.ar.paradigma.controladores.ZonaCuerpoController;
import unlar.edu.ar.paradigma.gui.forms.FormularioZonaCuerpo;
import unlar.edu.ar.paradigma.objetos.ZonaCuerpo;



/**
 *
 * @author facun
 */
public class AddZonaCuerpo extends javax.swing.JFrame {

    private ZonaCuerpoController zonaCuerpoController;
    private FormularioZonaCuerpo formularioZonaCuerpo;
    
    private static final String[] OPCIONES_ZONA_CUERPO = {"Izquierda", "Derecha"};
    
    /**
     * Creates new form AbmZonaCuerpo
     */
    public AddZonaCuerpo(FormularioZonaCuerpo formularioZonaCuerpo) {
        this.formularioZonaCuerpo = formularioZonaCuerpo;
        this.zonaCuerpoController = new ZonaCuerpoController();
        initComponents();
        initController();
        cargarOpcionesComboBoxes();

    }
    
    private void initController() {
        try {
            Connection connection = SetConexion.getConnection();
            zonaCuerpoController.setConexion(connection);
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
        }
    }
    
    private void cargarOpcionesComboBoxes() {
        jcbZonaCuerpo.setModel(new DefaultComboBoxModel<>(OPCIONES_ZONA_CUERPO));

        List<String> partesCuerpo = zonaCuerpoController.obtenerParteCuerpo();
        DefaultComboBoxModel<String> modeloPartesCuerpo = new DefaultComboBoxModel<>(partesCuerpo.toArray(new String[0]));
        jcbParteCuerpo.setModel(modeloPartesCuerpo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label2 = new java.awt.Label();
        jlZonaCuerpo = new javax.swing.JLabel();
        jlParteCuerpo = new javax.swing.JLabel();
        jbAceptar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jcbZonaCuerpo = new javax.swing.JComboBox<>();
        jcbParteCuerpo = new javax.swing.JComboBox<>();
        label3 = new java.awt.Label();

        label2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label2.setText("Agregar Tipo Accidente");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlZonaCuerpo.setText("Zona del cuerpo:");

        jlParteCuerpo.setText("Parte del cuerpo:");

        jbAceptar.setText("Aceptar");
        jbAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAceptarActionPerformed(evt);
            }
        });

        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jcbZonaCuerpo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jcbParteCuerpo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        label3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        label3.setText("Agregar Zona Cuerpo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jbAceptar)
                                .addGap(18, 18, 18)
                                .addComponent(jbCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jlParteCuerpo)
                                    .addComponent(jlZonaCuerpo))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jcbZonaCuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcbParteCuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlZonaCuerpo)
                    .addComponent(jcbZonaCuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlParteCuerpo)
                    .addComponent(jcbParteCuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCancelar)
                    .addComponent(jbAceptar))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAceptarActionPerformed
        String zonaCuerpo = (String) jcbZonaCuerpo.getSelectedItem();
        String parteCuerpo = (String) jcbParteCuerpo.getSelectedItem();
        
        if (zonaCuerpo == null || parteCuerpo == null) {
            System.err.println("Seleccione una zona y parte del cuerpo.");
            return;
        }
        
        ZonaCuerpo zonaCuerpoObj = construirZonaCuerpo(zonaCuerpo, parteCuerpo);
        
        if (zonaCuerpoController.crear(zonaCuerpoObj)) {
            System.out.println("Zona cuerpo creada exitosamente.");
            if (formularioZonaCuerpo != null) {
                formularioZonaCuerpo.actualizarTabla();
            }
        } else {
            System.err.println("Hubo un error al crear la zona cuerpo.");
        }

        dispose();
    }//GEN-LAST:event_jbAceptarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        // TODO add your handling code here:
                dispose();

    }//GEN-LAST:event_jbCancelarActionPerformed

    
    private ZonaCuerpo construirZonaCuerpo(String zonaCuerpo, String parteCuerpo) {
        int idParteCuerpo = zonaCuerpoController.obtenerIdParteCuerpo(parteCuerpo);
        int izquierdaDerecha = "Izquierda".equals(zonaCuerpo) ? 0 : 1;
        int idZona = zonaCuerpoController.obtenerNuevoIdZona();

        ZonaCuerpo zonaCuerpoObj = new ZonaCuerpo();
        zonaCuerpoObj.setId_zona(idZona);
        zonaCuerpoObj.setCodigo(idParteCuerpo);
        zonaCuerpoObj.setIzqder(izquierdaDerecha);

        return zonaCuerpoObj;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddZonaCuerpo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddZonaCuerpo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddZonaCuerpo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddZonaCuerpo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioZonaCuerpo formulariioZonaCuerpo = new FormularioZonaCuerpo();
                new AddZonaCuerpo(formulariioZonaCuerpo).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JComboBox<String> jcbParteCuerpo;
    private javax.swing.JComboBox<String> jcbZonaCuerpo;
    private javax.swing.JLabel jlParteCuerpo;
    private javax.swing.JLabel jlZonaCuerpo;
    private java.awt.Label label2;
    private java.awt.Label label3;
    // End of variables declaration//GEN-END:variables
}
