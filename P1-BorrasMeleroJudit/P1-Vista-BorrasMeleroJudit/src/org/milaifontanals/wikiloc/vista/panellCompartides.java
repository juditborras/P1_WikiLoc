/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.shef.Demo;
import org.milaifontanals.wikiloc.jdbc.GestorBDWikilocJdbc;
import org.milaifontanals.wikiloc.model.Comentari;
import org.milaifontanals.wikiloc.model.Companys;
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
    ImageIcon fotoNulla = new ImageIcon("img"+File.separator+"foto_nulla.jpg");
    boolean editar_estrelles = false;
    
    int id, hours, minutes, antic, qtat_estrelles, qtat_estrelles_actual;
    boolean hours_canviada = false, minutes_canviada = false, titol_canviada = false, dist_canviada = false, desc_canviada = false, desnP_canviada = false, desnN_canviada = false, estrella_canviada = false;
    boolean onEdit_click = false;
    int qc1,qc2, qc3;
    
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
    Tipus t;
    
    String num, nomPunt, descPunt, lat, lon, alt, filePath, ordre;
    boolean ordre_canviat = false, nomPunt_canviat = false, descPunt_canviat = false, lat_canviat = false, lon_canviat = false, alt_canviat = false, tipusPunt_canviada = false, fotoPunt_canviada = false;
    boolean editarPuntRuta = false, botoAfegirClicat = false, botoPujarClicat = false, botoBaixarClicat = false;
    ImageIcon fotoPunt;
    
    public panellCompartides(){
        
    }
    
    public panellCompartides(JPanel jPanel_menu, JPanel jPanel_principal, Usuari usuari_loginat) throws GestorBDWikilocException {
        
        UIManager.put("TabbedPane.selected", new Color(255,174,0));
        UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(new Color(255,255,255)));
        UIManager.put("TabbedPane.darkShadow", new ColorUIResource(new Color(255,255,255)));
        
        
        
        initComponents();    
        
        jComboBox_filtreDific.addItem("");
        for(int i=1; i<=5; i++){
            jComboBox_filtreDific.addItem(i+"");
        }
        jComboBox_filtreDific.setSelectedIndex(-1);
        
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
                editarPuntRuta = true;
                
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
            
                
                jTextField_ordrePunt.setVisible(false);
                jTextField_nomPunt.setVisible(false);
                jTextArea_descPunt.setVisible(false);
                jScrollPane4.setVisible(false);
                jTextField_latPunt.setVisible(false);
                jTextField_lonPunt.setVisible(false);
                jTextField_altPunt.setVisible(false);
                jComboBox_tipusPunt.setVisible(false);
                jLabel_fotoPunt.setVisible(false);             
                jButton_seleccionarFoto.setVisible(false);

                jButton_pujar.setVisible(false);
                jButton_baixar.setVisible(false);
                jButton_eliminar.setVisible(false);
                jButton_afegir.setVisible(true);
                jButton_netejar.setVisible(false);

                jButton_desarCanvisPunts.setVisible(false);
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
                
                editarPuntRuta = false;
                
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
                
 
                jTextField_ordrePunt.setVisible(false);
                jTextField_nomPunt.setVisible(false);
                jTextArea_descPunt.setVisible(false);
                jScrollPane4.setVisible(false);
                jTextField_latPunt.setVisible(false);
                jTextField_lonPunt.setVisible(false);
                jTextField_altPunt.setVisible(false);
                jComboBox_tipusPunt.setVisible(false);
                jLabel_fotoPunt.setVisible(false);             
                jButton_seleccionarFoto.setVisible(false);

                jButton_pujar.setVisible(false);
                jButton_baixar.setVisible(false);
                jButton_eliminar.setVisible(false);
                jButton_afegir.setVisible(false);
                jButton_netejar.setVisible(false);

                jButton_desarCanvisPunts.setVisible(false);
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

                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());
                                
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
        


        //pantalla estadístiques
        try {
            
            int qtatComentarisRuta = gestorBDWikilocJdbc.qtatComentarisRuta(id);
            jLabel_qtatTotalComentaris.setText(qtatComentarisRuta+"");
            
            int qtatComentarisRutaFeta = gestorBDWikilocJdbc.qtatComentarisRutaFeta(id);
            jLabel_qtatFetesAmbComentari.setText(qtatComentarisRutaFeta+"");
            
            int qtatComentarisRutaNoFeta = gestorBDWikilocJdbc.qtatComentarisRutaNoFeta(id);
            jLabel_qtatNoFetesAmbComentari.setText(qtatComentarisRutaNoFeta+"");
            
            double mitjaVinf = gestorBDWikilocJdbc.mitjaVinf(id);
            if(mitjaVinf >= 0.0 && mitjaVinf < 0.5){
                //buides
                jLabel_vInfoE1.setIcon(estrellaBlanca);
                jLabel_vInfoE2.setIcon(estrellaBlanca);
                jLabel_vInfoE3.setIcon(estrellaBlanca);
                jLabel_vInfoE4.setIcon(estrellaBlanca);
                jLabel_vInfoE5.setIcon(estrellaBlanca);
            }else if(mitjaVinf >= 0.5 && mitjaVinf < 1.5){
                //estrella 1
                jLabel_vInfoE1.setIcon(estrellaGroga);
                jLabel_vInfoE2.setIcon(estrellaBlanca);
                jLabel_vInfoE3.setIcon(estrellaBlanca);
                jLabel_vInfoE4.setIcon(estrellaBlanca);
                jLabel_vInfoE5.setIcon(estrellaBlanca);
            }else if(mitjaVinf >= 1.5 && mitjaVinf < 2.5){
                //estrella 2
                jLabel_vInfoE1.setIcon(estrellaGroga);
                jLabel_vInfoE2.setIcon(estrellaGroga);
                jLabel_vInfoE3.setIcon(estrellaBlanca);
                jLabel_vInfoE4.setIcon(estrellaBlanca);
                jLabel_vInfoE5.setIcon(estrellaBlanca);
            }else if(mitjaVinf >= 2.5 && mitjaVinf < 3.5){
                //estrella 3
                jLabel_vInfoE1.setIcon(estrellaGroga);
                jLabel_vInfoE2.setIcon(estrellaGroga);
                jLabel_vInfoE3.setIcon(estrellaGroga);
                jLabel_vInfoE4.setIcon(estrellaBlanca);
                jLabel_vInfoE5.setIcon(estrellaBlanca);
            }else if(mitjaVinf >= 3.5 && mitjaVinf < 4.5){
                //estrella 4
                jLabel_vInfoE1.setIcon(estrellaGroga);
                jLabel_vInfoE2.setIcon(estrellaGroga);
                jLabel_vInfoE3.setIcon(estrellaGroga);
                jLabel_vInfoE4.setIcon(estrellaGroga);
                jLabel_vInfoE5.setIcon(estrellaBlanca);
            }else if(mitjaVinf >= 4.5 && mitjaVinf <= 5.0){
                //estrella 5
                jLabel_vInfoE1.setIcon(estrellaGroga);
                jLabel_vInfoE2.setIcon(estrellaGroga);
                jLabel_vInfoE3.setIcon(estrellaGroga);
                jLabel_vInfoE4.setIcon(estrellaGroga);
                jLabel_vInfoE5.setIcon(estrellaGroga);
            }
            
            double mitjaVseg = gestorBDWikilocJdbc.mitjaVseg(id);
            if(mitjaVseg >= 0.0 && mitjaVseg < 0.5){
                //buides
                jLabel_vSegE1.setIcon(estrellaBlanca);
                jLabel_vSegE2.setIcon(estrellaBlanca);
                jLabel_vSegE3.setIcon(estrellaBlanca);
                jLabel_vSegE4.setIcon(estrellaBlanca);
                jLabel_vSegE5.setIcon(estrellaBlanca);
            }else if(mitjaVseg >= 0.5 && mitjaVseg < 1.5){
                //estrella 1
                jLabel_vSegE1.setIcon(estrellaGroga);
                jLabel_vSegE2.setIcon(estrellaBlanca);
                jLabel_vSegE3.setIcon(estrellaBlanca);
                jLabel_vSegE4.setIcon(estrellaBlanca);
                jLabel_vSegE5.setIcon(estrellaBlanca);
            }else if(mitjaVseg >= 1.5 && mitjaVseg < 2.5){
                //estrella 2
                jLabel_vSegE1.setIcon(estrellaGroga);
                jLabel_vSegE2.setIcon(estrellaGroga);
                jLabel_vSegE3.setIcon(estrellaBlanca);
                jLabel_vSegE4.setIcon(estrellaBlanca);
                jLabel_vSegE5.setIcon(estrellaBlanca);
            }else if(mitjaVseg >= 2.5 && mitjaVseg < 3.5){
                //estrella 3
                jLabel_vSegE1.setIcon(estrellaGroga);
                jLabel_vSegE2.setIcon(estrellaGroga);
                jLabel_vSegE3.setIcon(estrellaGroga);
                jLabel_vSegE4.setIcon(estrellaBlanca);
                jLabel_vSegE5.setIcon(estrellaBlanca);
            }else if(mitjaVseg >= 3.5 && mitjaVseg < 4.5){
                //estrella 4
                jLabel_vSegE1.setIcon(estrellaGroga);
                jLabel_vSegE2.setIcon(estrellaGroga);
                jLabel_vSegE3.setIcon(estrellaGroga);
                jLabel_vSegE4.setIcon(estrellaGroga);
                jLabel_vSegE5.setIcon(estrellaBlanca);
            }else if(mitjaVseg >= 4.5 && mitjaVseg <= 5.0){
                //estrella 5
                jLabel_vSegE1.setIcon(estrellaGroga);
                jLabel_vSegE2.setIcon(estrellaGroga);
                jLabel_vSegE3.setIcon(estrellaGroga);
                jLabel_vSegE4.setIcon(estrellaGroga);
                jLabel_vSegE5.setIcon(estrellaGroga);
            }
            
            double mitjaVpai = gestorBDWikilocJdbc.mitjaVpai(id);
            if(mitjaVpai >= 0.0 && mitjaVpai < 0.5){
                //buides
                jLabel_vPaiE1.setIcon(estrellaBlanca);
                jLabel_vPaiE2.setIcon(estrellaBlanca);
                jLabel_vPaiE3.setIcon(estrellaBlanca);
                jLabel_vPaiE4.setIcon(estrellaBlanca);
                jLabel_vPaiE5.setIcon(estrellaBlanca);
            }else if(mitjaVpai >= 0.5 && mitjaVpai < 1.5){
                //estrella 1
                jLabel_vPaiE1.setIcon(estrellaGroga);
                jLabel_vPaiE2.setIcon(estrellaBlanca);
                jLabel_vPaiE3.setIcon(estrellaBlanca);
                jLabel_vPaiE4.setIcon(estrellaBlanca);
                jLabel_vPaiE5.setIcon(estrellaBlanca);
            }else if(mitjaVpai >= 1.5 && mitjaVpai < 2.5){
                //estrella 2
                jLabel_vPaiE1.setIcon(estrellaGroga);
                jLabel_vPaiE2.setIcon(estrellaGroga);
                jLabel_vPaiE3.setIcon(estrellaBlanca);
                jLabel_vPaiE4.setIcon(estrellaBlanca);
                jLabel_vPaiE5.setIcon(estrellaBlanca);
            }else if(mitjaVpai >= 2.5 && mitjaVpai < 3.5){
                //estrella 3
                jLabel_vPaiE1.setIcon(estrellaGroga);
                jLabel_vPaiE2.setIcon(estrellaGroga);
                jLabel_vPaiE3.setIcon(estrellaGroga);
                jLabel_vPaiE4.setIcon(estrellaBlanca);
                jLabel_vPaiE5.setIcon(estrellaBlanca);
            }else if(mitjaVpai >= 3.5 && mitjaVpai < 4.5){
                //estrella 4
                jLabel_vPaiE1.setIcon(estrellaGroga);
                jLabel_vPaiE2.setIcon(estrellaGroga);
                jLabel_vPaiE3.setIcon(estrellaGroga);
                jLabel_vPaiE4.setIcon(estrellaGroga);
                jLabel_vPaiE5.setIcon(estrellaBlanca);
            }else if(mitjaVpai >= 4.5 && mitjaVpai <= 5.0){
                //estrella 5
                jLabel_vPaiE1.setIcon(estrellaGroga);
                jLabel_vPaiE2.setIcon(estrellaGroga);
                jLabel_vPaiE3.setIcon(estrellaGroga);
                jLabel_vPaiE4.setIcon(estrellaGroga);
                jLabel_vPaiE5.setIcon(estrellaGroga);
            }
            
            double mitjaVdific = gestorBDWikilocJdbc.mitjaVdific(id);
            if(mitjaVdific >= 0.0 && mitjaVdific < 0.5){
                //buides
                jLabel_vDificE1.setIcon(estrellaBlanca);
                jLabel_vDificE2.setIcon(estrellaBlanca);
                jLabel_vDificE3.setIcon(estrellaBlanca);
                jLabel_vDificE4.setIcon(estrellaBlanca);
                jLabel_vDificE5.setIcon(estrellaBlanca);
            }else if(mitjaVdific >= 0.5 && mitjaVdific < 1.5){
                //estrella 1
                jLabel_vDificE1.setIcon(estrellaGroga);
                jLabel_vDificE2.setIcon(estrellaBlanca);
                jLabel_vDificE3.setIcon(estrellaBlanca);
                jLabel_vDificE4.setIcon(estrellaBlanca);
                jLabel_vDificE5.setIcon(estrellaBlanca);
            }else if(mitjaVdific >= 1.5 && mitjaVdific < 2.5){
                //estrella 2
                jLabel_vDificE1.setIcon(estrellaGroga);
                jLabel_vDificE2.setIcon(estrellaGroga);
                jLabel_vDificE3.setIcon(estrellaBlanca);
                jLabel_vDificE4.setIcon(estrellaBlanca);
                jLabel_vDificE5.setIcon(estrellaBlanca);
            }else if(mitjaVdific >= 2.5 && mitjaVdific < 3.5){
                //estrella 3
                jLabel_vDificE1.setIcon(estrellaGroga);
                jLabel_vDificE2.setIcon(estrellaGroga);
                jLabel_vDificE3.setIcon(estrellaGroga);
                jLabel_vDificE4.setIcon(estrellaBlanca);
                jLabel_vDificE5.setIcon(estrellaBlanca);
            }else if(mitjaVdific >= 3.5 && mitjaVdific < 4.5){
                //estrella 4
                jLabel_vDificE1.setIcon(estrellaGroga);
                jLabel_vDificE2.setIcon(estrellaGroga);
                jLabel_vDificE3.setIcon(estrellaGroga);
                jLabel_vDificE4.setIcon(estrellaGroga);
                jLabel_vDificE5.setIcon(estrellaBlanca);
            }else if(mitjaVdific >= 4.5 && mitjaVdific <= 5.0){
                //estrella 5
                jLabel_vDificE1.setIcon(estrellaGroga);
                jLabel_vDificE2.setIcon(estrellaGroga);
                jLabel_vDificE3.setIcon(estrellaGroga);
                jLabel_vDificE4.setIcon(estrellaGroga);
                jLabel_vDificE5.setIcon(estrellaGroga);
            }
            
            
            List<Comentari> llistaComentaris = gestorBDWikilocJdbc.obtenirLlistaComentaris(id);
            
            String info_comentari = "";
            
            for(Comentari c : llistaComentaris){
                
                info_comentari += "COMENTARI FET PER: " + c.getLoginUsuari().getLogin() +"\n";
                info_comentari += "Contingut: " + c.getText() +"\n";
                info_comentari += "v_inf: " + c.getVinf() +"\n";
                info_comentari += "feta: " + c.getFeta() +"\n";
                info_comentari += "v_seg: " + c.getVseg() +"\n";
                info_comentari += "v_pai: " + c.getVpai() +"\n";
                info_comentari += "dific: " + c.getDific() +"\n";
                info_comentari += "mt: " + c.getMt() +"\n";
                
                Companys company = gestorBDWikilocJdbc.obtenirCompany(c.getId());
                if(company != null){
                    info_comentari += "Company de ruta: " + company.getLoginUsuari().getLogin() +"\n";
                }
                
                info_comentari += "\n";
            }
            
            
            jTextArea1.setText(info_comentari);
            
            
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
        jTextField_filtreTitol = new javax.swing.JTextField();
        jButton_netejarFiltre = new javax.swing.JButton();
        jButton_cercaFiltre = new javax.swing.JButton();
        jTextField_filtreDist = new javax.swing.JTextField();
        jComboBox_filtreDific = new javax.swing.JComboBox<>();
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
        jTextField_ordrePunt = new javax.swing.JTextField();
        jLabel_fotoPunt = new javax.swing.JLabel();
        jTextField_latPunt = new javax.swing.JTextField();
        jTextField_lonPunt = new javax.swing.JTextField();
        jTextField_altPunt = new javax.swing.JTextField();
        jButton_desarCanvisPunts = new javax.swing.JButton();
        jButton_netejar = new javax.swing.JButton();
        jButton_afegir = new javax.swing.JButton();
        jButton_eliminar = new javax.swing.JButton();
        jButton_pujar = new javax.swing.JButton();
        jButton_baixar = new javax.swing.JButton();
        jComboBox_tipusPunt = new javax.swing.JComboBox<>();
        jButton_seleccionarFoto = new javax.swing.JButton();
        jPanel_altimetria = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel_qtatTotalComentaris = new javax.swing.JLabel();
        jLabel_qtatFetesAmbComentari = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel_qtatNoFetesAmbComentari = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel_vInfoE1 = new javax.swing.JLabel();
        jLabel_vInfoE2 = new javax.swing.JLabel();
        jLabel_vInfoE3 = new javax.swing.JLabel();
        jLabel_vInfoE4 = new javax.swing.JLabel();
        jLabel_vInfoE5 = new javax.swing.JLabel();
        jLabel_vSegE1 = new javax.swing.JLabel();
        jLabel_vSegE2 = new javax.swing.JLabel();
        jLabel_vSegE3 = new javax.swing.JLabel();
        jLabel_vSegE4 = new javax.swing.JLabel();
        jLabel_vSegE5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel_vPaiE1 = new javax.swing.JLabel();
        jLabel_vPaiE2 = new javax.swing.JLabel();
        jLabel_vPaiE3 = new javax.swing.JLabel();
        jLabel_vPaiE4 = new javax.swing.JLabel();
        jLabel_vPaiE5 = new javax.swing.JLabel();
        jLabel_vDificE1 = new javax.swing.JLabel();
        jLabel_vDificE2 = new javax.swing.JLabel();
        jLabel_vDificE3 = new javax.swing.JLabel();
        jLabel_vDificE4 = new javax.swing.JLabel();
        jLabel_vDificE5 = new javax.swing.JLabel();

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

        jTextField_filtreTitol.setText("jTextField1");

        jButton_netejarFiltre.setText("netejar filtre");
        jButton_netejarFiltre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_netejarFiltreMouseClicked(evt);
            }
        });

        jButton_cercaFiltre.setText("cercar");
        jButton_cercaFiltre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_cercaFiltreMouseClicked(evt);
            }
        });

        jTextField_filtreDist.setText("jTextField3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField_filtreTitol, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_filtreDist, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_filtreDific, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)
                        .addComponent(jButton_cercaFiltre)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_netejarFiltre))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_filtreTitol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cercaFiltre)
                    .addComponent(jTextField_filtreDist, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_netejarFiltre)
                    .addComponent(jComboBox_filtreDific, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
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

        jTextField_ordrePunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_ordrePuntKeyReleased(evt);
            }
        });

        jLabel_fotoPunt.setText("fotooooooooo");

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

        jButton_desarCanvisPunts.setText("desar");
        jButton_desarCanvisPunts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_desarCanvisPuntsMouseClicked(evt);
            }
        });

        jButton_netejar.setText("netejar llista");
        jButton_netejar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_netejarMouseClicked(evt);
            }
        });

        jButton_afegir.setText("afegir element llista");
        jButton_afegir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_afegirActionPerformed(evt);
            }
        });

        jButton_eliminar.setText("eliminar element");
        jButton_eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_eliminarMouseClicked(evt);
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

        jComboBox_tipusPunt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tipusPuntItemStateChanged(evt);
            }
        });

        jButton_seleccionarFoto.setText("seleccionar foto");
        jButton_seleccionarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_seleccionarFotoActionPerformed(evt);
            }
        });

        jPanel_altimetria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("jButton1");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_pujar)
                                    .addComponent(jButton_baixar)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)
                                .addGap(55, 55, 55)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_ordrePunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_latPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jTextField_altPunt, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextField_lonPunt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jComboBox_tipusPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jButton_desarCanvisPunts))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel_altimetria, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jTextField_nomPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(257, 257, 257)
                                .addComponent(jButton_seleccionarFoto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(253, 253, 253)
                                .addComponent(jButton_netejar))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jButton_eliminar)
                                .addGap(42, 42, 42)
                                .addComponent(jButton_afegir)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel_fotoPunt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(jButton_pujar)
                                .addGap(35, 35, 35)
                                .addComponent(jButton_baixar)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_afegir)
                            .addComponent(jButton_eliminar))
                        .addGap(4, 4, 4)
                        .addComponent(jComboBox_tipusPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_netejar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_desarCanvisPunts)
                            .addComponent(jButton1)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel_fotoPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jTextField_ordrePunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jTextField_nomPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton_seleccionarFoto)
                        .addGap(14, 14, 14)
                        .addComponent(jTextField_latPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_lonPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_altPunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jPanel_altimetria, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab2", jPanel7);

        jLabel1.setText("estadistiques");

        jLabel2.setText("comentaris: QTAT ");

        jLabel3.setText("valoracions:");

        jLabel4.setText("v. info:");

        jLabel5.setText("v. seguiment");

        jLabel6.setText("v. paisatge");

        jLabel7.setText("I MOSTRAR LLISTA COMENTARIS AMB CONTINGUT");

        jLabel8.setText("qtat fetes amb comentari:");

        jLabel9.setText("login_usuari: text, v_inf, feta (v_seg, v_pai, dific), mt, company");

        jLabel10.setText("qtat comentaris sense fer la ruta:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane5.setViewportView(jTextArea1);

        jLabel_vInfoE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vInfoE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vInfoE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vInfoE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vInfoE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vSegE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vSegE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vSegE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vSegE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vSegE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel11.setText("v. dific");

        jLabel_vPaiE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vPaiE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vPaiE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vPaiE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vPaiE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vDificE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vDificE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vDificE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vDificE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        jLabel_vDificE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_qtatFetesAmbComentari, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_qtatNoFetesAmbComentari, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_qtatTotalComentaris, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel_vInfoE1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vInfoE2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel6)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel_vPaiE1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel_vPaiE2))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel_vSegE1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel_vSegE2))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel_vDificE1)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel_vDificE2)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_vInfoE3)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel_vInfoE4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vInfoE5))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_vSegE3)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel_vPaiE3)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel_vSegE4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel_vSegE5))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel_vPaiE4)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel_vDificE5)
                                            .addComponent(jLabel_vPaiE5)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_vDificE3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vDificE4)))))
                .addGap(288, 288, 288))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel_qtatTotalComentaris, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_qtatNoFetesAmbComentari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel_qtatFetesAmbComentari, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel_vInfoE5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_vSegE5))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel_vInfoE4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_vSegE4))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel_vInfoE3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel_vSegE3))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_vInfoE1)
                                        .addComponent(jLabel_vInfoE2))
                                    .addGap(76, 76, 76)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel_vSegE1)
                                        .addComponent(jLabel_vSegE2)))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(40, 40, 40)
                            .addComponent(jLabel4)
                            .addGap(98, 98, 98)
                            .addComponent(jLabel5))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_vPaiE1)
                            .addComponent(jLabel_vPaiE2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_vPaiE3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_vPaiE4)
                            .addComponent(jLabel_vPaiE5)
                            .addComponent(jLabel6))
                        .addGap(77, 77, 77)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_vDificE3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_vDificE4)
                            .addComponent(jLabel_vDificE5)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(jLabel_vDificE1)
                                .addComponent(jLabel_vDificE2)))))
                .addContainerGap(39, Short.MAX_VALUE))
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
            
            demo = new Demo(text_html, id,false,null);
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

            if(editarPuntRuta){
                jTextField_ordrePunt.setVisible(true);
                jTextField_nomPunt.setVisible(true);
                jTextArea_descPunt.setVisible(true);
                jScrollPane4.setVisible(true);
                jTextField_latPunt.setVisible(true);
                jTextField_lonPunt.setVisible(true);
                jTextField_altPunt.setVisible(true);
                jComboBox_tipusPunt.setVisible(true);
                jLabel_fotoPunt.setVisible(true);
                jButton_seleccionarFoto.setVisible(true);

                jButton_pujar.setVisible(true);
                jButton_baixar.setVisible(true);
                jButton_eliminar.setVisible(true);

                jButton_netejar.setVisible(true);
                
                jTextField_ordrePunt.setEditable(false);
                jTextField_nomPunt.setEditable(true);
                jTextArea_descPunt.setEditable(true);
                jTextField_latPunt.setEditable(true);
                jTextField_lonPunt.setEditable(true);
                jTextField_altPunt.setEditable(true);
                jComboBox_tipusPunt.setEnabled(true);

                jButton_desarCanvisPunts.setVisible(true);
                jButton_desarCanvisPunts.setEnabled(false);
                
            }else{
                
                jTextField_ordrePunt.setVisible(true);
                jTextField_nomPunt.setVisible(true);
                jTextArea_descPunt.setVisible(true);
                jScrollPane4.setVisible(true);
                jTextField_latPunt.setVisible(true);
                jTextField_lonPunt.setVisible(true);
                jTextField_altPunt.setVisible(true);
                jComboBox_tipusPunt.setVisible(true);
                jLabel_fotoPunt.setVisible(true);             

                
                jTextField_ordrePunt.setEditable(false);
                jTextField_nomPunt.setEditable(false);
                jTextArea_descPunt.setEditable(false);
                jTextField_latPunt.setEditable(false);
                jTextField_lonPunt.setEditable(false);
                jTextField_altPunt.setEditable(false);
                jComboBox_tipusPunt.setEnabled(false);


//                jButton_pujar.setVisible(false);
//                jButton_baixar.setVisible(false);
//                jButton_eliminar.setVisible(false);
//                jButton_afegir.setEnabled(false);
//                jButton_netejar.setEnabled(false);
//
                jButton_desarCanvisPunts.setVisible(false);
            }

            
            if(llistaPuntsRuta.size()>0 && idx!= -1){
                System.out.println("INDEX: "+idx);
                punt_seleccionat = llistaPuntsRuta.get(idx);
            }
            
            
            
            jTextField_ordrePunt.setText(punt_seleccionat.getOrdre().toString());
            jTextField_nomPunt.setText(punt_seleccionat.getNom());
            jTextArea_descPunt.setText(punt_seleccionat.getDescPunt());
            

            
            num = punt_seleccionat.getNum()+"";
            nomPunt = punt_seleccionat.getNom();
            descPunt = punt_seleccionat.getDescPunt();
            lat = punt_seleccionat.getLat()+"";
            lon = punt_seleccionat.getLon()+"";
            alt = punt_seleccionat.getAlt()+"";
            ordre = punt_seleccionat.getOrdre()+"";
            
            try {
                System.out.println("PUNT SELECCIONAT: "+punt_seleccionat.getNum()+" ID RUTA: "+id);
                t = gestorBDWikilocJdbc.obtenirTipusPunt(punt_seleccionat.getNum(), id);
                System.out.println("t: "+t);
                System.out.println("tipus punt: SELECCIONAT: "+t);
                jComboBox_tipusPunt.getModel().setSelectedItem(t);
                
                
                
                //jComboBox_tipusPunt.setSelectedItem(t);
                
                            

                
            } catch (GestorBDWikilocException ex) {
                System.out.println("catch: error");
                System.out.println(ex.getMessage());
            }
            
            
            //jLabel1
            if(punt_seleccionat.getFoto()!=null){
                System.out.println("BYTE[]"+punt_seleccionat.getFoto().length);
                
                BufferedImage bf = byteArrayToImage(punt_seleccionat.getFoto());
                fotoPunt = new ImageIcon(bf);
                jLabel_fotoPunt.setIcon(fotoPunt);
                             
             
            }else{
                jLabel_fotoPunt.setIcon(fotoNulla);
            }
            
            
            jTextField_latPunt.setText(punt_seleccionat.getLat().toString());
            jTextField_lonPunt.setText(punt_seleccionat.getLon().toString());
            jTextField_altPunt.setText(punt_seleccionat.getAlt().toString());

        }
        
    }//GEN-LAST:event_jList_puntsRutaValueChanged

    private void jButton_afegirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_afegirActionPerformed
        
        botoAfegirClicat = true;

        dlm.clear();
        try {
            llistaPuntsRuta = gestorBDWikilocJdbc.obtenirLlistaPuntsRuta(id);
            //dlm = new DefaultListModel();
            dlm.clear();
            System.out.println("QT PUNTS DE RUTA: "+dlm);
            for (Punt p : llistaPuntsRuta) {

                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

            }
            jList_puntsRuta.setModel(dlm);
            jList_puntsRuta = new JList(dlm);
            System.out.println(jList_puntsRuta.getModel().toString());

        } catch (GestorBDWikilocException ex) {
            Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        jTextField_ordrePunt.setVisible(true);
        jTextField_nomPunt.setVisible(true);
        jTextArea_descPunt.setVisible(true);
        jScrollPane4.setVisible(true);
        jTextField_latPunt.setVisible(true);
        jTextField_lonPunt.setVisible(true);
        jTextField_altPunt.setVisible(true);
        jComboBox_tipusPunt.setVisible(true);
        jLabel_fotoPunt.setVisible(true);
        jButton_seleccionarFoto.setVisible(true);

        jButton_pujar.setVisible(true);
        jButton_baixar.setVisible(true);
        jButton_eliminar.setVisible(true);

        jButton_netejar.setVisible(true);

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
        //jList_puntsRuta.getSelectionModel().clearSelection();
        
  
        jButton_desarCanvisPunts.setVisible(true);
        
    }//GEN-LAST:event_jButton_afegirActionPerformed

    private void jTextField_ordrePuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ordrePuntKeyReleased
        
        if(ordre.equals(jTextField_ordrePunt.getText())){           
            ordre_canviat = false;
            modificacionsCampsPunt();
        }else{
            ordre_canviat = true;
            modificacionsCampsPunt();
        }
    }//GEN-LAST:event_jTextField_ordrePuntKeyReleased

    private void jTextField_nomPuntKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_nomPuntKeyReleased
        
        if(botoAfegirClicat){

            if (jTextField_ordrePunt.getText().length() != 0 && jTextField_nomPunt.getText().length() != 0 && jTextArea_descPunt.getText().length() != 0 && jTextField_latPunt.getText().length() != 0 && jTextField_lonPunt.getText().length() != 0 && jTextField_altPunt.getText().length() != 0 && jComboBox_tipusPunt.getSelectedItem() != null) {
                jButton_desarCanvisPunts.setEnabled(true);
            }else{
                jButton_desarCanvisPunts.setEnabled(false);
            }
        } else {

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
            }else{
                jButton_desarCanvisPunts.setEnabled(false);
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
            }else{
                jButton_desarCanvisPunts.setEnabled(false);
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
            }else{
                jButton_desarCanvisPunts.setEnabled(false);
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
            }else{
                jButton_desarCanvisPunts.setEnabled(false);
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

    private void jButton_seleccionarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_seleccionarFotoActionPerformed
        
        JFileChooser jfc = new JFileChooser();
                
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        
        jfc.setFileFilter(imageFilter);
        
        jfc.showOpenDialog(jfc);
        
        File imgFile = jfc.getSelectedFile();
        
        if(imgFile != null){
            
           
            filePath = imgFile.getAbsolutePath();
            ImageIcon novaImatgeSeleccionada = new ImageIcon(filePath);
            
            jLabel_fotoPunt.setIcon(novaImatgeSeleccionada);
            
            BufferedImage bi = new BufferedImage(
            novaImatgeSeleccionada.getIconWidth(),
            novaImatgeSeleccionada.getIconHeight(),
            BufferedImage.TYPE_INT_RGB);
            
            
            String extensio = filePath.substring(filePath.length() - 3);
            byte[] bt = imageToByteArray(bi, extensio);
            
            if(Arrays.equals(punt_seleccionat.getFoto(), bt)){
                fotoPunt_canviada = true;
                //modificacionsCampsPunt();
            }else{
                fotoPunt_canviada = false;
                //modificacionsCampsPunt();
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

    private void jComboBox_tipusPuntItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_tipusPuntItemStateChanged
        
        try{
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                // Item was just selected

                qc3++;
                
                if(qc3>1){

                    JComboBox cb = (JComboBox) evt.getSource();

                    if (botoAfegirClicat) {

                        if (jTextField_ordrePunt.getText().length() != 0 && jTextField_nomPunt.getText().length() != 0 && jTextArea_descPunt.getText().length() != 0 && jTextField_latPunt.getText().length() != 0 && jTextField_lonPunt.getText().length() != 0 && jTextField_altPunt.getText().length() != 0 && jComboBox_tipusPunt.getSelectedItem() != null) {
                            jButton_desarCanvisPunts.setEnabled(true);
                        }else{
                            jButton_desarCanvisPunts.setEnabled(false);
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

                }
                
            }
        }catch(Exception ex){
            
        }
    }//GEN-LAST:event_jComboBox_tipusPuntItemStateChanged

    private void jButton_desarCanvisPuntsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_desarCanvisPuntsMouseClicked
        
        Integer nOrdre = Integer.parseInt(jTextField_ordrePunt.getText());
        String nNomP = jTextField_nomPunt.getText();
        String nDescP = jTextArea_descPunt.getText();
        Integer nLatP = Integer.parseInt(jTextField_latPunt.getText());
        Integer nLonP = Integer.parseInt(jTextField_lonPunt.getText());
        Integer nAltP = Integer.parseInt(jTextField_altPunt.getText());
        
        Tipus nTipus = (Tipus) jComboBox_tipusPunt.getModel().getSelectedItem();
        System.out.println("NTIPUS: "+nTipus);
        
        Punt nPunt = new Punt(nNomP,nDescP,nLatP,nLonP,nAltP,nOrdre,ruta_seleccionada,nTipus);
        
        String url_foto = filePath;
        
        if(botoAfegirClicat){
            
            
            try {
                boolean puntAfegit;


                puntAfegit = gestorBDWikilocJdbc.afegirPuntRuta(nPunt, url_foto);
                

                if (puntAfegit) {
                    int resposta = JOptionPane.showConfirmDialog(null, "Estàs segur de desar els canvis?",
                            "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);

                    if (resposta == 0) {
                        gestorBDWikilocJdbc.confirmarCanvis();

                        JOptionPane.showConfirmDialog(null, "Els canvis s'han desat correctament",
                                "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);

                        try {
                            llistaPuntsRuta = gestorBDWikilocJdbc.obtenirLlistaPuntsRuta(id);

                            
                            
                                    
                            //dlm = new DefaultListModel();
                            dlm.clear();
                            System.out.println("QT PUNTS DE RUTA: "+dlm);
                            for (Punt p : llistaPuntsRuta) {

                                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

                            }
                            jList_puntsRuta.setModel(dlm);
                            jList_puntsRuta = new JList(dlm);
                            System.out.println(jList_puntsRuta.getModel().toString());

                        } catch (GestorBDWikilocException ex) {
                            Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            llistaTipusPunts = gestorBDWikilocJdbc.obtenirLlistaTipus();

                            System.out.println("LEN: " + llistaTipusPunts.size());
                            Tipus[] tip = new Tipus[llistaTipusPunts.size()];
                            tip = llistaTipusPunts.toArray(tip);

                            //jComboBox_tipusPunt = new JComboBox(tip);
                            System.out.println("ITEM COUNT: " + jComboBox_tipusPunt.getItemCount());
                            //jComboBox_tipusPunt.setSelectedIndex(-1);

                            jComboBox_tipusPunt.setModel(new DefaultComboBoxModel<>(llistaTipusPunts.toArray(new Tipus[0])));
                            jComboBox_tipusPunt.setSelectedIndex(-1);

                            for (int i = 0; i < llistaTipusPunts.size(); i++) {
                                System.out.println(tip[i]);
                            }

                        } catch (GestorBDWikilocException ex) {
                            Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                } else {
                    System.out.println("POSAR PANTALLA ERROR");//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }

            } catch (GestorBDWikilocException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
            
            
        }else{
            
            
            try {
                boolean puntEditat;

                if (url_foto != null) {
                    System.out.println("AMB FOTO");
                    puntEditat = gestorBDWikilocJdbc.editarPuntRuta(nPunt, id, url_foto);
                } else {
                    System.out.println("SENSE FOTO");
                    puntEditat = gestorBDWikilocJdbc.editarPuntRutaSenseFoto(nPunt, id);
                }

                if (puntEditat) {
                    int resposta = JOptionPane.showConfirmDialog(null, "Estàs segur de desar els canvis?",
                            "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);

                    if (resposta == 0) {
                        

                        
                        gestorBDWikilocJdbc.confirmarCanvis();

                        JOptionPane.showConfirmDialog(null, "Els canvis s'han desat correctament",
                                "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);

                        try {
                            llistaPuntsRuta = gestorBDWikilocJdbc.obtenirLlistaPuntsRuta(id);

                            //dlm = new DefaultListModel();
                            dlm.clear();
                            for (Punt p : llistaPuntsRuta) {

                                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

                            }
                            jList_puntsRuta.setModel(dlm);
                            jList_puntsRuta = new JList(dlm);
                            System.out.println(jList_puntsRuta.getModel().toString());

                        } catch (GestorBDWikilocException ex) {
                            Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            llistaTipusPunts = gestorBDWikilocJdbc.obtenirLlistaTipus();

                            System.out.println("LEN: " + llistaTipusPunts.size());
                            Tipus[] tip = new Tipus[llistaTipusPunts.size()];
                            tip = llistaTipusPunts.toArray(tip);

                            //jComboBox_tipusPunt = new JComboBox(tip);
                            System.out.println("ITEM COUNT: " + jComboBox_tipusPunt.getItemCount());
                            //jComboBox_tipusPunt.setSelectedIndex(-1);

                            jComboBox_tipusPunt.setModel(new DefaultComboBoxModel<>(llistaTipusPunts.toArray(new Tipus[0])));
                            jComboBox_tipusPunt.setSelectedIndex(-1);

                            for (int i = 0; i < llistaTipusPunts.size(); i++) {
                                System.out.println(tip[i]);
                            }

                        } catch (GestorBDWikilocException ex) {
                            Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                } else {
                    System.out.println("POSAR PANTALLA ERROR");//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }

            } catch (GestorBDWikilocException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
            
            
            try{
                
                boolean ordreEditat;
                
                ordreEditat = gestorBDWikilocJdbc.editarOrdrePuntRuta(llistaPuntsRuta);
                
                if(ordreEditat){

                    int resposta = JOptionPane.showConfirmDialog(null, "Estàs segur de desar els canvis?",
                            "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);

                    if (resposta == 0) {
                        
                        gestorBDWikilocJdbc.confirmarCanvis();

                        JOptionPane.showConfirmDialog(null, "Els canvis s'han desat correctament",
                                "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                

                
            }catch(GestorBDWikilocException ex){
                System.out.println("ERROR: " + ex.getMessage());
            }
            
            
        }
        
        botoAfegirClicat = false;
        botoPujarClicat = false;
        botoBaixarClicat = false;



        jButton_pujar.setVisible(false);
        jButton_baixar.setVisible(false);
        jButton_eliminar.setVisible(false);
        jButton_afegir.setVisible(false);
        jButton_netejar.setVisible(false);

        jButton_desarCanvisPunts.setVisible(false);
          
        
    }//GEN-LAST:event_jButton_desarCanvisPuntsMouseClicked

    private void jButton_netejarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejarMouseClicked
        
//        dlm = (DefaultListModel) jList_puntsRuta.getModel();
//        dlm.removeAllElements();
//        dlm.clear();
//        jList_puntsRuta.setModel(dlm);
//        jList_puntsRuta = new JList(dlm);
        
//        int indiceSeleccionado = jList_puntsRuta.getSelectedIndex();
//        System.out.println("indiceSeleccionado: "+indiceSeleccionado);
//
//        for (int i = dlm.getSize() - 1; i >= 0; i--) {
//            if (i == indiceSeleccionado || jList_puntsRuta.isSelectedIndex(i)) {
//                dlm.remove(i);
//                System.out.println("esborrarrrrrrrrrrrr");
//            }
//        }
        
        //jList_puntsRuta.setSelectedIndex(0);
        dlm.clear();
        //jList_puntsRuta.setModel(dlm);
        //jList_puntsRuta = new JList(dlm);
        //eliminarPuntRutaTots(id);
        
        int resposta = JOptionPane.showConfirmDialog(null, "Estàs segur d'eliminar TOTS els punts de ruta associats a la ruta?",
                            "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);

        if (resposta == 0) {
            try {
                if(gestorBDWikilocJdbc.eliminarPuntRutaTots(id)){
                    gestorBDWikilocJdbc.confirmarCanvis();
                    
                    JOptionPane.showConfirmDialog(null, "Els canvis s'han realitzat correctament",
                        "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                }
                
            } catch (GestorBDWikilocException ex) {
                JOptionPane.showConfirmDialog(null, "Error: "+ex.getMessage(),
                        "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                        JOptionPane.ERROR_MESSAGE);
            }
        
        }
        

    }//GEN-LAST:event_jButton_netejarMouseClicked

    private void jButton_eliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_eliminarMouseClicked
        
        
        int resposta = JOptionPane.showConfirmDialog(null, "Estàs segur d'esborrar el punt de ruta?",
                           "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                           JOptionPane.INFORMATION_MESSAGE);
        
        
        if (resposta == 0) {
            try {
                if(gestorBDWikilocJdbc.eliminarPuntRuta(punt_seleccionat.getNum(),id)){
                    
                        
                    gestorBDWikilocJdbc.confirmarCanvis();

                    JOptionPane.showConfirmDialog(null, "El punt de ruta s'ha esborrat correctament",
                        "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    dlm.removeElement(punt_seleccionat);
                    
                    try {
                        llistaPuntsRuta = gestorBDWikilocJdbc.obtenirLlistaPuntsRuta(id);

                        //dlm = new DefaultListModel();
                        dlm.clear();
                        for (Punt p : llistaPuntsRuta) {

                            dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

                        }
                        
                        
                        jList_puntsRuta.setModel(dlm);
                        jList_puntsRuta = new JList(dlm);
                        System.out.println(jList_puntsRuta.getModel().toString());

                    } catch (GestorBDWikilocException ex) {
                        Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    botoAfegirClicat = true;
        
                    jTextField_ordrePunt.setText("");
                    jTextField_nomPunt.setText("");
                    jTextArea_descPunt.setText("");
                    jTextField_latPunt.setText("");
                    jTextField_lonPunt.setText("");
                    jTextField_altPunt.setText("");
                    jLabel_fotoPunt.setIcon(null);//posar imatge però amb imatge de sense imatge per defecte!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    jComboBox_tipusPunt.setSelectedIndex(-1);

                    jButton_desarCanvisPunts.setVisible(true);
                    
                    
                }
                
            } catch (GestorBDWikilocException ex) {
                JOptionPane.showConfirmDialog(null, "Error: "+ex.getMessage(),
                        "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                        JOptionPane.ERROR_MESSAGE);
            }
        
        }
    }//GEN-LAST:event_jButton_eliminarMouseClicked

    private void jButton_pujarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_pujarActionPerformed
        

        int ordre_actual = punt_seleccionat.getOrdre();
        
        int selectedIndex = punt_seleccionat.getOrdre() -1;

        if (selectedIndex > 0) {
            
            punt_seleccionat.setOrdre(ordre_actual-1);
            Punt punt_adalt = llistaPuntsRuta.get(selectedIndex-1);
            punt_adalt.setOrdre(punt_adalt.getOrdre()+1);
            
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
        
        int selectedIndex = punt_seleccionat.getOrdre() -1;

        if (selectedIndex + 2 <= llistaPuntsRuta.size()) {
            
            punt_seleccionat.setOrdre(ordre_actual+1);
            Punt punt_abaix = llistaPuntsRuta.get(selectedIndex+1);
            punt_abaix.setOrdre(punt_abaix.getOrdre()-1);
            
            Collections.sort(llistaPuntsRuta);
            
            dlm.clear();
            
            for (Punt p : llistaPuntsRuta) {

                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

            }          
            
            botoBaixarClicat = true;
            modificacionsCampsPunt();
        }
        
    }//GEN-LAST:event_jButton_baixarActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        
        provaaaaa provaaaaa = new provaaaaa(llistaPuntsRuta);
        provaaaaa.setVisible(true);
        provaaaaa.setSize(new Dimension(400,400));
        provaaaaa.pack();
                

        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton_cercaFiltreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cercaFiltreMouseClicked
        
        
        //gestorBDWikilocJdbc
        
        String titol_filtre = jTextField_filtreTitol.getText().trim();
        if(titol_filtre.length()==0){
            titol_filtre = "-1";
        }
        
        Integer dific_filtre = -1;
        if(jComboBox_filtreDific.getSelectedIndex()!=-1){
            try{
                dific_filtre = Integer.parseInt((String) jComboBox_filtreDific.getSelectedItem());
            }catch(Exception ex){
                dific_filtre = -1;
            }
            
        }
        
        Double dist_filtre = Double.valueOf(-1);
        if(jTextField_filtreDist.getText().trim().length()>0){
            dist_filtre = Double.parseDouble(jTextField_filtreDist.getText().trim());
        }
                
        
        try {
            
            if(tableModel.getRowCount() > 0){
                for(int i = tableModel.getRowCount()-1; i > -1; i--){
                    tableModel.removeRow(i);
                }
            }
            
            llistaRutesCreades = gestorBDWikilocJdbc.filtreRutaCreades(titol_filtre,dific_filtre,dist_filtre,usuari_loginat.getLogin());
            System.out.println("FILTRE COMPLETAT: "+llistaRutesCreades.size());
            

            
            
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
            
            
        } catch (GestorBDWikilocException ex) {
            System.out.println("ERROR: "+ex.getMessage());
        }
        
        
    }//GEN-LAST:event_jButton_cercaFiltreMouseClicked

    private void jButton_netejarFiltreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejarFiltreMouseClicked
        
        jComboBox_filtreDific.setSelectedIndex(-1);
        jTextField_filtreDist.setText("");
        jTextField_filtreTitol.setText("");
        
        try {
            
            if(tableModel.getRowCount() > 0){
                for(int i = tableModel.getRowCount()-1; i > -1; i--){
                    tableModel.removeRow(i);
                }
            }
            
            llistaRutesCreades = gestorBDWikilocJdbc.obtenirLlistaRutaUsuari(usuari_loginat.getLogin());
            System.out.println("FILTRE COMPLETAT: "+llistaRutesCreades.size());
            
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
            
            
        } catch (GestorBDWikilocException ex) {
            System.out.println("ERROR: "+ex.getMessage());
        }
        
    }//GEN-LAST:event_jButton_netejarFiltreMouseClicked

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
    
    
    public void modificacionsCampsPunt(){
        
        if(ordre_canviat || nomPunt_canviat || descPunt_canviat || lat_canviat || lon_canviat || alt_canviat || tipusPunt_canviada || fotoPunt_canviada || botoPujarClicat || botoBaixarClicat){
            jButton_desarCanvisPunts.setEnabled(true);
            System.out.println("C");
        }else{
            jButton_desarCanvisPunts.setEnabled(false);
            System.out.println("D");
        }
        
    }
    
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        // Dibuja el gráfico de altimetría
        //if (puntos != null && puntos.size() > 1) {
            for (int i = 0; i < llistaPuntsRuta.size() - 1; i++) {
                Punt puntoActual = llistaPuntsRuta.get(i);
                Punt puntoSiguiente = llistaPuntsRuta.get(i + 1);

                int x1 = i * 10; // Ajusta la escala horizontal
                int y1 = getHeight() - (int) puntoActual.getAlt();
                int x2 = (i + 1) * 10;
                int y2 = getHeight() - (int) puntoSiguiente.getAlt();

                g.drawLine(x1, y1, x2, y2);
            }
        //}
    }
    

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_afegir;
    private javax.swing.JButton jButton_baixar;
    private javax.swing.JButton jButton_cercaFiltre;
    private javax.swing.JButton jButton_desarCanvisPunts;
    private javax.swing.JButton jButton_desarCanvisRuta;
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JButton jButton_netejar;
    private javax.swing.JButton jButton_netejarFiltre;
    private javax.swing.JButton jButton_pujar;
    private javax.swing.JButton jButton_seleccionarFoto;
    private javax.swing.JButton jButton_textRuta;
    private javax.swing.JComboBox<String> jComboBox_filtreDific;
    private javax.swing.JComboBox<String> jComboBox_tempsH;
    private javax.swing.JComboBox<String> jComboBox_tempsM;
    private javax.swing.JComboBox<Tipus> jComboBox_tipusPunt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_dificEstrella1;
    private javax.swing.JLabel jLabel_dificEstrella2;
    private javax.swing.JLabel jLabel_dificEstrella3;
    private javax.swing.JLabel jLabel_dificEstrella4;
    private javax.swing.JLabel jLabel_dificEstrella5;
    private javax.swing.JLabel jLabel_fotoPunt;
    private javax.swing.JLabel jLabel_qtatFetesAmbComentari;
    private javax.swing.JLabel jLabel_qtatNoFetesAmbComentari;
    private javax.swing.JLabel jLabel_qtatTotalComentaris;
    private javax.swing.JLabel jLabel_vDificE1;
    private javax.swing.JLabel jLabel_vDificE2;
    private javax.swing.JLabel jLabel_vDificE3;
    private javax.swing.JLabel jLabel_vDificE4;
    private javax.swing.JLabel jLabel_vDificE5;
    private javax.swing.JLabel jLabel_vInfoE1;
    private javax.swing.JLabel jLabel_vInfoE2;
    private javax.swing.JLabel jLabel_vInfoE3;
    private javax.swing.JLabel jLabel_vInfoE4;
    private javax.swing.JLabel jLabel_vInfoE5;
    private javax.swing.JLabel jLabel_vPaiE1;
    private javax.swing.JLabel jLabel_vPaiE2;
    private javax.swing.JLabel jLabel_vPaiE3;
    private javax.swing.JLabel jLabel_vPaiE4;
    private javax.swing.JLabel jLabel_vPaiE5;
    private javax.swing.JLabel jLabel_vSegE1;
    private javax.swing.JLabel jLabel_vSegE2;
    private javax.swing.JLabel jLabel_vSegE3;
    private javax.swing.JLabel jLabel_vSegE4;
    private javax.swing.JLabel jLabel_vSegE5;
    private javax.swing.JList<String> jList_puntsRuta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel_altimetria;
    private javax.swing.JPanel jPanel_compartidesCanviant;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable_rutesCreadesUsuari;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea_descPunt;
    private javax.swing.JTextArea jTextArea_descRuta;
    private javax.swing.JTextField jTextField_altPunt;
    private javax.swing.JTextField jTextField_desnN;
    private javax.swing.JTextField jTextField_desnP;
    private javax.swing.JTextField jTextField_dist;
    private javax.swing.JTextField jTextField_filtreDist;
    private javax.swing.JTextField jTextField_filtreTitol;
    private javax.swing.JTextField jTextField_latPunt;
    private javax.swing.JTextField jTextField_lonPunt;
    private javax.swing.JTextField jTextField_nomPunt;
    private javax.swing.JTextField jTextField_ordrePunt;
    private javax.swing.JTextField jTextField_titol;
    // End of variables declaration//GEN-END:variables
}
