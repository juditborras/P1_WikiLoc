/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.wikiloc.components.TextPrompt;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.shef.Demo;
import org.milaifontanals.wikiloc.jdbc.GestorBDWikilocJdbc;
import org.milaifontanals.wikiloc.model.Comentari;
import org.milaifontanals.wikiloc.model.Companys;
import org.milaifontanals.wikiloc.model.Fetes;
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
    ImageIcon estrellaBlancaMini = new ImageIcon("img"+File.separator+"estrella_blanca_mini.png");
    ImageIcon estrellaGrogaMini = new ImageIcon("img"+File.separator+"estrella_groga_mini.png");
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
    SimpleDateFormat format;
    
    ImageIcon fotoCercar = new ImageIcon("img"+File.separator+"cercar.png");
    ImageIcon fotoCercarHoover = new ImageIcon("img"+File.separator+"cercarHoover.png");
    ImageIcon fotoNetejar = new ImageIcon("img"+File.separator+"netejar.png");
    ImageIcon fotoNetejarHoover = new ImageIcon("img"+File.separator+"netejarHoover.png");
    ImageIcon fotoDesarCanvis = new ImageIcon("img"+File.separator+"desarCanvis.png");
    ImageIcon fotoDesarCanvisHoover = new ImageIcon("img"+File.separator+"desarCanvisHoover.png");
    ImageIcon fotoHtml = new ImageIcon("img"+File.separator+"html.png");
    ImageIcon fotoHtmlHoover = new ImageIcon("img"+File.separator+"htmlHoover.png");
    ImageIcon fotoEliminarPunt = new ImageIcon("img"+File.separator+"eliminarPunt.png");
    ImageIcon fotoEliminarPuntHoover = new ImageIcon("img"+File.separator+"eliminarPuntHoover.png");
    ImageIcon fotoEliminarPunts = new ImageIcon("img"+File.separator+"eliminarPunts.png");
    ImageIcon fotoEliminarPuntsHoover = new ImageIcon("img"+File.separator+"eliminarPuntsHoover.png");
    ImageIcon fotoFletxaAmunt = new ImageIcon("img"+File.separator+"fletxaAmunt.png");
    ImageIcon fotoFletxaAmuntHoover = new ImageIcon("img"+File.separator+"fletxaAmuntHoover.png");
    ImageIcon fotoFletxaAvall = new ImageIcon("img"+File.separator+"fletxaAvall.png");
    ImageIcon fotoFletxaAvallHoover = new ImageIcon("img"+File.separator+"fletxaAvallHoover.png");
    ImageIcon fotoAfegirPunt = new ImageIcon("img"+File.separator+"afegirPunt.png");
    ImageIcon fotoAfegirPuntHoover = new ImageIcon("img"+File.separator+"afegirPuntHoover.png");
    ImageIcon fotoAfegirFotoPunt = new ImageIcon("img"+File.separator+"afegirFotoPunt.png");
    ImageIcon fotoAfegirFotoPuntHoover = new ImageIcon("img"+File.separator+"afegirFotoPuntHoover.png");
    
        private static Image createDisabledIcon(Icon icon) {
        Image image = ((ImageIcon) icon).getImage();
        final int targetColor = 0x4C8C2B;

        ImageFilter filter = new RGBImageFilter() {
            @Override
            public int filterRGB(int x, int y, int rgb) {
                // Obtiene los componentes RGB del color objetivo
                int targetRed = (targetColor >> 16) & 0xFF;
                int targetGreen = (targetColor >> 8) & 0xFF;
                int targetBlue = targetColor & 0xFF;

                // Obtiene los componentes RGB del color actual
                int currentRed = (rgb >> 16) & 0xFF;
                int currentGreen = (rgb >> 8) & 0xFF;
                int currentBlue = rgb & 0xFF;

                // Combina los componentes y aplica el filtro
                int filteredRed = (currentRed + targetRed) / 2;
                int filteredGreen = (currentGreen + targetGreen) / 2;
                int filteredBlue = (currentBlue + targetBlue) / 2;

                return (rgb & 0xFF000000) | (filteredRed << 16) | (filteredGreen << 8) | filteredBlue;
            }
        };

        ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
    
    public panellCompartides(){
        
    }
    
    public panellCompartides(JPanel jPanel_menu, JPanel jPanel_principal, Usuari usuari_loginat) throws GestorBDWikilocException {
        
        UIManager.put("TabbedPane.selected", new Color(255,174,0));
        UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(new Color(255,255,255)));
        UIManager.put("TabbedPane.darkShadow", new ColorUIResource(new Color(255,255,255)));
        
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(255,255,255)));
        
        UIManager.put("List.selectionBackground", new ColorUIResource(new Color(255,174,0)));
        
        initComponents();  
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

        jTable_rutesCreadesUsuari.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        jTable_rutesCreadesUsuari.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        jTable_rutesCreadesUsuari.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        jTable_rutesCreadesUsuari.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        jTable_rutesCreadesUsuari.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        jTable_rutesCreadesUsuari.setCellSelectionEnabled(false);
        jTable_rutesCreadesUsuari.setRowSelectionAllowed(true);
        jTable_rutesCreadesUsuari.setSelectionBackground(new Color(255, 163, 0));

        jTable_rutesCreadesUsuari.setShowGrid(false);
        jTable_rutesCreadesUsuari.setIntercellSpacing(new Dimension(0, 0));

        jTable_rutesCreadesUsuari.getTableHeader().setBackground(new Color(76, 140, 43));
        jTable_rutesCreadesUsuari.getTableHeader().setForeground(Color.white);
        jTable_rutesCreadesUsuari.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 20));
        jTable_rutesCreadesUsuari.getTableHeader().setPreferredSize(new Dimension(100, 50));
        
        jComboBox_filtreDific.setSize(87, 80);
        jComboBox_filtreDific.setUI(panellCataleg.ColorArrowUI.createUI(jComboBox_filtreDific));
        jComboBox_tempsH.setSize(141, 30);
        jComboBox_tempsM.setSize(141, 30);
        jComboBox_tempsH.setUI(panellCataleg.ColorArrowUI.createUI(jComboBox_tempsH));
        jComboBox_tempsM.setUI(panellCataleg.ColorArrowUI.createUI(jComboBox_tempsM));
        jComboBox_tipusPunt.setSize(257, 33);
        jComboBox_tipusPunt.setUI(panellCataleg.ColorArrowUI.createUI(jComboBox_tipusPunt));
        
        jButton_desarCanvisRuta.setDisabledIcon(new ImageIcon(createDisabledIcon(jButton_desarCanvisRuta.getIcon())));
        jButton_desarCanvisPunts.setDisabledIcon(new ImageIcon(createDisabledIcon(jButton_desarCanvisPunts.getIcon())));

        jPanel_menu.setVisible(false);
        jLabel_missatge.setVisible(false);
      
        TextPrompt placeHolder_titol = new TextPrompt("Ascensió al Pedraforca",jTextField_filtreTitol);
        TextPrompt placeHolder_dist = new TextPrompt(" >= 201,19 km",jTextField_filtreDist);
        
        jComboBox_filtreDific.addItem("");
//        for (int i = 1; i <= 5; i++) {
//            jComboBox_filtreDific.addItem(i + "");
//        }
        jComboBox_filtreDific.addItem("★☆☆☆☆");
        jComboBox_filtreDific.addItem("★★☆☆☆");
        jComboBox_filtreDific.addItem("★★★☆☆");
        jComboBox_filtreDific.addItem("★★★★☆");
        jComboBox_filtreDific.addItem("★★★★★");
        jComboBox_filtreDific.setSelectedIndex(-1);
        
        jTabbedPane2.setBackgroundAt(0, new Color(76,140,43));
        jTabbedPane2.setBackgroundAt(1, new Color(76,140,43));
        jTabbedPane2.setBackgroundAt(2, new Color(76,140,43));
        
        JLabel lab0 = new JLabel("Detalls de la ruta");
        lab0.setPreferredSize(new Dimension(230, 35));
        lab0.setFont(new Font("Calibri", Font.BOLD, 20));
        jTabbedPane2.setTabComponentAt(0, lab0);  // tab index, jLabel
        JLabel lab1 = new JLabel("Detalls dels punts de ruta");
        lab1.setPreferredSize(new Dimension(230, 35));
        lab1.setFont(new Font("Calibri", Font.BOLD, 20));
        jTabbedPane2.setTabComponentAt(1, lab1); 
        JLabel lab2 = new JLabel("Estadístiques de la ruta");
        lab2.setPreferredSize(new Dimension(230, 35));
        lab2.setFont(new Font("Calibri", Font.BOLD, 20));
        jTabbedPane2.setTabComponentAt(2, lab2);
        
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(255,255,255)));
        
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
            
            
            if(llistaRutesCreades.size()==0){
                jLabel_missatge.setVisible(true);
                jTextField_filtreTitol.setVisible(false);
                jTextField_filtreDist.setVisible(false);
                jComboBox_filtreDific.setVisible(false);
                jButton_cercaFiltre.setVisible(false);
                jButton_netejarFiltre.setVisible(false);
                jTable_rutesCreadesUsuari.setVisible(false);
                jScrollPane1.setVisible(false);
            }else{
                jLabel_missatge.setVisible(false);
                jTextField_filtreTitol.setVisible(true);
                jTextField_filtreDist.setVisible(true);
                jComboBox_filtreDific.setVisible(true);
                jButton_cercaFiltre.setVisible(true);
                jButton_netejarFiltre.setVisible(true);
                jTable_rutesCreadesUsuari.setVisible(true);
                jScrollPane1.setVisible(true);
            }
            
            tableModel = (DefaultTableModel) jTable_rutesCreadesUsuari.getModel();
            Object rowData[] = new Object[5];
            
            format = new SimpleDateFormat("dd/MM/yyyy");
            String reempl;
            int hours, minutes;        
            int e1 =1, e2 =2, e3 =3, e4 =4, e5 =5;
            
            for (Ruta r : llistaRutesCreades) {
              
                
                Fetes f = gestorBDWikilocJdbc.haFetRuta(r, usuari_loginat);
                
                rowData[0] = format.format(f.getMt())+"         ";
                
                reempl = r.getDist()+"";
                reempl = reempl.replace('.', ',');
                
                hours = r.getTemps() / 60;
                minutes = r.getTemps() % 60;
                
                
                rowData[1] = r.getTitol();
                rowData[2] = reempl + " km";
                rowData[3] = hours + "h " + minutes + "m";
                
                
                if(r.getDific() ==1){
                    rowData[4] = "*";
                }else if(r.getDific() ==2){
                    rowData[4] = "* *";
                }else if(r.getDific() ==3){
                    rowData[4] = "* * *";
                }else if(r.getDific() ==4){
                    rowData[4] = "* * * *";
                }else if(r.getDific() ==5){
                    rowData[4] = "* * * * *";
                }
                        
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
                
                jLabel21.setVisible(false);
                jLabel22.setVisible(false);
                jLabel23.setVisible(false);
                jLabel24.setVisible(false);
                jLabel25.setVisible(false);
                jLabel26.setVisible(false);
                jLabel27.setVisible(false);
                jSeparator7.setVisible(false);
                jSeparator8.setVisible(false);
                jSeparator9.setVisible(false);
                jSeparator10.setVisible(false);
                jSeparator11.setVisible(false);

                
                try {
                    System.out.println("ID DE LA RUTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: "+ruta_seleccionada.getId());
                    llistaPuntsRuta = gestorBDWikilocJdbc.obtenirLlistaPuntsRuta(ruta_seleccionada.getId());
                    //System.out.println("================0LLISTA ED PUNTS RUTA===================================");
                    
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Punts de ruta: ");
                    for(Punt punt : llistaPuntsRuta){
                        System.out.println(punt);
                    }
                    

                    //dlm = new DefaultListModel();
                    dlm.clear();
                    //System.out.println("DESPRES DEL CLEAR: "+dlm);
                    for (Punt p : llistaPuntsRuta) {

                        dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

                    }
                    //System.out.println("DESPRES DEL ADD: "+dlm);

                    jList1.setModel(dlm);
                    //jList_puntsRuta = new JList(dlm);
                    //System.out.println(jList_puntsRuta.getModel().toString());
                    
                    //System.out.println("LLISTA DE PUNTS DE RUTA ACTUALITZAT");
                    //jList1.revalidate();
                    //jList1.repaint();
                    jList1.updateUI();
                    
                    SwingUtilities.getWindowAncestor(jPanel_compartidesCanviant).revalidate();
                    SwingUtilities.getWindowAncestor(jPanel_compartidesCanviant).repaint();


                } catch (GestorBDWikilocException ex) {
                    System.out.println("ERROR LLISTA PUNTS DE RUTA: "+ex.getMessage());
                    Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                /*
                try {
                    System.out.println("ID DE LA RUTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: "+ruta_seleccionada.getId());
                    llistaPuntsRuta = gestorBDWikilocJdbc.obtenirLlistaPuntsRuta(ruta_seleccionada.getId());
                    System.out.println("================0LLISTA ED PUNTS RUTA===================================");
                    for(Punt punt : llistaPuntsRuta){
                        System.out.println(punt);
                    }

                    //dlm = new DefaultListModel();
                    dlm.clear();
                    System.out.println("DESPRES DEL CLEAR: "+dlm);
                    for (Punt p : llistaPuntsRuta) {

                        dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

                    }
                    System.out.println("DESPRES DEL ADD: "+dlm);

                    jList_puntsRuta.setModel(dlm);
                    jList_puntsRuta = null;
                    jList_puntsRuta = new JList(dlm);
                    //jList_puntsRuta = new JList(dlm);
                    System.out.println(jList_puntsRuta.getModel().toString());
                    
                    System.out.println("LLISTA DE PUNTS DE RUTA ACTUALITZAT");
                    jList_puntsRuta.revalidate();
                    jList_puntsRuta.repaint();
                    
                    JScrollPane scrollPane = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, jList_puntsRuta);
                    if (scrollPane != null) {
                        scrollPane.revalidate();
                        scrollPane.repaint();
                    }
                   
                    


                } catch (GestorBDWikilocException ex) {
                    System.out.println("ERROR LLISTA PUNTS DE RUTA: "+ex.getMessage());
                    Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
                }
                */
                


                try {
                    llistaTipusPunts = gestorBDWikilocJdbc.obtenirLlistaTipus();

                    //System.out.println("LEN: " + llistaTipusPunts.size());
                    Tipus[] tip = new Tipus[llistaTipusPunts.size()];
                    tip = llistaTipusPunts.toArray(tip);

                    //jComboBox_tipusPunt = new JComboBox(tip);
                    //System.out.println("ITEM COUNT: " + jComboBox_tipusPunt.getItemCount());
                    //jComboBox_tipusPunt.setSelectedIndex(-1);

                    jComboBox_tipusPunt.setModel(new DefaultComboBoxModel<>(llistaTipusPunts.toArray(new Tipus[0])));
                    jComboBox_tipusPunt.setSelectedIndex(-1);

                    for (int i = 0; i < llistaTipusPunts.size(); i++) {
                        //System.out.println(tip[i]);
                    }
                    //System.out.println("COMBO TIPUS PUNTS ACTUALITZAT");
                } catch (GestorBDWikilocException ex) {
                    System.out.println("ERROR COMBO TIPUS PUNTS "+ex.getMessage());
                    Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
                }
                
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
                
                jLabel21.setVisible(false);
                jLabel22.setVisible(false);
                jLabel23.setVisible(false);
                jLabel24.setVisible(false);
                jLabel25.setVisible(false);
                jLabel26.setVisible(false);
                jLabel27.setVisible(false);
                jSeparator7.setVisible(false);
                jSeparator8.setVisible(false);
                jSeparator9.setVisible(false);
                jSeparator10.setVisible(false);
                jSeparator11.setVisible(false);
                
                try {
                    llistaPuntsRuta = gestorBDWikilocJdbc.obtenirLlistaPuntsRuta(id);

                    //dlm = new DefaultListModel();
                    dlm.clear();
                    for (Punt p : llistaPuntsRuta) {

                        dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

                    }

                    //jList_puntsRuta.setModel(dlm);
                    //jList_puntsRuta = new JList(dlm);
                    //System.out.println(jList_puntsRuta.getModel().toString());

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

        String reempl = (llistaRutesCreades.get(row).getDist()+"").replace('.', ',');
        jTextField_dist.setText(reempl);
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
            //jList_puntsRuta.setModel(dlm);
            //jList_puntsRuta = new JList(dlm);
            //System.out.println(jList_puntsRuta.getModel().toString());
            
          
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
            //jLabel_qtatTotalComentaris.setText(qtatComentarisRuta+"");
            jLabel7.setText(qtatComentarisRuta+"");
            int qtatComentarisRutaFeta = gestorBDWikilocJdbc.qtatComentarisRutaFeta(id);
            jLabel_qtatFetesAmbComentari.setText(qtatComentarisRutaFeta+"");
            
            int qtatComentarisRutaNoFeta = gestorBDWikilocJdbc.qtatComentarisRutaNoFeta(id);
            //jLabel_qtatNoFetesAmbComentari.setText(qtatComentarisRutaNoFeta+"");
            
            double mitjaVinf = gestorBDWikilocJdbc.mitjaVinf(id);
            if(mitjaVinf >= 0.0 && mitjaVinf < 0.5){
                //buides
                jLabel_vInfoE1.setIcon(estrellaBlancaMini);
                jLabel_vInfoE2.setIcon(estrellaBlancaMini);
                jLabel_vInfoE3.setIcon(estrellaBlancaMini);
                jLabel_vInfoE4.setIcon(estrellaBlancaMini);
                jLabel_vInfoE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVinf >= 0.5 && mitjaVinf < 1.5){
                //estrella 1
                jLabel_vInfoE1.setIcon(estrellaGrogaMini);
                jLabel_vInfoE2.setIcon(estrellaBlancaMini);
                jLabel_vInfoE3.setIcon(estrellaBlancaMini);
                jLabel_vInfoE4.setIcon(estrellaBlancaMini);
                jLabel_vInfoE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVinf >= 1.5 && mitjaVinf < 2.5){
                //estrella 2
                jLabel_vInfoE1.setIcon(estrellaGrogaMini);
                jLabel_vInfoE2.setIcon(estrellaGrogaMini);
                jLabel_vInfoE3.setIcon(estrellaBlancaMini);
                jLabel_vInfoE4.setIcon(estrellaBlancaMini);
                jLabel_vInfoE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVinf >= 2.5 && mitjaVinf < 3.5){
                //estrella 3
                jLabel_vInfoE1.setIcon(estrellaGrogaMini);
                jLabel_vInfoE2.setIcon(estrellaGrogaMini);
                jLabel_vInfoE3.setIcon(estrellaGrogaMini);
                jLabel_vInfoE4.setIcon(estrellaBlancaMini);
                jLabel_vInfoE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVinf >= 3.5 && mitjaVinf < 4.5){
                //estrella 4
                jLabel_vInfoE1.setIcon(estrellaGrogaMini);
                jLabel_vInfoE2.setIcon(estrellaGrogaMini);
                jLabel_vInfoE3.setIcon(estrellaGrogaMini);
                jLabel_vInfoE4.setIcon(estrellaGrogaMini);
                jLabel_vInfoE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVinf >= 4.5 && mitjaVinf <= 5.0){
                //estrella 5
                jLabel_vInfoE1.setIcon(estrellaGrogaMini);
                jLabel_vInfoE2.setIcon(estrellaGrogaMini);
                jLabel_vInfoE3.setIcon(estrellaGrogaMini);
                jLabel_vInfoE4.setIcon(estrellaGrogaMini);
                jLabel_vInfoE5.setIcon(estrellaGrogaMini);
            }
            
            double mitjaVseg = gestorBDWikilocJdbc.mitjaVseg(id);
            if(mitjaVseg >= 0.0 && mitjaVseg < 0.5){
                //buides
                jLabel_vSegE1.setIcon(estrellaBlancaMini);
                jLabel_vSegE2.setIcon(estrellaBlancaMini);
                jLabel_vSegE3.setIcon(estrellaBlancaMini);
                jLabel_vSegE4.setIcon(estrellaBlancaMini);
                jLabel_vSegE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVseg >= 0.5 && mitjaVseg < 1.5){
                //estrella 1
                jLabel_vSegE1.setIcon(estrellaGrogaMini);
                jLabel_vSegE2.setIcon(estrellaBlancaMini);
                jLabel_vSegE3.setIcon(estrellaBlancaMini);
                jLabel_vSegE4.setIcon(estrellaBlancaMini);
                jLabel_vSegE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVseg >= 1.5 && mitjaVseg < 2.5){
                //estrella 2
                jLabel_vSegE1.setIcon(estrellaGrogaMini);
                jLabel_vSegE2.setIcon(estrellaGrogaMini);
                jLabel_vSegE3.setIcon(estrellaBlancaMini);
                jLabel_vSegE4.setIcon(estrellaBlancaMini);
                jLabel_vSegE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVseg >= 2.5 && mitjaVseg < 3.5){
                //estrella 3
                jLabel_vSegE1.setIcon(estrellaGrogaMini);
                jLabel_vSegE2.setIcon(estrellaGrogaMini);
                jLabel_vSegE3.setIcon(estrellaGrogaMini);
                jLabel_vSegE4.setIcon(estrellaBlancaMini);
                jLabel_vSegE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVseg >= 3.5 && mitjaVseg < 4.5){
                //estrella 4
                jLabel_vSegE1.setIcon(estrellaGrogaMini);
                jLabel_vSegE2.setIcon(estrellaGrogaMini);
                jLabel_vSegE3.setIcon(estrellaGrogaMini);
                jLabel_vSegE4.setIcon(estrellaGrogaMini);
                jLabel_vSegE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVseg >= 4.5 && mitjaVseg <= 5.0){
                //estrella 5
                jLabel_vSegE1.setIcon(estrellaGrogaMini);
                jLabel_vSegE2.setIcon(estrellaGrogaMini);
                jLabel_vSegE3.setIcon(estrellaGrogaMini);
                jLabel_vSegE4.setIcon(estrellaGrogaMini);
                jLabel_vSegE5.setIcon(estrellaGrogaMini);
            }
            
            double mitjaVpai = gestorBDWikilocJdbc.mitjaVpai(id);
            if(mitjaVpai >= 0.0 && mitjaVpai < 0.5){
                //buides
                jLabel_vPaiE1.setIcon(estrellaBlancaMini);
                jLabel_vPaiE2.setIcon(estrellaBlancaMini);
                jLabel_vPaiE3.setIcon(estrellaBlancaMini);
                jLabel_vPaiE4.setIcon(estrellaBlancaMini);
                jLabel_vPaiE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVpai >= 0.5 && mitjaVpai < 1.5){
                //estrella 1
                jLabel_vPaiE1.setIcon(estrellaGrogaMini);
                jLabel_vPaiE2.setIcon(estrellaBlancaMini);
                jLabel_vPaiE3.setIcon(estrellaBlancaMini);
                jLabel_vPaiE4.setIcon(estrellaBlancaMini);
                jLabel_vPaiE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVpai >= 1.5 && mitjaVpai < 2.5){
                //estrella 2
                jLabel_vPaiE1.setIcon(estrellaGrogaMini);
                jLabel_vPaiE2.setIcon(estrellaGrogaMini);
                jLabel_vPaiE3.setIcon(estrellaBlancaMini);
                jLabel_vPaiE4.setIcon(estrellaBlancaMini);
                jLabel_vPaiE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVpai >= 2.5 && mitjaVpai < 3.5){
                //estrella 3
                jLabel_vPaiE1.setIcon(estrellaGrogaMini);
                jLabel_vPaiE2.setIcon(estrellaGrogaMini);
                jLabel_vPaiE3.setIcon(estrellaGrogaMini);
                jLabel_vPaiE4.setIcon(estrellaBlancaMini);
                jLabel_vPaiE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVpai >= 3.5 && mitjaVpai < 4.5){
                //estrella 4
                jLabel_vPaiE1.setIcon(estrellaGrogaMini);
                jLabel_vPaiE2.setIcon(estrellaGrogaMini);
                jLabel_vPaiE3.setIcon(estrellaGrogaMini);
                jLabel_vPaiE4.setIcon(estrellaGrogaMini);
                jLabel_vPaiE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVpai >= 4.5 && mitjaVpai <= 5.0){
                //estrella 5
                jLabel_vPaiE1.setIcon(estrellaGrogaMini);
                jLabel_vPaiE2.setIcon(estrellaGrogaMini);
                jLabel_vPaiE3.setIcon(estrellaGrogaMini);
                jLabel_vPaiE4.setIcon(estrellaGrogaMini);
                jLabel_vPaiE5.setIcon(estrellaGrogaMini);
            }
            
            double mitjaVdific = gestorBDWikilocJdbc.mitjaVdific(id);
            if(mitjaVdific >= 0.0 && mitjaVdific < 0.5){
                //buides
                jLabel_vDificE1.setIcon(estrellaBlancaMini);
                jLabel_vDificE2.setIcon(estrellaBlancaMini);
                jLabel_vDificE3.setIcon(estrellaBlancaMini);
                jLabel_vDificE4.setIcon(estrellaBlancaMini);
                jLabel_vDificE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVdific >= 0.5 && mitjaVdific < 1.5){
                //estrella 1
                jLabel_vDificE1.setIcon(estrellaGrogaMini);
                jLabel_vDificE2.setIcon(estrellaBlancaMini);
                jLabel_vDificE3.setIcon(estrellaBlancaMini);
                jLabel_vDificE4.setIcon(estrellaBlancaMini);
                jLabel_vDificE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVdific >= 1.5 && mitjaVdific < 2.5){
                //estrella 2
                jLabel_vDificE1.setIcon(estrellaGrogaMini);
                jLabel_vDificE2.setIcon(estrellaGrogaMini);
                jLabel_vDificE3.setIcon(estrellaBlancaMini);
                jLabel_vDificE4.setIcon(estrellaBlancaMini);
                jLabel_vDificE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVdific >= 2.5 && mitjaVdific < 3.5){
                //estrella 3
                jLabel_vDificE1.setIcon(estrellaGrogaMini);
                jLabel_vDificE2.setIcon(estrellaGrogaMini);
                jLabel_vDificE3.setIcon(estrellaGrogaMini);
                jLabel_vDificE4.setIcon(estrellaBlancaMini);
                jLabel_vDificE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVdific >= 3.5 && mitjaVdific < 4.5){
                //estrella 4
                jLabel_vDificE1.setIcon(estrellaGrogaMini);
                jLabel_vDificE2.setIcon(estrellaGrogaMini);
                jLabel_vDificE3.setIcon(estrellaGrogaMini);
                jLabel_vDificE4.setIcon(estrellaGrogaMini);
                jLabel_vDificE5.setIcon(estrellaBlancaMini);
            }else if(mitjaVdific >= 4.5 && mitjaVdific <= 5.0){
                //estrella 5
                jLabel_vDificE1.setIcon(estrellaGrogaMini);
                jLabel_vDificE2.setIcon(estrellaGrogaMini);
                jLabel_vDificE3.setIcon(estrellaGrogaMini);
                jLabel_vDificE4.setIcon(estrellaGrogaMini);
                jLabel_vDificE5.setIcon(estrellaGrogaMini);
            }
            
            
            List<Comentari> llistaComentaris = gestorBDWikilocJdbc.obtenirLlistaComentaris(id);
            
            String info_comentari = "";
            int i1=1,i2=2,i3=3,i4=4,i5=5;
            String i = "";
            
            jTextPane1.setContentType("text/html");
            
            jTextPane1.setPreferredSize(new Dimension(610, 253));
            jTextPane1.setMinimumSize(new Dimension(610, 253));
            jTextPane1.setMaximumSize(new Dimension(610, 253));
            jScrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            jScrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            
            for(Comentari c : llistaComentaris){
                
                if(c.getVinf()==1 || c.getVseg()==1 || c.getVpai()==1 || c.getDific()==1){
                    i = "★☆☆☆☆";
                }else if(c.getVinf()==2 || c.getVseg()==2 || c.getVpai()==2 || c.getDific()==2){
                    i = "★★☆☆☆";
                }else if(c.getVinf()==3 || c.getVseg()==3 || c.getVpai()==3 || c.getDific()==3){
                    i = "★★★☆☆";
                }else if(c.getVinf()==4 || c.getVseg()==4 || c.getVpai()==4 || c.getDific()==4){
                    i = "★★★★☆";
                }else if(c.getVinf()==5 || c.getVseg()==5 || c.getVpai()==5 || c.getDific()==5){
                    i = "★★★★★";
                }                               
                
                
                info_comentari += "<font size='20' face='Calibri'><b><font color='green'>" + c.getLoginUsuari().getLogin().toUpperCase() + "</font></b> ha comentat:</font><br>";
                info_comentari += "<font size='12px' face='Calibri'><i>" + c.getText() +"</i></font><br><br>";
                info_comentari += "<font face='Calibri'>Valoracions de l'usuari:</font><br>";
                info_comentari += "<font face='Calibri'><ul><li>Informació: " + i +"</li>";
                
                
                if(c.getFeta()){
                    info_comentari += "<li>Completada: Sí</li>";
                    info_comentari += "<li>Seguiment: " + i + "</li>\n";
                    info_comentari += "<li>Paisatge: " + i + "</li>\n";
                    info_comentari += "<li>Dificultat: " + i + "</li></ul></font>";
                    info_comentari += "<font face='Calibri'>Data en què es va completar la ruta: " + format.format(c.getMt()) + "</font><br>";
                }else{
                    info_comentari += "<li>Completada: No</li><br>";
                }
                
                
                Companys company = gestorBDWikilocJdbc.obtenirCompany(c.getId());
                if(company != null){
                    info_comentari += "<font face='Calibri'>Company de ruta: " + company.getLoginUsuari().getLogin().toUpperCase() +"</font><br>";
                }
                
                info_comentari += "<br>";
            }
            //System.out.println("INFO COMENTARI: "+info_comentari);            
            
            jTextPane1.setText(info_comentari);
            
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
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField_filtreTitol = new javax.swing.JTextField();
        jTextField_filtreDist = new javax.swing.JTextField();
        jComboBox_filtreDific = new javax.swing.JComboBox<>();
        jButton_cercaFiltre = new javax.swing.JButton();
        jButton_netejarFiltre = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_rutesCreadesUsuari = new javax.swing.JTable();
        jLabel_missatge = new javax.swing.JLabel();
        jPanel_compartidesCanviant = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jTextField_titol = new javax.swing.JTextField();
        jButton_textRuta = new javax.swing.JButton();
        jLabel_dificEstrella1 = new javax.swing.JLabel();
        jLabel_dificEstrella2 = new javax.swing.JLabel();
        jLabel_dificEstrella3 = new javax.swing.JLabel();
        jLabel_dificEstrella4 = new javax.swing.JLabel();
        jLabel_dificEstrella5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_descRuta = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField_dist = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jTextField_desnP = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jTextField_desnN = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jComboBox_tempsH = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jComboBox_tempsM = new javax.swing.JComboBox<>();
        jButton_desarCanvisRuta = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton_eliminar = new javax.swing.JButton();
        jButton_netejar = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton_afegir = new javax.swing.JButton();
        jButton_pujar = new javax.swing.JButton();
        jButton_baixar = new javax.swing.JButton();
        jButton_desarCanvisPunts = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextField_ordrePunt = new javax.swing.JTextField();
        jTextField_nomPunt = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea_descPunt = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        jTextField_latPunt = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField_lonPunt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField_altPunt = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jLabel_fotoPunt = new javax.swing.JLabel();
        jButton_seleccionarFoto = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jComboBox_tipusPunt = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel_qtatFetesAmbComentari = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel_vInfoE1 = new javax.swing.JLabel();
        jLabel_vInfoE2 = new javax.swing.JLabel();
        jLabel_vInfoE3 = new javax.swing.JLabel();
        jLabel_vInfoE4 = new javax.swing.JLabel();
        jLabel_vInfoE5 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_vSegE1 = new javax.swing.JLabel();
        jLabel_vSegE2 = new javax.swing.JLabel();
        jLabel_vSegE3 = new javax.swing.JLabel();
        jLabel_vSegE4 = new javax.swing.JLabel();
        jLabel_vSegE5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel_vPaiE1 = new javax.swing.JLabel();
        jLabel_vPaiE2 = new javax.swing.JLabel();
        jLabel_vPaiE3 = new javax.swing.JLabel();
        jLabel_vPaiE4 = new javax.swing.JLabel();
        jLabel_vPaiE5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel_vDificE1 = new javax.swing.JLabel();
        jLabel_vDificE2 = new javax.swing.JLabel();
        jLabel_vDificE3 = new javax.swing.JLabel();
        jLabel_vDificE4 = new javax.swing.JLabel();
        jLabel_vDificE5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Rutes creades per l'usuari");

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField_filtreTitol.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_filtreTitol.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_filtreTitol.setBorder(null);

        jTextField_filtreDist.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_filtreDist.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_filtreDist.setBorder(null);
        jTextField_filtreDist.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_filtreDistKeyTyped(evt);
            }
        });

        jComboBox_filtreDific.setForeground(new java.awt.Color(204, 204, 204));
        jComboBox_filtreDific.setBorder(null);

        jButton_cercaFiltre.setBackground(new java.awt.Color(76, 140, 43));
        jButton_cercaFiltre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/cercar.png"))); // NOI18N
        jButton_cercaFiltre.setBorder(null);
        jButton_cercaFiltre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_cercaFiltre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_cercaFiltreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_cercaFiltreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_cercaFiltreMouseExited(evt);
            }
        });

        jButton_netejarFiltre.setBackground(new java.awt.Color(76, 140, 43));
        jButton_netejarFiltre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/netejar.png"))); // NOI18N
        jButton_netejarFiltre.setBorder(null);
        jButton_netejarFiltre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_netejarFiltre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_netejarFiltreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_netejarFiltreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_netejarFiltreMouseExited(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(76, 140, 43));

        jTable_rutesCreadesUsuari.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTable_rutesCreadesUsuari.setForeground(new java.awt.Color(153, 153, 153));
        jTable_rutesCreadesUsuari.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data de realització", "Títol", "Distància", "Durada", "Dificultat", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_rutesCreadesUsuari.setColumnSelectionAllowed(true);
        jTable_rutesCreadesUsuari.setRowHeight(55);
        jTable_rutesCreadesUsuari.getTableHeader().setReorderingAllowed(false);
        jTable_rutesCreadesUsuari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_rutesCreadesUsuariMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_rutesCreadesUsuari);
        jTable_rutesCreadesUsuari.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jTable_rutesCreadesUsuari.getColumnModel().getColumnCount() > 0) {
            jTable_rutesCreadesUsuari.getColumnModel().getColumn(0).setMinWidth(190);
            jTable_rutesCreadesUsuari.getColumnModel().getColumn(0).setMaxWidth(190);
            jTable_rutesCreadesUsuari.getColumnModel().getColumn(1).setMinWidth(300);
            jTable_rutesCreadesUsuari.getColumnModel().getColumn(1).setMaxWidth(300);
            jTable_rutesCreadesUsuari.getColumnModel().getColumn(2).setMinWidth(150);
            jTable_rutesCreadesUsuari.getColumnModel().getColumn(2).setMaxWidth(150);
            jTable_rutesCreadesUsuari.getColumnModel().getColumn(3).setMinWidth(150);
            jTable_rutesCreadesUsuari.getColumnModel().getColumn(3).setMaxWidth(150);
            jTable_rutesCreadesUsuari.getColumnModel().getColumn(4).setMinWidth(150);
            jTable_rutesCreadesUsuari.getColumnModel().getColumn(4).setMaxWidth(150);
            jTable_rutesCreadesUsuari.getColumnModel().getColumn(5).setMinWidth(200);
            jTable_rutesCreadesUsuari.getColumnModel().getColumn(5).setMaxWidth(200);
        }

        jLabel_missatge.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel_missatge.setForeground(new java.awt.Color(153, 153, 153));
        jLabel_missatge.setText("ENCARA NO HAS COMPARTIT CAP RUTA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField_filtreTitol, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_filtreDist, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2))
                        .addGap(97, 97, 97)
                        .addComponent(jComboBox_filtreDific, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jButton_cercaFiltre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_netejarFiltre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(381, 381, 381)
                .addComponent(jLabel_missatge, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton_cercaFiltre, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_netejarFiltre, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_filtreDist, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_filtreTitol, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox_filtreDific, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_missatge)
                .addContainerGap())
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

        jTextField_titol.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_titol.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_titol.setBorder(null);
        jTextField_titol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_titolKeyReleased(evt);
            }
        });

        jButton_textRuta.setBackground(new java.awt.Color(76, 140, 43));
        jButton_textRuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/html.png"))); // NOI18N
        jButton_textRuta.setBorder(null);
        jButton_textRuta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_textRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_textRutaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_textRutaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_textRutaMouseExited(evt);
            }
        });
        jButton_textRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_textRutaActionPerformed(evt);
            }
        });

        jLabel_dificEstrella1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_groga.png"))); // NOI18N
        jLabel_dificEstrella1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_dificEstrella1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella1MouseEntered(evt);
            }
        });

        jLabel_dificEstrella2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_dificEstrella2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_dificEstrella2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella2MouseEntered(evt);
            }
        });

        jLabel_dificEstrella3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_dificEstrella3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_dificEstrella3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella3MouseEntered(evt);
            }
        });

        jLabel_dificEstrella4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_dificEstrella4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_dificEstrella4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella4MouseEntered(evt);
            }
        });

        jLabel_dificEstrella5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_dificEstrella5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_dificEstrella5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_dificEstrella5MouseEntered(evt);
            }
        });

        jTextArea_descRuta.setColumns(20);
        jTextArea_descRuta.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextArea_descRuta.setForeground(new java.awt.Color(153, 153, 153));
        jTextArea_descRuta.setRows(5);
        jTextArea_descRuta.setBorder(null);
        jTextArea_descRuta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea_descRutaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea_descRuta);

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel13.setText("Títol:");

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel14.setText("Descripció:");

        jSeparator3.setForeground(new java.awt.Color(76, 140, 43));

        jLabel18.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel18.setText("Dificultat:");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel15.setText("Distància:");

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel16.setText("Desnivell (+) :");

        jLabel17.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel17.setText("Desnivell (-) :");

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

        jSeparator4.setForeground(new java.awt.Color(76, 140, 43));

        jTextField_desnP.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_desnP.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_desnP.setBorder(null);
        jTextField_desnP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_desnPKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_desnPKeyTyped(evt);
            }
        });

        jSeparator5.setForeground(new java.awt.Color(76, 140, 43));

        jTextField_desnN.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_desnN.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_desnN.setBorder(null);
        jTextField_desnN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_desnNKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_desnNKeyTyped(evt);
            }
        });

        jSeparator6.setForeground(new java.awt.Color(76, 140, 43));

        jComboBox_tempsH.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jComboBox_tempsH.setForeground(new java.awt.Color(204, 204, 204));
        jComboBox_tempsH.setBorder(null);
        jComboBox_tempsH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tempsHItemStateChanged(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel19.setText("Hores:");

        jLabel20.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel20.setText("Minuts:");

        jComboBox_tempsM.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jComboBox_tempsM.setForeground(new java.awt.Color(204, 204, 204));
        jComboBox_tempsM.setBorder(null);
        jComboBox_tempsM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tempsMItemStateChanged(evt);
            }
        });

        jButton_desarCanvisRuta.setBackground(new java.awt.Color(76, 140, 43));
        jButton_desarCanvisRuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/desarCanvis.png"))); // NOI18N
        jButton_desarCanvisRuta.setBorder(null);
        jButton_desarCanvisRuta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_desarCanvisRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_desarCanvisRutaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_desarCanvisRutaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_desarCanvisRutaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_desnP)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator5)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6)
                            .addComponent(jTextField_desnN, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)))
                    .addComponent(jTextField_dist))
                .addGap(95, 95, 95)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox_tempsM, 0, 141, Short.MAX_VALUE)
                            .addComponent(jComboBox_tempsH, 0, 141, Short.MAX_VALUE)))
                    .addComponent(jButton_desarCanvisRuta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jComboBox_tempsH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_tempsM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(89, 89, 89)
                        .addComponent(jButton_desarCanvisRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(168, 168, 168)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField_desnN, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField_dist, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField_desnP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel_dificEstrella1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_dificEstrella2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_dificEstrella3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_dificEstrella4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_dificEstrella5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_textRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3)
                    .addComponent(jTextField_titol)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_titol, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_dificEstrella1)
                                        .addComponent(jLabel_dificEstrella2)
                                        .addComponent(jLabel_dificEstrella3)
                                        .addComponent(jLabel_dificEstrella4)
                                        .addComponent(jLabel_dificEstrella5))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton_textRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab1", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton_eliminar.setBackground(new java.awt.Color(76, 140, 43));
        jButton_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/eliminarPunt.png"))); // NOI18N
        jButton_eliminar.setToolTipText("Eliminar el punt de ruta seleccionat");
        jButton_eliminar.setBorder(null);
        jButton_eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_eliminarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_eliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_eliminarMouseExited(evt);
            }
        });

        jButton_netejar.setBackground(new java.awt.Color(76, 140, 43));
        jButton_netejar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/eliminarPunts.png"))); // NOI18N
        jButton_netejar.setToolTipText("Eliminar tots els punts de ruta");
        jButton_netejar.setBorder(null);
        jButton_netejar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_netejar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_netejarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_netejarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_netejarMouseExited(evt);
            }
        });

        jList1.setBorder(null);
        jList1.setFont(new java.awt.Font("Calibri", 2, 20)); // NOI18N
        jList1.setForeground(new java.awt.Color(153, 153, 153));
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane6.setViewportView(jList1);

        jButton_afegir.setBackground(new java.awt.Color(76, 140, 43));
        jButton_afegir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/afegirPunt.png"))); // NOI18N
        jButton_afegir.setToolTipText("Afegir nou punt de ruta");
        jButton_afegir.setBorder(null);
        jButton_afegir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_afegir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_afegirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_afegirMouseExited(evt);
            }
        });
        jButton_afegir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_afegirActionPerformed(evt);
            }
        });

        jButton_pujar.setBackground(new java.awt.Color(76, 140, 43));
        jButton_pujar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/fletxaAmunt.png"))); // NOI18N
        jButton_pujar.setToolTipText("Canviar ascendentment l'ordre del punt de ruta");
        jButton_pujar.setBorder(null);
        jButton_pujar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_pujar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_pujarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_pujarMouseExited(evt);
            }
        });
        jButton_pujar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_pujarActionPerformed(evt);
            }
        });

        jButton_baixar.setBackground(new java.awt.Color(76, 140, 43));
        jButton_baixar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/fletxaAvall.png"))); // NOI18N
        jButton_baixar.setToolTipText("Canviar descendentment l'ordre del punt de ruta");
        jButton_baixar.setBorder(null);
        jButton_baixar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_baixar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_baixarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_baixarMouseExited(evt);
            }
        });
        jButton_baixar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_baixarActionPerformed(evt);
            }
        });

        jButton_desarCanvisPunts.setBackground(new java.awt.Color(76, 140, 43));
        jButton_desarCanvisPunts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/desarCanvis.png"))); // NOI18N
        jButton_desarCanvisPunts.setBorder(null);
        jButton_desarCanvisPunts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_desarCanvisPunts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_desarCanvisPuntsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_desarCanvisPuntsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_desarCanvisPuntsMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton_netejar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton_desarCanvisPunts, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_afegir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_pujar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_baixar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton_desarCanvisPunts, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_netejar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton_pujar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_baixar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_afegir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel21.setText("Número:");

        jTextField_ordrePunt.setEditable(false);
        jTextField_ordrePunt.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_ordrePunt.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_ordrePunt.setBorder(null);
        jTextField_ordrePunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_ordrePuntKeyReleased(evt);
            }
        });

        jTextField_nomPunt.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_nomPunt.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_nomPunt.setBorder(null);
        jTextField_nomPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_nomPuntKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel22.setText("Nom:");

        jLabel23.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel23.setText("Descripció:");

        jTextArea_descPunt.setColumns(20);
        jTextArea_descPunt.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextArea_descPunt.setForeground(new java.awt.Color(153, 153, 153));
        jTextArea_descPunt.setRows(5);
        jTextArea_descPunt.setBorder(null);
        jTextArea_descPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea_descPuntKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTextArea_descPunt);

        jLabel24.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel24.setText("Latitud:");

        jTextField_latPunt.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_latPunt.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_latPunt.setBorder(null);
        jTextField_latPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_latPuntKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_latPuntKeyTyped(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel25.setText("Longitud:");

        jTextField_lonPunt.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_lonPunt.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_lonPunt.setBorder(null);
        jTextField_lonPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_lonPuntKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_lonPuntKeyTyped(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel26.setText("Altitud:");

        jTextField_altPunt.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_altPunt.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_altPunt.setBorder(null);
        jTextField_altPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_altPuntKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_altPuntKeyTyped(evt);
            }
        });

        jSeparator7.setForeground(new java.awt.Color(76, 140, 43));

        jSeparator8.setForeground(new java.awt.Color(76, 140, 43));

        jSeparator9.setForeground(new java.awt.Color(76, 140, 43));

        jSeparator10.setForeground(new java.awt.Color(76, 140, 43));

        jSeparator11.setForeground(new java.awt.Color(76, 140, 43));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(120, 120, 120))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jTextField_ordrePunt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22)
                                    .addGap(19, 19, 19)))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField_nomPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jSeparator8)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_latPunt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator10)
                            .addComponent(jTextField_lonPunt, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator11)
                            .addComponent(jTextField_altPunt, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField_ordrePunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_nomPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_latPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jTextField_lonPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField_altPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jButton_seleccionarFoto.setBackground(new java.awt.Color(76, 140, 43));
        jButton_seleccionarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/afegirFotoPunt.png"))); // NOI18N
        jButton_seleccionarFoto.setToolTipText("Carregar fotografía del punt de ruta");
        jButton_seleccionarFoto.setBorder(null);
        jButton_seleccionarFoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_seleccionarFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_seleccionarFotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_seleccionarFotoMouseExited(evt);
            }
        });
        jButton_seleccionarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_seleccionarFotoActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel27.setText("Tipus:");

        jComboBox_tipusPunt.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jComboBox_tipusPunt.setForeground(new java.awt.Color(153, 153, 153));
        jComboBox_tipusPunt.setBorder(null);
        jComboBox_tipusPunt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tipusPuntItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_tipusPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_seleccionarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_fotoPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(168, 168, 168))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_fotoPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton_seleccionarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jComboBox_tipusPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab2", jPanel7);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel8.setText("comentaris són d'usuaris que han completat la ruta.");

        jLabel_qtatFetesAmbComentari.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel_qtatFetesAmbComentari.setForeground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel1.setText("/");

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));

        jSeparator12.setForeground(new java.awt.Color(76, 140, 43));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Valoracions rebudes");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel3.setText("Informació:");

        jLabel_vInfoE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vInfoE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vInfoE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vInfoE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vInfoE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel5.setText("Seguiment:");

        jLabel_vSegE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vSegE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vSegE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vSegE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vSegE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel6.setText("Paisatge:");

        jLabel_vPaiE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vPaiE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vPaiE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vPaiE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vPaiE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel11.setText("Dificultat:");

        jLabel_vDificE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vDificE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vDificE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vDificE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_vDificE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel_vInfoE1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vInfoE2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vInfoE3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vInfoE4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vInfoE5))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel_vSegE1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vSegE2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vSegE3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vSegE4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vSegE5)))
                        .addGap(117, 117, 117)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel_vPaiE1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vPaiE2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vPaiE3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vPaiE4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vPaiE5))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel_vDificE1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vDificE2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vDificE3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vDificE4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_vDificE5))))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel_qtatFetesAmbComentari, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator13))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_qtatFetesAmbComentari, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel_vInfoE1)
                    .addComponent(jLabel_vInfoE2)
                    .addComponent(jLabel_vInfoE3)
                    .addComponent(jLabel_vInfoE4)
                    .addComponent(jLabel_vInfoE5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel_vPaiE1)
                    .addComponent(jLabel_vPaiE2)
                    .addComponent(jLabel_vPaiE3)
                    .addComponent(jLabel_vPaiE4)
                    .addComponent(jLabel_vPaiE5))
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel_vSegE1)
                    .addComponent(jLabel_vSegE2)
                    .addComponent(jLabel_vSegE3)
                    .addComponent(jLabel_vSegE4)
                    .addComponent(jLabel_vSegE5)
                    .addComponent(jLabel11)
                    .addComponent(jLabel_vDificE1)
                    .addComponent(jLabel_vDificE2)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel_vDificE3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel_vDificE4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel_vDificE5, javax.swing.GroupLayout.Alignment.TRAILING))))
        );

        jTextPane1.setEditable(false);
        jTextPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextPane1.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextPane1.setForeground(new java.awt.Color(153, 153, 153));
        jScrollPane3.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab3", jPanel2);

        javax.swing.GroupLayout jPanel_compartidesCanviantLayout = new javax.swing.GroupLayout(jPanel_compartidesCanviant);
        jPanel_compartidesCanviant.setLayout(jPanel_compartidesCanviantLayout);
        jPanel_compartidesCanviantLayout.setHorizontalGroup(
            jPanel_compartidesCanviantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_compartidesCanviantLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_compartidesCanviantLayout.setVerticalGroup(
            jPanel_compartidesCanviantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_compartidesCanviantLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                .addComponent(jPanel_compartidesCanviant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            
            //System.out.println(textHtmlEditat);
            
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

                    format = new SimpleDateFormat("dd/MM/yyyy");
                    String reempl;
                    int hours, minutes;
                    int e1 = 1, e2 = 2, e3 = 3, e4 = 4, e5 = 5;

                    for (Ruta r : llistaRutesCreades) {

                        Fetes f = gestorBDWikilocJdbc.haFetRuta(r, usuari_loginat);

                        rowData[0] = format.format(f.getMt()) + "         ";

                        reempl = r.getDist() + "";
                        reempl = reempl.replace('.', ',');

                        hours = r.getTemps() / 60;
                        minutes = r.getTemps() % 60;

                        rowData[1] = r.getTitol();
                        rowData[2] = reempl + " km";
                        rowData[3] = hours + "h " + minutes + "m";

                        if (r.getDific() == 1) {
                            rowData[4] = "*";
                        } else if (r.getDific() == 2) {
                            rowData[4] = "* *";
                        } else if (r.getDific() == 3) {
                            rowData[4] = "* * *";
                        } else if (r.getDific() == 4) {
                            rowData[4] = "* * * *";
                        } else if (r.getDific() == 5) {
                            rowData[4] = "* * * * *";
                        }

                        tableModel.addRow(rowData);
                    }
                    
                }
                
                jButton_desarCanvisRuta.setEnabled(false);
                
            }
            
            
            
        } catch (GestorBDWikilocException ex) {
            //System.out.println("ERROR: "+ex.getMessage());
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
                    //System.out.println("C1 ANTIC: "+hours+" NOU: "+nou);
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
                    //System.out.println("C2 ANTIC: "+minutes+" NOU: "+nou);
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

    private void jButton_afegirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_afegirActionPerformed
        
        botoAfegirClicat = true;

        dlm.clear();
        try {
            llistaPuntsRuta = gestorBDWikilocJdbc.obtenirLlistaPuntsRuta(id);
            //System.out.println("IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD: "+ruta_seleccionada.getId());
            //dlm = new DefaultListModel();
            dlm.clear();
            //System.out.println("QT PUNTS DE RUTA: "+dlm);
            for (Punt p : llistaPuntsRuta) {

                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

            }
            //jList_puntsRuta.setModel(dlm);
            /*
            
            jList_puntsRuta = new JList(dlm);
            System.out.println(jList_puntsRuta.getModel().toString());
            */
            jList1.setModel(dlm);
            //jList1 = new JList(dlm);
            //System.out.println(jList1.getModel().toString());

        } catch (GestorBDWikilocException ex) {
            //System.out.println("ERRORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR "+ ex.getMessage());
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
        
        jLabel21.setVisible(true);
        jLabel22.setVisible(true);
        jLabel23.setVisible(true);
        jLabel24.setVisible(true);
        jLabel25.setVisible(true);
        jLabel26.setVisible(true);
        jLabel27.setVisible(true);
        jSeparator7.setVisible(true);
        jSeparator8.setVisible(true);
        jSeparator9.setVisible(true);
        jSeparator10.setVisible(true);
        jSeparator11.setVisible(true);

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
        //System.out.println("NTIPUS: "+nTipus);
        
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
                            //System.out.println("QT PUNTS DE RUTA: "+dlm);
                            for (Punt p : llistaPuntsRuta) {

                                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

                            }
                            //jList_puntsRuta.setModel(dlm);
                            //jList_puntsRuta = new JList(dlm);
                            //System.out.println(jList_puntsRuta.getModel().toString());

                        } catch (GestorBDWikilocException ex) {
                            Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            llistaTipusPunts = gestorBDWikilocJdbc.obtenirLlistaTipus();

                            //System.out.println("LEN: " + llistaTipusPunts.size());
                            Tipus[] tip = new Tipus[llistaTipusPunts.size()];
                            tip = llistaTipusPunts.toArray(tip);

                            //jComboBox_tipusPunt = new JComboBox(tip);
                            //System.out.println("ITEM COUNT: " + jComboBox_tipusPunt.getItemCount());
                            //jComboBox_tipusPunt.setSelectedIndex(-1);

                            jComboBox_tipusPunt.setModel(new DefaultComboBoxModel<>(llistaTipusPunts.toArray(new Tipus[0])));
                            jComboBox_tipusPunt.setSelectedIndex(-1);

                            for (int i = 0; i < llistaTipusPunts.size(); i++) {
                                //System.out.println(tip[i]);
                            }

                        } catch (GestorBDWikilocException ex) {
                            Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                } else {
                    //System.out.println("POSAR PANTALLA ERROR");//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }

            } catch (GestorBDWikilocException ex) {
                //System.out.println("ERROR: " + ex.getMessage());
            }
            
            
        }else{
            
            
            try {
                boolean puntEditat;

                if (url_foto != null) {
                    //System.out.println("AMB FOTO");
                    puntEditat = gestorBDWikilocJdbc.editarPuntRuta(nPunt, id, url_foto);
                } else {
                    //System.out.println("SENSE FOTO");
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
                            //jList_puntsRuta.setModel(dlm);
                            //jList_puntsRuta = new JList(dlm);
                            //System.out.println(jList_puntsRuta.getModel().toString());

                        } catch (GestorBDWikilocException ex) {
                            Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            llistaTipusPunts = gestorBDWikilocJdbc.obtenirLlistaTipus();

                            //System.out.println("LEN: " + llistaTipusPunts.size());
                            Tipus[] tip = new Tipus[llistaTipusPunts.size()];
                            tip = llistaTipusPunts.toArray(tip);

                            //jComboBox_tipusPunt = new JComboBox(tip);
                            //System.out.println("ITEM COUNT: " + jComboBox_tipusPunt.getItemCount());
                            //jComboBox_tipusPunt.setSelectedIndex(-1);

                            jComboBox_tipusPunt.setModel(new DefaultComboBoxModel<>(llistaTipusPunts.toArray(new Tipus[0])));
                            jComboBox_tipusPunt.setSelectedIndex(-1);

                            for (int i = 0; i < llistaTipusPunts.size(); i++) {
                                //System.out.println(tip[i]);
                            }

                        } catch (GestorBDWikilocException ex) {
                            Logger.getLogger(panellCompartides.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                } else {
                    //System.out.println("POSAR PANTALLA ERROR");//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }

            } catch (GestorBDWikilocException ex) {
                //System.out.println("ERROR: " + ex.getMessage());
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
                //System.out.println("ERROR: " + ex.getMessage());
            }
            
            
        }
        
        botoAfegirClicat = false;
        botoPujarClicat = false;
        botoBaixarClicat = false;



        jButton_pujar.setVisible(false);
        jButton_baixar.setVisible(false);
        jButton_eliminar.setVisible(false);
        jButton_afegir.setVisible(true);
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
                    dlm.clear();
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
                        
                        /*
                        jList_puntsRuta.setModel(dlm);
                        jList_puntsRuta = new JList(dlm);
                        System.out.println(jList_puntsRuta.getModel().toString());
                        */
                        jList1.setModel(dlm);
                        jList1 = new JList(dlm);
                        //System.out.println(jList1.getModel().toString());

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
                    jLabel_fotoPunt.setIcon(fotoNulla);//posar imatge però amb imatge de sense imatge per defecte!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    jComboBox_tipusPunt.setSelectedIndex(-1);

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


                    jLabel21.setVisible(false);
                    jLabel22.setVisible(false);
                    jLabel23.setVisible(false);
                    jLabel24.setVisible(false);
                    jLabel25.setVisible(false);
                    jLabel26.setVisible(false);
                    jLabel27.setVisible(false);
                    jSeparator7.setVisible(false);
                    jSeparator8.setVisible(false);
                    jSeparator9.setVisible(false);
                    jSeparator10.setVisible(false);
                    jSeparator11.setVisible(false);

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
        
        int selectedIndex = punt_seleccionat.getOrdre()-1;

        if (selectedIndex > 0) {
            
            punt_seleccionat.setOrdre(ordre_actual-1);
            Punt punt_adalt = llistaPuntsRuta.get(selectedIndex-1);
            punt_adalt.setOrdre(punt_adalt.getOrdre()+1);
            
            Collections.sort(llistaPuntsRuta);
            
            dlm.clear();
            
            for (Punt p : llistaPuntsRuta) {

                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

            }          
            //jList1.setModel(dlm);
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

    private void jButton_cercaFiltreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cercaFiltreMouseClicked
        
        
        //gestorBDWikilocJdbc
        
        String titol_filtre = jTextField_filtreTitol.getText().trim();
        if(titol_filtre.length()==0){
            titol_filtre = "-1";
        }
        
        Integer dific_filtre = -1;
        if(jComboBox_filtreDific.getSelectedIndex()!=-1){
            try{
                //dific_filtre = Integer.parseInt((String) jComboBox_filtreDific.getSelectedItem());
                dific_filtre = jComboBox_filtreDific.getSelectedIndex();
            }catch(Exception ex){
                dific_filtre = -1;
            }
            
        }
        
        String rpl = jTextField_filtreDist.getText().trim();
        Double dist_filtre = Double.valueOf(-1);
        if(jTextField_filtreDist.getText().trim().length()>0){
            rpl = rpl.replace(',', '.');
            dist_filtre = Double.parseDouble(rpl);
        }
                
        
        try {
            
            if(tableModel.getRowCount() > 0){
                for(int i = tableModel.getRowCount()-1; i > -1; i--){
                    tableModel.removeRow(i);
                }
            }
            
            llistaRutesCreades = gestorBDWikilocJdbc.filtreRutaCreades(titol_filtre,dific_filtre,dist_filtre,usuari_loginat.getLogin());
            //System.out.println("FILTRE COMPLETAT: "+llistaRutesCreades.size());
            

            
            
            tableModel = (DefaultTableModel) jTable_rutesCreadesUsuari.getModel();
            Object rowData[] = new Object[5];
            
            format = new SimpleDateFormat("dd/MM/yyyy");
            String reempl;
            int hours, minutes;        
            int e1 =1, e2 =2, e3 =3, e4 =4, e5 =5;
            
            for (Ruta r : llistaRutesCreades) {
              
                
                Fetes f = gestorBDWikilocJdbc.haFetRuta(r, usuari_loginat);
                
                rowData[0] = format.format(f.getMt())+"         ";
                
                reempl = r.getDist()+"";
                reempl = reempl.replace('.', ',');
                
                hours = r.getTemps() / 60;
                minutes = r.getTemps() % 60;
                
                
                rowData[1] = r.getTitol();
                rowData[2] = reempl + " km";
                rowData[3] = hours + "h " + minutes + "m";
                
                
                if(r.getDific() ==1){
                    rowData[4] = "*";
                }else if(r.getDific() ==2){
                    rowData[4] = "* *";
                }else if(r.getDific() ==3){
                    rowData[4] = "* * *";
                }else if(r.getDific() ==4){
                    rowData[4] = "* * * *";
                }else if(r.getDific() ==5){
                    rowData[4] = "* * * * *";
                }
                        
                tableModel.addRow(rowData);
            }
            
            
        } catch (GestorBDWikilocException ex) {
            //System.out.println("ERROR: "+ex.getMessage());
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
            //System.out.println("FILTRE COMPLETAT: "+llistaRutesCreades.size());
            
            tableModel = (DefaultTableModel) jTable_rutesCreadesUsuari.getModel();
            Object rowData[] = new Object[5];
            
            format = new SimpleDateFormat("dd/MM/yyyy");
            String reempl;
            int hours, minutes;        
            int e1 =1, e2 =2, e3 =3, e4 =4, e5 =5;
            
            for (Ruta r : llistaRutesCreades) {
              
                
                Fetes f = gestorBDWikilocJdbc.haFetRuta(r, usuari_loginat);
                
                rowData[0] = format.format(f.getMt())+"         ";
                
                reempl = r.getDist()+"";
                reempl = reempl.replace('.', ',');
                
                hours = r.getTemps() / 60;
                minutes = r.getTemps() % 60;
                
                
                rowData[1] = r.getTitol();
                rowData[2] = reempl + " km";
                rowData[3] = hours + "h " + minutes + "m";
                
                
                if(r.getDific() ==1){
                    rowData[4] = "*";
                }else if(r.getDific() ==2){
                    rowData[4] = "* *";
                }else if(r.getDific() ==3){
                    rowData[4] = "* * *";
                }else if(r.getDific() ==4){
                    rowData[4] = "* * * *";
                }else if(r.getDific() ==5){
                    rowData[4] = "* * * * *";
                }
                        
                tableModel.addRow(rowData);
            }
            
            
        } catch (GestorBDWikilocException ex) {
            //System.out.println("ERROR: "+ex.getMessage());
        }
        
    }//GEN-LAST:event_jButton_netejarFiltreMouseClicked

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
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
                
                jLabel21.setVisible(true);
                jLabel22.setVisible(true);
                jLabel23.setVisible(true);
                jLabel24.setVisible(true);
                jLabel25.setVisible(true);
                jLabel26.setVisible(true);
                jLabel27.setVisible(true);
                jSeparator7.setVisible(true);
                jSeparator8.setVisible(true);
                jSeparator9.setVisible(true);
                jSeparator10.setVisible(true);
                jSeparator11.setVisible(true);
                jButton_afegir.setVisible(true);

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

                jLabel21.setVisible(true);
                jLabel22.setVisible(true);
                jLabel23.setVisible(true);
                jLabel24.setVisible(true);
                jLabel25.setVisible(true);
                jLabel26.setVisible(true);
                jLabel27.setVisible(true);
                jSeparator7.setVisible(true);
                jSeparator8.setVisible(true);
                jSeparator9.setVisible(true);
                jSeparator10.setVisible(true);
                jSeparator11.setVisible(true);
                jButton_afegir.setVisible(true);
                
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
                //System.out.println("INDEX: "+idx);
                try{
                    punt_seleccionat = llistaPuntsRuta.get(idx);
                }catch(Exception ex){
                    //System.out.println("ERROR: "+ex.getMessage());
                }
                
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
                //System.out.println("PUNT SELECCIONAT: "+punt_seleccionat.getNum()+" ID RUTA: "+id);
                t = gestorBDWikilocJdbc.obtenirTipusPunt(punt_seleccionat.getNum(), id);
                //System.out.println("t: "+t);
                //System.out.println("tipus punt: SELECCIONAT: "+t);
                jComboBox_tipusPunt.getModel().setSelectedItem(t);
                
                
                
                //jComboBox_tipusPunt.setSelectedItem(t);
                
                            

                
            } catch (GestorBDWikilocException ex) {
                //System.out.println("catch: error");
                //System.out.println(ex.getMessage());
            }
            
            
            //jLabel1
            if(punt_seleccionat.getFoto()!=null){
                //System.out.println("BYTE[]"+punt_seleccionat.getFoto().length);
                
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
    }//GEN-LAST:event_jList1ValueChanged

    private void jTextField_distKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_distKeyTyped
        
        
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || c==',')) {
            evt.consume();
        }
        
    }//GEN-LAST:event_jTextField_distKeyTyped

    private void jButton_cercaFiltreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cercaFiltreMouseEntered
        jButton_cercaFiltre.setIcon(fotoCercarHoover);
        jButton_cercaFiltre.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_cercaFiltreMouseEntered

    private void jButton_cercaFiltreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cercaFiltreMouseExited
        jButton_cercaFiltre.setIcon(fotoCercar);
        jButton_cercaFiltre.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_cercaFiltreMouseExited

    private void jButton_netejarFiltreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejarFiltreMouseEntered
        jButton_netejarFiltre.setIcon(fotoNetejarHoover);
        jButton_netejarFiltre.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_netejarFiltreMouseEntered

    private void jButton_netejarFiltreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejarFiltreMouseExited
        jButton_netejarFiltre.setIcon(fotoNetejar);
        jButton_netejarFiltre.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_netejarFiltreMouseExited

    private void jTextField_filtreDistKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_filtreDistKeyTyped
        
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || c==',')) {
            evt.consume();
        }
  
    }//GEN-LAST:event_jTextField_filtreDistKeyTyped

    private void jButton_textRutaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_textRutaMouseEntered
        jButton_textRuta.setIcon(fotoHtmlHoover);
        jButton_textRuta.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_textRutaMouseEntered

    private void jButton_textRutaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_textRutaMouseExited
        jButton_textRuta.setIcon(fotoHtml);
        jButton_textRuta.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_textRutaMouseExited

    private void jButton_desarCanvisRutaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_desarCanvisRutaMouseEntered
        if(jButton_desarCanvisRuta.isEnabled()){
            jButton_desarCanvisRuta.setIcon(fotoDesarCanvisHoover);
            jButton_desarCanvisRuta.setBackground(new Color(255, 163, 0));
        }
    }//GEN-LAST:event_jButton_desarCanvisRutaMouseEntered

    private void jButton_desarCanvisRutaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_desarCanvisRutaMouseExited
        if(jButton_desarCanvisRuta.isEnabled()){
            jButton_desarCanvisRuta.setIcon(fotoDesarCanvis);
            jButton_desarCanvisRuta.setBackground(new Color(76,140,43));
        }
    }//GEN-LAST:event_jButton_desarCanvisRutaMouseExited

    private void jTextField_desnPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_desnPKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        } 
    }//GEN-LAST:event_jTextField_desnPKeyTyped

    private void jTextField_desnNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_desnNKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        } 
    }//GEN-LAST:event_jTextField_desnNKeyTyped

    private void jButton_desarCanvisPuntsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_desarCanvisPuntsMouseEntered
        if(jButton_desarCanvisPunts.isEnabled()){
            jButton_desarCanvisPunts.setIcon(fotoDesarCanvisHoover);
            jButton_desarCanvisPunts.setBackground(new Color(255, 163, 0));
        }
    }//GEN-LAST:event_jButton_desarCanvisPuntsMouseEntered

    private void jButton_desarCanvisPuntsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_desarCanvisPuntsMouseExited
        if(jButton_desarCanvisPunts.isEnabled()){
            jButton_desarCanvisPunts.setIcon(fotoDesarCanvis);
            jButton_desarCanvisPunts.setBackground(new Color(76,140,43));
        }
    }//GEN-LAST:event_jButton_desarCanvisPuntsMouseExited

    private void jButton_eliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_eliminarMouseEntered
        jButton_eliminar.setIcon(fotoEliminarPuntHoover);
        jButton_eliminar.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_eliminarMouseEntered

    private void jButton_eliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_eliminarMouseExited
        jButton_eliminar.setIcon(fotoEliminarPunt);
        jButton_eliminar.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_eliminarMouseExited

    private void jButton_netejarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejarMouseEntered
        jButton_netejar.setIcon(fotoEliminarPuntsHoover);
        jButton_netejar.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_netejarMouseEntered

    private void jButton_netejarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejarMouseExited
        jButton_netejar.setIcon(fotoEliminarPunts);
        jButton_netejar.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_netejarMouseExited

    private void jButton_pujarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_pujarMouseEntered
        jButton_pujar.setIcon(fotoFletxaAmuntHoover);
        jButton_pujar.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_pujarMouseEntered

    private void jButton_pujarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_pujarMouseExited
        jButton_pujar.setIcon(fotoFletxaAmunt);
        jButton_pujar.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_pujarMouseExited

    private void jButton_baixarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_baixarMouseEntered
        jButton_baixar.setIcon(fotoFletxaAvallHoover);
        jButton_baixar.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_baixarMouseEntered

    private void jButton_baixarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_baixarMouseExited
        jButton_baixar.setIcon(fotoFletxaAvall);
        jButton_baixar.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_baixarMouseExited

    private void jButton_afegirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_afegirMouseEntered
        jButton_afegir.setIcon(fotoAfegirPuntHoover);
        jButton_afegir.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_afegirMouseEntered

    private void jButton_afegirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_afegirMouseExited
        jButton_afegir.setIcon(fotoAfegirPunt);
        jButton_afegir.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_afegirMouseExited

    private void jButton_seleccionarFotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_seleccionarFotoMouseEntered
        jButton_seleccionarFoto.setIcon(fotoAfegirFotoPuntHoover);
        jButton_seleccionarFoto.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_seleccionarFotoMouseEntered

    private void jButton_seleccionarFotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_seleccionarFotoMouseExited
        jButton_seleccionarFoto.setIcon(fotoAfegirFotoPunt);
        jButton_seleccionarFoto.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_seleccionarFotoMouseExited

    private void jTextField_latPuntKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_latPuntKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_latPuntKeyTyped

    private void jTextField_lonPuntKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_lonPuntKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_lonPuntKeyTyped

    private void jTextField_altPuntKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_altPuntKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_altPuntKeyTyped

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
    
    public void horesMinutsModificats(){
 
        //System.out.println("hora"+hours_canviada+" minuts"+ minutes_canviada);
        if(hours_canviada || minutes_canviada || titol_canviada || dist_canviada || desc_canviada || desnP_canviada || desnN_canviada || estrella_canviada){
            jButton_desarCanvisRuta.setEnabled(true);
            //System.out.println("A");
        }else{
            jButton_desarCanvisRuta.setEnabled(false);
            //System.out.println("B");
        }
    }
    
    
    public void modificacionsCampsPunt(){
        
        if(ordre_canviat || nomPunt_canviat || descPunt_canviat || lat_canviat || lon_canviat || alt_canviat || tipusPunt_canviada || fotoPunt_canviada || botoPujarClicat || botoBaixarClicat){
            jButton_desarCanvisPunts.setEnabled(true);
            //System.out.println("C");
        }else{
            jButton_desarCanvisPunts.setEnabled(false);
            //System.out.println("D");
        }
        
    }
    
    
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        
//
//        // Dibuja el gráfico de altimetría
//        //if (puntos != null && puntos.size() > 1) {
//            for (int i = 0; i < llistaPuntsRuta.size() - 1; i++) {
//                Punt puntoActual = llistaPuntsRuta.get(i);
//                Punt puntoSiguiente = llistaPuntsRuta.get(i + 1);
//
//                int x1 = i * 10; // Ajusta la escala horizontal
//                int y1 = getHeight() - (int) puntoActual.getAlt();
//                int x2 = (i + 1) * 10;
//                int y2 = getHeight() - (int) puntoSiguiente.getAlt();
//
//                g.drawLine(x1, y1, x2, y2);
//            }
//        //}
//    }
    

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_dificEstrella1;
    private javax.swing.JLabel jLabel_dificEstrella2;
    private javax.swing.JLabel jLabel_dificEstrella3;
    private javax.swing.JLabel jLabel_dificEstrella4;
    private javax.swing.JLabel jLabel_dificEstrella5;
    private javax.swing.JLabel jLabel_fotoPunt;
    private javax.swing.JLabel jLabel_missatge;
    private javax.swing.JLabel jLabel_qtatFetesAmbComentari;
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
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_compartidesCanviant;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable_rutesCreadesUsuari;
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
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
