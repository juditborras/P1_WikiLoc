/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Cursor;
import java.io.File;
import javax.swing.ImageIcon;
import org.milaifontanals.wikiloc.components.TextPrompt;

/**
 *
 * @author JUDIT
 */
public class CrearCompte extends javax.swing.JFrame {

    /**
     * Creates new form IniciarSessio
     */
    public CrearCompte() {
        initComponents();
        TextPrompt placeHolder_email = new TextPrompt("correu electrònic",jTextField_email);
        TextPrompt placeHolder_login = new TextPrompt("nom d'usuari",jTextField_login);
        TextPrompt placeHolder_pwd = new TextPrompt("●●●●●●●●●●●",jPasswordField);
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
        jPanel_iniciarSessio = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jTextField_login = new javax.swing.JTextField();
        jButton_registrat = new javax.swing.JButton();
        jLabel_iniciaSessio = new javax.swing.JLabel();
        jLabel_logo = new javax.swing.JLabel();
        jTextField_email = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WikiLoc");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel_iniciarSessio.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_iniciarSessio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel3.setText("Ja ets membre?");
        jPanel_iniciarSessio.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 380, 140, -1));

        jPasswordField.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jPasswordField.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_iniciarSessio.add(jPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 610, 690, 51));

        jTextField_login.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_login.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_loginActionPerformed(evt);
            }
        });
        jPanel_iniciarSessio.add(jTextField_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 530, 690, 51));

        jButton_registrat.setBackground(new java.awt.Color(76, 140, 43));
        jButton_registrat.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jButton_registrat.setForeground(new java.awt.Color(255, 255, 255));
        jButton_registrat.setText("Registra't");
        jButton_registrat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_iniciarSessio.add(jButton_registrat, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 770, 390, 51));

        jLabel_iniciaSessio.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel_iniciaSessio.setForeground(new java.awt.Color(255, 174, 0));
        jLabel_iniciaSessio.setText("<html><a href=\"\">Inicia sessió</a></html>");
        jLabel_iniciaSessio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_iniciaSessio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_iniciaSessioMouseClicked(evt);
            }
        });
        jPanel_iniciarSessio.add(jLabel_iniciaSessio, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 370, 378, 30));

        jLabel_logo.setIcon(new javax.swing.ImageIcon("G:\\DAM\\2on\\Curs 2023-2024\\M13-Projecte\\1_WikiLoc\\P1_WikiLoc\\P1-BorrasMeleroJudit\\P1-Vista-BorrasMeleroJudit\\img\\wikiloc_logo.png")); // NOI18N
        jPanel_iniciarSessio.add(jLabel_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, -1, -1));

        jTextField_email.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_email.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_emailActionPerformed(evt);
            }
        });
        jPanel_iniciarSessio.add(jTextField_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 450, 690, 51));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel1.setText("En continuar, estàs acceptant les Condicions d'Ús i la Política de Privadesa de Wikiloc.");
        jPanel_iniciarSessio.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 720, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_iniciarSessio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1492, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_iniciarSessio, javax.swing.GroupLayout.DEFAULT_SIZE, 1118, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_loginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_loginActionPerformed

    private void jTextField_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_emailActionPerformed

    private void jLabel_iniciaSessioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_iniciaSessioMouseClicked
        
        this.setVisible(false);
        this.dispose();
        
        IniciarSessio iniciarSessio = new IniciarSessio();
        
        ImageIcon img = new ImageIcon("img"+File.separator+"wikiloc_logo_simple.png");

        iniciarSessio.setIconImage(img.getImage());
        
        iniciarSessio.setExtendedState(iniciarSessio.MAXIMIZED_BOTH); 
        iniciarSessio.setResizable(false);
        iniciarSessio.setLocationRelativeTo(null);
        iniciarSessio.setVisible(true);
        
    }//GEN-LAST:event_jLabel_iniciaSessioMouseClicked

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IniciarSessio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IniciarSessio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IniciarSessio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IniciarSessio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IniciarSessio().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_registrat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_iniciaSessio;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_iniciarSessio;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextField_email;
    private javax.swing.JTextField jTextField_login;
    // End of variables declaration//GEN-END:variables
}
