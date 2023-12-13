/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
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
    ImageIcon fotoHtml = new ImageIcon("img"+File.separator+"html.png");
    ImageIcon fotoHtmlHoover = new ImageIcon("img"+File.separator+"htmlHoover.png");
    
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
    
    int contador;
    
    
    List<Punt> llistaPunts;
    
    public panellAfegir_ruta(Breadcrumb breadcrumb1, Usuari usuari_loginat, List<Punt> llistaPunts, Ruta ruta, int contador) {
        this.contador = contador;
        //System.out.println("CONTADOR: "+contador);
        //System.out.println("RUTAAAAA: "+ruta);
        
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(255,255,255)));
        
        this.r = ruta;
        initComponents();
        
        jComboBox_tempsHruta.setSize(141, 30);
        jComboBox_tempsHruta.setUI(panellCataleg.ColorArrowUI.createUI(jComboBox_tempsHruta));
        jComboBox_tempsMruta.setSize(141, 30);
        jComboBox_tempsMruta.setUI(panellCataleg.ColorArrowUI.createUI(jComboBox_tempsMruta));
        
        TextPrompt placeHolder_titol = new TextPrompt("Ascensió al Pedraforca",jTextField_titolRuta);
        TextPrompt placeHolder_desc = new TextPrompt("El Pedraforca és una muntanya emblemàtica de Catalunya que es troba a la serra del Cadí, fent de límit entre les províncies de Barcelona i Lleida.",jTextArea1);
        TextPrompt placeHolder_dist = new TextPrompt("1200",jTextField_dist);
        TextPrompt placeHolder_desnP = new TextPrompt("2506",jTextField_desnPruta);
        TextPrompt placeHolder_desnN = new TextPrompt("550",jTextField_desnNruta);
        
        //TextPrompt placeHolder_titol = new TextPrompt("títol de la ruta",jTextField_titolRuta);
        this.breadcrumb1 = breadcrumb1;
        this.usuari_loginat = usuari_loginat;
        this.llistaPunts = llistaPunts;
        
        for(int i = 1; i <= 24; i++ ){            
            jComboBox_tempsHruta.addItem(i+"");
        }
        
        for(int i = 1; i <= 59; i++ ){            
            jComboBox_tempsMruta.addItem(i+"");
        }
        
        if(contador==1){
            ruta = null;
        }
        
        
        //System.out.println("CONTADOR: "+contador);
        //System.out.println("RUTAAAAA: "+ruta);
        if(ruta == null && contador >0){
            //System.out.println("ENTROOOO");
            this.r = null;
            llistaPunts = new ArrayList();
            new CambiaPanel(jPanel1,new panellAfegir());
            try{
 
//                breadcrumb1.remove(1);
//                breadcrumb1.remove(2);
//                breadcrumb1.remove(0);

breadcrumb1 = new Breadcrumb();
                  
                //breadcrumb1.removeAll();

                  
            }catch(Exception ex){
                //System.out.println("ERROR: "+ex.getMessage());
            }

        }else{
            //System.out.println("NO ENTROOOO");
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
            //System.out.println("breadcrumb1.getComponentCount(): "+breadcrumb1.getComponentCount());
            if(breadcrumb1.getComponentCount()==2){
                breadcrumb1.addItem("Item 1"); 
                breadcrumb1.addItem("Item 2"); 
            }
            
            //System.out.println("RUTA INFO: "+r.getTitol());
            jTextField_titolRuta.setText(r.getTitol());
            jTextField_dist.setText(r.getDist()+"");
            jTextArea1.setText(r.getDescRuta());
                        
            
            int r_total = r.getTemps();
            //System.out.println("TEMPS: "+r.getTemps());
            
            int r_hores = r_total / 60;
            int r_minuts = r_total % 60;
            //System.out.println("HORES: "+r_hores);
            //System.out.println("MINUTS: "+r_minuts);
            
            jComboBox_tempsHruta.setSelectedItem(r_hores+"");
            jComboBox_tempsMruta.setSelectedItem(r_minuts+"");
                                    
            jTextField_desnPruta.setText(r.getDesnP()+"");
            jTextField_desnNruta.setText(r.getDesnN()+"");
                             
            int r_dific = r.getDific();
            //System.out.println("DIFICULTAT: "+r_dific);
            
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
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_titolRuta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel_estrella1 = new javax.swing.JLabel();
        jLabel_estrella2 = new javax.swing.JLabel();
        jLabel_estrella3 = new javax.swing.JLabel();
        jLabel_estrella4 = new javax.swing.JLabel();
        jLabel_estrella5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_dist = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_desnPruta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField_desnNruta = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_tempsHruta = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_tempsMruta = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(76, 140, 43));
        jLabel11.setText("Atenció: els canvis no es desaran si no completeu totes les dades de la ruta seguint l'ordre de les pantalles!");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel4.setText("Títol:");

        jTextField_titolRuta.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_titolRuta.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_titolRuta.setBorder(null);
        jTextField_titolRuta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_titolRutaKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel5.setText("Descripció:");

        jSeparator2.setForeground(new java.awt.Color(76, 140, 43));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(153, 153, 153));
        jTextArea1.setRows(5);
        jTextArea1.setBorder(null);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel3.setText("Dificultat:");

        jLabel_estrella1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_groga.png"))); // NOI18N
        jLabel_estrella1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_estrella1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_estrella1MouseClicked(evt);
            }
        });

        jLabel_estrella2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_estrella2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_estrella2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_estrella2MouseClicked(evt);
            }
        });

        jLabel_estrella3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_estrella3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_estrella3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_estrella3MouseClicked(evt);
            }
        });

        jLabel_estrella4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_estrella4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_estrella4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_estrella4MouseClicked(evt);
            }
        });

        jLabel_estrella5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_estrella5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_estrella5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_estrella5MouseClicked(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(76, 140, 43));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/html.png"))); // NOI18N
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(69, 69, 69)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator2)
                    .addComponent(jTextField_titolRuta)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel_estrella1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_estrella2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_estrella3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_estrella4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_estrella5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_titolRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel_estrella1)
                                .addComponent(jLabel_estrella4)
                                .addComponent(jLabel_estrella3)
                                .addComponent(jLabel_estrella2)
                                .addComponent(jLabel_estrella5)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Introduïu els paràmetres que componen la ruta");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel10.setText("Distància:");

        jTextField_dist.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_dist.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_dist.setBorder(null);
        jTextField_dist.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_distKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_distKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel7.setText("Desnivell (+) :");

        jTextField_desnPruta.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_desnPruta.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_desnPruta.setBorder(null);
        jTextField_desnPruta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_desnPrutaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_desnPrutaKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel8.setText("Desnivell (-) :");

        jTextField_desnNruta.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_desnNruta.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_desnNruta.setBorder(null);
        jTextField_desnNruta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_desnNrutaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_desnNrutaKeyTyped(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(76, 140, 43));

        jSeparator4.setForeground(new java.awt.Color(76, 140, 43));

        jSeparator5.setForeground(new java.awt.Color(76, 140, 43));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel1.setText("Hores:");

        jComboBox_tempsHruta.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jComboBox_tempsHruta.setForeground(new java.awt.Color(204, 204, 204));
        jComboBox_tempsHruta.setBorder(null);
        jComboBox_tempsHruta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tempsHrutaItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel2.setText("Minuts:");

        jComboBox_tempsMruta.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jComboBox_tempsMruta.setForeground(new java.awt.Color(204, 204, 204));
        jComboBox_tempsMruta.setBorder(null);
        jComboBox_tempsMruta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tempsMrutaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField_dist, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_desnPruta, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField_desnNruta, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                .addComponent(jSeparator5)))))
                .addGap(92, 92, 92)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_tempsHruta, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_tempsMruta, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField_dist, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_desnPruta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox_tempsHruta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox_tempsMruta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_desnNruta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(jLabel11)
                .addContainerGap(237, Short.MAX_VALUE))
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
                //System.out.println(f.getAbsoluteFile());
                if(f.exists()){
                    //System.out.println("EL FITXER EXISTEIX");
                    Scanner myReader = new Scanner(f);

                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        html_s += data;
                    }
                    myReader.close();
                }else{
                    //System.out.println("El fitxer no existeix???");
                }

            } catch (FileNotFoundException ex) {
                //System.out.println("error: " + ex.getMessage());
                Path currentRelativePath = Paths.get("");
                String s = currentRelativePath.toAbsolutePath().toString();
                //System.out.println("Current absolute path is: " + s);
            } finally{

            }
        //System.out.println("provas: "+html_s);
       
//            if(this.contador == 1){
//                html_s = "";
//            }
        
        //html_s = "prova";
        try{
            //System.out.println("DIFIC: "+dific);
            //System.out.println("PROCEDIM A CREAR EL OBJECTE RUTA");
            r = new Ruta(jTextField_titolRuta.getText(),jTextArea1.getText(),html_s,Double.parseDouble(jTextField_dist.getText()),total,Integer.parseInt(jTextField_desnPruta.getText()),Integer.parseInt(jTextField_desnNruta.getText()),dific,usuari_loginat);
            //System.out.println("RUTA CREADA: "+r);
        
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

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setIcon(fotoHtmlHoover);
        jButton1.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setIcon(fotoHtml);
        jButton1.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton1MouseExited

    private void jTextField_distKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_distKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || c==',')) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_distKeyTyped

    private void jTextField_desnPrutaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_desnPrutaKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_desnPrutaKeyTyped

    private void jTextField_desnNrutaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_desnNrutaKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_desnNrutaKeyTyped

    boolean qtat = true;
    
    public void afegirItemBreadcrumb(){
        
        if(dadesCorrectes && qtat){
            if(r==null){
                qtat = false;
                //System.out.println("COUNT COMPONENTS "+breadcrumb1.getComponentCount());
                if(breadcrumb1.getComponentCount()<4){
                    
                    breadcrumb1.addItem("Punts"); 
                    
                    
                }
                
            }
             
        }

         try {
                //Llegir fitxer
                File f = new File("info_ruta.txt");
                //System.out.println(f.getAbsoluteFile());
                if(f.exists()){
                    //System.out.println("EL FITXER EXISTEIX");
                    Scanner myReader = new Scanner(f);

                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        html_s += data;
                    }
                    myReader.close();
                }else{
                    //System.out.println("El fitxer no existeix???");
                }

            } catch (FileNotFoundException ex) {
                //System.out.println("error: " + ex.getMessage());
                Path currentRelativePath = Paths.get("");
                String s = currentRelativePath.toAbsolutePath().toString();
                //System.out.println("Current absolute path is: " + s);
            } finally{

            }
        //System.out.println("afegirItemBreadcrumb: "+html_s);
         if(this.contador == 1){
             html_s = "";
         }
        
        if(jComboBox_tempsHruta.getSelectedIndex()!=-1){
            hores = Integer.parseInt(jComboBox_tempsHruta.getSelectedItem().toString());
        }
        
        if(jComboBox_tempsMruta.getSelectedIndex()!=-1){
            minuts = Integer.parseInt(jComboBox_tempsMruta.getSelectedItem().toString());
        }
        
        total = (hores * 60) + minuts;
        
        //System.out.println("LEN: "+html_s.length());
        if(dadesCorrectes && html_s.length()>0){
            //html_s = "prova";
            //System.out.println("PROCEDIM A CREAR EL OBJECTE RUTA");
            r = new Ruta(jTextField_titolRuta.getText(),jTextArea1.getText(),html_s,Double.parseDouble(jTextField_dist.getText()),total,Integer.parseInt(jTextField_desnPruta.getText()),Integer.parseInt(jTextField_desnNruta.getText()),dific,usuari_loginat);
            //System.out.println("RUTA CREADA: "+r);
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_estrella1;
    private javax.swing.JLabel jLabel_estrella2;
    private javax.swing.JLabel jLabel_estrella3;
    private javax.swing.JLabel jLabel_estrella4;
    private javax.swing.JLabel jLabel_estrella5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField_desnNruta;
    private javax.swing.JTextField jTextField_desnPruta;
    private javax.swing.JTextField jTextField_dist;
    private javax.swing.JTextField jTextField_titolRuta;
    // End of variables declaration//GEN-END:variables
}
