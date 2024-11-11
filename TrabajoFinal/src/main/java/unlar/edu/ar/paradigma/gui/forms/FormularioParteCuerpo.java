/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package unlar.edu.ar.paradigma.gui.forms;

import java.awt.Frame;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lucia
 */
public class FormularioParteCuerpo extends javax.swing.JPanel {

    /**
     * Creates new form ParteCuerpo
     */
    public FormularioParteCuerpo() {
        initComponents();
        initCustomComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grillaParteCuerpo = new unlar.edu.ar.paradigma.gui.forms.grillas.GrillaParteCuerpo();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jTable1.setModel(grillaParteCuerpo);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        jButton4.setText("jButton4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private unlar.edu.ar.paradigma.gui.forms.grillas.GrillaParteCuerpo grillaParteCuerpo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void initCustomComponents() {
        DefaultTableModel model = new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Parte"
            }
        );
        jTable1.setModel(model);
        jTable1.setPreferredScrollableViewportSize(new java.awt.Dimension(800, 400));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(800, 400)); 

         jButton1.setText("Agregar");
           jButton1.addActionListener(e -> {
           // Definir las columnas que se mostrarán en el formulario agregar
           List<String> ParteCuerpo = List.of("Codigo", "Tipo");
            
// Crear y mostrar el formulario de agregar
            GenericFormAgregar formulario = new GenericFormAgregar(
                (Frame) SwingUtilities.getWindowAncestor(this), 
                true, 
                ParteCuerpo
            );
            formulario.setVisible(true);
          });
           
             jButton1.addActionListener(e -> {
           // Definir las columnas que se mostrarán en el formulario agregar
           List<String> ParteCuerpo = List.of("Codigo", "Tipo");
            
// Crear y mostrar el formulario de agregar
            GenericFormAgregar formulario = new GenericFormAgregar(
                (Frame) SwingUtilities.getWindowAncestor(this), 
                true, 
                ParteCuerpo
            );
            formulario.setVisible(true);
          });


        jButton2.setText("Modificar");

        jButton3.setText("Eliminar");
        
        jButton4.setText("Cerrar");
        
       
        
        revalidate();
        repaint();
    }
}
