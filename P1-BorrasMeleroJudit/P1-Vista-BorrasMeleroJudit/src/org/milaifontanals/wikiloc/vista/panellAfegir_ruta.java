/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.milaifontanals.wikiloc.breadcrumb.com.raven.component.Breadcrumb;
import org.milaifontanals.wikiloc.breadcrumb.com.raven.component.BreadcrumbItem;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.io.IOUtils;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.shef.Demo;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.shef.HTMLEditorPane;
import org.milaifontanals.wikiloc.components.TextPrompt;
import org.milaifontanals.wikiloc.model.Punt;
import org.milaifontanals.wikiloc.model.Ruta;
import org.milaifontanals.wikiloc.model.Usuari;

/**
 *
 * @author JUDIT
 */
public class panellAfegir_ruta extends javax.swing.JPanel {
   
    Usuari usuari_loginat;
    ImageIcon estrellaBlanca = new ImageIcon("img"+File.separator+"estrella_blanca.png");
    ImageIcon estrellaGroga = new ImageIcon("img"+File.separator+"estrella_groga.png");
    
    private HTMLEditorPane editor;
    private JFrame frame;
    Demo demo;
    Breadcrumb breadcrumb1; 
    int dific = 1;
    int total;
    
    boolean dadesCorrectes = false;
    static Ruta r;
    
    String html_s = "";
    int hores = 0, minuts = 0;
    
    List<Punt> llistaPunts;
    
    public panellAfegir_ruta(Breadcrumb breadcrumb1, Usuari usuari_loginat, List<Punt> llistaPunts) {
        initComponents();
        //TextPrompt placeHolder_titol = new TextPrompt("títol de la ruta",jTextField_titolRuta);
        this.breadcrumb1 = breadcrumb1;
        this.usuari_loginat = usuari_loginat;
        this.llistaPunts = llistaPunts;
        
        for(int i = 1; i <= 24; i++ ){            
            jComboBox_tempsHruta.addItem(i+"");
        }
        
        for(int i = 1; i <= 60; i++ ){            
            jComboBox_tempsMruta.addItem(i+"");
        }
        
        

        /*
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(editor.getEditMenu());
        menuBar.add(editor.getFormatMenu());
        menuBar.add(editor.getInsertMenu());
        //jPanel2.setJMenuBar(menuBar);
        jPanel2.add(menuBar);
        jPanel2.add(editor);
        */
        
        if(r!=null){
            System.out.println("RUTA INFO: "+r.getTitol());
            jTextField_titolRuta.setText(r.getTitol());
            jTextField_dist.setText(r.getDist()+"");
            jTextArea1.setText(r.getDescRuta());
                        
            
            int r_total = r.getTemps();
            System.out.println("TEMPS: "+r.getTemps());
            
            int r_hores = r_total / 60;
            int r_minuts = r_total % 60;
            System.out.println("HORES: "+r_hores);
            System.out.println("MINUTS: "+r_minuts);
            
            jComboBox_tempsHruta.setSelectedItem(r_hores+"");
            jComboBox_tempsMruta.setSelectedItem(r_minuts+"");
                                    
            jTextField_desnPruta.setText(r.getDesnP()+"");
            jTextField_desnNruta.setText(r.getDesnN()+"");
                             
            int r_dific = r.getDific();
            System.out.println("DIFICULTAT: "+r_dific);
            
            if (r_dific == 1) {
                jLabel_estrella1.setIcon(estrellaGroga);
                jLabel_estrella2.setIcon(estrellaBlanca);
                jLabel_estrella3.setIcon(estrellaBlanca);
                jLabel_estrella4.setIcon(estrellaBlanca);
                jLabel_estrella5.setIcon(estrellaBlanca);
            } else if (r_dific == 2) {
                jLabel_estrella1.setIcon(estrellaGroga);
                jLabel_estrella2.setIcon(estrellaGroga);
                jLabel_estrella3.setIcon(estrellaBlanca);
                jLabel_estrella4.setIcon(estrellaBlanca);
                jLabel_estrella5.setIcon(estrellaBlanca);
            } else if (r_dific == 3) {
                jLabel_estrella1.setIcon(estrellaGroga);
                jLabel_estrella2.setIcon(estrellaGroga);
                jLabel_estrella3.setIcon(estrellaGroga);
                jLabel_estrella4.setIcon(estrellaBlanca);
                jLabel_estrella5.setIcon(estrellaBlanca);
            } else if (r_dific == 4) {
                jLabel_estrella1.setIcon(estrellaGroga);
                jLabel_estrella2.setIcon(estrellaGroga);
                jLabel_estrella3.setIcon(estrellaGroga);
                jLabel_estrella4.setIcon(estrellaGroga);
                jLabel_estrella5.setIcon(estrellaBlanca);
            } else if (r_dific == 5) {
                jLabel_estrella1.setIcon(estrellaGroga);
                jLabel_estrella2.setIcon(estrellaGroga);
                jLabel_estrella3.setIcon(estrellaGroga);
                jLabel_estrella4.setIcon(estrellaGroga);
                jLabel_estrella5.setIcon(estrellaGroga);
            }
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
        jTextField_titolRuta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_desnPruta = new javax.swing.JTextField();
        jTextField_desnNruta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel_estrella1 = new javax.swing.JLabel();
        jLabel_estrella2 = new javax.swing.JLabel();
        jLabel_estrella3 = new javax.swing.JLabel();
        jLabel_estrella4 = new javax.swing.JLabel();
        jLabel_estrella5 = new javax.swing.JLabel();
        jComboBox_tempsHruta = new javax.swing.JComboBox<>();
        jComboBox_tempsMruta = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField_dist = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jTextField_titolRuta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_titolRutaKeyReleased(evt);
            }
        });

        jLabel1.setText("hores");

        jLabel2.setText("minuts");

        jLabel3.setText("dificultat 5 estrelles: ");

        jTextField_desnPruta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_desnPrutaKeyReleased(evt);
            }
        });

        jTextField_desnNruta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_desnNrutaKeyReleased(evt);
            }
        });

        jLabel4.setText("titol");

        jLabel5.setText("desc");

        jLabel6.setText("temps");

        jLabel7.setText("desnP");

        jLabel8.setText("desnN");

        jLabel9.setText("cercar llibreria format html??????");

        jLabel_estrella1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_groga.png"))); // NOI18N
        jLabel_estrella1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_estrella1MouseClicked(evt);
            }
        });

        jLabel_estrella2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_estrella2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_estrella2MouseClicked(evt);
            }
        });

        jLabel_estrella3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_estrella3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_estrella3MouseClicked(evt);
            }
        });

        jLabel_estrella4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_estrella4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_estrella4MouseClicked(evt);
            }
        });

        jLabel_estrella5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_estrella5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_estrella5MouseClicked(evt);
            }
        });

        jComboBox_tempsHruta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tempsHrutaItemStateChanged(evt);
            }
        });

        jComboBox_tempsMruta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tempsMrutaItemStateChanged(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jTextField_dist.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_distKeyReleased(evt);
            }
        });

        jLabel10.setText("dist");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_estrella1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_estrella2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_estrella3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_estrella4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_estrella5)
                        .addGap(252, 252, 252))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(229, 229, 229)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addComponent(jLabel9))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jTextField_titolRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField_dist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox_tempsHruta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jTextField_desnNruta, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextField_desnPruta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1)))
                                .addGap(26, 26, 26)
                                .addComponent(jComboBox_tempsMruta, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)))
                        .addGap(28, 28, 28))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(340, 340, 340))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_titolRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_dist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jButton1))
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox_tempsHruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_tempsMruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_desnPruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_desnNruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3))
                    .addComponent(jLabel_estrella1)
                    .addComponent(jLabel_estrella4)
                    .addComponent(jLabel_estrella3)
                    .addComponent(jLabel_estrella2)
                    .addComponent(jLabel_estrella5))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(506, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_estrella1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_estrella1MouseClicked
        
        jLabel_estrella1.setIcon(estrellaGroga);
        jLabel_estrella2.setIcon(estrellaBlanca);
        jLabel_estrella3.setIcon(estrellaBlanca);
        jLabel_estrella4.setIcon(estrellaBlanca);
        jLabel_estrella5.setIcon(estrellaBlanca);
        
        dific = 1;
        if(r!=null){
            r.setDific(dific);
        }
        
        afegirItemBreadcrumb();
        
    }//GEN-LAST:event_jLabel_estrella1MouseClicked

    private void jLabel_estrella2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_estrella2MouseClicked
        
        jLabel_estrella1.setIcon(estrellaGroga);
        jLabel_estrella2.setIcon(estrellaGroga);
        jLabel_estrella3.setIcon(estrellaBlanca);
        jLabel_estrella4.setIcon(estrellaBlanca);
        jLabel_estrella5.setIcon(estrellaBlanca);
        
        dific = 2;
        if(r!=null){
            r.setDific(dific);
        }
        afegirItemBreadcrumb();
    }//GEN-LAST:event_jLabel_estrella2MouseClicked

    private void jLabel_estrella3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_estrella3MouseClicked
        
        jLabel_estrella1.setIcon(estrellaGroga);
        jLabel_estrella2.setIcon(estrellaGroga);
        jLabel_estrella3.setIcon(estrellaGroga);
        jLabel_estrella4.setIcon(estrellaBlanca);
        jLabel_estrella5.setIcon(estrellaBlanca);
        
        dific = 3;
        if(r!=null){
            r.setDific(dific);
        }
        afegirItemBreadcrumb();
    }//GEN-LAST:event_jLabel_estrella3MouseClicked

    private void jLabel_estrella4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_estrella4MouseClicked
        
        jLabel_estrella1.setIcon(estrellaGroga);
        jLabel_estrella2.setIcon(estrellaGroga);
        jLabel_estrella3.setIcon(estrellaGroga);
        jLabel_estrella4.setIcon(estrellaGroga);
        jLabel_estrella5.setIcon(estrellaBlanca);
        
        dific = 4;
        if(r!=null){
            r.setDific(dific);
        }
        afegirItemBreadcrumb();
    }//GEN-LAST:event_jLabel_estrella4MouseClicked

    private void jLabel_estrella5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_estrella5MouseClicked
        
        jLabel_estrella1.setIcon(estrellaGroga);
        jLabel_estrella2.setIcon(estrellaGroga);
        jLabel_estrella3.setIcon(estrellaGroga);
        jLabel_estrella4.setIcon(estrellaGroga);
        jLabel_estrella5.setIcon(estrellaGroga);

        dific = 5;
        if(r!=null){
            r.setDific(dific);
        }
        afegirItemBreadcrumb();
    }//GEN-LAST:event_jLabel_estrella5MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        
        
    
        demo = new Demo("",0,true,this);
        
        
            


    }//GEN-LAST:event_jButton1MouseClicked

    public void provas(){
       
        try {
                //Llegir fitxer
                File f = new File("info_ruta.txt");
                System.out.println(f.getAbsoluteFile());
                if(f.exists()){
                    System.out.println("EL FITXER EXISTEIX");
                    Scanner myReader = new Scanner(f);

                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        html_s += data;
                    }
                    myReader.close();
                }else{
                    System.out.println("El fitxer no existeix???");
                }

            } catch (FileNotFoundException ex) {
                System.out.println("error: " + ex.getMessage());
                Path currentRelativePath = Paths.get("");
                String s = currentRelativePath.toAbsolutePath().toString();
                System.out.println("Current absolute path is: " + s);
            } finally{

            }
        
        //html_s = "prova";
        try{
            System.out.println("DIFIC: "+dific);
            System.out.println("PROCEDIM A CREAR EL OBJECTE RUTA");
            r = new Ruta(jTextField_titolRuta.getText(),jTextArea1.getText(),html_s,Double.parseDouble(jTextField_dist.getText()),total,Integer.parseInt(jTextField_desnPruta.getText()),Integer.parseInt(jTextField_desnNruta.getText()),dific,usuari_loginat);
            System.out.println("RUTA CREADA: "+r);
        
        }catch(Exception ex){
            
        }

    }
    
    private void jTextField_titolRutaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_titolRutaKeyReleased
        
        if (jTextField_titolRuta.getText().length() != 0 && jTextArea1.getText().length() != 0 && jTextField_dist.getText().length() != 0 && jComboBox_tempsHruta.getSelectedItem() != null && jComboBox_tempsMruta.getSelectedItem() != null && jTextField_desnPruta.getText().length() != 0 && jTextField_desnNruta.getText().length() != 0) {
            dadesCorrectes = true;
            afegirItemBreadcrumb();
        }else{
            dadesCorrectes = false;
            afegirItemBreadcrumb();
        }
    }//GEN-LAST:event_jTextField_titolRutaKeyReleased

    private void jTextArea1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyReleased
        if (jTextField_titolRuta.getText().length() != 0 && jTextArea1.getText().length() != 0 && jTextField_dist.getText().length() != 0 && jComboBox_tempsHruta.getSelectedItem() != null && jComboBox_tempsMruta.getSelectedItem() != null && jTextField_desnPruta.getText().length() != 0 && jTextField_desnNruta.getText().length() != 0) {
            dadesCorrectes = true;
            afegirItemBreadcrumb();
        }else{
            dadesCorrectes = false;
            afegirItemBreadcrumb();
        }
    }//GEN-LAST:event_jTextArea1KeyReleased

    private void jComboBox_tempsHrutaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_tempsHrutaItemStateChanged
        if (jTextField_titolRuta.getText().length() != 0 && jTextArea1.getText().length() != 0 && jTextField_dist.getText().length() != 0 && jComboBox_tempsHruta.getSelectedItem() != null && jComboBox_tempsMruta.getSelectedItem() != null && jTextField_desnPruta.getText().length() != 0 && jTextField_desnNruta.getText().length() != 0) {
            dadesCorrectes = true;
            afegirItemBreadcrumb();
        }else{
            dadesCorrectes = false;
            afegirItemBreadcrumb();
        }
    }//GEN-LAST:event_jComboBox_tempsHrutaItemStateChanged

    private void jComboBox_tempsMrutaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_tempsMrutaItemStateChanged
        if (jTextField_titolRuta.getText().length() != 0 && jTextArea1.getText().length() != 0 && jTextField_dist.getText().length() != 0 && jComboBox_tempsHruta.getSelectedItem() != null && jComboBox_tempsMruta.getSelectedItem() != null && jTextField_desnPruta.getText().length() != 0 && jTextField_desnNruta.getText().length() != 0) {
            dadesCorrectes = true;
            afegirItemBreadcrumb();
        }else{
            dadesCorrectes = false;
            afegirItemBreadcrumb();
        }
    }//GEN-LAST:event_jComboBox_tempsMrutaItemStateChanged

    private void jTextField_desnPrutaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_desnPrutaKeyReleased
        if (jTextField_titolRuta.getText().length() != 0 && jTextArea1.getText().length() != 0 && jTextField_dist.getText().length() != 0 && jComboBox_tempsHruta.getSelectedItem() != null && jComboBox_tempsMruta.getSelectedItem() != null && jTextField_desnPruta.getText().length() != 0 && jTextField_desnNruta.getText().length() != 0) {
            dadesCorrectes = true;
            afegirItemBreadcrumb();
        }else{
            dadesCorrectes = false;
            afegirItemBreadcrumb();
        }
    }//GEN-LAST:event_jTextField_desnPrutaKeyReleased

    private void jTextField_desnNrutaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_desnNrutaKeyReleased
        if (jTextField_titolRuta.getText().length() != 0 && jTextArea1.getText().length() != 0 && jTextField_dist.getText().length() != 0 && jComboBox_tempsHruta.getSelectedItem() != null && jComboBox_tempsMruta.getSelectedItem() != null && jTextField_desnPruta.getText().length() != 0 && jTextField_desnNruta.getText().length() != 0) {
            dadesCorrectes = true;
            afegirItemBreadcrumb();
        }else{
            dadesCorrectes = false;
            afegirItemBreadcrumb();
        }
    }//GEN-LAST:event_jTextField_desnNrutaKeyReleased

    private void jTextField_distKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_distKeyReleased
        if (jTextField_titolRuta.getText().length() != 0 && jTextArea1.getText().length() != 0 && jTextField_dist.getText().length() != 0 && jComboBox_tempsHruta.getSelectedItem() != null && jComboBox_tempsMruta.getSelectedItem() != null && jTextField_desnPruta.getText().length() != 0 && jTextField_desnNruta.getText().length() != 0) {
            dadesCorrectes = true;
            afegirItemBreadcrumb();
        }else{
            dadesCorrectes = false;
            afegirItemBreadcrumb();
        }
    }//GEN-LAST:event_jTextField_distKeyReleased

    boolean qtat = true;
    
    public void afegirItemBreadcrumb(){
        
        if(dadesCorrectes && qtat){
            if(r==null){
                qtat = false;
                breadcrumb1.addItem("Item 1"); 
            }
             
        }

         try {
                //Llegir fitxer
                File f = new File("info_ruta.txt");
                System.out.println(f.getAbsoluteFile());
                if(f.exists()){
                    System.out.println("EL FITXER EXISTEIX");
                    Scanner myReader = new Scanner(f);

                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        html_s += data;
                    }
                    myReader.close();
                }else{
                    System.out.println("El fitxer no existeix???");
                }

            } catch (FileNotFoundException ex) {
                System.out.println("error: " + ex.getMessage());
                Path currentRelativePath = Paths.get("");
                String s = currentRelativePath.toAbsolutePath().toString();
                System.out.println("Current absolute path is: " + s);
            } finally{

            }
        
        
        if(jComboBox_tempsHruta.getSelectedIndex()!=-1){
            hores = Integer.parseInt(jComboBox_tempsHruta.getSelectedItem().toString());
        }
        
        if(jComboBox_tempsMruta.getSelectedIndex()!=-1){
            minuts = Integer.parseInt(jComboBox_tempsMruta.getSelectedItem().toString());
        }
        
        total = (hores * 60) + minuts;
        
        System.out.println("LEN: "+html_s.length());
        if(dadesCorrectes && html_s.length()>0){
            //html_s = "prova";
            System.out.println("PROCEDIM A CREAR EL OBJECTE RUTA");
            r = new Ruta(jTextField_titolRuta.getText(),jTextArea1.getText(),html_s,Double.parseDouble(jTextField_dist.getText()),total,Integer.parseInt(jTextField_desnPruta.getText()),Integer.parseInt(jTextField_desnNruta.getText()),dific,usuari_loginat);
            System.out.println("RUTA CREADA: "+r);
        }
        
    }
    
    public static Ruta retornarNovaRuta(){
        
        return r;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox_tempsHruta;
    private javax.swing.JComboBox<String> jComboBox_tempsMruta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_estrella1;
    private javax.swing.JLabel jLabel_estrella2;
    private javax.swing.JLabel jLabel_estrella3;
    private javax.swing.JLabel jLabel_estrella4;
    private javax.swing.JLabel jLabel_estrella5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField_desnNruta;
    private javax.swing.JTextField jTextField_desnPruta;
    private javax.swing.JTextField jTextField_dist;
    private javax.swing.JTextField jTextField_titolRuta;
    // End of variables declaration//GEN-END:variables
}
