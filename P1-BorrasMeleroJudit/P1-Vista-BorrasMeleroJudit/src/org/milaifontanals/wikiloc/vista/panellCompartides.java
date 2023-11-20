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
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
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
import org.milaifontanals.wikiloc.model.Punt;
import org.milaifontanals.wikiloc.model.Ruta;
import org.milaifontanals.wikiloc.model.Tipus;
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
    List<Punt> llistaPuntsRuta;
    List<Tipus> llistaTipusPunts;
    
    DefaultTableModel tableModel;
    DefaultListModel dlm, dlm_tipus;
    
    Punt punt_seleccionat;
    Ruta ruta_seleccionada;
    
    public panellCompartides(){
        
    }
    
    public panellCompartides(JPanel jPanel_menu, JPanel jPanel_principal, Usuari usuari_loginat) throws GestorBDWikilocException {
        
        UIManager.put("TabbedPane.selected", new Color(255,174,0));
        UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(new Color(255,255,255)));
        UIManager.put("TabbedPane.darkShadow", new ColorUIResource(new Color(255,255,255)));
        
        
        
        initComponents();    
        
        jTabbedPane2.setBackgroundAt(0, new Color(76,140,43));
        jTabbedPane2.setBackgroundAt(1, new Color(76,140,43));
        jTabbedPane2.setBackgroundAt(2, new Color(76,140,43));
        
        JLabel lab0 = new JLabel("Detalls ruta");
        lab0.setPreferredSize(new Dimension(200, 30));
        jTabbedPane2.setTabComponentAt(0, lab0);  // tab index, jLabel
        JLabel lab1 = new JLabel("Detalls punts de ruta");
        lab1.setPreferredSize(new Dimension(200, 30));
        jTabbedPane2.setTabComponentAt(1, lab1); 
        JLabel lab2 = new JLabel("Estadístiques de la ruta");
        lab2.setPreferredSize(new Dimension(200, 30));
        jTabbedPane2.setTabComponentAt(2, lab2);
        
        this.usuari_loginat = usuari_loginat;
        
        System.out.println(usuari_loginat);
        
        jPanel_compartidesCanviant.setVisible(false);
        jButton_desarCanvisRuta.setVisible(false);
        jButton_incorporarNouElement.setVisible(false);
        
       
        llistaRutesCreades = new ArrayList();
        llistaPuntsRuta = new ArrayList();
        llistaTipusPunts = new ArrayList();
        
        try {
            
            gestorBDWikilocJdbc = new GestorBDWikilocJdbc();
            
            //public Punt(Integer num, String nom, String descPunt, Integer lat, Integer lon, Integer alt, Ruta idRuta, Tipus idTipus) {
//            gestorBDWikilocJdbc.editarPuntRuta(new Punt(1,"Can Titó","Poliesportiu del poble per a la iniciació de diferents esports",129,400,279,new Ruta(3),new Tipus(15)), 3, "img"+File.separator+"icons8-inicio-48.png");
//            gestorBDWikilocJdbc.confirmarCanvis();
            
            llistaRutesCreades = gestorBDWikilocJdbc.obtenirLlistaRutaUsuari(usuari_loginat.getLogin());
            
            tableModel = (DefaultTableModel) jTable_rutesCreadesUsuari.getModel();
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
                jComboBox_tempsH.addItem(i + "");
            }


            for (int i = 1; i <= 60; i++) {
                jComboBox_tempsM.addItem(i + "");
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
                ruta_seleccionada = llistaRutesCreades.get(row);
                
                jPanel_menu.setVisible(false);
                
                jPanel_compartidesCanviant.setVisible(true);
                
                obtenirDadesRuta(llistaRutesCreades, row);
                
                jButton_desarCanvisRuta.setVisible(true);
                jButton_desarCanvisRuta.setEnabled(false);
                
                editar_estrelles = true;
                
                //new CambiaPanel(jPanel_compartidesCanviant,new panellCompartidesDetallsRuta(usuari_loginat,llistaRutesCreades.get(row)));
                
                
                jTextField_titol.setEditable(true); 
                jTextField_dist.setEditable(true); 
                jTextArea_descRuta.setEditable(true);
                jButton_textRuta.setEnabled(true);

                                     
                jComboBox_tempsH.setEnabled(true);
                jComboBox_tempsM.setEnabled(true);
                
                jTextField_desnP.setEditable(true);
                jTextField_desnN.setEditable(true);
                

                
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
                        
                        if (jTable_rutesCreadesUsuari.isEditing()) {
                            jTable_rutesCreadesUsuari.getCellEditor().stopCellEditing();
                        }
                        
                        DefaultTableModel model = (DefaultTableModel) jTable_rutesCreadesUsuari.getModel();
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
                
                jButton_desarCanvisRuta.setVisible(false);
                
                editar_estrelles = false;
                

                jButton_textRuta.setEnabled(false);
                
                jTextField_titol.setEditable(false);
                jTextField_dist.setEditable(false); 
                jTextArea_descRuta.setEditable(false);
                
                
                jComboBox_tempsH.setEnabled(false);
                jComboBox_tempsM.setEnabled(false);
                
                jTextField_desnP.setEditable(false);
                jTextField_desnN.setEditable(false);
                
 
            }
            
        
        };
        jTable_rutesCreadesUsuari.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        jTable_rutesCreadesUsuari.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
        jTable_rutesCreadesUsuari.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
        });
        
    }
    
    
    public void obtenirDadesRuta(List<Ruta> llistaRutesCreades, int row){
        
        id = llistaRutesCreades.get(row).getId();
        
        jTextField_titol.setText(llistaRutesCreades.get(row).getTitol());
        titol = llistaRutesCreades.get(row).getTitol();
        
        jTextArea_descRuta.setText(llistaRutesCreades.get(row).getDescRuta());
        desc = llistaRutesCreades.get(row).getDescRuta();
        
        text_html = llistaRutesCreades.get(row).getTextRuta();

        
        jTextField_dist.setText(llistaRutesCreades.get(row).getDist()+"");
        dist = llistaRutesCreades.get(row).getDist()+"";
        
        int temps_total = llistaRutesCreades.get(row).getTemps();
        
       

        hours = temps_total / 60;
        minutes = temps_total % 60;
        
        

        System.out.println("temps hores:" + hours + " minuts:" + minutes);

        jComboBox_tempsH.getModel().setSelectedItem(hours);
        jComboBox_tempsM.getModel().setSelectedItem(minutes);
        
        jTextField_desnP.setText(llistaRutesCreades.get(row).getDesnP() + "");
        desnP = llistaRutesCreades.get(row).getDesnP()+"";
        
        jTextField_desnN.setText(llistaRutesCreades.get(row).getDesnN() + "");
        desnN = llistaRutesCreades.get(row).getDesnN()+"";
        
        qtat_estrelles = llistaRutesCreades.get(row).getDific();

        if (qtat_estrelles == 1) {
            jLabel_dificEstrella1.setIcon(estrellaGroga);
            jLabel_dificEstrella2.setIcon(estrellaBlanca);
            jLabel_dificEstrella3.setIcon(estrellaBlanca);
            jLabel_dificEstrella4.setIcon(estrellaBlanca);
            jLabel_dificEstrella5.setIcon(estrellaBlanca);
        } else if (qtat_estrelles == 2) {
            jLabel_dificEstrella1.setIcon(estrellaGroga);
            jLabel_dificEstrella2.setIcon(estrellaGroga);
            jLabel_dificEstrella3.setIcon(estrellaBlanca);
            jLabel_dificEstrella4.setIcon(estrellaBlanca);
            jLabel_dificEstrella5.setIcon(estrellaBlanca);
        } else if (qtat_estrelles == 3) {
            jLabel_dificEstrella1.setIcon(estrellaGroga);
            jLabel_dificEstrella2.setIcon(estrellaGroga);
            jLabel_dificEstrella3.setIcon(estrellaGroga);
            jLabel_dificEstrella4.setIcon(estrellaBlanca);
            jLabel_dificEstrella5.setIcon(estrellaBlanca);
        } else if (qtat_estrelles == 4) {
            jLabel_dificEstrella1.setIcon(estrellaGroga);
            jLabel_dificEstrella2.setIcon(estrellaGroga);
            jLabel_dificEstrella3.setIcon(estrellaGroga);
            jLabel_dificEstrella4.setIcon(estrellaGroga);
            jLabel_dificEstrella5.setIcon(estrellaBlanca);
        } else if (qtat_estrelles == 5) {
            jLabel_dificEstrella1.setIcon(estrellaGroga);
            jLabel_dificEstrella2.setIcon(estrellaGroga);
            jLabel_dificEstrella3.setIcon(estrellaGroga);
            jLabel_dificEstrella4.setIcon(estrellaGroga);
            jLabel_dificEstrella5.setIcon(estrellaGroga);
        }
        
        
        
        try {
            llistaPuntsRuta = gestorBDWikilocJdbc.obtenirLlistaPuntsRuta(id);

            dlm = new DefaultListModel();
            
            for (Punt p : llistaPuntsRuta) {

                dlm.addElement(p.getNum().toString() + " - " + p.getNom().toString());
            }
            jList_puntsRuta.setModel(dlm);
            jList_puntsRuta = new JList(dlm);
            System.out.println(jList_puntsRuta.getModel().toString());
            
        } catch (GestorBDWikilocException ex) {
            Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            llistaTipusPunts = gestorBDWikilocJdbc.obtenirLlistaTipus();
         
            
            System.out.println("LEN: "+llistaTipusPunts.size());
            Tipus[] tip = new Tipus[llistaTipusPunts.size()];
            tip = llistaTipusPunts.toArray(tip);
            
            
            //jComboBox_tipusPunt = new JComboBox(tip);
            System.out.println("ITEM COUNT: "+jComboBox_tipusPunt.getItemCount());
            //jComboBox_tipusPunt.setSelectedIndex(-1);
            
            jComboBox_tipusPunt.setModel(new DefaultComboBoxModel<>(llistaTipusPunts.toArray(new Tipus[0])));
            jComboBox_tipusPunt.setSelectedIndex(-1);
            
            
            for(int i=0; i<llistaTipusPunts.size(); i++){
                System.out.println(tip[i]);
            }
          
        } catch (GestorBDWikilocException ex) {
            Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        
    }
    
    public void canviarCursorEstrelles(){
        
        if(editar_estrelles){
            jLabel_dificEstrella1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jLabel_dificEstrella2.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jLabel_dificEstrella3.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jLabel_dificEstrella4.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jLabel_dificEstrella5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }else{
            jLabel_dificEstrella1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            jLabel_dificEstrella2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            jLabel_dificEstrella3.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            jLabel_dificEstrella4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            jLabel_dificEstrella5.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
        jTable_rutesCreadesUsuari = new javax.swing.JTable();
        jPanel_compartidesCanviant = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jTextField_titol = new javax.swing.JTextField();
        jButton_textRuta = new javax.swing.JButton();
        jTextField_dist = new javax.swing.JTextField();
        jComboBox_tempsH = new javax.swing.JComboBox<>();
        jComboBox_tempsM = new javax.swing.JComboBox<>();
        jTextField_desnP = new javax.swing.JTextField();
        jTextField_desnN = new javax.swing.JTextField();
        jLabel_dificEstrella1 = new javax.swing.JLabel();
        jLabel_dificEstrella2 = new javax.swing.JLabel();
        jLabel_dificEstrella3 = new javax.swing.JLabel();
        jLabel_dificEstrella4 = new javax.swing.JLabel();
        jLabel_dificEstrella5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_descRuta = new javax.swing.JTextArea();
        jButton_desarCanvisRuta = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList_puntsRuta = new javax.swing.JList<>();
        jTextField_nomPunt = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea_descPunt = new javax.swing.JTextArea();
        jTextField_numPunt = new javax.swing.JTextField();
        jLabel_fotoPunt = new javax.swing.JLabel();
        jTextField_latPunt = new javax.swing.JTextField();
        jTextField_lonPunt = new javax.swing.JTextField();
        jTextField_altPunt = new javax.swing.JTextField();
        jButton_desarCanvisPunts = new javax.swing.JButton();
        jButton_netejar = new javax.swing.JButton();
        jButton_editar = new javax.swing.JButton();
        jButton_afegir = new javax.swing.JButton();
        jButton_eliminar = new javax.swing.JButton();
        jButton_pujar = new javax.swing.JButton();
        jButton_baixar = new javax.swing.JButton();
        jButton_incorporarNouElement = new javax.swing.JButton();
        jComboBox_tipusPunt = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable_rutesCreadesUsuari.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_rutesCreadesUsuari.setRowHeight(40);
        jTable_rutesCreadesUsuari.getTableHeader().setReorderingAllowed(false);
        jTable_rutesCreadesUsuari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_rutesCreadesUsuariMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_rutesCreadesUsuari);
        jTable_rutesCreadesUsuari.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

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

        jTextField_titol.setText("jTextField1");
        jTextField_titol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_titolKeyReleased(evt);
            }
        });

        jButton_textRuta.setText("html");
        jButton_textRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_textRutaMouseClicked(evt);
            }
        });
        jButton_textRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_textRutaActionPerformed(evt);
            }
        });

        jTextField_dist.setText("jTextField2");
        jTextField_dist.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_distKeyReleased(evt);
            }
        });

        jComboBox_tempsH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tempsHItemStateChanged(evt);
            }
        });

        jComboBox_tempsM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tempsMItemStateChanged(evt);
            }
        });

        jTextField_desnP.setText("jTextField3");
        jTextField_desnP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_desnPKeyReleased(evt);
            }
        });

        jTextField_desnN.setText("jTextField4");
        jTextField_desnN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_desnNKeyReleased(evt);
            }
        });

        jLabel_dificEstrella1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_groga.png"))); // NOI18N
        jLabel_dificEstrella1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella1MouseEntered(evt);
            }
        });

        jLabel_dificEstrella2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_dificEstrella2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella2MouseEntered(evt);
            }
        });

        jLabel_dificEstrella3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_dificEstrella3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella3MouseEntered(evt);
            }
        });

        jLabel_dificEstrella4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_dificEstrella4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella4MouseEntered(evt);
            }
        });

        jLabel_dificEstrella5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_dificEstrella5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella5MouseEntered(evt);
            }
        });

        jTextArea_descRuta.setColumns(20);
        jTextArea_descRuta.setRows(5);
        jTextArea_descRuta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea_descRutaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea_descRuta);

        jButton_desarCanvisRuta.setText("desar");
        jButton_desarCanvisRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_desarCanvisRutaMouseClicked(evt);
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
                            .addComponent(jTextField_titol, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_dist, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jTextField_desnP, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)
                            .addComponent(jTextField_desnN, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jComboBox_tempsH, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(jComboBox_tempsM, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton_textRuta))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel_dificEstrella1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_dificEstrella2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_dificEstrella3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_dificEstrella4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_dificEstrella5)))
                .addGap(75, 75, 75)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_desarCanvisRuta)
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_titol, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_dist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton_textRuta)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_tempsH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_tempsM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_desnP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_desnN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_dificEstrella1)
                    .addComponent(jLabel_dificEstrella2)
                    .addComponent(jLabel_dificEstrella3)
                    .addComponent(jLabel_dificEstrella4)
                    .addComponent(jLabel_dificEstrella5)
                    .addComponent(jButton_desarCanvisRuta))
                .addGap(298, 298, 298))
        );

        jTabbedPane2.addTab("tab1", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jList_puntsRuta.setDragEnabled(true);
        jList_puntsRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList_puntsRutaMouseClicked(evt);
            }
        });
        jList_puntsRuta.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList_puntsRutaValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jList_puntsRuta);

        jTextField_nomPunt.setText("jTextField1");
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
        jScrollPane4.setViewportView(jTextArea_descPunt);

        jTextField_numPunt.setText("jTextField2");
        jTextField_numPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_numPuntKeyReleased(evt);
            }
        });

        jLabel_fotoPunt.setText("fotooooooooo");

        jTextField_latPunt.setText("jTextField3");
        jTextField_latPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_latPuntKeyReleased(evt);
            }
        });

        jTextField_lonPunt.setText("jTextField4");
        jTextField_lonPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_lonPuntKeyReleased(evt);
            }
        });

        jTextField_altPunt.setText("jTextField5");
        jTextField_altPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_altPuntKeyReleased(evt);
            }
        });

        jButton_desarCanvisPunts.setText("desar");

        jButton_netejar.setText("netejar llista");

        jButton_editar.setText("editar element llista");

        jButton_afegir.setText("afegir element llista");
        jButton_afegir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_afegirActionPerformed(evt);
            }
        });

        jButton_eliminar.setText("eliminar element");

        jButton_pujar.setText("pujar elem");

        jButton_baixar.setText("baixar elem");

        jButton_incorporarNouElement.setText("guardar elem a llista");
        jButton_incorporarNouElement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_incorporarNouElementActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_pujar)
                    .addComponent(jButton_baixar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_numPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_nomPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_latPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField_altPunt, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_lonPunt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(115, 115, 115)
                                .addComponent(jButton_desarCanvisPunts))
                            .addComponent(jComboBox_tipusPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(65, 65, 65)
                .addComponent(jLabel_fotoPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jButton_editar)
                        .addGap(27, 27, 27)
                        .addComponent(jButton_netejar))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jButton_eliminar)
                        .addGap(42, 42, 42)
                        .addComponent(jButton_afegir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_incorporarNouElement)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jButton_pujar)
                        .addGap(35, 35, 35)
                        .addComponent(jButton_baixar))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton_desarCanvisPunts)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jTextField_numPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(jTextField_nomPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel_fotoPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(70, 70, 70)
                            .addComponent(jTextField_latPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField_lonPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField_altPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_afegir)
                    .addComponent(jButton_eliminar)
                    .addComponent(jButton_incorporarNouElement))
                .addGap(4, 4, 4)
                .addComponent(jComboBox_tipusPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_netejar)
                    .addComponent(jButton_editar))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab2", jPanel7);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1391, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("tab3", jPanel2);

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

    private void jTable_rutesCreadesUsuariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_rutesCreadesUsuariMouseClicked
        

        jPanel_compartidesCanviant.setVisible(false);
        
        
    }//GEN-LAST:event_jTable_rutesCreadesUsuariMouseClicked

    private void jButton_textRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_textRutaMouseClicked
        try {
            llistaRutesCreades = gestorBDWikilocJdbc.obtenirLlistaRutaUsuari(usuari_loginat.getLogin());
            text_html = llistaRutesCreades.get(row_sel).getTextRuta();
            
            demo = new Demo(text_html, id);
        } catch (GestorBDWikilocException ex) {
            
        }
    }//GEN-LAST:event_jButton_textRutaMouseClicked

    private void jLabel_dificEstrella1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dificEstrella1MouseClicked
        
        if(editar_estrelles){
            jLabel_dificEstrella1.setIcon(estrellaGroga);
            jLabel_dificEstrella2.setIcon(estrellaBlanca);
            jLabel_dificEstrella3.setIcon(estrellaBlanca);
            jLabel_dificEstrella4.setIcon(estrellaBlanca);
            jLabel_dificEstrella5.setIcon(estrellaBlanca);
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
        
    }//GEN-LAST:event_jLabel_dificEstrella1MouseClicked

    private void jLabel_dificEstrella2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dificEstrella2MouseClicked
        
        if(editar_estrelles){
            jLabel_dificEstrella1.setIcon(estrellaGroga);
            jLabel_dificEstrella2.setIcon(estrellaGroga);
            jLabel_dificEstrella3.setIcon(estrellaBlanca);
            jLabel_dificEstrella4.setIcon(estrellaBlanca);
            jLabel_dificEstrella5.setIcon(estrellaBlanca);
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
        
    }//GEN-LAST:event_jLabel_dificEstrella2MouseClicked

    private void jLabel_dificEstrella3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dificEstrella3MouseClicked
        
        if(editar_estrelles){
            jLabel_dificEstrella1.setIcon(estrellaGroga);
            jLabel_dificEstrella2.setIcon(estrellaGroga);
            jLabel_dificEstrella3.setIcon(estrellaGroga);
            jLabel_dificEstrella4.setIcon(estrellaBlanca);
            jLabel_dificEstrella5.setIcon(estrellaBlanca);
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
        
    }//GEN-LAST:event_jLabel_dificEstrella3MouseClicked

    private void jLabel_dificEstrella4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dificEstrella4MouseClicked
        
        if(editar_estrelles){
            
            jLabel_dificEstrella1.setIcon(estrellaGroga);
            jLabel_dificEstrella2.setIcon(estrellaGroga);
            jLabel_dificEstrella3.setIcon(estrellaGroga);
            jLabel_dificEstrella4.setIcon(estrellaGroga);
            jLabel_dificEstrella5.setIcon(estrellaBlanca);
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
        
    }//GEN-LAST:event_jLabel_dificEstrella4MouseClicked

    private void jLabel_dificEstrella5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dificEstrella5MouseClicked
               
        if(editar_estrelles){
            jLabel_dificEstrella1.setIcon(estrellaGroga);
            jLabel_dificEstrella2.setIcon(estrellaGroga);
            jLabel_dificEstrella3.setIcon(estrellaGroga);
            jLabel_dificEstrella4.setIcon(estrellaGroga);
            jLabel_dificEstrella5.setIcon(estrellaGroga);
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
        
    }//GEN-LAST:event_jLabel_dificEstrella5MouseClicked

    private void jButton_desarCanvisRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_desarCanvisRutaMouseClicked
        
        
        try {
            gestorBDWikilocJdbc = new GestorBDWikilocJdbc();
                                
            int total = (hours * 60) + minutes;
            
            System.out.println(textHtmlEditat);
            
            Ruta ruta_editada;
            
            if(textHtmlEditat!=null){
                ruta_editada = new Ruta(id,jTextField_titol.getText(),jTextArea_descRuta.getText(),textHtmlEditat,Double.parseDouble(jTextField_dist.getText()),total,Integer.parseInt(jTextField_desnP.getText()),Integer.parseInt(jTextField_desnN.getText()),qtat_estrelles_actual,usuari_loginat);
            }else{
                ruta_editada = new Ruta(id,jTextField_titol.getText(),jTextArea_descRuta.getText(),text_html,Double.parseDouble(jTextField_dist.getText()),total,Integer.parseInt(jTextField_desnP.getText()),Integer.parseInt(jTextField_desnN.getText()),qtat_estrelles,usuari_loginat);
                
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
                    
                    tableModel = (DefaultTableModel) jTable_rutesCreadesUsuari.getModel();
                    
                    
                    
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
                
                jButton_desarCanvisRuta.setEnabled(false);
                
            }
            
            
            
        } catch (GestorBDWikilocException ex) {
            System.out.println("ERROR: "+ex.getMessage());
        }
        
        
    }//GEN-LAST:event_jButton_desarCanvisRutaMouseClicked

    private void jLabel_dificEstrella1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dificEstrella1MouseEntered
        canviarCursorEstrelles();
    }//GEN-LAST:event_jLabel_dificEstrella1MouseEntered

    private void jLabel_dificEstrella2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dificEstrella2MouseEntered
        canviarCursorEstrelles();
    }//GEN-LAST:event_jLabel_dificEstrella2MouseEntered

    private void jLabel_dificEstrella3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dificEstrella3MouseEntered
        canviarCursorEstrelles();
    }//GEN-LAST:event_jLabel_dificEstrella3MouseEntered

    private void jLabel_dificEstrella4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dificEstrella4MouseEntered
        canviarCursorEstrelles();
    }//GEN-LAST:event_jLabel_dificEstrella4MouseEntered

    private void jLabel_dificEstrella5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dificEstrella5MouseEntered
        canviarCursorEstrelles();
    }//GEN-LAST:event_jLabel_dificEstrella5MouseEntered

    private void jComboBox_tempsHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_tempsHItemStateChanged
       
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
    }//GEN-LAST:event_jComboBox_tempsHItemStateChanged

    private void jComboBox_tempsMItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_tempsMItemStateChanged
        
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
    }//GEN-LAST:event_jComboBox_tempsMItemStateChanged

    private void jTextField_titolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_titolKeyReleased
        
        if (titol.equals(jTextField_titol.getText())) {
            titol_canviada = false;
            horesMinutsModificats();
        } else {
            titol_canviada = true;
            horesMinutsModificats();
        }

    }//GEN-LAST:event_jTextField_titolKeyReleased

    private void jTextArea_descRutaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea_descRutaKeyReleased
        
        if (desc.equals(jTextArea_descRuta.getText())) {
            desc_canviada = false;
            horesMinutsModificats();
        } else {
            desc_canviada = true;
            horesMinutsModificats();
        }
    }//GEN-LAST:event_jTextArea_descRutaKeyReleased

    private void jButton_textRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_textRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_textRutaActionPerformed

    private void jTextField_desnPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_desnPKeyReleased
        
        
        if (desnP.equals(jTextField_desnP.getText())) {
            desnP_canviada = false;
            horesMinutsModificats();
        } else {
            desnP_canviada = true;
            horesMinutsModificats();
        }
        
    }//GEN-LAST:event_jTextField_desnPKeyReleased

    private void jTextField_desnNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_desnNKeyReleased
        
        if (desnN.equals(jTextField_desnN.getText())) {
            desnN_canviada = false;
            horesMinutsModificats();
        } else {
            desnN_canviada = true;
            horesMinutsModificats();
        }
        
    }//GEN-LAST:event_jTextField_desnNKeyReleased

    private void jTextField_distKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_distKeyReleased
        
        if (dist.equals(jTextField_dist.getText())) {
            dist_canviada = false;
            horesMinutsModificats();
        } else {
            dist_canviada = true;
            horesMinutsModificats();
        }
        
        
    }//GEN-LAST:event_jTextField_distKeyReleased

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
        
        
        
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void jList_puntsRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList_puntsRutaMouseClicked
        
        //String value = (String)jList_puntsRuta.getModel().getElementAt(jList_puntsRuta.locationToIndex(evt.getPoint()));
        //System.out.println("value:"+value);
        
        //Punt punt = (Punt) jList_puntsRuta.getModel().getElementAt(0);
        //Punt punt = (Punt)dlm.getElementAt(jList_puntsRuta.getSelectedIndex());
        //System.out.println("punt: "+punt);
    }//GEN-LAST:event_jList_puntsRutaMouseClicked

    private void jList_puntsRutaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList_puntsRutaValueChanged
        
        if (!evt.getValueIsAdjusting()) {
            JList jlist = (JList)evt.getSource();
            
            int idx = jlist.getSelectedIndex();
            
            punt_seleccionat = llistaPuntsRuta.get(idx);
            
            
            jTextField_numPunt.setText(punt_seleccionat.getNum().toString());
            jTextField_nomPunt.setText(punt_seleccionat.getNom());
            jTextArea_descPunt.setText(punt_seleccionat.getDescPunt());
            
            Tipus t;
            try {
                t = gestorBDWikilocJdbc.obtenirTipusPunt(punt_seleccionat.getNum(), id);
                
                System.out.println("tipus punt: SELECCIONAT: "+t);
                jComboBox_tipusPunt.getModel().setSelectedItem(t);
                
                
                
                //jComboBox_tipusPunt.setSelectedItem(t);
                
                            

                
            } catch (GestorBDWikilocException ex) {
                System.out.println(ex.getMessage());
            }
            
            
            //jLabel1
            if(punt_seleccionat.getFoto()!=null){
                System.out.println("BYTE[]"+punt_seleccionat.getFoto().length);
                
                BufferedImage bf = byteArrayToImage(punt_seleccionat.getFoto());
                ImageIcon foto = new ImageIcon(bf);
                jLabel_fotoPunt.setIcon(foto);
                             
             
            }else{
                jLabel_fotoPunt.setIcon(null);
            }
            
            
            jTextField_latPunt.setText(punt_seleccionat.getLat().toString());
            jTextField_lonPunt.setText(punt_seleccionat.getLon().toString());
            jTextField_altPunt.setText(punt_seleccionat.getAlt().toString());

        }
        
    }//GEN-LAST:event_jList_puntsRutaValueChanged

    private void jButton_afegirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_afegirActionPerformed
        
        jTextField_numPunt.setText("");
        jTextField_nomPunt.setText("");
        jTextArea_descPunt.setText("");
        jTextField_latPunt.setText("");
        jTextField_lonPunt.setText("");
        jTextField_altPunt.setText("");
        jLabel_fotoPunt.setIcon(null);//posar imatge però amb imatge de sense imatge per defecte!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        jComboBox_tipusPunt.setSelectedIndex(-1);
        
        jButton_incorporarNouElement.setVisible(true);
    }//GEN-LAST:event_jButton_afegirActionPerformed

    private void jButton_incorporarNouElementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_incorporarNouElementActionPerformed
        
        //AQUEST AFEGEIX ELEMENT A LA LLISTA
        int nou_num = Integer.parseInt(jTextField_numPunt.getText());
        String nou_nom = jTextField_nomPunt.getText();
        String nova_desc = jTextArea_descPunt.getText();
        int nova_lat = Integer.parseInt(jTextField_latPunt.getText());
        int nova_lon = Integer.parseInt(jTextField_lonPunt.getText());
        int nova_alt = Integer.parseInt(jTextField_altPunt.getText());
        byte[] nova_foto = null; //jLabel_fotoPunt.getIcon();!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! IMPORTANT
        
        Tipus tipusPunt = (Tipus) jComboBox_tipusPunt.getSelectedItem();
        
        Punt nou_punt = new Punt(nou_num,nou_nom,nova_desc,nova_foto,nova_lat,nova_lon,nova_alt,ruta_seleccionada,tipusPunt);
        
        dlm_tipus = new DefaultListModel();

        for (Punt p : llistaPuntsRuta) {

            dlm.addElement(p.getNum().toString() + " - " + p.getNom().toString());
        }
        jList_puntsRuta.setModel(dlm);
        jList_puntsRuta = new JList(dlm);
        
        dlm.addElement(nou_punt.getNum().toString() + " - " + nou_punt.getNom().toString());
        jList_puntsRuta.setModel(dlm);
        jList_puntsRuta = new JList(dlm);
        
        
        
        
        

        jButton_incorporarNouElement.setVisible(false);
    }//GEN-LAST:event_jButton_incorporarNouElementActionPerformed

    private void jTextField_numPuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_numPuntKeyReleased
        
        if(jTextField_numPunt.getText().length() == 0 || jTextField_nomPunt.getText().length() == 0 || jTextArea_descPunt.getText().length() == 0 || jTextField_latPunt.getText().length() == 0 || jTextField_lonPunt.getText().length() == 0 || jTextField_altPunt.getText().length() == 0){
            jButton_incorporarNouElement.setEnabled(false);
        }else{
            jButton_incorporarNouElement.setEnabled(true);
        }
    }//GEN-LAST:event_jTextField_numPuntKeyReleased

    private void jTextField_nomPuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_nomPuntKeyReleased
        
        if(jTextField_numPunt.getText().length() == 0 || jTextField_nomPunt.getText().length() == 0 || jTextArea_descPunt.getText().length() == 0 || jTextField_latPunt.getText().length() == 0 || jTextField_lonPunt.getText().length() == 0 || jTextField_altPunt.getText().length() == 0){
            jButton_incorporarNouElement.setEnabled(false);
        }else{
            jButton_incorporarNouElement.setEnabled(true);
        }
    }//GEN-LAST:event_jTextField_nomPuntKeyReleased

    private void jTextArea_descPuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea_descPuntKeyReleased
        
        if(jTextField_numPunt.getText().length() == 0 || jTextField_nomPunt.getText().length() == 0 || jTextArea_descPunt.getText().length() == 0 || jTextField_latPunt.getText().length() == 0 || jTextField_lonPunt.getText().length() == 0 || jTextField_altPunt.getText().length() == 0){
            jButton_incorporarNouElement.setEnabled(false);
        }else{
            jButton_incorporarNouElement.setEnabled(true);
        }
    }//GEN-LAST:event_jTextArea_descPuntKeyReleased

    private void jTextField_latPuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_latPuntKeyReleased
        
        if(jTextField_numPunt.getText().length() == 0 || jTextField_nomPunt.getText().length() == 0 || jTextArea_descPunt.getText().length() == 0 || jTextField_latPunt.getText().length() == 0 || jTextField_lonPunt.getText().length() == 0 || jTextField_altPunt.getText().length() == 0){
            jButton_incorporarNouElement.setEnabled(false);
        }else{
            jButton_incorporarNouElement.setEnabled(true);
        }
    }//GEN-LAST:event_jTextField_latPuntKeyReleased

    private void jTextField_lonPuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_lonPuntKeyReleased
        
        if(jTextField_numPunt.getText().length() == 0 || jTextField_nomPunt.getText().length() == 0 || jTextArea_descPunt.getText().length() == 0 || jTextField_latPunt.getText().length() == 0 || jTextField_lonPunt.getText().length() == 0 || jTextField_altPunt.getText().length() == 0){
            jButton_incorporarNouElement.setEnabled(false);
        }else{
            jButton_incorporarNouElement.setEnabled(true);
        }
    }//GEN-LAST:event_jTextField_lonPuntKeyReleased

    private void jTextField_altPuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_altPuntKeyReleased
        
        if(jTextField_numPunt.getText().length() == 0 || jTextField_nomPunt.getText().length() == 0 || jTextArea_descPunt.getText().length() == 0 || jTextField_latPunt.getText().length() == 0 || jTextField_lonPunt.getText().length() == 0 || jTextField_altPunt.getText().length() == 0){
            jButton_incorporarNouElement.setEnabled(false);
        }else{
            jButton_incorporarNouElement.setEnabled(true);
        }
    }//GEN-LAST:event_jTextField_altPuntKeyReleased

    public BufferedImage byteArrayToImage(byte[] bytes) {
        BufferedImage bufferedImage = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return bufferedImage;
    }
    
    public void horesMinutsModificats(){
 
        System.out.println("hora"+hours_canviada+" minuts"+ minutes_canviada);
        if(hours_canviada || minutes_canviada || titol_canviada || dist_canviada || desc_canviada || desnP_canviada || desnN_canviada || estrella_canviada){
            jButton_desarCanvisRuta.setEnabled(true);
            System.out.println("A");
        }else{
            jButton_desarCanvisRuta.setEnabled(false);
            System.out.println("B");
        }
    }
    
    


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_afegir;
    private javax.swing.JButton jButton_baixar;
    private javax.swing.JButton jButton_desarCanvisPunts;
    private javax.swing.JButton jButton_desarCanvisRuta;
    private javax.swing.JButton jButton_editar;
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JButton jButton_incorporarNouElement;
    private javax.swing.JButton jButton_netejar;
    private javax.swing.JButton jButton_pujar;
    private javax.swing.JButton jButton_textRuta;
    private javax.swing.JComboBox<String> jComboBox_tempsH;
    private javax.swing.JComboBox<String> jComboBox_tempsM;
    private javax.swing.JComboBox<Tipus> jComboBox_tipusPunt;
    private javax.swing.JLabel jLabel_dificEstrella1;
    private javax.swing.JLabel jLabel_dificEstrella2;
    private javax.swing.JLabel jLabel_dificEstrella3;
    private javax.swing.JLabel jLabel_dificEstrella4;
    private javax.swing.JLabel jLabel_dificEstrella5;
    private javax.swing.JLabel jLabel_fotoPunt;
    private javax.swing.JList<String> jList_puntsRuta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel_compartidesCanviant;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable_rutesCreadesUsuari;
    private javax.swing.JTextArea jTextArea_descPunt;
    private javax.swing.JTextArea jTextArea_descRuta;
    private javax.swing.JTextField jTextField_altPunt;
    private javax.swing.JTextField jTextField_desnN;
    private javax.swing.JTextField jTextField_desnP;
    private javax.swing.JTextField jTextField_dist;
    private javax.swing.JTextField jTextField_latPunt;
    private javax.swing.JTextField jTextField_lonPunt;
    private javax.swing.JTextField jTextField_nomPunt;
    private javax.swing.JTextField jTextField_numPunt;
    private javax.swing.JTextField jTextField_titol;
    // End of variables declaration//GEN-END:variables
}
