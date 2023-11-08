/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.milaifontanals.wikiloc.components.TextPrompt;
import org.milaifontanals.wikiloc.jdbc.GestorBDWikilocJdbc;
import org.milaifontanals.wikiloc.persistencia.GestorBDWikilocException;

/**
 *
 * @author JUDIT
 */
public class IniciarSessio extends javax.swing.JFrame {

    /**
     * Creates new form IniciarSessio
     */
    private GestorBDWikilocJdbc gestorBDWikilocJdbc;
    private boolean amagarPwd = true;
    
    public IniciarSessio() {
        
        initComponents();
        TextPrompt placeHolder_usuari = new TextPrompt("correu electrònic o nom d'usuari",jTextField_loginEmail);
        TextPrompt placeHolder_pwd = new TextPrompt("●●●●●●●●●●●",jPasswordField);
        
        jButton_mostrarPwd.setBackground(new Color(255,255,255));
               
        try {
            gestorBDWikilocJdbc = new GestorBDWikilocJdbc();
        } catch (GestorBDWikilocException ex) {

            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Error - Inici sessió", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel_iniciarSessio = new javax.swing.JPanel();
        jLabel_logo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel_crearCompte = new javax.swing.JLabel();
        jTextField_loginEmail = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();
        jButton_iniciaSessio = new javax.swing.JButton();
        jButton_mostrarPwd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WikiLoc");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel_iniciarSessio.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_iniciarSessio.setLayout(new java.awt.GridBagLayout());

        jLabel_logo.setIcon(new javax.swing.ImageIcon("G:\\DAM\\2on\\Curs 2023-2024\\M13-Projecte\\1_WikiLoc\\P1_WikiLoc\\P1-BorrasMeleroJudit\\P1-Vista-BorrasMeleroJudit\\img\\wikiloc_logo.png")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(112, 245, 0, 0);
        jPanel_iniciarSessio.add(jLabel_logo, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel3.setText("Encara no ets membre?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(55, 385, 0, 0);
        jPanel_iniciarSessio.add(jLabel3, gridBagConstraints);

        jLabel_crearCompte.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel_crearCompte.setForeground(new java.awt.Color(255, 174, 0));
        jLabel_crearCompte.setText("<html><a href=\"\">Crea un compte</a></html>");
        jLabel_crearCompte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_crearCompte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_crearCompteMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.ipadx = 349;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(45, 8, 0, 71);
        jPanel_iniciarSessio.add(jLabel_crearCompte, gridBagConstraints);

        jTextField_loginEmail.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_loginEmail.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_loginEmail.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipadx = 593;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(48, 245, 0, 0);
        jPanel_iniciarSessio.add(jTextField_loginEmail, gridBagConstraints);

        jPasswordField.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jPasswordField.setForeground(new java.awt.Color(204, 204, 204));
        jPasswordField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 535;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 245, 0, 0);
        jPanel_iniciarSessio.add(jPasswordField, gridBagConstraints);

        jButton_iniciaSessio.setBackground(new java.awt.Color(76, 140, 43));
        jButton_iniciaSessio.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jButton_iniciaSessio.setForeground(new java.awt.Color(255, 255, 255));
        jButton_iniciaSessio.setText("Inicia sessió");
        jButton_iniciaSessio.setBorder(null);
        jButton_iniciaSessio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_iniciaSessio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_iniciaSessioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_iniciaSessioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_iniciaSessioMouseExited(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 262;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(56, 355, 125, 0);
        jPanel_iniciarSessio.add(jButton_iniciaSessio, gridBagConstraints);

        jButton_mostrarPwd.setForeground(new java.awt.Color(255, 255, 255));
        jButton_mostrarPwd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/ull.png"))); // NOI18N
        jButton_mostrarPwd.setBorder(null);
        jButton_mostrarPwd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_mostrarPwd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_mostrarPwdMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 22;
        gridBagConstraints.ipady = 21;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 6, 0, 0);
        jPanel_iniciarSessio.add(jButton_mostrarPwd, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_iniciarSessio, javax.swing.GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_iniciarSessio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_mostrarPwdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_mostrarPwdMouseClicked

        amagarPwd = !amagarPwd;

        if(amagarPwd){
            jPasswordField.setEchoChar('●');
        }else{
            jPasswordField.setEchoChar((char)0);
        }
    }//GEN-LAST:event_jButton_mostrarPwdMouseClicked

    private void jButton_iniciaSessioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_iniciaSessioMouseClicked

        try {
            boolean correcte = gestorBDWikilocJdbc.iniciarSessio(jTextField_loginEmail.getText(), jPasswordField.getText());

            if(correcte){

                this.setVisible(false);
                //this.dispose();

                Menu menu = new Menu();

                ImageIcon img = new ImageIcon("img" + File.separator + "wikiloc_logo_simple.png");

                menu.setIconImage(img.getImage());

                menu.setExtendedState(menu.MAXIMIZED_BOTH);
                menu.setResizable(false);
                menu.setLocationRelativeTo(null);
                menu.setVisible(true);

            }else{

                JOptionPane.showMessageDialog(this,
                    "No s'ha pogut iniciar sessió. L'usuari o contrasenya no són correctes.",
                    "Error - Inici sessió",JOptionPane.ERROR_MESSAGE);

            }

        } catch (GestorBDWikilocException ex) {

            JOptionPane.showMessageDialog(this,
                "Error: " + ex.getMessage(),
                "Error - Inici sessió", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_iniciaSessioMouseClicked

    private void jLabel_crearCompteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_crearCompteMouseClicked

        this.setVisible(false);
        this.dispose();

        CrearCompte crearCompte = new CrearCompte();

        ImageIcon img = new ImageIcon("img"+File.separator+"wikiloc_logo_simple.png");

        crearCompte.setIconImage(img.getImage());

        crearCompte.setExtendedState(crearCompte.MAXIMIZED_BOTH);
        crearCompte.setResizable(false);
        crearCompte.setLocationRelativeTo(null);
        crearCompte.setVisible(true);
    }//GEN-LAST:event_jLabel_crearCompteMouseClicked

    private void jButton_iniciaSessioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_iniciaSessioMouseEntered
        
        jButton_iniciaSessio.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_iniciaSessioMouseEntered

    private void jButton_iniciaSessioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_iniciaSessioMouseExited
        
        jButton_iniciaSessio.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_iniciaSessioMouseExited

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
    private javax.swing.JButton jButton_iniciaSessio;
    private javax.swing.JButton jButton_mostrarPwd;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_crearCompte;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JPanel jPanel_iniciarSessio;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextField_loginEmail;
    // End of variables declaration//GEN-END:variables
}
