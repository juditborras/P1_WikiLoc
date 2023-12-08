/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.milaifontanals.wikiloc.breadcrumb.com.raven.component.Breadcrumb;
import org.milaifontanals.wikiloc.jdbc.GestorBDWikilocJdbc;
import org.milaifontanals.wikiloc.model.Punt;
import org.milaifontanals.wikiloc.model.Ruta;
import org.milaifontanals.wikiloc.model.Tipus;
import org.milaifontanals.wikiloc.persistencia.GestorBDWikilocException;

/**
 *
 * @author JUDIT
 */
public class panellAfegir_rutaPunts extends javax.swing.JPanel {

    /**
     * Creates new form panellAfegir_rutaPunts
     */
    
    private GestorBDWikilocJdbc gestorBDWikilocJdbc;
    static List<Punt> llistaPuntsRuta;
    Punt punt_seleccionat;
    List<Tipus> llistaTipusPunts;
    Tipus t;
    DefaultListModel dlm, dlm_tipus;
    Breadcrumb breadcrumb1;
    
    ImageIcon novaImatgeSeleccionada;
    
    String num, nomPunt, descPunt, lat, lon, alt, filePath, ordre;
    boolean ordre_canviat = false, nomPunt_canviat = false, descPunt_canviat = false, lat_canviat = false, lon_canviat = false, alt_canviat = false, tipusPunt_canviada = false, fotoPunt_canviada = false;
    boolean editarPuntRuta = false, botoAfegirClicat = false, botoPujarClicat = false, botoBaixarClicat = false;
    int qc3;
    
    String tmpUrlFoto;
    
    ImageIcon fotoNulla = new ImageIcon("img"+File.separator+"foto_nulla.jpg");
    boolean dadesCorrectes = false;
    
    public panellAfegir_rutaPunts(Breadcrumb breadcrumb1,Ruta nova_ruta, List<Punt> punts_p) {
        initComponents();
        this.breadcrumb1 = breadcrumb1;
        dlm = new DefaultListModel();
        
        if(punts_p!=null){
            llistaPuntsRuta = punts_p;
            
            dlm.clear();
            System.out.println("QT PUNTS DE RUTA: "+dlm);
            for (Punt p : llistaPuntsRuta) {

                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

            }
            jList_puntsRuta.setModel(dlm);
            jList_puntsRuta = new JList(dlm);
            
            
        }else{
            llistaPuntsRuta = new ArrayList();
        }
        

        jTextField_ordrePunt.setVisible(false);
        jTextField_nomPunt.setVisible(false);
        jTextArea_descPunt.setVisible(false);
        jScrollPane2.setVisible(false);
        jTextField_latPunt.setVisible(false);
        jTextField_lonPunt.setVisible(false);
        jTextField_altPunt.setVisible(false);
        jComboBox_tipusPunt.setVisible(false);
        jLabel_fotoPunt.setVisible(false);
        jButton_seleccionarFoto.setVisible(false);

        jButton_pujar.setVisible(false);
        jButton_baixar.setVisible(false);
        jButton_eliminarPunt.setVisible(false);
        jButton_afegirPunt.setVisible(true);
        jButton_netejarPunts.setVisible(false);

        jButton_desarCanvisPunts.setVisible(false);
        
        
        jTextField_ordrePunt.setText(llistaPuntsRuta.size()+1+"");
        
        llistaTipusPunts = new ArrayList();
        
        try {
            gestorBDWikilocJdbc = new GestorBDWikilocJdbc();
            
            llistaTipusPunts = gestorBDWikilocJdbc.obtenirLlistaTipus();

            System.out.println("LEN: " + llistaTipusPunts.size());
            Tipus[] tip = new Tipus[llistaTipusPunts.size()];
            tip = llistaTipusPunts.toArray(tip);

            //jComboBox_tipusPunt = new JComboBox(tip);
            System.out.println("ITEM COUNT: " + jComboBox_tipusPunt.getItemCount());
            //jComboBox_tipusPunt.setSelectedIndex(-1);

            jComboBox_tipusPunt.setModel(new DefaultComboBoxModel<>(llistaTipusPunts.toArray(new Tipus[0])));
            jComboBox_tipusPunt.setSelectedIndex(-1);
            
            //jComboBox_tipusPunt.setModel(new DefaultComboBoxModel<>(llistaTipusPunts.toArray(new Tipus[0])));

            for (int i = 0; i < llistaTipusPunts.size(); i++) {
                System.out.println(tip[i]);
            }

        } catch (GestorBDWikilocException ex) {
            Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("NOVA RUTA: "+nova_ruta);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jList_puntsRuta = new javax.swing.JList<>();
        jButton_eliminarPunt = new javax.swing.JButton();
        jButton_afegirPunt = new javax.swing.JButton();
        jButton_netejarPunts = new javax.swing.JButton();
        jButton_pujar = new javax.swing.JButton();
        jButton_baixar = new javax.swing.JButton();
        jTextField_ordrePunt = new javax.swing.JTextField();
        jTextField_nomPunt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_descPunt = new javax.swing.JTextArea();
        jTextField_latPunt = new javax.swing.JTextField();
        jTextField_lonPunt = new javax.swing.JTextField();
        jTextField_altPunt = new javax.swing.JTextField();
        jComboBox_tipusPunt = new javax.swing.JComboBox<>();
        jLabel_fotoPunt = new javax.swing.JLabel();
        jButton_seleccionarFoto = new javax.swing.JButton();
        jButton_desarCanvisPunts = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jList_puntsRuta.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList_puntsRutaValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList_puntsRuta);

        jButton_eliminarPunt.setText("eliminar element");
        jButton_eliminarPunt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_eliminarPuntMouseClicked(evt);
            }
        });

        jButton_afegirPunt.setText("afegir element llista");
        jButton_afegirPunt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_afegirPuntActionPerformed(evt);
            }
        });

        jButton_netejarPunts.setText("netejar llista");
        jButton_netejarPunts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_netejarPuntsMouseClicked(evt);
            }
        });

        jButton_pujar.setText("pujar elem");
        jButton_pujar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_pujarActionPerformed(evt);
            }
        });

        jButton_baixar.setText("baixar elem");
        jButton_baixar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_baixarActionPerformed(evt);
            }
        });

        jTextField_ordrePunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_ordrePuntKeyReleased(evt);
            }
        });

        jTextField_nomPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_nomPuntKeyReleased(evt);
            }
        });

        jTextArea_descPunt.setColumns(20);
        jTextArea_descPunt.setRows(5);
        jTextArea_descPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea_descPuntKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea_descPunt);

        jTextField_latPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_latPuntKeyReleased(evt);
            }
        });

        jTextField_lonPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_lonPuntKeyReleased(evt);
            }
        });

        jTextField_altPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_altPuntKeyReleased(evt);
            }
        });

        jComboBox_tipusPunt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tipusPuntItemStateChanged(evt);
            }
        });

        jLabel_fotoPunt.setText("foto");

        jButton_seleccionarFoto.setText("seleccionar foto");
        jButton_seleccionarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_seleccionarFotoActionPerformed(evt);
            }
        });

        jButton_desarCanvisPunts.setText("desar");
        jButton_desarCanvisPunts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_desarCanvisPuntsMouseClicked(evt);
            }
        });

        jLabel1.setText("Atenció: els canvis no es desaran si no completes les dades requerides dels punts de la ruta seguint l'ordre de les pantalles!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton_pujar)
                                            .addComponent(jButton_baixar))
                                        .addGap(79, 79, 79)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_ordrePunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_nomPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(192, 192, 192)
                                        .addComponent(jTextField_latPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_fotoPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_seleccionarFoto)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_eliminarPunt)
                                .addGap(48, 48, 48)
                                .addComponent(jButton_afegirPunt))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_netejarPunts)
                                .addGap(339, 339, 339)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_lonPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jComboBox_tipusPunt, javax.swing.GroupLayout.Alignment.LEADING, 0, 219, Short.MAX_VALUE)
                                            .addComponent(jTextField_altPunt, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(120, 120, 120)
                                        .addComponent(jButton_desarCanvisPunts)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_eliminarPunt)
                            .addComponent(jButton_afegirPunt))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jButton_netejarPunts))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jTextField_lonPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_altPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jTextField_ordrePunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jTextField_nomPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton_pujar)))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_baixar)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel_fotoPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addComponent(jTextField_latPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton_seleccionarFoto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_tipusPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_desarCanvisPunts))
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_ordrePuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ordrePuntKeyReleased
        
        if (ordre.equals(jTextField_ordrePunt.getText())) {
            ordre_canviat = false;
            modificacionsCampsPunt();
        } else {
            ordre_canviat = true;
            modificacionsCampsPunt();
        }
    }//GEN-LAST:event_jTextField_ordrePuntKeyReleased

    private void jTextField_nomPuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_nomPuntKeyReleased
        
        if (botoAfegirClicat) {

            System.out.println("ordre: "+jTextField_ordrePunt.getText().length());
            System.out.println("nom: "+jTextField_nomPunt.getText().length());
            System.out.println("textarea: "+jTextArea_descPunt.getText().length());
            System.out.println("lat: "+jTextField_latPunt.getText().length());
            System.out.println("lon: "+jTextField_lonPunt.getText().length());
            System.out.println("alt: "+jTextField_altPunt.getText().length());
            System.out.println("tipus: "+jComboBox_tipusPunt.getSelectedItem());
            
            if (jTextField_ordrePunt.getText().length() != 0 && jTextField_nomPunt.getText().length() != 0 && jTextArea_descPunt.getText().length() != 0 && jTextField_latPunt.getText().length() != 0 && jTextField_lonPunt.getText().length() != 0 && jTextField_altPunt.getText().length() != 0 && jComboBox_tipusPunt.getSelectedItem() != null) {
                jButton_desarCanvisPunts.setEnabled(true);
                dadesCorrectes = true;
                System.out.println("nom punt entra");
            } else {
                jButton_desarCanvisPunts.setEnabled(false);
                dadesCorrectes = false;
                System.out.println("nom punt no entra");
            }
        } else {
            System.out.println("entra else i no hauria deeeeee");
            if (nomPunt.equals(jTextField_nomPunt.getText())) {
                nomPunt_canviat = false;
                
                modificacionsCampsPunt();
            } else {
                nomPunt_canviat = true;
                modificacionsCampsPunt();
            }
        }
        
    }//GEN-LAST:event_jTextField_nomPuntKeyReleased

    private void jTextArea_descPuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea_descPuntKeyReleased
        
        if (botoAfegirClicat) {
            if (jTextField_ordrePunt.getText().length() != 0 && jTextField_nomPunt.getText().length() != 0 && jTextArea_descPunt.getText().length() != 0 && jTextField_latPunt.getText().length() != 0 && jTextField_lonPunt.getText().length() != 0 && jTextField_altPunt.getText().length() != 0 && jComboBox_tipusPunt.getSelectedItem() != null) {
                jButton_desarCanvisPunts.setEnabled(true);
                dadesCorrectes = true;
            } else {
                jButton_desarCanvisPunts.setEnabled(false);
                dadesCorrectes = false;
            }
        } else {
            if (descPunt.equals(jTextArea_descPunt.getText())) {
                descPunt_canviat = false;
                modificacionsCampsPunt();
            } else {
                descPunt_canviat = true;
                modificacionsCampsPunt();
            }
        }
        
    }//GEN-LAST:event_jTextArea_descPuntKeyReleased

    private void jTextField_latPuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_latPuntKeyReleased

        if (botoAfegirClicat) {

            if (jTextField_ordrePunt.getText().length() != 0 && jTextField_nomPunt.getText().length() != 0 && jTextArea_descPunt.getText().length() != 0 && jTextField_latPunt.getText().length() != 0 && jTextField_lonPunt.getText().length() != 0 && jTextField_altPunt.getText().length() != 0 && jComboBox_tipusPunt.getSelectedItem() != null) {
                jButton_desarCanvisPunts.setEnabled(true);
                dadesCorrectes = true;
            } else {
                jButton_desarCanvisPunts.setEnabled(false);
                dadesCorrectes = false;
            }
        } else {
            if (lat.equals(jTextField_latPunt.getText())) {
                lat_canviat = false;
                modificacionsCampsPunt();
            } else {
                lat_canviat = true;
                modificacionsCampsPunt();
            }
        }
        
    }//GEN-LAST:event_jTextField_latPuntKeyReleased

    private void jTextField_lonPuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_lonPuntKeyReleased
        
        if (botoAfegirClicat) {

            if (jTextField_ordrePunt.getText().length() != 0 && jTextField_nomPunt.getText().length() != 0 && jTextArea_descPunt.getText().length() != 0 && jTextField_latPunt.getText().length() != 0 && jTextField_lonPunt.getText().length() != 0 && jTextField_altPunt.getText().length() != 0 && jComboBox_tipusPunt.getSelectedItem() != null) {
                jButton_desarCanvisPunts.setEnabled(true);
                dadesCorrectes = true;
            } else {
                jButton_desarCanvisPunts.setEnabled(false);
                dadesCorrectes = false;
            }

        } else {
            if (lon.equals(jTextField_lonPunt.getText())) {
                lon_canviat = false;
                modificacionsCampsPunt();
            } else {
                lon_canviat = true;
                modificacionsCampsPunt();
            }
        }
        
    }//GEN-LAST:event_jTextField_lonPuntKeyReleased

    private void jTextField_altPuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_altPuntKeyReleased
        
        if (botoAfegirClicat) {

            if (jTextField_ordrePunt.getText().length() != 0 && jTextField_nomPunt.getText().length() != 0 && jTextArea_descPunt.getText().length() != 0 && jTextField_latPunt.getText().length() != 0 && jTextField_lonPunt.getText().length() != 0 && jTextField_altPunt.getText().length() != 0 && jComboBox_tipusPunt.getSelectedItem() != null) {
                jButton_desarCanvisPunts.setEnabled(true);
                dadesCorrectes = true;
            } else {
                jButton_desarCanvisPunts.setEnabled(false);
                dadesCorrectes = false;
            }
        } else {
            if (alt.equals(jTextField_altPunt.getText())) {
                alt_canviat = false;
                modificacionsCampsPunt();
            } else {
                alt_canviat = true;
                modificacionsCampsPunt();
            }
        }
        
    }//GEN-LAST:event_jTextField_altPuntKeyReleased

    private void jComboBox_tipusPuntItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_tipusPuntItemStateChanged
        
        try {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                // Item was just selected

                //qc3++;

                //if (qc3 > 1) {

                    JComboBox cb = (JComboBox) evt.getSource();

                    if (botoAfegirClicat) {

                        if (jTextField_ordrePunt.getText().length() != 0 && jTextField_nomPunt.getText().length() != 0 && jTextArea_descPunt.getText().length() != 0 && jTextField_latPunt.getText().length() != 0 && jTextField_lonPunt.getText().length() != 0 && jTextField_altPunt.getText().length() != 0 && jComboBox_tipusPunt.getSelectedItem() != null) {
                            jButton_desarCanvisPunts.setEnabled(true);
                            dadesCorrectes = true;
                        } else {
                            jButton_desarCanvisPunts.setEnabled(false);
                            dadesCorrectes = false;
                        }
                    } else {

                        Tipus tipus_nou = (Tipus) cb.getSelectedItem();

                        if (t.getId() != tipus_nou.getId()) {
                            tipusPunt_canviada = true;
                            modificacionsCampsPunt();

                        } else {
                            tipusPunt_canviada = false;
                            modificacionsCampsPunt();
                        }
                    }

                //}

            }
        } catch (Exception ex) {

        }
        
    }//GEN-LAST:event_jComboBox_tipusPuntItemStateChanged

    private void jButton_seleccionarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_seleccionarFotoActionPerformed
        
        JFileChooser jfc = new JFileChooser();
                
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        
        jfc.setFileFilter(imageFilter);
        
        jfc.showOpenDialog(jfc);
        
        File imgFile = jfc.getSelectedFile();
        tmpUrlFoto = imgFile.getAbsolutePath();
        
        if(imgFile != null){
            
           
            filePath = imgFile.getAbsolutePath();
            novaImatgeSeleccionada = new ImageIcon(filePath);
            
            jLabel_fotoPunt.setIcon(novaImatgeSeleccionada);
            
            BufferedImage bi = new BufferedImage(
            novaImatgeSeleccionada.getIconWidth(),
            novaImatgeSeleccionada.getIconHeight(),
            BufferedImage.TYPE_INT_RGB);
            
            
            String extensio = filePath.substring(filePath.length() - 3);
            byte[] bt = imageToByteArray(bi, extensio);
            
            try{
                if(Arrays.equals(punt_seleccionat.getFoto(), bt)){
                    fotoPunt_canviada = true;
                    //modificacionsCampsPunt();
                }else{
                    fotoPunt_canviada = false;
                    //modificacionsCampsPunt();
                }
            }catch(Exception ex){
                
            }

            
            if(jTextField_ordrePunt.getText().length() != 0 && jTextField_nomPunt.getText().length() != 0 && jTextArea_descPunt.getText().length() != 0 && jTextField_latPunt.getText().length() != 0 && jTextField_lonPunt.getText().length() != 0 && jTextField_altPunt.getText().length() != 0 && jComboBox_tipusPunt.getSelectedItem() != null){
                jButton_desarCanvisPunts.setEnabled(true);
                
            }else{
                jButton_desarCanvisPunts.setEnabled(false);
            }
            
        
        }else{
            jLabel_fotoPunt.setIcon(fotoNulla);
        }
        
    }//GEN-LAST:event_jButton_seleccionarFotoActionPerformed

    private void jButton_desarCanvisPuntsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_desarCanvisPuntsMouseClicked
        /*
        System.out.println("CLICO");
        // public Punt(String nom, String descPunt, Integer lat, Integer lon, Integer alt, Integer ordre, Ruta idRuta, Tipus idTipus) {
        Punt punt = new Punt(jTextField_nomPunt.getText(),jTextArea_descPunt.getText(),Integer.parseInt(jTextField_latPunt.getText()),Integer.parseInt(jTextField_lonPunt.getText()),Integer.parseInt(jTextField_altPunt.getText()),Integer.parseInt(jTextField_ordrePunt.getText()),null,(Tipus)jComboBox_tipusPunt.getSelectedItem());
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        dlm = new DefaultListModel();
        
        llistaPuntsRuta.add(punt);
        
        
        /*
        for (Punt p : llistaPuntsRuta) {

            dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

        }
*/
        if(llistaPuntsRuta == null || llistaPuntsRuta.size()==0){
            afegirItemBreadcrumb();
            System.out.println("SOC NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }else{
            System.out.println("NO SOC NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        
        
        Punt punt = new Punt(jTextField_nomPunt.getText(),jTextArea_descPunt.getText(),Integer.parseInt(jTextField_latPunt.getText()),Integer.parseInt(jTextField_lonPunt.getText()),Integer.parseInt(jTextField_altPunt.getText()),Integer.parseInt(jTextField_ordrePunt.getText()),null,(Tipus)jComboBox_tipusPunt.getSelectedItem());
        punt.setTmpFoto(novaImatgeSeleccionada);
        punt.setTmpUrlFoto(tmpUrlFoto);
        llistaPuntsRuta.add(punt);
            //dlm = new DefaultListModel();
            dlm.clear();
            System.out.println("QT PUNTS DE RUTA: "+dlm);
            for (Punt p : llistaPuntsRuta) {

                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

            }
            jList_puntsRuta.setModel(dlm);
            jList_puntsRuta = new JList(dlm);
            System.out.println(jList_puntsRuta.getModel().toString());


        
        /*
        jList_puntsRuta.setModel(dlm);
        jList_puntsRuta = new JList(dlm);
        */
        
        /*
        System.out.println("llistaPuntsRuta size: "+llistaPuntsRuta.size());
        
        //dlm.clear();
        System.out.println("Capacity: "+dlm.capacity());
        for (Punt p : llistaPuntsRuta) {

            dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());
            System.out.println(p.getOrdre().toString() + " - " + p.getNom().toString());
        }
        jList_puntsRuta.setModel(dlm);
        jList_puntsRuta = new JList(dlm);
        */
        
        
        
        
        jTextField_ordrePunt.setText(llistaPuntsRuta.size()+1+"");
        jTextField_nomPunt.setText("");
        jTextArea_descPunt.setText("");
        jTextField_latPunt.setText("");
        jTextField_lonPunt.setText("");
        jTextField_altPunt.setText("");
        jLabel_fotoPunt.setIcon(fotoNulla);
        jComboBox_tipusPunt.setSelectedIndex(-1);
        
        
        jTextField_ordrePunt.setVisible(false);
        jTextField_nomPunt.setVisible(false);
        jTextArea_descPunt.setVisible(false);
        jScrollPane2.setVisible(false);
        jTextField_latPunt.setVisible(false);
        jTextField_lonPunt.setVisible(false);
        jTextField_altPunt.setVisible(false);
        jComboBox_tipusPunt.setVisible(false);
        jLabel_fotoPunt.setVisible(false);
        jButton_seleccionarFoto.setVisible(false);

        jButton_pujar.setVisible(false);
        jButton_baixar.setVisible(false);
        jButton_eliminarPunt.setVisible(false);
        jButton_afegirPunt.setVisible(true);
        jButton_netejarPunts.setVisible(false);

        jButton_desarCanvisPunts.setVisible(false);
        
        jList_puntsRuta.updateUI();
        

        
    }//GEN-LAST:event_jButton_desarCanvisPuntsMouseClicked

    private void jButton_pujarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_pujarActionPerformed
        
        int ordre_actual = punt_seleccionat.getOrdre();

        int selectedIndex = punt_seleccionat.getOrdre() - 1;

        if (selectedIndex > 0) {

            punt_seleccionat.setOrdre(ordre_actual - 1);
            Punt punt_adalt = llistaPuntsRuta.get(selectedIndex - 1);
            punt_adalt.setOrdre(punt_adalt.getOrdre() + 1);

            Collections.sort(llistaPuntsRuta);

            dlm.clear();
            
           
            for (Punt p : llistaPuntsRuta) {

                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

            }

            botoPujarClicat = true;
            modificacionsCampsPunt();
        }
        
    }//GEN-LAST:event_jButton_pujarActionPerformed

    private void jButton_baixarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_baixarActionPerformed
        
        int ordre_actual = punt_seleccionat.getOrdre();

        int selectedIndex = punt_seleccionat.getOrdre() - 1;

        if (selectedIndex + 2 <= llistaPuntsRuta.size()) {

            punt_seleccionat.setOrdre(ordre_actual + 1);
            Punt punt_abaix = llistaPuntsRuta.get(selectedIndex + 1);
            punt_abaix.setOrdre(punt_abaix.getOrdre() - 1);

            Collections.sort(llistaPuntsRuta);

            dlm.clear();

            for (Punt p : llistaPuntsRuta) {

                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

            }

            botoBaixarClicat = true;
            modificacionsCampsPunt();
        }
        
    }//GEN-LAST:event_jButton_baixarActionPerformed

    private void jButton_eliminarPuntMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_eliminarPuntMouseClicked
        
        int resposta = JOptionPane.showConfirmDialog(null, "Estàs segur d'esborrar el punt de ruta?",
                           "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                           JOptionPane.INFORMATION_MESSAGE);
        
        
        if (resposta == 0) {
            //dlm.removeElement(punt_seleccionat);
            System.out.println("ESBORRARE: "+punt_seleccionat);
            
            Punt p_esborrar = null;
            int i=0;
            int pos_borrar = 0;
            for (Punt p : llistaPuntsRuta) {
                if(p.getNom().equals(punt_seleccionat.getNom()) && p.getOrdre()==punt_seleccionat.getOrdre()){
                   pos_borrar = i; 
                    System.out.println("LLISTA ESBORRAR; "+pos_borrar);
                }
                i++;
            }
            
            llistaPuntsRuta.remove(pos_borrar);
            
            
            
            dlm.clear();
            System.out.println("QT PUNTS DE RUTA: "+dlm);
            i=1;
            for (Punt p : llistaPuntsRuta) {
                p.setOrdre(i);
                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());
                System.out.println(p.getOrdre().toString() + " - " + p.getNom().toString());

                i++;
            }
            jList_puntsRuta.setModel(dlm);
            jList_puntsRuta = new JList(dlm);
            System.out.println(jList_puntsRuta.getModel().toString());
        }
        
    }//GEN-LAST:event_jButton_eliminarPuntMouseClicked

    private void jButton_netejarPuntsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejarPuntsMouseClicked
        
        int resposta = JOptionPane.showConfirmDialog(null, "Estàs segur d'eliminar TOTS els punts de ruta associats a la ruta?",
                            "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);

        if (resposta == 0) {
            dlm.clear();
            llistaPuntsRuta.clear();

            jTextField_ordrePunt.setText(llistaPuntsRuta.size() + 1 + "");
            jTextField_nomPunt.setText("");
            jTextArea_descPunt.setText("");
            jTextField_latPunt.setText("");
            jTextField_lonPunt.setText("");
            jTextField_altPunt.setText("");
            jLabel_fotoPunt.setIcon(fotoNulla);
            jComboBox_tipusPunt.setSelectedIndex(-1);

            jTextField_ordrePunt.setVisible(false);
            jTextField_nomPunt.setVisible(false);
            jTextArea_descPunt.setVisible(false);
            jScrollPane2.setVisible(false);
            jTextField_latPunt.setVisible(false);
            jTextField_lonPunt.setVisible(false);
            jTextField_altPunt.setVisible(false);
            jComboBox_tipusPunt.setVisible(false);
            jLabel_fotoPunt.setVisible(false);
            jButton_seleccionarFoto.setVisible(false);

            jButton_pujar.setVisible(false);
            jButton_baixar.setVisible(false);
            jButton_eliminarPunt.setVisible(false);
            jButton_afegirPunt.setVisible(true);
            jButton_netejarPunts.setVisible(false);

            jButton_desarCanvisPunts.setVisible(false);
        }
        
    }//GEN-LAST:event_jButton_netejarPuntsMouseClicked

    private void jList_puntsRutaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList_puntsRutaValueChanged
        
        if (!evt.getValueIsAdjusting()) {

            JList jlist = (JList) evt.getSource();

            int idx = jlist.getSelectedIndex();

            if (llistaPuntsRuta.size() > 0 && idx != -1) {
                System.out.println("INDEX: " + idx);
                punt_seleccionat = llistaPuntsRuta.get(idx);
                System.out.println("INDEX OBJ: "+punt_seleccionat);
            }

            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            jTextField_ordrePunt.setVisible(true);
            jTextField_nomPunt.setVisible(true);
            jTextArea_descPunt.setVisible(true);
            jScrollPane2.setVisible(true);
            jTextField_latPunt.setVisible(true);
            jTextField_lonPunt.setVisible(true);
            jTextField_altPunt.setVisible(true);
            jComboBox_tipusPunt.setVisible(true);
            jLabel_fotoPunt.setVisible(true);
            jButton_seleccionarFoto.setVisible(true);

            jButton_pujar.setVisible(true);
            jButton_baixar.setVisible(true);
            jButton_eliminarPunt.setVisible(true);

            jButton_netejarPunts.setVisible(true);

            jTextField_ordrePunt.setEditable(false);
            jTextField_nomPunt.setEditable(true);
            jTextArea_descPunt.setEditable(true);
            jTextField_latPunt.setEditable(true);
            jTextField_lonPunt.setEditable(true);
            jTextField_altPunt.setEditable(true);
            jComboBox_tipusPunt.setEnabled(true);

            jButton_desarCanvisPunts.setVisible(true);
            jButton_desarCanvisPunts.setEnabled(false);

            jTextField_ordrePunt.setText(punt_seleccionat.getOrdre().toString());
            jTextField_nomPunt.setText(punt_seleccionat.getNom());
            jTextArea_descPunt.setText(punt_seleccionat.getDescPunt());
            jTextField_latPunt.setText(punt_seleccionat.getLat()+"");
            jTextField_lonPunt.setText(punt_seleccionat.getLon()+"");
            jTextField_altPunt.setText(punt_seleccionat.getAlt()+"");
            jComboBox_tipusPunt.getModel().setSelectedItem(punt_seleccionat.getIdTipus());
            
            if(punt_seleccionat.getTmpFoto() != null){
                jLabel_fotoPunt.setIcon(punt_seleccionat.getTmpFoto());
            }else{
                jLabel_fotoPunt.setIcon(fotoNulla);
            }
            

            //num = punt_seleccionat.getNum() + "";
            nomPunt = punt_seleccionat.getNom();
            descPunt = punt_seleccionat.getDescPunt();
            lat = punt_seleccionat.getLat() + "";
            lon = punt_seleccionat.getLon() + "";
            alt = punt_seleccionat.getAlt() + "";
            ordre = punt_seleccionat.getOrdre() + "";
            /*
            dlm.clear();
            System.out.println("QT PUNTS DE RUTA: "+dlm);
            for (Punt p : llistaPuntsRuta) {

                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

            }
            jList_puntsRuta.setModel(dlm);
            jList_puntsRuta = new JList(dlm);
            System.out.println(jList_puntsRuta.getModel().toString());
             */
        }
    }//GEN-LAST:event_jList_puntsRutaValueChanged

    private void jButton_afegirPuntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_afegirPuntActionPerformed
        
        botoAfegirClicat = true;

        
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        jTextField_ordrePunt.setVisible(true);
        jTextField_nomPunt.setVisible(true);
        jTextArea_descPunt.setVisible(true);
        jScrollPane2.setVisible(true);
        jTextField_latPunt.setVisible(true);
        jTextField_lonPunt.setVisible(true);
        jTextField_altPunt.setVisible(true);
        jComboBox_tipusPunt.setVisible(true);
        jLabel_fotoPunt.setVisible(true);
        jButton_seleccionarFoto.setVisible(true);

        jButton_pujar.setVisible(true);
        jButton_baixar.setVisible(true);
        jButton_eliminarPunt.setVisible(true);

        jButton_netejarPunts.setVisible(true);

        jTextField_ordrePunt.setEditable(false);
        jTextField_nomPunt.setEditable(true);
        jTextArea_descPunt.setEditable(true);
        jTextField_latPunt.setEditable(true);
        jTextField_lonPunt.setEditable(true);
        jTextField_altPunt.setEditable(true);
        jComboBox_tipusPunt.setEnabled(true);

        jButton_desarCanvisPunts.setVisible(true);
        jButton_desarCanvisPunts.setEnabled(false);
        
        jTextField_ordrePunt.setText(llistaPuntsRuta.size()+1+"");
        jTextField_nomPunt.setText("");
        jTextArea_descPunt.setText("");
        jTextField_latPunt.setText("");
        jTextField_lonPunt.setText("");
        jTextField_altPunt.setText("");
        jLabel_fotoPunt.setIcon(fotoNulla);
        jComboBox_tipusPunt.setSelectedIndex(-1);
        
        
        
    }//GEN-LAST:event_jButton_afegirPuntActionPerformed


    public void modificacionsCampsPunt(){
        
        if(ordre_canviat || nomPunt_canviat || descPunt_canviat || lat_canviat || lon_canviat || alt_canviat || tipusPunt_canviada || fotoPunt_canviada || botoPujarClicat || botoBaixarClicat){
            jButton_desarCanvisPunts.setEnabled(true);
            System.out.println("C");
        }else{
            jButton_desarCanvisPunts.setEnabled(false);
            System.out.println("D");
        }
        
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
    
    public static List<Punt> retornaLlistaPunts(){
        return llistaPuntsRuta;
    }
    
    public void afegirItemBreadcrumb(){
        
        if(jTextField_ordrePunt.getText().length() != 0 && jTextField_nomPunt.getText().length() != 0 && jTextArea_descPunt.getText().length() != 0 && jTextField_latPunt.getText().length() != 0 && jTextField_lonPunt.getText().length() != 0 && jTextField_altPunt.getText().length() != 0 && jComboBox_tipusPunt.getSelectedItem() != null){
            System.out.println("DADES CORRECTES");

            if(breadcrumb1.getComponentCount()<4){
                    
                breadcrumb1.addItem("Item 2"); 


            }
            //breadcrumb1.addItem("Item 2"); 

             
        }else{
            System.out.println("DADES INCORRECTES!!");
            System.out.println("RESUM: ");
            System.out.println("jTextField_ordrePunt.getText().length(): "+jTextField_ordrePunt.getText().length());
            System.out.println("jTextField_nomPunt.getText().length(): "+jTextField_nomPunt.getText().length());
            System.out.println("jTextArea_descPunt.getText().length(): "+jTextArea_descPunt.getText().length());
            System.out.println("jTextField_latPunt.getText().length(): "+jTextField_latPunt.getText().length());
            System.out.println("jTextField_lonPunt.getText().length(): "+jTextField_lonPunt.getText().length());
            System.out.println("jTextField_altPunt.getText().length(): "+jTextField_altPunt.getText().length());
            System.out.println("jComboBox_tipusPunt.getSelectedItem(): "+jComboBox_tipusPunt.getSelectedItem());

        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_afegirPunt;
    private javax.swing.JButton jButton_baixar;
    private javax.swing.JButton jButton_desarCanvisPunts;
    private javax.swing.JButton jButton_eliminarPunt;
    private javax.swing.JButton jButton_netejarPunts;
    private javax.swing.JButton jButton_pujar;
    private javax.swing.JButton jButton_seleccionarFoto;
    private javax.swing.JComboBox<Tipus> jComboBox_tipusPunt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_fotoPunt;
    private javax.swing.JList<String> jList_puntsRuta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea_descPunt;
    private javax.swing.JTextField jTextField_altPunt;
    private javax.swing.JTextField jTextField_latPunt;
    private javax.swing.JTextField jTextField_lonPunt;
    private javax.swing.JTextField jTextField_nomPunt;
    private javax.swing.JTextField jTextField_ordrePunt;
    // End of variables declaration//GEN-END:variables
}
