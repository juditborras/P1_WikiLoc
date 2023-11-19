/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.shef.Demo;
import org.milaifontanals.wikiloc.jdbc.GestorBDWikilocJdbc;
import org.milaifontanals.wikiloc.model.Ruta;
import org.milaifontanals.wikiloc.model.Usuari;
import org.milaifontanals.wikiloc.persistencia.GestorBDWikilocException;

/**
 *
 * @author JUDIT
 */
public class panellCompartides extends javax.swing.JPanel {

    /**
     * Creates new form panellCompartides
     */
    
    private GestorBDWikilocJdbc gestorBDWikilocJdbc;
    Usuari usuari_loginat;
    Demo demo;
    ImageIcon estrellaBlanca = new ImageIcon("img"+File.separator+"estrella_blanca.png");
    ImageIcon estrellaGroga = new ImageIcon("img"+File.separator+"estrella_groga.png");
    boolean editar_estrelles = false;
    
    int id, hours, minutes, antic, qtat_estrelles, qtat_estrelles_actual;
    boolean hours_canviada = false, minutes_canviada = false, titol_canviada = false, dist_canviada = false, desc_canviada = false, desnP_canviada = false, desnN_canviada = false, estrella_canviada = false;
    boolean onEdit_click = false;
    int qc1,qc2;
    
    String titol, desc, dist, desnP, desnN;
    String textHtmlEditat;
    
    String text_html;
    
    int row_sel;
    List<Ruta> llistaRutesCreades;
    
    DefaultTableModel tableModel;
    
    public panellCompartides(){
        
    }
    
    public panellCompartides(JPanel jPanel_menu, JPanel jPanel_principal, Usuari usuari_loginat) throws GestorBDWikilocException {
        
        UIManager.put("TabbedPane.selected", new Color(255,174,0));
        UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(new Color(255,255,255)));
        UIManager.put("TabbedPane.darkShadow", new ColorUIResource(new Color(255,255,255)));
        
        
        initComponents();    
        
        jTabbedPane2.setBackgroundAt(0, new Color(76,140,43));
        jTabbedPane2.setBackgroundAt(1, new Color(76,140,43));
        
        JLabel lab0 = new JLabel("Detalls ruta");
        lab0.setPreferredSize(new Dimension(200, 30));
        jTabbedPane2.setTabComponentAt(0, lab0);  // tab index, jLabel
        JLabel lab1 = new JLabel("Detalls punts de ruta");
        lab1.setPreferredSize(new Dimension(200, 30));
        jTabbedPane2.setTabComponentAt(1, lab1); 
        
        this.usuari_loginat = usuari_loginat;
        
        System.out.println(usuari_loginat);
        
        jPanel_compartidesCanviant.setVisible(false);
        jButton2.setVisible(false);
        
       
        llistaRutesCreades = new ArrayList();
        
        try {
            
            gestorBDWikilocJdbc = new GestorBDWikilocJdbc();
            
            llistaRutesCreades = gestorBDWikilocJdbc.obtenirLlistaRutaUsuari(usuari_loginat.getLogin());
            
            tableModel = (DefaultTableModel) jTable1.getModel();
            Object rowData[] = new Object[5];
            
            for (Ruta r : llistaRutesCreades) {
              
                rowData[0] = r.getTitol();
                rowData[1] = r.getDescRuta();
                rowData[2] = r.getDist();
                rowData[3] = r.getTemps();
                rowData[4] = r.getDific();
                        
                tableModel.addRow(rowData);
            }
            
            for (int i = 1; i <= 24; i++) {
                jComboBox1.addItem(i + "");
            }


            for (int i = 1; i <= 60; i++) {
                jComboBox2.addItem(i + "");
            }
            
            
        } catch (GestorBDWikilocException ex) {
            Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        TableActionEvent event = new TableActionEvent() {
            List<Ruta> llistaRutesCreades = gestorBDWikilocJdbc.obtenirLlistaRutaUsuari(usuari_loginat.getLogin());
  
            @Override
            public void onEdit(int row) {
                
                onEdit_click = true;
                
                
                row_sel = row;
                System.out.println("Edit row : " + row);
                System.out.println(llistaRutesCreades.get(row)); 
                
                jPanel_menu.setVisible(false);
                
                jPanel_compartidesCanviant.setVisible(true);
                
                obtenirDadesRuta(llistaRutesCreades, row);
                
                jButton2.setVisible(true);
                jButton2.setEnabled(false);
                
                editar_estrelles = true;
                
                //new CambiaPanel(jPanel_compartidesCanviant,new panellCompartidesDetallsRuta(usuari_loginat,llistaRutesCreades.get(row)));
                
                
                jTextField1.setEditable(true); 
                jTextField2.setEditable(true); 
                jTextArea1.setEditable(true);
                jButton1.setEnabled(true);

                                     
                jComboBox1.setEnabled(true);
                jComboBox2.setEnabled(true);
                
                jTextField3.setEditable(true);
                jTextField4.setEditable(true);
                

                
            }

            @Override
            public void onDelete(int row) {
                
                jPanel_menu.setVisible(false);
                
                
                
                System.out.println("hola esborrem ruta" + id);
                
                try {
                    
                    id = llistaRutesCreades.get(row).getId();
                    
                    int qtat_comentaris = gestorBDWikilocJdbc.qtatComentarisRuta(id);
                    System.out.println("qtat_comentaris. "+qtat_comentaris);
                    if(qtat_comentaris == 0){
                        
                        if (jTable1.isEditing()) {
                            jTable1.getCellEditor().stopCellEditing();
                        }
                        
                        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                        model.removeRow(row);
                                                
                        
                        jPanel_compartidesCanviant.setVisible(false);
                        
                    }else{
                        
                        JOptionPane.showConfirmDialog(null, "No es pot esborrar una ruta si aquesta té comentaris",
                                "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                        
                        
                        if(onEdit_click){
                            jPanel_compartidesCanviant.setVisible(true);
                        }else{
                            jPanel_compartidesCanviant.setVisible(false);
                        }
                        
                        onEdit_click = false;
                    }
                    
                                       
                } catch (GestorBDWikilocException ex) {
                    Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }

            @Override
            public void onView(int row) {
                System.out.println("View row : " + row);
                
                jPanel_menu.setVisible(false);
                
                jPanel_compartidesCanviant.setVisible(true);
                
                obtenirDadesRuta(llistaRutesCreades, row);
                
                jButton2.setVisible(false);
                
                editar_estrelles = false;
                

                jButton1.setEnabled(false);
                
                jTextField1.setEditable(false);
                jTextField2.setEditable(false); 
                jTextArea1.setEditable(false);
                
                
                jComboBox1.setEnabled(false);
                jComboBox2.setEnabled(false);
                
                jTextField3.setEditable(false);
                jTextField4.setEditable(false);
                
 
            }
            
        
        };
        jTable1.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        jTable1.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
        jTable1.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
        });
        
    }
    
    
    public void obtenirDadesRuta(List<Ruta> llistaRutesCreades, int row){
        
        id = llistaRutesCreades.get(row).getId();
        
        jTextField1.setText(llistaRutesCreades.get(row).getTitol());
        titol = llistaRutesCreades.get(row).getTitol();
        
        jTextArea1.setText(llistaRutesCreades.get(row).getDescRuta());
        desc = llistaRutesCreades.get(row).getDescRuta();
        
        text_html = llistaRutesCreades.get(row).getTextRuta();

        
        jTextField2.setText(llistaRutesCreades.get(row).getDist()+"");
        dist = llistaRutesCreades.get(row).getDist()+"";
        
        int temps_total = llistaRutesCreades.get(row).getTemps();
        
       

        hours = temps_total / 60;
        minutes = temps_total % 60;
        
        

        System.out.println("temps hores:" + hours + " minuts:" + minutes);

        jComboBox1.getModel().setSelectedItem(hours);
        jComboBox2.getModel().setSelectedItem(minutes);
        
        jTextField3.setText(llistaRutesCreades.get(row).getDesnP() + "");
        desnP = llistaRutesCreades.get(row).getDesnP()+"";
        
        jTextField4.setText(llistaRutesCreades.get(row).getDesnN() + "");
        desnN = llistaRutesCreades.get(row).getDesnN()+"";
        
        qtat_estrelles = llistaRutesCreades.get(row).getDific();

        if (qtat_estrelles == 1) {
            jLabel3.setIcon(estrellaGroga);
            jLabel4.setIcon(estrellaBlanca);
            jLabel5.setIcon(estrellaBlanca);
            jLabel6.setIcon(estrellaBlanca);
            jLabel7.setIcon(estrellaBlanca);
        } else if (qtat_estrelles == 2) {
            jLabel3.setIcon(estrellaGroga);
            jLabel4.setIcon(estrellaGroga);
            jLabel5.setIcon(estrellaBlanca);
            jLabel6.setIcon(estrellaBlanca);
            jLabel7.setIcon(estrellaBlanca);
        } else if (qtat_estrelles == 3) {
            jLabel3.setIcon(estrellaGroga);
            jLabel4.setIcon(estrellaGroga);
            jLabel5.setIcon(estrellaGroga);
            jLabel6.setIcon(estrellaBlanca);
            jLabel7.setIcon(estrellaBlanca);
        } else if (qtat_estrelles == 4) {
            jLabel3.setIcon(estrellaGroga);
            jLabel4.setIcon(estrellaGroga);
            jLabel5.setIcon(estrellaGroga);
            jLabel6.setIcon(estrellaGroga);
            jLabel7.setIcon(estrellaBlanca);
        } else if (qtat_estrelles == 5) {
            jLabel3.setIcon(estrellaGroga);
            jLabel4.setIcon(estrellaGroga);
            jLabel5.setIcon(estrellaGroga);
            jLabel6.setIcon(estrellaGroga);
            jLabel7.setIcon(estrellaGroga);
        }
    }
    
    public void canviarCursorEstrelles(){
        
        if(editar_estrelles){
            jLabel3.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jLabel4.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jLabel5.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jLabel6.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jLabel7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }else{
            jLabel3.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            jLabel4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            jLabel5.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            jLabel6.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            jLabel7.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel_compartidesCanviant = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "experimento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(40);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        jPanel_compartidesCanviant.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_compartidesCanviant.setOpaque(false);

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setOpaque(true);
        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jTextField1.setText("jTextField1");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton1.setText("html");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.setText("jTextField2");
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jTextField3.setText("jTextField3");
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jTextField4.setText("jTextField4");
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_groga.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea1);

        jButton2.setText("desar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)))
                .addGap(75, 75, 75)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jButton2))
                .addGap(298, 298, 298))
        );

        jTabbedPane2.addTab("tab1", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1391, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("tab2", jPanel7);

        javax.swing.GroupLayout jPanel_compartidesCanviantLayout = new javax.swing.GroupLayout(jPanel_compartidesCanviant);
        jPanel_compartidesCanviant.setLayout(jPanel_compartidesCanviantLayout);
        jPanel_compartidesCanviantLayout.setHorizontalGroup(
            jPanel_compartidesCanviantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_compartidesCanviantLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel_compartidesCanviantLayout.setVerticalGroup(
            jPanel_compartidesCanviantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_compartidesCanviantLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_compartidesCanviant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_compartidesCanviant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        

        jPanel_compartidesCanviant.setVisible(false);
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try {
            llistaRutesCreades = gestorBDWikilocJdbc.obtenirLlistaRutaUsuari(usuari_loginat.getLogin());
            text_html = llistaRutesCreades.get(row_sel).getTextRuta();
            
            demo = new Demo(text_html, id);
        } catch (GestorBDWikilocException ex) {
            
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        
        if(editar_estrelles){
            jLabel3.setIcon(estrellaGroga);
            jLabel4.setIcon(estrellaBlanca);
            jLabel5.setIcon(estrellaBlanca);
            jLabel6.setIcon(estrellaBlanca);
            jLabel7.setIcon(estrellaBlanca);
            qtat_estrelles_actual = 1;
            
            if(qtat_estrelles_actual == qtat_estrelles){
                estrella_canviada = false;
            }else{
                estrella_canviada = true;
            }
            
            horesMinutsModificats();
            
        }else{
            evt.consume();
        }
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        
        if(editar_estrelles){
            jLabel3.setIcon(estrellaGroga);
            jLabel4.setIcon(estrellaGroga);
            jLabel5.setIcon(estrellaBlanca);
            jLabel6.setIcon(estrellaBlanca);
            jLabel7.setIcon(estrellaBlanca);
            qtat_estrelles_actual = 2;
            
            if(qtat_estrelles_actual == qtat_estrelles){
                estrella_canviada = false;
            }else{
                estrella_canviada = true;
            }
            
            horesMinutsModificats();
            
        }else{
            evt.consume();
        }
        
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        
        if(editar_estrelles){
            jLabel3.setIcon(estrellaGroga);
            jLabel4.setIcon(estrellaGroga);
            jLabel5.setIcon(estrellaGroga);
            jLabel6.setIcon(estrellaBlanca);
            jLabel7.setIcon(estrellaBlanca);
            qtat_estrelles_actual = 3;
            
            if(qtat_estrelles_actual == qtat_estrelles){
                estrella_canviada = false;
            }else{
                estrella_canviada = true;
            }
            
            horesMinutsModificats();
            
        }else{
            evt.consume();
        }
        
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        
        if(editar_estrelles){
            
            jLabel3.setIcon(estrellaGroga);
            jLabel4.setIcon(estrellaGroga);
            jLabel5.setIcon(estrellaGroga);
            jLabel6.setIcon(estrellaGroga);
            jLabel7.setIcon(estrellaBlanca);
            qtat_estrelles_actual = 4;
            
            if(qtat_estrelles_actual == qtat_estrelles){
                estrella_canviada = false;
            }else{
                estrella_canviada = true;
            }
            
            horesMinutsModificats();
            
        }else{
            evt.consume();
        }
        
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
               
        if(editar_estrelles){
            jLabel3.setIcon(estrellaGroga);
            jLabel4.setIcon(estrellaGroga);
            jLabel5.setIcon(estrellaGroga);
            jLabel6.setIcon(estrellaGroga);
            jLabel7.setIcon(estrellaGroga);
            qtat_estrelles_actual = 5;
            
            if(qtat_estrelles_actual == qtat_estrelles){
                estrella_canviada = false;
            }else{
                estrella_canviada = true;
            }
            
            horesMinutsModificats();
            
        }else{
            evt.consume();
        }
        
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        
        
        try {
            gestorBDWikilocJdbc = new GestorBDWikilocJdbc();
                                
            int total = (hours * 60) + minutes;
            
            System.out.println(textHtmlEditat);
            
            Ruta ruta_editada;
            
            if(textHtmlEditat!=null){
                ruta_editada = new Ruta(id,jTextField1.getText(),jTextArea1.getText(),textHtmlEditat,Double.parseDouble(jTextField2.getText()),total,Integer.parseInt(jTextField3.getText()),Integer.parseInt(jTextField4.getText()),qtat_estrelles_actual,usuari_loginat);
            }else{
                ruta_editada = new Ruta(id,jTextField1.getText(),jTextArea1.getText(),text_html,Double.parseDouble(jTextField2.getText()),total,Integer.parseInt(jTextField3.getText()),Integer.parseInt(jTextField4.getText()),qtat_estrelles,usuari_loginat);
                
            }
            
            
            
            boolean rutaEditada = gestorBDWikilocJdbc.editarRuta(ruta_editada);
            
            if(!rutaEditada){
                
                JOptionPane.showMessageDialog(this,
                    "Error: No s'ha pogut editar la ruta" ,
                    "Error - Editar ruta", JOptionPane.ERROR_MESSAGE);
            }else{
                
                int resposta =JOptionPane.showConfirmDialog(null, "Estàs segur de desar els canvis?",
                "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
                
                if(resposta == 0){
                    gestorBDWikilocJdbc.confirmarCanvis();

                    JOptionPane.showConfirmDialog(null, "Els canvis s'han desat correctament",
                    "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
                    
                    gestorBDWikilocJdbc = new GestorBDWikilocJdbc();

                    llistaRutesCreades = gestorBDWikilocJdbc.obtenirLlistaRutaUsuari(usuari_loginat.getLogin());

                    
                    for(int i=0; i< llistaRutesCreades.size(); i++){
                        tableModel.removeRow(i);
                    }
                    
                    tableModel = (DefaultTableModel) jTable1.getModel();
                    
                    
                    
                    Object rowData[] = new Object[5];

                    for (Ruta r : llistaRutesCreades) {

                        rowData[0] = r.getTitol();
                        rowData[1] = r.getDescRuta();
                        rowData[2] = r.getDist();
                        rowData[3] = r.getTemps();
                        rowData[4] = r.getDific();

                        tableModel.addRow(rowData);
                    }
                    
                }
                
                jButton2.setEnabled(false);
                
            }
            
            
            
        } catch (GestorBDWikilocException ex) {
            System.out.println("ERROR: "+ex.getMessage());
        }
        
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        canviarCursorEstrelles();
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        canviarCursorEstrelles();
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        canviarCursorEstrelles();
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
        canviarCursorEstrelles();
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        canviarCursorEstrelles();
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
       
        try{
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                
                qc1++;
                
                if(qc1>1){
                    // Item was just selected

                    JComboBox cb = (JComboBox) evt.getSource();

                    String n_nou = (String)cb.getSelectedItem();
                    Integer nou = Integer.parseInt(n_nou);
                    System.out.println("C1 ANTIC: "+hours+" NOU: "+nou);
                    if(hours!=nou){
                        hours_canviada = true;
                        horesMinutsModificats();
                        
                    }else{
                        hours_canviada = false;
                        horesMinutsModificats();
                        
                    }
                }
                
            }
        }catch(Exception ex){
            
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        
        try{
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                // Item was just selected

                qc2++;
                
                if(qc2>1){
                    JComboBox cb = (JComboBox) evt.getSource();

                    String n_nou = (String)cb.getSelectedItem();
                    Integer nou = Integer.parseInt(n_nou);
                    System.out.println("C2 ANTIC: "+minutes+" NOU: "+nou);
                    if(minutes!=nou){
                        minutes_canviada = true;
                        horesMinutsModificats();
                        
                    }else{
                        minutes_canviada = false;
                        horesMinutsModificats();
                    }
                }
                
            }
        }catch(Exception ex){
            
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        
        if (titol.equals(jTextField1.getText())) {
            titol_canviada = false;
            horesMinutsModificats();
        } else {
            titol_canviada = true;
            horesMinutsModificats();
        }

    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextArea1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyReleased
        
        if (desc.equals(jTextArea1.getText())) {
            desc_canviada = false;
            horesMinutsModificats();
        } else {
            desc_canviada = true;
            horesMinutsModificats();
        }
    }//GEN-LAST:event_jTextArea1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        
        
        if (desnP.equals(jTextField3.getText())) {
            desnP_canviada = false;
            horesMinutsModificats();
        } else {
            desnP_canviada = true;
            horesMinutsModificats();
        }
        
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        
        if (desnN.equals(jTextField4.getText())) {
            desnN_canviada = false;
            horesMinutsModificats();
        } else {
            desnN_canviada = true;
            horesMinutsModificats();
        }
        
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        
        if (dist.equals(jTextField2.getText())) {
            dist_canviada = false;
            horesMinutsModificats();
        } else {
            dist_canviada = true;
            horesMinutsModificats();
        }
        
        
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
        

        
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    public void horesMinutsModificats(){
 
        System.out.println("hora"+hours_canviada+" minuts"+ minutes_canviada);
        if(hours_canviada || minutes_canviada || titol_canviada || dist_canviada || desc_canviada || desnP_canviada || desnN_canviada || estrella_canviada){
            jButton2.setEnabled(true);
            System.out.println("A");
        }else{
            jButton2.setEnabled(false);
            System.out.println("B");
        }
    }
    
    


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel_compartidesCanviant;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
