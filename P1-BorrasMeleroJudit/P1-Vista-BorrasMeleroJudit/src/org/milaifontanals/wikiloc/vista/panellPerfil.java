/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import org.milaifontanals.wikiloc.components.TextPrompt;
import org.milaifontanals.wikiloc.jdbc.GestorBDWikilocJdbc;
import org.milaifontanals.wikiloc.model.Usuari;
import org.milaifontanals.wikiloc.persistencia.GestorBDWikilocException;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author JUDIT
 */
public class panellPerfil extends javax.swing.JPanel {

    /**
     * Creates new form panellPerfil
     */
    Usuari usuari_loginat;
    private GestorBDWikilocJdbc gestorBDWikilocJdbc;
    private boolean amagarPwd1 = true;
    private boolean amagarPwd2 = true;
    private boolean amagarPwd3 = true;
    ImageIcon fotoUsuari;
    ImageIcon fotoNulla = new ImageIcon("img"+File.separator+"perfil.png");
    ImageIcon fotoDesarCanvis = new ImageIcon("img"+File.separator+"desarCanvis.png");
    ImageIcon fotoDesarCanvisHoover = new ImageIcon("img"+File.separator+"desarCanvisHoover.png");
    ImageIcon fotoEditarImgPerfil = new ImageIcon("img"+File.separator+"editarImgPerfil.png");
    ImageIcon fotoEditarImgPerfilHoover = new ImageIcon("img"+File.separator+"editarImgPerfilHoover.png");
    String filePath;
    Usuari u_dades;
    
    public panellPerfil(JPanel jPanel_menu, Usuari usuari_loginat) {
       
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("a {color:#FFAE00;}");
        
        initComponents();
        
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int screenWidth = screenSize.width;
//        int screenHeight = screenSize.height;
//        
//        jPanel1.setPreferredSize(new Dimension(screenWidth,screenHeight));
//        jPanel1.setLayout(new AbsoluteLayout());
//        JScrollPane jsc = new JScrollPane();
//        jsc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        jsc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        
//        jPanel1.add(jsc);

        jPanel_menu.setVisible(false);
        this.usuari_loginat = usuari_loginat;
                        
        jPanel2.setVisible(false);
        jPanel3.setVisible(false);
        
        jLabel_canviarPwd.setForeground(new Color(255,174,0));
        jLabel_canviarEmail.setForeground(new Color(255,174,0));
        
        TextPrompt placeHolder_correu = new TextPrompt("correu_electronic@wikiloc.com",jTextField_nouEmail);
        TextPrompt placeHolder_pwd1 = new TextPrompt("●●●●●●●●●●●",jPasswordField_novaPwd);
        TextPrompt placeHolder_pwd2 = new TextPrompt("●●●●●●●●●●●",jPasswordField_repetirPwd);
        
        try {
            
            gestorBDWikilocJdbc = new GestorBDWikilocJdbc();
            
            u_dades = gestorBDWikilocJdbc.obtenirUsuari(usuari_loginat.getLogin());
            
            //jLabel_fotoUsuari;
            
            if(u_dades.getFoto()!=null){
                //System.out.println("BYTE[]"+punt_seleccionat.getFoto().length);
                
                BufferedImage bf = byteArrayToImage(u_dades.getFoto());
                fotoUsuari = new ImageIcon(bf);
                jLabel_fotoUsuari.setIcon(fotoUsuari);
                             
             
            }else{
                jLabel_fotoUsuari.setIcon(fotoNulla);
            }
            
                        
            jTextField_login.setText(u_dades.getLogin());
            jTextField_email.setText(u_dades.getEmail());
            
            
            
            int qtat_punts = u_dades.getPwd().length();
            String punts = "";
            for(int i = 0; i < qtat_punts; i++){
                punts += "●";
            }
            TextPrompt placeHolder_pwd = new TextPrompt(punts,jTextField_pwd);
            TextPrompt placeHolder_novaPwd = new TextPrompt(punts, jPasswordField_novaPwd);
            TextPrompt placeHolder_repetirPwd = new TextPrompt(punts, jPasswordField_repetirPwd);
            
            //jPasswordField_pwd.setText(punts);
            
        } catch (GestorBDWikilocException ex) {
            //System.out.println("No s'han pogut obtenir les dades de l'usuari connectat");
            JOptionPane.showConfirmDialog(null, "Error a l'obtenir les dades de l'usuari: "+ex.getMessage(),
                        "Obtenir dades usuari", JOptionPane.CLOSED_OPTION,
                        JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel_fotoUsuari = new javax.swing.JLabel();
        jTextField_login = new javax.swing.JTextField();
        jTextField_email = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPasswordField_novaPwd = new javax.swing.JPasswordField();
        jPasswordField_repetirPwd = new javax.swing.JPasswordField();
        jLabel_mostrarPwd2 = new javax.swing.JLabel();
        jLabel_mostrarPwd3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel_canviarPwd = new javax.swing.JLabel();
        jLabel_mostrarPwd1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel_canviarEmail = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField_nouEmail = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jTextField_pwd = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField_login.setEditable(false);
        jTextField_login.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_login.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_login.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_login.setBorder(null);
        jTextField_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_loginActionPerformed(evt);
            }
        });

        jTextField_email.setEditable(false);
        jTextField_email.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_email.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_email.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_email.setBorder(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(76, 140, 43));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/desarCanvis.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });

        jPasswordField_novaPwd.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jPasswordField_novaPwd.setForeground(new java.awt.Color(153, 153, 153));
        jPasswordField_novaPwd.setBorder(null);

        jPasswordField_repetirPwd.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jPasswordField_repetirPwd.setForeground(new java.awt.Color(153, 153, 153));
        jPasswordField_repetirPwd.setBorder(null);

        jLabel_mostrarPwd2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/ull.png"))); // NOI18N
        jLabel_mostrarPwd2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_mostrarPwd2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_mostrarPwd2MouseClicked(evt);
            }
        });

        jLabel_mostrarPwd3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/ull.png"))); // NOI18N
        jLabel_mostrarPwd3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_mostrarPwd3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_mostrarPwd3MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel8.setText("Nova contrasenya:");

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel7.setText("Repetir nova contrasenya:");

        jSeparator7.setForeground(new java.awt.Color(76, 140, 43));

        jSeparator8.setForeground(new java.awt.Color(76, 140, 43));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(76, 76, 76)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jPasswordField_novaPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel_mostrarPwd2)
                            .addGap(108, 108, 108))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jPasswordField_repetirPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel_mostrarPwd3)
                            .addGap(52, 52, 52)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                    .addComponent(jSeparator8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_mostrarPwd2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jPasswordField_novaPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jPasswordField_repetirPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(jLabel_mostrarPwd3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(296, 296, 296))
        );

        jLabel_canviarPwd.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel_canviarPwd.setForeground(new java.awt.Color(255, 174, 0));
        jLabel_canviarPwd.setText("Editar");
        jLabel_canviarPwd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_canviarPwd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_canviarPwdMouseClicked(evt);
            }
        });

        jLabel_mostrarPwd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/ull.png"))); // NOI18N
        jLabel_mostrarPwd1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_mostrarPwd1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_mostrarPwd1MouseClicked(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(76, 140, 43));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/editarImgPerfil.png"))); // NOI18N
        jButton2.setAlignmentY(0.0F);
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setIconTextGap(0);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel_canviarEmail.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel_canviarEmail.setForeground(new java.awt.Color(255, 174, 0));
        jLabel_canviarEmail.setText("Editar");
        jLabel_canviarEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_canviarEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_canviarEmailMouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setBackground(new java.awt.Color(76, 140, 43));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/desarCanvis.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel5.setText("Nou correu electrònic:");

        jTextField_nouEmail.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_nouEmail.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_nouEmail.setBorder(null);

        jSeparator5.setForeground(new java.awt.Color(76, 140, 43));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Edició de dades de l'usuari");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField_nouEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jTextField_nouEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextField_pwd.setEditable(false);
        jTextField_pwd.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_pwd.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_pwd.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_pwd.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Compte d'usuari");

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jSeparator2.setForeground(new java.awt.Color(76, 140, 43));

        jSeparator3.setForeground(new java.awt.Color(76, 140, 43));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel2.setText("Usuari:");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel3.setText("Correu electrònic:");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel4.setText("Contrasenya:");

        jSeparator4.setForeground(new java.awt.Color(76, 140, 43));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(226, 226, 226)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(79, 79, 79)
                                        .addComponent(jTextField_login, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSeparator4)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jTextField_pwd, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel_mostrarPwd1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                            .addComponent(jLabel_canviarPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(81, 81, 81)
                                        .addComponent(jTextField_email, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabel_canviarEmail))
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel_fotoUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(260, 260, 260))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_fotoUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_login, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(3, 3, 3)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_email, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_canviarEmail)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_pwd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_canviarPwd)
                        .addComponent(jLabel4))
                    .addComponent(jLabel_mostrarPwd1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_loginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_loginActionPerformed

    private void jLabel_canviarPwdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_canviarPwdMouseClicked
        
        
        jPanel2.setVisible(true);
        
        
    }//GEN-LAST:event_jLabel_canviarPwdMouseClicked

    private void jLabel_mostrarPwd1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_mostrarPwd1MouseClicked
        
        amagarPwd1 = !amagarPwd1;
        
        ImageIcon image1 = new ImageIcon("img"+File.separator+"ull.png");
        ImageIcon image2 = new ImageIcon("img"+File.separator+"ull_amagar.png");

        if (amagarPwd1) {

            int qtat_punts = u_dades.getPwd().length();
            String punts = "";
            for(int i = 0; i < qtat_punts; i++){
                punts += "●";
            }
            jTextField_pwd.setText(punts);
            jLabel_mostrarPwd1.setIcon(image1);

        } else {

            jTextField_pwd.setText(u_dades.getPwd());
            jLabel_mostrarPwd1.setIcon(image2);
        }
    }//GEN-LAST:event_jLabel_mostrarPwd1MouseClicked

    private void jLabel_mostrarPwd2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_mostrarPwd2MouseClicked
        
        amagarPwd2 = !amagarPwd2;
        
        ImageIcon image1 = new ImageIcon("img"+File.separator+"ull.png");
        ImageIcon image2 = new ImageIcon("img"+File.separator+"ull_amagar.png");

        if (amagarPwd2) {

            jPasswordField_novaPwd.setEchoChar('●');
            jLabel_mostrarPwd2.setIcon(image1);

        } else {

            jPasswordField_novaPwd.setEchoChar((char) 0);
            jLabel_mostrarPwd2.setIcon(image2);
        }
    }//GEN-LAST:event_jLabel_mostrarPwd2MouseClicked

    private void jLabel_mostrarPwd3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_mostrarPwd3MouseClicked
        
        amagarPwd3 = !amagarPwd3;
        
        ImageIcon image1 = new ImageIcon("img"+File.separator+"ull.png");
        ImageIcon image2 = new ImageIcon("img"+File.separator+"ull_amagar.png");

        if (amagarPwd3) {

            jPasswordField_repetirPwd.setEchoChar('●');
            jLabel_mostrarPwd3.setIcon(image1);

        } else {

            jPasswordField_repetirPwd.setEchoChar((char) 0);
            jLabel_mostrarPwd3.setIcon(image2);
        }
        
    }//GEN-LAST:event_jLabel_mostrarPwd3MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        JFileChooser jfc = new JFileChooser();
                
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        
        jfc.setFileFilter(imageFilter);
        
        jfc.showOpenDialog(jfc);
        
        File imgFile = jfc.getSelectedFile();
        
        if(imgFile != null){
            
           
            filePath = imgFile.getAbsolutePath();
            ImageIcon novaImatgeSeleccionada = new ImageIcon(filePath);
            
            jLabel_fotoUsuari.setIcon(novaImatgeSeleccionada);
            
            BufferedImage bi = new BufferedImage(
            novaImatgeSeleccionada.getIconWidth(),
            novaImatgeSeleccionada.getIconHeight(),
            BufferedImage.TYPE_INT_RGB);
            
            
            String extensio = filePath.substring(filePath.length() - 3);
            byte[] bt = imageToByteArray(bi, extensio);
            
//            if(Arrays.equals(u_dades.getFoto(), bt)){
//                fotoUs_canviada = true;
//                //modificacionsCampsPunt();
//            }else{
//                fotoPunt_canviada = false;
//                //modificacionsCampsPunt();
//            }                       
        
        }else{
            jLabel_fotoUsuari.setIcon(fotoNulla);
            filePath = null;
        }
        
        
        try {
               
            boolean editat = gestorBDWikilocJdbc.editarFotoUsuari(usuari_loginat, filePath);

            if(editat){
                
                gestorBDWikilocJdbc.confirmarCanvis();

                JOptionPane.showConfirmDialog(null, "La foto s'ha desat correctament",
                        "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
            }else{
                //System.out.println("no editadaaaa");
            }         
            
        } catch (GestorBDWikilocException ex) {
            JOptionPane.showConfirmDialog(null, "Error a l'editar la fotografia de l'usuari: "+ex.getMessage(),
                        "Editar fotografia", JOptionPane.CLOSED_OPTION,
                        JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        
        
        try {

            if(jPasswordField_novaPwd.getText().equals(jPasswordField_repetirPwd.getText())){
                
                boolean editat = gestorBDWikilocJdbc.editarPwdUsuari(usuari_loginat, jPasswordField_novaPwd.getText());

                if (editat) {

                    gestorBDWikilocJdbc.confirmarCanvis();

                    JOptionPane.showConfirmDialog(null, "La contrassenya s'ha desat correctament",
                            "Canviar contrasenya", JOptionPane.CLOSED_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    jTextField_pwd.setText("");
                    jPasswordField_novaPwd.setText("");
                    jPasswordField_repetirPwd.setText("");
                    
                    try {

                        u_dades = gestorBDWikilocJdbc.obtenirUsuari(usuari_loginat.getLogin());

                        //jLabel_fotoUsuari;
                        if (u_dades.getFoto() != null) {
                            //System.out.println("BYTE[]"+punt_seleccionat.getFoto().length);

                            BufferedImage bf = byteArrayToImage(u_dades.getFoto());
                            fotoUsuari = new ImageIcon(bf);
                            jLabel_fotoUsuari.setIcon(fotoUsuari);

                        } else {
                            jLabel_fotoUsuari.setIcon(fotoNulla);
                        }

                        jTextField_login.setText(u_dades.getLogin());
                        jTextField_email.setText(u_dades.getEmail());

                        int qtat_punts = u_dades.getPwd().length();
                        String punts = "";
                        for (int i = 0; i < qtat_punts; i++) {
                            punts += "●";
                        }
                        TextPrompt placeHolder_pwd = new TextPrompt(punts, jTextField_pwd);
                        TextPrompt placeHolder_novaPwd = new TextPrompt(punts, jPasswordField_novaPwd);
                        TextPrompt placeHolder_repetirPwd = new TextPrompt(punts, jPasswordField_repetirPwd);
                        
                        

                        //jPasswordField_pwd.setText(punts);
                    } catch (GestorBDWikilocException ex) {
                        JOptionPane.showConfirmDialog(null, "Error a l'obtenir les dades de l'usuari: "+ex.getMessage(),
                        "Obtenir dades usuari", JOptionPane.CLOSED_OPTION,
                        JOptionPane.ERROR_MESSAGE);
                    }
                    
                    
                    
                } else {

                    JOptionPane.showConfirmDialog(null, "La contrassenya no s'ha pogut canviar",
                            "Canviar contrasenya", JOptionPane.CLOSED_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);
                }
                
                jPanel2.setVisible(false);
            
            }else{
                JOptionPane.showConfirmDialog(null, "Els camps de la nova contrasenya no coincideixen",
                            "Canviar contrasenya", JOptionPane.CLOSED_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);
                
                jPasswordField_novaPwd.setText("");
                jPasswordField_repetirPwd.setText("");
            }
            
            


        } catch (GestorBDWikilocException ex) {
            JOptionPane.showConfirmDialog(null, "Error a l'obtenir les dades de l'usuari: "+ex.getMessage(),
                        "Obtenir dades usuari", JOptionPane.CLOSED_OPTION,
                        JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        
        
        try {

            boolean editat = gestorBDWikilocJdbc.editarEmailUsuari(usuari_loginat,jTextField_nouEmail.getText().trim());

            if (editat) {

                gestorBDWikilocJdbc.confirmarCanvis();

                JOptionPane.showConfirmDialog(null, "L'email s'ha desat correctament",
                        "Wikiloc", JOptionPane.CLOSED_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                
                jTextField_nouEmail.setText("");
                
                try {

                    u_dades = gestorBDWikilocJdbc.obtenirUsuari(usuari_loginat.getLogin());

                    //jLabel_fotoUsuari;
                    if (u_dades.getFoto() != null) {
                        //System.out.println("BYTE[]"+punt_seleccionat.getFoto().length);

                        BufferedImage bf = byteArrayToImage(u_dades.getFoto());
                        fotoUsuari = new ImageIcon(bf);
                        jLabel_fotoUsuari.setIcon(fotoUsuari);

                    } else {
                        jLabel_fotoUsuari.setIcon(fotoNulla);
                    }

                    jTextField_login.setText(u_dades.getLogin());
                    jTextField_email.setText(u_dades.getEmail());

                    int qtat_punts = u_dades.getPwd().length();
                    String punts = "";
                    for (int i = 0; i < qtat_punts; i++) {
                        punts += "●";
                    }
                    TextPrompt placeHolder_pwd = new TextPrompt(punts, jTextField_pwd);
                    TextPrompt placeHolder_novaPwd = new TextPrompt(punts, jPasswordField_novaPwd);
                    TextPrompt placeHolder_repetirPwd = new TextPrompt(punts, jPasswordField_repetirPwd);
                    
                    //jPasswordField_pwd.setText(punts);
                } catch (GestorBDWikilocException ex) {
                    JOptionPane.showConfirmDialog(null, "Error a l'obtenir les dades de l'usuari: "+ex.getMessage(),
                        "Error - Obtenir dades usuari", JOptionPane.CLOSED_OPTION,
                        JOptionPane.ERROR_MESSAGE);
                }
                
                
            } else {

                JOptionPane.showConfirmDialog(null, "L'email no s'ha pogut canviar",
                        "Error - Canvi email", JOptionPane.CLOSED_OPTION,
                        JOptionPane.ERROR_MESSAGE);
            }
            
            jPanel3.setVisible(false);

        } catch (GestorBDWikilocException ex) {
            JOptionPane.showConfirmDialog(null, "Error a l'editar l'usuari: "+ex.getMessage(),
                        "Editar usuari", JOptionPane.CLOSED_OPTION,
                        JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_jButton3MouseClicked

    private void jLabel_canviarEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_canviarEmailMouseClicked
        
        
        jPanel3.setVisible(true);
        
        
    }//GEN-LAST:event_jLabel_canviarEmailMouseClicked

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        
        jButton2.setIcon(fotoEditarImgPerfilHoover);
        jButton2.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setIcon(fotoEditarImgPerfil);
        jButton2.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        jButton3.setIcon(fotoDesarCanvisHoover);
        jButton3.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jButton3.setIcon(fotoDesarCanvis);
        jButton3.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setIcon(fotoDesarCanvisHoover);
        jButton1.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setIcon(fotoDesarCanvis);
        jButton1.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton1MouseExited

    public BufferedImage byteArrayToImage(byte[] bytes) {
        BufferedImage bufferedImage = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException ex) {
            //System.out.println(ex.getMessage());
        }
        return bufferedImage;
    }
    
    public byte[] imageToByteArray(BufferedImage bi,String format){
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, format, baos);
            
            byte[] bytes = baos.toByteArray();
            
            return bytes;
            
        } catch (IOException ex) {
            throw new RuntimeException("Error"+ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_canviarEmail;
    private javax.swing.JLabel jLabel_canviarPwd;
    private javax.swing.JLabel jLabel_fotoUsuari;
    private javax.swing.JLabel jLabel_mostrarPwd1;
    private javax.swing.JLabel jLabel_mostrarPwd2;
    private javax.swing.JLabel jLabel_mostrarPwd3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField_novaPwd;
    private javax.swing.JPasswordField jPasswordField_repetirPwd;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField jTextField_email;
    private javax.swing.JTextField jTextField_login;
    private javax.swing.JTextField jTextField_nouEmail;
    private javax.swing.JTextField jTextField_pwd;
    // End of variables declaration//GEN-END:variables
}
