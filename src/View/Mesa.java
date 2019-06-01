/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Connection.Connection;
import Controller.Mesa_Controller;
import Model.Baraja;
import Model.Carta;
import Model.Mano;
import Utilidades.Constantes;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Collections;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Techiend
 */
public class Mesa extends javax.swing.JFrame {

    private Mano mano;
    /**
     * Creates new form Init
     */
    public Mesa() {
        initComponents();
        
        if (Constantes.repartidor == 0){
            btnRepartirCards.setVisible(false);
        }
        
        Mesa_Controller mesaController = Mesa_Controller.getInstance();
        
        mesaController.setCarta1(carta1);
        mesaController.setCarta2(carta2);
        mesaController.setCarta3(carta3);
        mesaController.setCbCartas(cbCartas);
        
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        setTitle(this.getClass().getSimpleName());
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        carta3 = new javax.swing.JLabel();
        carta1 = new javax.swing.JLabel();
        carta2 = new javax.swing.JLabel();
        carta4 = new javax.swing.JLabel();
        carta5 = new javax.swing.JLabel();
        carta6 = new javax.swing.JLabel();
        carta7 = new javax.swing.JLabel();
        carta8 = new javax.swing.JLabel();
        carta9 = new javax.swing.JLabel();
        carta10 = new javax.swing.JLabel();
        carta11 = new javax.swing.JLabel();
        carta12 = new javax.swing.JLabel();
        tirada1 = new javax.swing.JLabel();
        carta13 = new javax.swing.JLabel();
        carta14 = new javax.swing.JLabel();
        carta15 = new javax.swing.JLabel();
        btnRepartirCards = new javax.swing.JButton();
        cbCartas = new javax.swing.JComboBox<>();
        btnTirarCarta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 800));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 800));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 800));

        carta3.setText("Carta 3");
        carta3.setMaximumSize(new java.awt.Dimension(100, 139));
        carta3.setMinimumSize(new java.awt.Dimension(100, 139));
        carta3.setPreferredSize(new java.awt.Dimension(100, 139));

        carta1.setText("Carta 1");
        carta1.setMaximumSize(new java.awt.Dimension(100, 139));
        carta1.setMinimumSize(new java.awt.Dimension(100, 139));
        carta1.setPreferredSize(new java.awt.Dimension(100, 139));

        carta2.setText("Carta 2");
        carta2.setMaximumSize(new java.awt.Dimension(100, 139));
        carta2.setMinimumSize(new java.awt.Dimension(100, 139));
        carta2.setPreferredSize(new java.awt.Dimension(100, 139));

        carta4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilidades/Images/backH.jpg"))); // NOI18N
        carta4.setText("Carta");
        carta4.setMaximumSize(new java.awt.Dimension(139, 100));
        carta4.setMinimumSize(new java.awt.Dimension(139, 100));
        carta4.setPreferredSize(new java.awt.Dimension(139, 100));

        carta5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilidades/Images/back.jpg"))); // NOI18N
        carta5.setText("Carta");
        carta5.setMaximumSize(new java.awt.Dimension(100, 139));
        carta5.setMinimumSize(new java.awt.Dimension(100, 139));
        carta5.setPreferredSize(new java.awt.Dimension(100, 139));

        carta6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilidades/Images/back.jpg"))); // NOI18N
        carta6.setText("Carta");
        carta6.setMaximumSize(new java.awt.Dimension(100, 139));
        carta6.setMinimumSize(new java.awt.Dimension(100, 139));
        carta6.setPreferredSize(new java.awt.Dimension(100, 139));

        carta7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilidades/Images/backH.jpg"))); // NOI18N
        carta7.setText("Carta");
        carta7.setMaximumSize(new java.awt.Dimension(139, 100));
        carta7.setMinimumSize(new java.awt.Dimension(139, 100));
        carta7.setPreferredSize(new java.awt.Dimension(139, 100));

        carta8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilidades/Images/backH.jpg"))); // NOI18N
        carta8.setText("Carta");
        carta8.setMaximumSize(new java.awt.Dimension(139, 100));
        carta8.setMinimumSize(new java.awt.Dimension(139, 100));
        carta8.setPreferredSize(new java.awt.Dimension(139, 100));

        carta9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilidades/Images/backH.jpg"))); // NOI18N
        carta9.setText("Carta");
        carta9.setMaximumSize(new java.awt.Dimension(139, 100));
        carta9.setMinimumSize(new java.awt.Dimension(139, 100));
        carta9.setPreferredSize(new java.awt.Dimension(139, 100));

        carta10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilidades/Images/backH.jpg"))); // NOI18N
        carta10.setText("Carta");
        carta10.setMaximumSize(new java.awt.Dimension(139, 100));
        carta10.setMinimumSize(new java.awt.Dimension(139, 100));
        carta10.setPreferredSize(new java.awt.Dimension(139, 100));

        carta11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilidades/Images/backH.jpg"))); // NOI18N
        carta11.setText("Carta");
        carta11.setMaximumSize(new java.awt.Dimension(139, 100));
        carta11.setMinimumSize(new java.awt.Dimension(139, 100));
        carta11.setPreferredSize(new java.awt.Dimension(139, 100));

        carta12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Utilidades/Images/back.jpg"))); // NOI18N
        carta12.setText("Carta");
        carta12.setMaximumSize(new java.awt.Dimension(100, 139));
        carta12.setMinimumSize(new java.awt.Dimension(100, 139));
        carta12.setPreferredSize(new java.awt.Dimension(100, 139));

        tirada1.setText("Carta tirada");
        tirada1.setMaximumSize(new java.awt.Dimension(100, 139));
        tirada1.setMinimumSize(new java.awt.Dimension(100, 139));
        tirada1.setPreferredSize(new java.awt.Dimension(100, 139));

        carta13.setText("Carta tirada");
        carta13.setMaximumSize(new java.awt.Dimension(139, 100));
        carta13.setMinimumSize(new java.awt.Dimension(139, 100));
        carta13.setPreferredSize(new java.awt.Dimension(139, 100));

        carta14.setText("Carta tirada");
        carta14.setMaximumSize(new java.awt.Dimension(139, 100));
        carta14.setMinimumSize(new java.awt.Dimension(139, 100));
        carta14.setPreferredSize(new java.awt.Dimension(139, 100));

        carta15.setText("Carta tirada");
        carta15.setMaximumSize(new java.awt.Dimension(100, 139));
        carta15.setMinimumSize(new java.awt.Dimension(100, 139));
        carta15.setPreferredSize(new java.awt.Dimension(100, 139));

        btnRepartirCards.setText("Repartir");
        btnRepartirCards.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepartirCardsActionPerformed(evt);
            }
        });

        btnTirarCarta.setText("Tirar carta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(cbCartas, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTirarCarta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRepartirCards))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(carta4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(carta8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(carta14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(228, 228, 228)
                                        .addComponent(carta12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(carta15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                                .addComponent(carta13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(carta5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(carta6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(carta7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(carta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(carta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(carta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tirada1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(90, 90, 90)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(carta11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carta9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carta10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carta6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(carta11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(carta10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carta13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(carta4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(carta8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carta14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(carta15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(carta9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carta7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTirarCarta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRepartirCards)
                            .addComponent(cbCartas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tirada1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(carta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(93, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRepartirCardsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepartirCardsActionPerformed
        // TODO add your handling code here:
        Constantes.repartirCartas();
        btnRepartirCards.setVisible(false);
    }//GEN-LAST:event_btnRepartirCardsActionPerformed

    
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
            java.util.logging.Logger.getLogger(Mesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mesa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRepartirCards;
    private javax.swing.JButton btnTirarCarta;
    private javax.swing.JLabel carta1;
    private javax.swing.JLabel carta10;
    private javax.swing.JLabel carta11;
    private javax.swing.JLabel carta12;
    private javax.swing.JLabel carta13;
    private javax.swing.JLabel carta14;
    private javax.swing.JLabel carta15;
    private javax.swing.JLabel carta2;
    private javax.swing.JLabel carta3;
    private javax.swing.JLabel carta4;
    private javax.swing.JLabel carta5;
    private javax.swing.JLabel carta6;
    private javax.swing.JLabel carta7;
    private javax.swing.JLabel carta8;
    private javax.swing.JLabel carta9;
    private javax.swing.JComboBox<String> cbCartas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel tirada1;
    // End of variables declaration//GEN-END:variables
}
