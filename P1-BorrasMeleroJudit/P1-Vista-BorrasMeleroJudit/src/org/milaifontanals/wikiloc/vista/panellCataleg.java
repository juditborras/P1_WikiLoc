/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.wikiloc.components.TextPrompt;
import org.milaifontanals.wikiloc.jdbc.GestorBDWikilocJdbc;
import org.milaifontanals.wikiloc.model.Comentari;
import org.milaifontanals.wikiloc.model.Companys;
import org.milaifontanals.wikiloc.model.Fetes;
import org.milaifontanals.wikiloc.model.Ruta;
import org.milaifontanals.wikiloc.model.Tipus;
import org.milaifontanals.wikiloc.model.Usuari;
import org.milaifontanals.wikiloc.persistencia.GestorBDWikilocException;
import org.milaifontanals.wikiloc.toggle.ToggleAdapter;

/**
 *
 * @author JUDIT
 */
public class panellCataleg extends javax.swing.JPanel {

    /**
     * Creates new form panellCataleg
     */
    private GestorBDWikilocJdbc gestorBDWikilocJdbc;
    Usuari usuari_loginat;
    DefaultTableModel tableModel;
    DefaultListModel dlmLogins;
    List<Ruta> llistaRutes;
    List<Usuari> llistaLogins;
    SimpleDateFormat format;
    Ruta ruta_seleccionada;
    int id;
    ImageIcon estrellaBlancaMini = new ImageIcon("img"+File.separator+"estrella_blanca_mini.png");
    ImageIcon estrellaGrogaMini = new ImageIcon("img"+File.separator+"estrella_groga_mini.png");
    ImageIcon estrellaBlanca = new ImageIcon("img"+File.separator+"estrella_blanca.png");
    ImageIcon estrellaGroga = new ImageIcon("img"+File.separator+"estrella_groga.png");
    int v_inf = 1, v_seg = 1, v_pai = 1, dific = 1;
    boolean feta = false;
    String text, login_usuari_company;
    
    ImageIcon fotoCercar = new ImageIcon("img"+File.separator+"cercar.png");
    ImageIcon fotoCercarHoover = new ImageIcon("img"+File.separator+"cercarHoover.png");
    ImageIcon fotoNetejar = new ImageIcon("img"+File.separator+"netejar.png");
    ImageIcon fotoNetejarHoover = new ImageIcon("img"+File.separator+"netejarHoover.png");
    ImageIcon fotoComentar = new ImageIcon("img"+File.separator+"comentar.png");
    ImageIcon fotoComentarHoover = new ImageIcon("img"+File.separator+"comentarHoover.png");
    ImageIcon fotoDesarCanvis = new ImageIcon("img"+File.separator+"desarCanvis.png");
    ImageIcon fotoDesarCanvisHoover = new ImageIcon("img"+File.separator+"desarCanvisHoover.png");
    
    static class ColorArrowUI extends BasicComboBoxUI {

        public static ComboBoxUI createUI(JComponent c) {
            return new ColorArrowUI();
        }

        @Override
        protected JButton createArrowButton() {
            return new BasicArrowButton(
                    BasicArrowButton.SOUTH,
                    new Color(76,140,43), null,
                    new Color(255,163,0), null);
        }
    }

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
    
    
    public panellCataleg(JPanel jPanel_menu, JPanel jPanel_principal, Usuari usuari_loginat) throws GestorBDWikilocException {
        
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(255,255,255)));       
        
        initComponents();
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        
        jTable_rutesTotals.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        jTable_rutesTotals.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        jTable_rutesTotals.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        jTable_rutesTotals.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        jTable_rutesTotals.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        jTable_rutesTotals.setCellSelectionEnabled(false);
        jTable_rutesTotals.setRowSelectionAllowed(true);
        jTable_rutesTotals.setSelectionBackground(new Color(255,163,0));
        
        
        jTable_rutesTotals.setShowGrid(false);
        jTable_rutesTotals.setIntercellSpacing(new Dimension(0, 0));
        
        jTable_rutesTotals.getTableHeader().setBackground(new Color(76,140,43));
        jTable_rutesTotals.getTableHeader().setForeground(Color.white);
        jTable_rutesTotals.getTableHeader().setFont(new Font("Calibri",Font.BOLD,20));
        jTable_rutesTotals.getTableHeader().setPreferredSize(new Dimension(100,50));
        
        
        jComboBox_filtreDific.setSize(87, 80);
        jComboBox_filtreDific.setUI(ColorArrowUI.createUI(jComboBox_filtreDific));
        
        jComboBox_filtreCreador.setUI(ColorArrowUI.createUI(jComboBox_filtreCreador));
        jComboBox_filtreCreador.setSize(87, 250);
        
        jButton_desarValoracio.setDisabledIcon(new ImageIcon(createDisabledIcon(jButton_desarValoracio.getIcon())));

        jComboBox_seleccionarCompany.setUI(ColorArrowUI.createUI(jComboBox_seleccionarCompany));
        
        jPanel_menu.setVisible(false);
        this.usuari_loginat = usuari_loginat;
        jPanel2.setVisible(false);
        jPanel4.setVisible(false);
        jPanel3.setVisible(false);
        jComboBox_seleccionarCompany.setVisible(false);
        jButton_desarValoracio.setEnabled(false);
        
        TextPrompt placeHolder_titol = new TextPrompt("Ascensió al Pedraforca",jTextField_filtreTitol);
        TextPrompt placeHolder_dist = new TextPrompt(" >= 201,19 km",jTextField_filtreDist);
        TextPrompt placeHolder_comentari = new TextPrompt("Escriu el teu comentari...",jTextArea_escriureComentari);
        
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
        
        
        try {

            gestorBDWikilocJdbc = new GestorBDWikilocJdbc();
            
            llistaLogins = gestorBDWikilocJdbc.obtenirUsuaris();

            Usuari[] u = new Usuari[llistaLogins.size()];
            u = llistaLogins.toArray(u);
            
            
            //jComboBox_tipusPunt = new JComboBox(tip);
            //System.out.println("ITEM COUNT: "+jComboBox_tipusPunt.getItemCount());
            //jComboBox_tipusPunt.setSelectedIndex(-1);
            
            
            jComboBox_filtreCreador.setModel(new DefaultComboBoxModel<>(llistaLogins.toArray(new Usuari[0])));
            jComboBox_filtreCreador.insertItemAt(null, 0);
            jComboBox_filtreCreador.setSelectedIndex(-1);
            
            jComboBox_seleccionarCompany.setModel(new DefaultComboBoxModel<>(llistaLogins.toArray(new Usuari[0])));
            jComboBox_seleccionarCompany.insertItemAt(null, 0);
            jComboBox_seleccionarCompany.setSelectedIndex(-1);
            
            for(int i=0; i<llistaLogins.size(); i++){
                System.out.println(u[i].getLogin().toString());
            }

        } catch (GestorBDWikilocException ex) {
            
        }
        
        
        
        try {
            
            
            
            
            llistaRutes = gestorBDWikilocJdbc.obtenirLlistaRuta();
            
            tableModel = (DefaultTableModel) jTable_rutesTotals.getModel();
            Object rowData[] = new Object[8];
            
            format = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("LLISTA RUTES FETES: "+llistaRutes.size());
            
            
            String reempl;
            int hours, minutes;        
            int e1 =1, e2 =2, e3 =3, e4 =4, e5 =5;
            
            
            for (Ruta r : llistaRutes) {
              
                Fetes f = gestorBDWikilocJdbc.haFetRuta(r, usuari_loginat);
                
                if(f!=null){
                    
                    rowData[0] = format.format(f.getMt())+"         ";
                    rowData[5] = true;
                }else{
                    rowData[5] = false;
                }
                
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
                
                //rowData[5] = (f!=null?"Si":"No");
                        
                tableModel.addRow(rowData);
            }
                        
            
        } catch (GestorBDWikilocException ex) {
            System.out.println("Error en carregar la taula de rutes fetes");
        }
        

        TableActionEvent2 event = new TableActionEvent2() {
            List<Ruta> llistaRutes = gestorBDWikilocJdbc.obtenirLlistaRuta();
  
            @Override
            public void onEdit(int row) {
                
                //COMENTARIS I VALORACIONS!!!!!!!!!!!
                jPanel2.setVisible(true);
                
                ruta_seleccionada = llistaRutes.get(row);
                
                if(ruta_seleccionada.getLoginUsuari().getLogin().equals(usuari_loginat.getLogin())){
                    jButton_valorarRuta.setVisible(false);
                }else{
                    jButton_valorarRuta.setVisible(true);
                }
                
                obtenirDadesRuta(llistaRutes, row);
                
                try {
                    Fetes f = gestorBDWikilocJdbc.haFetRuta(ruta_seleccionada, usuari_loginat);
                    
                    if(f!=null){
                        jLabel1.setVisible(false);
                        jLabel30.setVisible(false);
                        toggleButton_haFetRuta.setVisible(false);
                        jLabel31.setVisible(false);
                        jLabel15.setVisible(false);
                        jSeparator4.setVisible(false);
                        jLabel_valorarInfE1.setVisible(false);
                        jLabel_valorarInfE2.setVisible(false);
                        jLabel_valorarInfE3.setVisible(false);
                        jLabel_valorarInfE4.setVisible(false);
                        jLabel_valorarInfE5.setVisible(false);
                        jLabel10.setVisible(false);
                        
                        jLabel11.setVisible(false);
                        jLabel_valorarSegE1.setVisible(false);
                        jLabel_valorarSegE2.setVisible(false);
                        jLabel_valorarSegE3.setVisible(false);
                        jLabel_valorarSegE4.setVisible(false);
                        jLabel_valorarSegE5.setVisible(false);
                        jLabel12.setVisible(false);
                        jLabel_valorarPaiE1.setVisible(false);
                        jLabel_valorarPaiE2.setVisible(false);
                        jLabel_valorarPaiE3.setVisible(false);
                        jLabel_valorarPaiE4.setVisible(false);
                        jLabel_valorarPaiE5.setVisible(false);
                        jLabel13.setVisible(false);
                        jLabel_valorarDificE1.setVisible(false);
                        jLabel_valorarDificE2.setVisible(false);
                        jLabel_valorarDificE3.setVisible(false);
                        jLabel_valorarDificE4.setVisible(false);
                        jLabel_valorarDificE5.setVisible(false);
                        jTextArea_escriureComentari.setVisible(false);
                        jScrollPane3.setVisible(false);
                        jComboBox_seleccionarCompany.setVisible(false);
                        jButton_desarValoracio.setVisible(false);
                        feta = true;
                        
                    }else{
                        jLabel1.setVisible(false);
                        jLabel30.setVisible(false);
                        toggleButton_haFetRuta.setVisible(false);
                        jLabel31.setVisible(false);
                        jLabel15.setVisible(false);
                        jSeparator4.setVisible(false);
                        jLabel_valorarInfE1.setVisible(false);
                        jLabel_valorarInfE2.setVisible(false);
                        jLabel_valorarInfE3.setVisible(false);
                        jLabel_valorarInfE4.setVisible(false);
                        jLabel_valorarInfE5.setVisible(false);
                        
                        jLabel10.setVisible(false);

                        jLabel11.setVisible(false);
                        jLabel_valorarSegE1.setVisible(false);
                        jLabel_valorarSegE2.setVisible(false);
                        jLabel_valorarSegE3.setVisible(false);
                        jLabel_valorarSegE4.setVisible(false);
                        jLabel_valorarSegE5.setVisible(false);
                        jLabel12.setVisible(false);
                        jLabel_valorarPaiE1.setVisible(false);
                        jLabel_valorarPaiE2.setVisible(false);
                        jLabel_valorarPaiE3.setVisible(false);
                        jLabel_valorarPaiE4.setVisible(false);
                        jLabel_valorarPaiE5.setVisible(false);
                        jLabel13.setVisible(false);
                        jLabel_valorarDificE1.setVisible(false);
                        jLabel_valorarDificE2.setVisible(false);
                        jLabel_valorarDificE3.setVisible(false);
                        jLabel_valorarDificE4.setVisible(false);
                        jLabel_valorarDificE5.setVisible(false);
                        jTextArea_escriureComentari.setVisible(false);
                        jScrollPane3.setVisible(false);
                        jComboBox_seleccionarCompany.setVisible(false);
                        jButton_desarValoracio.setVisible(false);
                        
                        feta = false;
                    }
                } catch (GestorBDWikilocException ex) {
                   
                }
                
                
                
            }

            @Override
            public void onDelete(int row) {
                
                //REPORT!!!!!!!!!!!!!!!!!!!!!!!!
                
            }


            
        
        };
        
        jTable_rutesTotals.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender2());
        jTable_rutesTotals.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor2(event));
        jTable_rutesTotals.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
        });        
        
        
        
        
        toggleButton_haFetRuta.addEventToggleSelected(new ToggleAdapter() {
            @Override
            public void onSelected(boolean selected){
                //super.onSelected(selected);
                System.out.println(selected);
                
                
                if(selected){
                    jPanel3.setVisible(true);
                    feta = true;
                    
                    jComboBox_seleccionarCompany.setVisible(true);
                    
                    jLabel11.setVisible(true);
                    jLabel_valorarSegE1.setVisible(true);
                    jLabel_valorarSegE2.setVisible(true);
                    jLabel_valorarSegE3.setVisible(true);
                    jLabel_valorarSegE4.setVisible(true);
                    jLabel_valorarSegE5.setVisible(true);
                    jLabel12.setVisible(true);
                    jLabel_valorarPaiE1.setVisible(true);
                    jLabel_valorarPaiE2.setVisible(true);
                    jLabel_valorarPaiE3.setVisible(true);
                    jLabel_valorarPaiE4.setVisible(true);
                    jLabel_valorarPaiE5.setVisible(true);
                    jLabel13.setVisible(true);
                    jLabel_valorarDificE1.setVisible(true);
                    jLabel_valorarDificE2.setVisible(true);
                    jLabel_valorarDificE3.setVisible(true);
                    jLabel_valorarDificE4.setVisible(true);
                    jLabel_valorarDificE5.setVisible(true);
                    
                }else{
                    jPanel3.setVisible(false);
                    feta = false;
                    
                    jComboBox_seleccionarCompany.setVisible(false);
                    
                    jLabel11.setVisible(false);
                    jLabel_valorarSegE1.setVisible(false);
                    jLabel_valorarSegE2.setVisible(false);
                    jLabel_valorarSegE3.setVisible(false);
                    jLabel_valorarSegE4.setVisible(false);
                    jLabel_valorarSegE5.setVisible(false);
                    jLabel12.setVisible(false);
                    jLabel_valorarPaiE1.setVisible(false);
                    jLabel_valorarPaiE2.setVisible(false);
                    jLabel_valorarPaiE3.setVisible(false);
                    jLabel_valorarPaiE4.setVisible(false);
                    jLabel_valorarPaiE5.setVisible(false);
                    jLabel13.setVisible(false);
                    jLabel_valorarDificE1.setVisible(false);
                    jLabel_valorarDificE2.setVisible(false);
                    jLabel_valorarDificE3.setVisible(false);
                    jLabel_valorarDificE4.setVisible(false);
                    jLabel_valorarDificE5.setVisible(false);
                }
                
            }
            
        });
        
        
       
        
        
    }
    
    public void obtenirDadesRuta(List<Ruta> llistaRutes, int row){
        
        id = llistaRutes.get(row).getId();
        
        try {
            
            double mitjaVinf = gestorBDWikilocJdbc.mitjaVinf(id);
            if (mitjaVinf >= 0.0 && mitjaVinf < 0.5) {
                //buides
                jLabel_mostrarInfE1.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVinf >= 0.5 && mitjaVinf < 1.5) {
                //estrella 1
                jLabel_mostrarInfE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVinf >= 1.5 && mitjaVinf < 2.5) {
                //estrella 2
                jLabel_mostrarInfE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVinf >= 2.5 && mitjaVinf < 3.5) {
                //estrella 3
                jLabel_mostrarInfE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVinf >= 3.5 && mitjaVinf < 4.5) {
                //estrella 4
                jLabel_mostrarInfE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVinf >= 4.5 && mitjaVinf <= 5.0) {
                //estrella 5
                jLabel_mostrarInfE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE5.setIcon(estrellaGrogaMini);
            }
            
            double mitjaVseg = gestorBDWikilocJdbc.mitjaVseg(id);
            if (mitjaVseg >= 0.0 && mitjaVseg < 0.5) {
                //buides
                jLabel_mostrarSegE1.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVseg >= 0.5 && mitjaVseg < 1.5) {
                //estrella 1
                jLabel_mostrarSegE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVseg >= 1.5 && mitjaVseg < 2.5) {
                //estrella 2
                jLabel_mostrarSegE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVseg >= 2.5 && mitjaVseg < 3.5) {
                //estrella 3
                jLabel_mostrarSegE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVseg >= 3.5 && mitjaVseg < 4.5) {
                //estrella 4
                jLabel_mostrarSegE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVseg >= 4.5 && mitjaVseg <= 5.0) {
                //estrella 5
                jLabel_mostrarSegE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE5.setIcon(estrellaGrogaMini);
            }
            
            double mitjaVpai = gestorBDWikilocJdbc.mitjaVpai(id);
            if (mitjaVpai >= 0.0 && mitjaVpai < 0.5) {
                //buides
                jLabel_mostrarPaiE1.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVpai >= 0.5 && mitjaVpai < 1.5) {
                //estrella 1
                jLabel_mostrarPaiE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVpai >= 1.5 && mitjaVpai < 2.5) {
                //estrella 2
                jLabel_mostrarPaiE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVpai >= 2.5 && mitjaVpai < 3.5) {
                //estrella 3
                jLabel_mostrarPaiE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVpai >= 3.5 && mitjaVpai < 4.5) {
                //estrella 4
                jLabel_mostrarPaiE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVpai >= 4.5 && mitjaVpai <= 5.0) {
                //estrella 5
                jLabel_mostrarPaiE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE5.setIcon(estrellaGrogaMini);
            }
            
            double mitjaVdific = gestorBDWikilocJdbc.mitjaVdific(id);
            if (mitjaVdific >= 0.0 && mitjaVdific < 0.5) {
                //buides
                jLabel_mostrarDificE1.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVdific >= 0.5 && mitjaVdific < 1.5) {
                //estrella 1
                jLabel_mostrarDificE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVdific >= 1.5 && mitjaVdific < 2.5) {
                //estrella 2
                jLabel_mostrarDificE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVdific >= 2.5 && mitjaVdific < 3.5) {
                //estrella 3
                jLabel_mostrarDificE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVdific >= 3.5 && mitjaVdific < 4.5) {
                //estrella 4
                jLabel_mostrarDificE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVdific >= 4.5 && mitjaVdific <= 5.0) {
                //estrella 5
                jLabel_mostrarDificE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE5.setIcon(estrellaGrogaMini);
            }
            
            
            
            List<Comentari> llistaComentaris = gestorBDWikilocJdbc.obtenirLlistaComentaris(id);
            
            
            System.out.println("SIZE LLISTA COMENTARIS "+llistaComentaris.size());
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
            System.out.println("INFO COMENTARI: "+info_comentari);
            
            jTextArea_comentarisTotals.setText(info_comentari);
            
            
            
        } catch (GestorBDWikilocException ex) {
            System.out.println("ERROR DEL CATCH: "+ex.getMessage());
            Logger.getLogger(panellCataleg.class.getName()).log(Level.SEVERE, null, ex);
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
        jTable_rutesTotals = new javax.swing.JTable();
        jTextField_filtreTitol = new javax.swing.JTextField();
        jTextField_filtreDist = new javax.swing.JTextField();
        jComboBox_filtreDific = new javax.swing.JComboBox<>();
        jButton_cercaFiltre = new javax.swing.JButton();
        jButton_netejaFiltre = new javax.swing.JButton();
        jCheckBox_feta = new javax.swing.JCheckBox();
        jComboBox_filtreCreador = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel_mostrarInfE1 = new javax.swing.JLabel();
        jLabel_mostrarInfE2 = new javax.swing.JLabel();
        jLabel_mostrarInfE3 = new javax.swing.JLabel();
        jLabel_mostrarInfE4 = new javax.swing.JLabel();
        jLabel_mostrarInfE5 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel_mostrarSegE1 = new javax.swing.JLabel();
        jLabel_mostrarSegE2 = new javax.swing.JLabel();
        jLabel_mostrarSegE3 = new javax.swing.JLabel();
        jLabel_mostrarSegE4 = new javax.swing.JLabel();
        jLabel_mostrarSegE5 = new javax.swing.JLabel();
        jLabel_mostrarPaiE1 = new javax.swing.JLabel();
        jLabel_mostrarPaiE2 = new javax.swing.JLabel();
        jLabel_mostrarPaiE3 = new javax.swing.JLabel();
        jLabel_mostrarPaiE4 = new javax.swing.JLabel();
        jLabel_mostrarPaiE5 = new javax.swing.JLabel();
        jLabel_mostrarDificE1 = new javax.swing.JLabel();
        jLabel_mostrarDificE2 = new javax.swing.JLabel();
        jLabel_mostrarDificE3 = new javax.swing.JLabel();
        jLabel_mostrarDificE4 = new javax.swing.JLabel();
        jLabel_mostrarDificE5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_comentarisTotals = new javax.swing.JTextArea();
        jButton_valorarRuta = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        toggleButton_haFetRuta = new org.milaifontanals.wikiloc.toggle.ToggleButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel_valorarInfE1 = new javax.swing.JLabel();
        jLabel_valorarInfE2 = new javax.swing.JLabel();
        jLabel_valorarInfE3 = new javax.swing.JLabel();
        jLabel_valorarInfE4 = new javax.swing.JLabel();
        jLabel_valorarInfE5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel_valorarPaiE4 = new javax.swing.JLabel();
        jLabel_valorarPaiE3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel_valorarDificE2 = new javax.swing.JLabel();
        jLabel_valorarDificE4 = new javax.swing.JLabel();
        jLabel_valorarPaiE2 = new javax.swing.JLabel();
        jLabel_valorarDificE5 = new javax.swing.JLabel();
        jLabel_valorarDificE1 = new javax.swing.JLabel();
        jLabel_valorarPaiE5 = new javax.swing.JLabel();
        jLabel_valorarPaiE1 = new javax.swing.JLabel();
        jLabel_valorarDificE3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel_valorarSegE1 = new javax.swing.JLabel();
        jLabel_valorarSegE2 = new javax.swing.JLabel();
        jLabel_valorarSegE3 = new javax.swing.JLabel();
        jLabel_valorarSegE4 = new javax.swing.JLabel();
        jLabel_valorarSegE5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea_escriureComentari = new javax.swing.JTextArea();
        jComboBox_seleccionarCompany = new javax.swing.JComboBox<>();
        jButton_desarValoracio = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable_rutesTotals.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTable_rutesTotals.setForeground(new java.awt.Color(153, 153, 153));
        jTable_rutesTotals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data de realització", "Títol", "Distància", "Durada", "Dificultat", "Completada", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_rutesTotals.setColumnSelectionAllowed(true);
        jTable_rutesTotals.setGridColor(new java.awt.Color(76, 140, 43));
        jTable_rutesTotals.setRowHeight(55);
        jTable_rutesTotals.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_rutesTotals.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable_rutesTotals);
        jTable_rutesTotals.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jTable_rutesTotals.getColumnModel().getColumnCount() > 0) {
            jTable_rutesTotals.getColumnModel().getColumn(0).setMinWidth(190);
            jTable_rutesTotals.getColumnModel().getColumn(0).setMaxWidth(190);
            jTable_rutesTotals.getColumnModel().getColumn(2).setMinWidth(150);
            jTable_rutesTotals.getColumnModel().getColumn(2).setMaxWidth(150);
            jTable_rutesTotals.getColumnModel().getColumn(3).setMinWidth(150);
            jTable_rutesTotals.getColumnModel().getColumn(3).setMaxWidth(150);
            jTable_rutesTotals.getColumnModel().getColumn(4).setMinWidth(150);
            jTable_rutesTotals.getColumnModel().getColumn(4).setMaxWidth(150);
            jTable_rutesTotals.getColumnModel().getColumn(5).setMinWidth(130);
            jTable_rutesTotals.getColumnModel().getColumn(5).setMaxWidth(130);
            jTable_rutesTotals.getColumnModel().getColumn(6).setMinWidth(120);
            jTable_rutesTotals.getColumnModel().getColumn(6).setMaxWidth(120);
        }

        jTextField_filtreTitol.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_filtreTitol.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_filtreTitol.setBorder(null);

        jTextField_filtreDist.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_filtreDist.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_filtreDist.setBorder(null);

        jComboBox_filtreDific.setForeground(new java.awt.Color(204, 204, 204));
        jComboBox_filtreDific.setBorder(null);

        jButton_cercaFiltre.setBackground(new java.awt.Color(76, 140, 43));
        jButton_cercaFiltre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/cercar.png"))); // NOI18N
        jButton_cercaFiltre.setBorder(null);
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

        jButton_netejaFiltre.setBackground(new java.awt.Color(76, 140, 43));
        jButton_netejaFiltre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/netejar.png"))); // NOI18N
        jButton_netejaFiltre.setBorder(null);
        jButton_netejaFiltre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_netejaFiltreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_netejaFiltreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_netejaFiltreMouseExited(evt);
            }
        });

        jCheckBox_feta.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox_feta.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jCheckBox_feta.setForeground(new java.awt.Color(153, 153, 153));
        jCheckBox_feta.setText("Completada");
        jCheckBox_feta.setIconTextGap(10);

        jComboBox_filtreCreador.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jComboBox_filtreCreador.setForeground(new java.awt.Color(204, 204, 204));
        jComboBox_filtreCreador.setBorder(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 153, 153));
        jLabel14.setText("Valoracions");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 952, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 167, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 497, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 133, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("Comentaris");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel4.setText("Informació:");

        jLabel_mostrarInfE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarInfE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarInfE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarInfE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarInfE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel5.setText("Seguiment:");

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel6.setText("Paisatge:");

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel7.setText("Dificultat:");

        jLabel_mostrarSegE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarSegE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarSegE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarSegE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarSegE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarPaiE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarPaiE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarPaiE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarPaiE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarPaiE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarDificE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarDificE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarDificE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarDificE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        jLabel_mostrarDificE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca_mini.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel5))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(61, 61, 61)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel_mostrarSegE1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarSegE2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarSegE3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarSegE4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarSegE5))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel_mostrarInfE1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarInfE2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarInfE3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarInfE4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarInfE5)))
                .addGap(92, 92, 92)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7)))
                .addGap(54, 54, 54)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel_mostrarDificE1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarDificE2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarDificE3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarDificE4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarDificE5))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel_mostrarPaiE1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarPaiE2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarPaiE3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarPaiE4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_mostrarPaiE5)))
                .addGap(0, 145, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_mostrarInfE5)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel_mostrarInfE1)
                                .addComponent(jLabel_mostrarInfE2)
                                .addComponent(jLabel_mostrarInfE3)
                                .addComponent(jLabel_mostrarInfE4)))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_mostrarSegE5)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel_mostrarSegE1)
                                .addComponent(jLabel_mostrarSegE2)
                                .addComponent(jLabel_mostrarSegE3)
                                .addComponent(jLabel_mostrarSegE4)))))
                .addGap(105, 105, 105))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_mostrarPaiE3)
                    .addComponent(jLabel_mostrarPaiE4)
                    .addComponent(jLabel_mostrarPaiE5)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_mostrarPaiE1)
                        .addComponent(jLabel_mostrarPaiE2))
                    .addComponent(jLabel6))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel_mostrarDificE3)
                    .addComponent(jLabel_mostrarDificE4)
                    .addComponent(jLabel_mostrarDificE5)
                    .addComponent(jLabel_mostrarDificE1)
                    .addComponent(jLabel_mostrarDificE2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jTextArea_comentarisTotals.setEditable(false);
        jTextArea_comentarisTotals.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea_comentarisTotals.setColumns(20);
        jTextArea_comentarisTotals.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextArea_comentarisTotals.setForeground(new java.awt.Color(153, 153, 153));
        jTextArea_comentarisTotals.setRows(5);
        jTextArea_comentarisTotals.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jScrollPane2.setViewportView(jTextArea_comentarisTotals);

        jButton_valorarRuta.setBackground(new java.awt.Color(76, 140, 43));
        jButton_valorarRuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/comentar.png"))); // NOI18N
        jButton_valorarRuta.setBorder(null);
        jButton_valorarRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_valorarRutaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_valorarRutaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_valorarRutaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_valorarRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_valorarRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel30.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel30.setText("NO");

        toggleButton_haFetRuta.setForeground(new java.awt.Color(76, 140, 43));

        jLabel31.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel31.setText("SÍ");

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel10.setText("Has realitzat aquesta ruta?");

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("Valoració de l'usuari");

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel1.setText("Informació:");

        jLabel_valorarInfE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_groga.png"))); // NOI18N
        jLabel_valorarInfE1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarInfE1MouseClicked(evt);
            }
        });

        jLabel_valorarInfE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarInfE2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarInfE2MouseClicked(evt);
            }
        });

        jLabel_valorarInfE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarInfE3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarInfE3MouseClicked(evt);
            }
        });

        jLabel_valorarInfE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarInfE4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarInfE4MouseClicked(evt);
            }
        });

        jLabel_valorarInfE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarInfE5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarInfE5MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel12.setText("Paisatge:");

        jLabel_valorarPaiE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarPaiE4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarPaiE4MouseClicked(evt);
            }
        });

        jLabel_valorarPaiE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarPaiE3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarPaiE3MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel13.setText("Dificultat:");

        jLabel_valorarDificE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarDificE2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarDificE2MouseClicked(evt);
            }
        });

        jLabel_valorarDificE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarDificE4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarDificE4MouseClicked(evt);
            }
        });

        jLabel_valorarPaiE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarPaiE2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarPaiE2MouseClicked(evt);
            }
        });

        jLabel_valorarDificE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarDificE5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarDificE5MouseClicked(evt);
            }
        });

        jLabel_valorarDificE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_groga.png"))); // NOI18N
        jLabel_valorarDificE1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarDificE1MouseClicked(evt);
            }
        });

        jLabel_valorarPaiE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarPaiE5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarPaiE5MouseClicked(evt);
            }
        });

        jLabel_valorarPaiE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_groga.png"))); // NOI18N
        jLabel_valorarPaiE1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarPaiE1MouseClicked(evt);
            }
        });

        jLabel_valorarDificE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarDificE3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarDificE3MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel11.setText("Seguiment:");

        jLabel_valorarSegE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_groga.png"))); // NOI18N
        jLabel_valorarSegE1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarSegE1MouseClicked(evt);
            }
        });

        jLabel_valorarSegE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarSegE2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarSegE2MouseClicked(evt);
            }
        });

        jLabel_valorarSegE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarSegE3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarSegE3MouseClicked(evt);
            }
        });

        jLabel_valorarSegE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarSegE4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarSegE4MouseClicked(evt);
            }
        });

        jLabel_valorarSegE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/estrella_blanca.png"))); // NOI18N
        jLabel_valorarSegE5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_valorarSegE5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toggleButton_haFetRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel_valorarInfE1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarInfE2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarInfE3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarInfE4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarInfE5))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(47, 47, 47)
                                .addComponent(jLabel_valorarSegE1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarSegE2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarSegE3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarSegE4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarSegE5)))
                        .addGap(106, 106, 106)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel_valorarPaiE1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarPaiE2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarPaiE3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarPaiE4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarPaiE5))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel_valorarDificE1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarDificE2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarDificE3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarDificE4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_valorarDificE5))))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 45, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(toggleButton_haFetRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel_valorarInfE2)
                            .addComponent(jLabel_valorarInfE1)
                            .addComponent(jLabel_valorarInfE3)
                            .addComponent(jLabel_valorarInfE4)
                            .addComponent(jLabel_valorarInfE5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel_valorarSegE1)
                            .addComponent(jLabel_valorarSegE2)
                            .addComponent(jLabel_valorarSegE3)
                            .addComponent(jLabel_valorarSegE4)
                            .addComponent(jLabel_valorarSegE5)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGap(7, 7, 7)
                                    .addComponent(jLabel12))
                                .addComponent(jLabel_valorarPaiE2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel_valorarPaiE3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel_valorarPaiE4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel_valorarPaiE5, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel_valorarPaiE1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel_valorarDificE1)
                            .addComponent(jLabel_valorarDificE2)
                            .addComponent(jLabel_valorarDificE3)
                            .addComponent(jLabel_valorarDificE4)
                            .addComponent(jLabel_valorarDificE5))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextArea_escriureComentari.setColumns(20);
        jTextArea_escriureComentari.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextArea_escriureComentari.setForeground(new java.awt.Color(153, 153, 153));
        jTextArea_escriureComentari.setRows(5);
        jTextArea_escriureComentari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextArea_escriureComentari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea_escriureComentariKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTextArea_escriureComentari);

        jComboBox_seleccionarCompany.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N

        jButton_desarValoracio.setBackground(new java.awt.Color(76, 140, 43));
        jButton_desarValoracio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/desarCanvis.png"))); // NOI18N
        jButton_desarValoracio.setBorder(null);
        jButton_desarValoracio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_desarValoracioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_desarValoracioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_desarValoracioMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox_seleccionarCompany, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_desarValoracio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel9)
                                .addGap(512, 512, 512)
                                .addComponent(jLabel14))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1526, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton_desarValoracio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_seleccionarCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Catàleg de rutes");

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jSeparator2.setForeground(new java.awt.Color(76, 140, 43));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField_filtreTitol, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_filtreDist, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_filtreDific, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_filtreCreador, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox_feta)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_cercaFiltre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_netejaFiltre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_netejaFiltre, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cercaFiltre, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_filtreTitol, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_filtreDist, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_filtreDific, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_filtreCreador)
                    .addComponent(jCheckBox_feta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        
        Double dist_filtre = Double.valueOf(-1);
        if(jTextField_filtreDist.getText().trim().length()>0){
            dist_filtre = Double.parseDouble(jTextField_filtreDist.getText().trim());
        }
        
        
        Usuari creador = (Usuari) jComboBox_filtreCreador.getSelectedItem();
        String login_creador;
        if(creador==null){
            login_creador = "-1";
        }else{
            login_creador = creador.getLogin();
        }
        
        String usuari_fetes;
        if(jCheckBox_feta.isSelected()){
            usuari_fetes = usuari_loginat.getLogin();
        }else{
            usuari_fetes = "-1";
        }

        
        try {

            if(tableModel.getRowCount() > 0){
                for(int i = tableModel.getRowCount()-1; i > -1; i--){
                    tableModel.removeRow(i);
                }
            }

            llistaRutes = gestorBDWikilocJdbc.filtreTotalRutes(titol_filtre, dific_filtre, dist_filtre, login_creador, usuari_fetes);

            tableModel = (DefaultTableModel) jTable_rutesTotals.getModel();
            Object rowData[] = new Object[6];

            for (Ruta r : llistaRutes) {

                Fetes f = gestorBDWikilocJdbc.haFetRuta(r, usuari_loginat);
                
                if(f!=null){
                    
                    rowData[0] = format.format(f.getMt());
                    rowData[5] = true;
                }else{
                    rowData[5] = false;
                }
                
                
                rowData[1] = r.getTitol();
                rowData[2] = r.getDist();
                rowData[3] = r.getTemps();
                rowData[4] = r.getDific();
                //rowData[5] = (f!=null?"Si":"No");
                        
                tableModel.addRow(rowData);
            }


            
            
            
            
        } catch (GestorBDWikilocException ex) {
            System.out.println("ERROR DE FILTRE: "+ex.getMessage());
        }
        
        
    }//GEN-LAST:event_jButton_cercaFiltreMouseClicked

    private void jButton_netejaFiltreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejaFiltreMouseClicked
        
        jTextField_filtreTitol.setText("");
        jTextField_filtreDist.setText("");
        jComboBox_filtreDific.setSelectedIndex(-1);
        jComboBox_filtreCreador.setSelectedIndex(-1);
        jCheckBox_feta.setSelected(false);
        jButton_desarValoracio.setEnabled(false);

        try {
  
            if(tableModel.getRowCount() > 0){
                for(int i = tableModel.getRowCount()-1; i > -1; i--){
                    tableModel.removeRow(i);
                }
            }
            
            llistaRutes = gestorBDWikilocJdbc.obtenirLlistaRuta();
            
            tableModel = (DefaultTableModel) jTable_rutesTotals.getModel();
            Object rowData[] = new Object[8];
            
            format = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("LLISTA RUTES FETES: "+llistaRutes.size());
            for (Ruta r : llistaRutes) {
              
                Fetes f = gestorBDWikilocJdbc.haFetRuta(r, usuari_loginat);
                
                if(f!=null){
                    
                    rowData[0] = format.format(f.getMt());
                    rowData[5] = true;
                }else{
                    rowData[5] = false;
                }
                
                
                rowData[1] = r.getTitol();
                rowData[2] = r.getDist();
                rowData[3] = r.getTemps();
                rowData[4] = r.getDific();
                //rowData[5] = (f!=null?"Si":"No");
                        
                tableModel.addRow(rowData);
            }
                        
            
        } catch (GestorBDWikilocException ex) {
            System.out.println("Error en carregar la taula de rutes fetes");
        }        

        
        
    }//GEN-LAST:event_jButton_netejaFiltreMouseClicked

    private void jButton_valorarRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_valorarRutaMouseClicked
        
        
        jPanel4.setVisible(true);
        
        try {
            Fetes f = gestorBDWikilocJdbc.haFetRuta(ruta_seleccionada, usuari_loginat);

            if (f != null) {
                jLabel1.setVisible(false);
                jLabel30.setVisible(false);
                toggleButton_haFetRuta.setVisible(false);
                jLabel31.setVisible(false);
                jLabel15.setVisible(false);
                jSeparator4.setVisible(false);
                jLabel_valorarInfE1.setVisible(false);
                jLabel_valorarInfE2.setVisible(false);
                jLabel_valorarInfE3.setVisible(false);
                jLabel_valorarInfE4.setVisible(false);
                jLabel_valorarInfE5.setVisible(false);
                
                jTextArea_escriureComentari.setVisible(true);
                jScrollPane3.setVisible(true);
                jLabel10.setVisible(false);
                jButton_desarValoracio.setVisible(true);
                
                jComboBox_seleccionarCompany.setVisible(false);
                jLabel11.setVisible(false);
                jLabel_valorarSegE1.setVisible(false);
                jLabel_valorarSegE2.setVisible(false);
                jLabel_valorarSegE3.setVisible(false);
                jLabel_valorarSegE4.setVisible(false);
                jLabel_valorarSegE5.setVisible(false);
                jLabel12.setVisible(false);
                jLabel_valorarPaiE1.setVisible(false);
                jLabel_valorarPaiE2.setVisible(false);
                jLabel_valorarPaiE3.setVisible(false);
                jLabel_valorarPaiE4.setVisible(false);
                jLabel_valorarPaiE5.setVisible(false);
                jLabel13.setVisible(false);
                jLabel_valorarDificE1.setVisible(false);
                jLabel_valorarDificE2.setVisible(false);
                jLabel_valorarDificE3.setVisible(false);
                jLabel_valorarDificE4.setVisible(false);
                jLabel_valorarDificE5.setVisible(false);
                
                
                

            } else {
                jLabel1.setVisible(true);
                jLabel30.setVisible(true);
                toggleButton_haFetRuta.setVisible(true);
                jLabel31.setVisible(true);
                jLabel15.setVisible(true);
                jSeparator4.setVisible(true);
                jLabel_valorarInfE1.setVisible(true);
                jLabel_valorarInfE2.setVisible(true);
                jLabel_valorarInfE3.setVisible(true);
                jLabel_valorarInfE4.setVisible(true);
                jLabel_valorarInfE5.setVisible(true);
                jTextArea_escriureComentari.setVisible(true);
                jScrollPane3.setVisible(true);
                jLabel10.setVisible(true);
                jButton_desarValoracio.setVisible(true);
            }
        } catch (GestorBDWikilocException ex) {

        }
        
        
    }//GEN-LAST:event_jButton_valorarRutaMouseClicked

    private void jLabel_valorarInfE1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarInfE1MouseClicked
                        
        jLabel_valorarInfE1.setIcon(estrellaGroga);
        jLabel_valorarInfE2.setIcon(estrellaBlanca);
        jLabel_valorarInfE3.setIcon(estrellaBlanca);
        jLabel_valorarInfE4.setIcon(estrellaBlanca);
        jLabel_valorarInfE5.setIcon(estrellaBlanca);
        
        v_inf = 1;
//        if(r!=null){
//            r.setDific(dific);
//        }
    }//GEN-LAST:event_jLabel_valorarInfE1MouseClicked

    private void jLabel_valorarInfE2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarInfE2MouseClicked
        
        jLabel_valorarInfE1.setIcon(estrellaGroga);
        jLabel_valorarInfE2.setIcon(estrellaGroga);
        jLabel_valorarInfE3.setIcon(estrellaBlanca);
        jLabel_valorarInfE4.setIcon(estrellaBlanca);
        jLabel_valorarInfE5.setIcon(estrellaBlanca);
        
        v_inf = 2;
//        if(r!=null){
//            r.setDific(dific);
//        }        
               
    }//GEN-LAST:event_jLabel_valorarInfE2MouseClicked

    private void jLabel_valorarInfE3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarInfE3MouseClicked
        
        jLabel_valorarInfE1.setIcon(estrellaGroga);
        jLabel_valorarInfE2.setIcon(estrellaGroga);
        jLabel_valorarInfE3.setIcon(estrellaGroga);
        jLabel_valorarInfE4.setIcon(estrellaBlanca);
        jLabel_valorarInfE5.setIcon(estrellaBlanca);
        
        v_inf = 3;
//        if(r!=null){
//            r.setDific(dific);
//        }                 
        
    }//GEN-LAST:event_jLabel_valorarInfE3MouseClicked

    private void jLabel_valorarInfE4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarInfE4MouseClicked
        
        jLabel_valorarInfE1.setIcon(estrellaGroga);
        jLabel_valorarInfE2.setIcon(estrellaGroga);
        jLabel_valorarInfE3.setIcon(estrellaGroga);
        jLabel_valorarInfE4.setIcon(estrellaGroga);
        jLabel_valorarInfE5.setIcon(estrellaBlanca);
        
        v_inf = 4;
//        if(r!=null){
//            r.setDific(dific);
//        }        
                
    }//GEN-LAST:event_jLabel_valorarInfE4MouseClicked

    private void jLabel_valorarInfE5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarInfE5MouseClicked
        
        jLabel_valorarInfE1.setIcon(estrellaGroga);
        jLabel_valorarInfE2.setIcon(estrellaGroga);
        jLabel_valorarInfE3.setIcon(estrellaGroga);
        jLabel_valorarInfE4.setIcon(estrellaGroga);
        jLabel_valorarInfE5.setIcon(estrellaGroga);
        
        v_inf = 5;
//        if(r!=null){
//            r.setDific(dific);
//        }        
               
    }//GEN-LAST:event_jLabel_valorarInfE5MouseClicked

    private void jLabel_valorarSegE1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarSegE1MouseClicked
        
        jLabel_valorarSegE1.setIcon(estrellaGroga);
        jLabel_valorarSegE2.setIcon(estrellaBlanca);
        jLabel_valorarSegE3.setIcon(estrellaBlanca);
        jLabel_valorarSegE4.setIcon(estrellaBlanca);
        jLabel_valorarSegE5.setIcon(estrellaBlanca);
        
        v_seg = 1;
//        if(r!=null){
//            r.setDific(dific);
//        }        
        
    }//GEN-LAST:event_jLabel_valorarSegE1MouseClicked

    private void jLabel_valorarSegE2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarSegE2MouseClicked
        
        jLabel_valorarSegE1.setIcon(estrellaGroga);
        jLabel_valorarSegE2.setIcon(estrellaGroga);
        jLabel_valorarSegE3.setIcon(estrellaBlanca);
        jLabel_valorarSegE4.setIcon(estrellaBlanca);
        jLabel_valorarSegE5.setIcon(estrellaBlanca);
        
        v_seg = 2;
//        if(r!=null){
//            r.setDific(dific);
//        }        
        
    }//GEN-LAST:event_jLabel_valorarSegE2MouseClicked

    private void jLabel_valorarSegE3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarSegE3MouseClicked
        
        jLabel_valorarSegE1.setIcon(estrellaGroga);
        jLabel_valorarSegE2.setIcon(estrellaGroga);
        jLabel_valorarSegE3.setIcon(estrellaGroga);
        jLabel_valorarSegE4.setIcon(estrellaBlanca);
        jLabel_valorarSegE5.setIcon(estrellaBlanca);
        
        v_seg = 3;
//        if(r!=null){
//            r.setDific(dific);
//        }        
        
    }//GEN-LAST:event_jLabel_valorarSegE3MouseClicked

    private void jLabel_valorarSegE4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarSegE4MouseClicked
        
        jLabel_valorarSegE1.setIcon(estrellaGroga);
        jLabel_valorarSegE2.setIcon(estrellaGroga);
        jLabel_valorarSegE3.setIcon(estrellaGroga);
        jLabel_valorarSegE4.setIcon(estrellaGroga);
        jLabel_valorarSegE5.setIcon(estrellaBlanca);
        
        v_seg = 4;
//        if(r!=null){
//            r.setDific(dific);
//        }         
        
    }//GEN-LAST:event_jLabel_valorarSegE4MouseClicked

    private void jLabel_valorarSegE5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarSegE5MouseClicked
        
        jLabel_valorarSegE1.setIcon(estrellaGroga);
        jLabel_valorarSegE2.setIcon(estrellaGroga);
        jLabel_valorarSegE3.setIcon(estrellaGroga);
        jLabel_valorarSegE4.setIcon(estrellaGroga);
        jLabel_valorarSegE5.setIcon(estrellaGroga);
        
        v_seg = 5;
//        if(r!=null){
//            r.setDific(dific);
//        }         
        
    }//GEN-LAST:event_jLabel_valorarSegE5MouseClicked

    private void jLabel_valorarPaiE1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarPaiE1MouseClicked
        
        jLabel_valorarPaiE1.setIcon(estrellaGroga);
        jLabel_valorarPaiE2.setIcon(estrellaBlanca);
        jLabel_valorarPaiE3.setIcon(estrellaBlanca);
        jLabel_valorarPaiE4.setIcon(estrellaBlanca);
        jLabel_valorarPaiE5.setIcon(estrellaBlanca);
        
        v_pai = 1;
//        if(r!=null){
//            r.setDific(dific);
//        }        
        
    }//GEN-LAST:event_jLabel_valorarPaiE1MouseClicked

    private void jLabel_valorarPaiE2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarPaiE2MouseClicked
        
        jLabel_valorarPaiE1.setIcon(estrellaGroga);
        jLabel_valorarPaiE2.setIcon(estrellaGroga);
        jLabel_valorarPaiE3.setIcon(estrellaBlanca);
        jLabel_valorarPaiE4.setIcon(estrellaBlanca);
        jLabel_valorarPaiE5.setIcon(estrellaBlanca);
        
        v_pai = 2;
//        if(r!=null){
//            r.setDific(dific);
//        }        
        
    }//GEN-LAST:event_jLabel_valorarPaiE2MouseClicked

    private void jLabel_valorarPaiE3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarPaiE3MouseClicked
        
        jLabel_valorarPaiE1.setIcon(estrellaGroga);
        jLabel_valorarPaiE2.setIcon(estrellaGroga);
        jLabel_valorarPaiE3.setIcon(estrellaGroga);
        jLabel_valorarPaiE4.setIcon(estrellaBlanca);
        jLabel_valorarPaiE5.setIcon(estrellaBlanca);
        
        v_pai = 3;
//        if(r!=null){
//            r.setDific(dific);
//        }        
        
    }//GEN-LAST:event_jLabel_valorarPaiE3MouseClicked

    private void jLabel_valorarPaiE4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarPaiE4MouseClicked
        
        jLabel_valorarPaiE1.setIcon(estrellaGroga);
        jLabel_valorarPaiE2.setIcon(estrellaGroga);
        jLabel_valorarPaiE3.setIcon(estrellaGroga);
        jLabel_valorarPaiE4.setIcon(estrellaGroga);
        jLabel_valorarPaiE5.setIcon(estrellaBlanca);
        
        v_pai = 4;
//        if(r!=null){
//            r.setDific(dific);
//        }         
        
    }//GEN-LAST:event_jLabel_valorarPaiE4MouseClicked

    private void jLabel_valorarPaiE5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarPaiE5MouseClicked
        
        jLabel_valorarPaiE1.setIcon(estrellaGroga);
        jLabel_valorarPaiE2.setIcon(estrellaGroga);
        jLabel_valorarPaiE3.setIcon(estrellaGroga);
        jLabel_valorarPaiE4.setIcon(estrellaGroga);
        jLabel_valorarPaiE5.setIcon(estrellaGroga);
        
        v_pai = 5;
//        if(r!=null){
//            r.setDific(dific);
//        }         
        
    }//GEN-LAST:event_jLabel_valorarPaiE5MouseClicked

    private void jLabel_valorarDificE1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarDificE1MouseClicked
        
        jLabel_valorarDificE1.setIcon(estrellaGroga);
        jLabel_valorarDificE2.setIcon(estrellaBlanca);
        jLabel_valorarDificE3.setIcon(estrellaBlanca);
        jLabel_valorarDificE4.setIcon(estrellaBlanca);
        jLabel_valorarDificE5.setIcon(estrellaBlanca);
        
        dific = 1;
//        if(r!=null){
//            r.setDific(dific);
//        }        
        
    }//GEN-LAST:event_jLabel_valorarDificE1MouseClicked

    private void jLabel_valorarDificE2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarDificE2MouseClicked
        
        jLabel_valorarDificE1.setIcon(estrellaGroga);
        jLabel_valorarDificE2.setIcon(estrellaGroga);
        jLabel_valorarDificE3.setIcon(estrellaBlanca);
        jLabel_valorarDificE4.setIcon(estrellaBlanca);
        jLabel_valorarDificE5.setIcon(estrellaBlanca);
        
        dific = 2;
//        if(r!=null){
//            r.setDific(dific);
//        }        
        
    }//GEN-LAST:event_jLabel_valorarDificE2MouseClicked

    private void jLabel_valorarDificE3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarDificE3MouseClicked
        
        jLabel_valorarDificE1.setIcon(estrellaGroga);
        jLabel_valorarDificE2.setIcon(estrellaGroga);
        jLabel_valorarDificE3.setIcon(estrellaGroga);
        jLabel_valorarDificE4.setIcon(estrellaBlanca);
        jLabel_valorarDificE5.setIcon(estrellaBlanca);
        
        dific = 3;
//        if(r!=null){
//            r.setDific(dific);
//        }        
        
    }//GEN-LAST:event_jLabel_valorarDificE3MouseClicked

    private void jLabel_valorarDificE4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarDificE4MouseClicked
        
        jLabel_valorarDificE1.setIcon(estrellaGroga);
        jLabel_valorarDificE2.setIcon(estrellaGroga);
        jLabel_valorarDificE3.setIcon(estrellaGroga);
        jLabel_valorarDificE4.setIcon(estrellaGroga);
        jLabel_valorarDificE5.setIcon(estrellaBlanca);
        
        dific = 4;
//        if(r!=null){
//            r.setDific(dific);
//        }        
        
    }//GEN-LAST:event_jLabel_valorarDificE4MouseClicked

    private void jLabel_valorarDificE5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_valorarDificE5MouseClicked
        
        jLabel_valorarDificE1.setIcon(estrellaGroga);
        jLabel_valorarDificE2.setIcon(estrellaGroga);
        jLabel_valorarDificE3.setIcon(estrellaGroga);
        jLabel_valorarDificE4.setIcon(estrellaGroga);
        jLabel_valorarDificE5.setIcon(estrellaGroga);
        
        dific = 5;
//        if(r!=null){
//            r.setDific(dific);
//        }        
        
    }//GEN-LAST:event_jLabel_valorarDificE5MouseClicked

    private void jButton_desarValoracioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_desarValoracioMouseClicked
        
        try {
            text = jTextArea_escriureComentari.getText();
            
            Usuari usuari_company = (Usuari) jComboBox_seleccionarCompany.getSelectedItem();
            if(usuari_company != null){
                login_usuari_company = usuari_company.getLogin();
            }
            
            //public Comentari(String text, Integer vInf, boolean feta, Integer vSeg, Integer vPai, Integer dific, Usuari loginUsuari, Ruta idRuta)
            Comentari c;
            if(usuari_company!=null){
                c = new Comentari(text,v_inf,feta,v_seg,v_pai,dific,usuari_company,ruta_seleccionada);
            }else{
                c = new Comentari(text,v_inf,feta,v_seg,v_pai,dific,usuari_loginat,ruta_seleccionada);
            }
            
            
            
            
            
            
            int comentari_inserit = gestorBDWikilocJdbc.afegirComentari(c, usuari_loginat.getLogin(), ruta_seleccionada.getId());
            
            if(comentari_inserit!=-1){
                System.out.println("Comentari inserit correctament!");             
                
                if(usuari_company!=null){
                    
                    
                    
                    //public Fetes(Usuari loginUsuari, Date mt, Ruta idRuta) {
                    if (gestorBDWikilocJdbc.afegirFetes(new Fetes(usuari_loginat, new Date(), ruta_seleccionada))) {

                        if (gestorBDWikilocJdbc.afegirFetes(new Fetes(usuari_company, new Date(), ruta_seleccionada))) {
                            
                            if (gestorBDWikilocJdbc.afegirCompany(usuari_company, comentari_inserit)) {

                                gestorBDWikilocJdbc.confirmarCanvis();

                                JOptionPane.showConfirmDialog(null, "Comentari inserit correctament",
                                        "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                System.out.println("El company no s'ha inserit");
                                gestorBDWikilocJdbc.desferCanvis();
                                JOptionPane.showConfirmDialog(null, "Error el company no s'ha inserit",
                                        "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                            
                            

                        } else {
                            System.out.println("ERROR AL INSERIR EL COMPANY A FETES");
                            JOptionPane.showConfirmDialog(null, "Error a l'inserir el company a fetes",
                                    "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else {
                        gestorBDWikilocJdbc.desferCanvis();
                        System.out.println("ERROR AL INSERIR EL USUARI A FETES");
                        JOptionPane.showConfirmDialog(null, "Error a l'inserir l'usuari a fetes",
                                "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    

                    
                    
                    
                }else{
                    if(feta){
                        if (gestorBDWikilocJdbc.afegirFetes(new Fetes(usuari_loginat, new Date(), ruta_seleccionada))) {
                            gestorBDWikilocJdbc.confirmarCanvis();

                            JOptionPane.showConfirmDialog(null, "Comentari inserit correctament",
                                        "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else{
                        gestorBDWikilocJdbc.confirmarCanvis();

                        JOptionPane.showConfirmDialog(null, "Comentari inserit correctament",
                                        "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                

                
            }else{
                System.out.println("El comentari no s'ha inserit");
                gestorBDWikilocJdbc.desferCanvis();
                JOptionPane.showConfirmDialog(null, "Error el comentari no s'ha inserit",
                                "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (GestorBDWikilocException ex) {
            System.out.println("Error: "+ex.getMessage());
            try {
                gestorBDWikilocJdbc.desferCanvis();
            } catch (GestorBDWikilocException ex1) {
                
            }
        }
        
        try {
  
            if(tableModel.getRowCount() > 0){
                for(int i = tableModel.getRowCount()-1; i > -1; i--){
                    tableModel.removeRow(i);
                }
            }
            
            llistaRutes = gestorBDWikilocJdbc.obtenirLlistaRuta();
            
            tableModel = (DefaultTableModel) jTable_rutesTotals.getModel();
            Object rowData[] = new Object[8];
            
            format = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("LLISTA RUTES FETES: "+llistaRutes.size());
            for (Ruta r : llistaRutes) {
              
                Fetes f = gestorBDWikilocJdbc.haFetRuta(r, usuari_loginat);
                
                if(f!=null){
                    
                    rowData[0] = format.format(f.getMt());
                    rowData[5] = true;
                }else{
                    rowData[5] = false;
                }
                
                
                rowData[1] = r.getTitol();
                rowData[2] = r.getDist();
                rowData[3] = r.getTemps();
                rowData[4] = r.getDific();
                //rowData[5] = (f!=null?"Si":"No");
                        
                tableModel.addRow(rowData);
            }
                        
            
        } catch (GestorBDWikilocException ex) {
            System.out.println("Error en carregar la taula de rutes fetes");
        }

        jTextArea_escriureComentari.setText("");

        try {
            
            double mitjaVinf = gestorBDWikilocJdbc.mitjaVinf(ruta_seleccionada.getId());
            if (mitjaVinf >= 0.0 && mitjaVinf < 0.5) {
                //buides
                jLabel_mostrarInfE1.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVinf >= 0.5 && mitjaVinf < 1.5) {
                //estrella 1
                jLabel_mostrarInfE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVinf >= 1.5 && mitjaVinf < 2.5) {
                //estrella 2
                jLabel_mostrarInfE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVinf >= 2.5 && mitjaVinf < 3.5) {
                //estrella 3
                jLabel_mostrarInfE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarInfE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVinf >= 3.5 && mitjaVinf < 4.5) {
                //estrella 4
                jLabel_mostrarInfE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVinf >= 4.5 && mitjaVinf <= 5.0) {
                //estrella 5
                jLabel_mostrarInfE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarInfE5.setIcon(estrellaGrogaMini);
            }
            
            double mitjaVseg = gestorBDWikilocJdbc.mitjaVseg(ruta_seleccionada.getId());
            if (mitjaVseg >= 0.0 && mitjaVseg < 0.5) {
                //buides
                jLabel_mostrarSegE1.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVseg >= 0.5 && mitjaVseg < 1.5) {
                //estrella 1
                jLabel_mostrarSegE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVseg >= 1.5 && mitjaVseg < 2.5) {
                //estrella 2
                jLabel_mostrarSegE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVseg >= 2.5 && mitjaVseg < 3.5) {
                //estrella 3
                jLabel_mostrarSegE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarSegE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVseg >= 3.5 && mitjaVseg < 4.5) {
                //estrella 4
                jLabel_mostrarSegE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVseg >= 4.5 && mitjaVseg <= 5.0) {
                //estrella 5
                jLabel_mostrarSegE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarSegE5.setIcon(estrellaGrogaMini);
            }
            
            double mitjaVpai = gestorBDWikilocJdbc.mitjaVpai(ruta_seleccionada.getId());
            if (mitjaVpai >= 0.0 && mitjaVpai < 0.5) {
                //buides
                jLabel_mostrarPaiE1.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVpai >= 0.5 && mitjaVpai < 1.5) {
                //estrella 1
                jLabel_mostrarPaiE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVpai >= 1.5 && mitjaVpai < 2.5) {
                //estrella 2
                jLabel_mostrarPaiE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVpai >= 2.5 && mitjaVpai < 3.5) {
                //estrella 3
                jLabel_mostrarPaiE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarPaiE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVpai >= 3.5 && mitjaVpai < 4.5) {
                //estrella 4
                jLabel_mostrarPaiE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVpai >= 4.5 && mitjaVpai <= 5.0) {
                //estrella 5
                jLabel_mostrarPaiE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarPaiE5.setIcon(estrellaGrogaMini);
            }
            
            double mitjaVdific = gestorBDWikilocJdbc.mitjaVdific(ruta_seleccionada.getId());
            if (mitjaVdific >= 0.0 && mitjaVdific < 0.5) {
                //buides
                jLabel_mostrarDificE1.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVdific >= 0.5 && mitjaVdific < 1.5) {
                //estrella 1
                jLabel_mostrarDificE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE2.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVdific >= 1.5 && mitjaVdific < 2.5) {
                //estrella 2
                jLabel_mostrarDificE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE3.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVdific >= 2.5 && mitjaVdific < 3.5) {
                //estrella 3
                jLabel_mostrarDificE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE4.setIcon(estrellaBlancaMini);
                jLabel_mostrarDificE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVdific >= 3.5 && mitjaVdific < 4.5) {
                //estrella 4
                jLabel_mostrarDificE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE5.setIcon(estrellaBlancaMini);
            } else if (mitjaVdific >= 4.5 && mitjaVdific <= 5.0) {
                //estrella 5
                jLabel_mostrarDificE1.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE2.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE3.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE4.setIcon(estrellaGrogaMini);
                jLabel_mostrarDificE5.setIcon(estrellaGrogaMini);
            }
            
            
            
            List<Comentari> llistaComentaris = gestorBDWikilocJdbc.obtenirLlistaComentaris(ruta_seleccionada.getId());
            
            
            System.out.println("SIZE LLISTA COMENTARIS "+llistaComentaris.size());
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
            System.out.println("INFO COMENTARI: "+info_comentari);
            
            jTextArea_comentarisTotals.setText(info_comentari);
            
            
            
        } catch (GestorBDWikilocException ex) {
            System.out.println("ERROR DEL CATCH: "+ex.getMessage());
            Logger.getLogger(panellCataleg.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        
        jPanel4.setVisible(false);
        jPanel3.setVisible(false);
        jComboBox_seleccionarCompany.setSelectedIndex(-1);
        jComboBox_seleccionarCompany.setVisible(false);
        jButton_desarValoracio.setEnabled(false);
        
        

        jLabel10.setVisible(false);
        jLabel30.setVisible(false);
        toggleButton_haFetRuta.setVisible(false);
        jLabel31.setVisible(false);
        jLabel15.setVisible(false);
        jSeparator4.setVisible(false);
        jTextArea_escriureComentari.setVisible(false);
        jScrollPane3.setVisible(false);
        jComboBox_seleccionarCompany.setVisible(false);
        jButton_desarValoracio.setVisible(false);
        jLabel1.setVisible(false);
        jLabel_valorarInfE1.setVisible(false);
        jLabel_valorarInfE2.setVisible(false);
        jLabel_valorarInfE3.setVisible(false);
        jLabel_valorarInfE4.setVisible(false);
        jLabel_valorarInfE5.setVisible(false);
        jLabel11.setVisible(false);
        jLabel_valorarSegE1.setVisible(false);
        jLabel_valorarSegE2.setVisible(false);
        jLabel_valorarSegE3.setVisible(false);
        jLabel_valorarSegE4.setVisible(false);
        jLabel_valorarSegE5.setVisible(false);
        jLabel12.setVisible(false);
        jLabel_valorarPaiE1.setVisible(false);
        jLabel_valorarPaiE2.setVisible(false);
        jLabel_valorarPaiE3.setVisible(false);
        jLabel_valorarPaiE4.setVisible(false);
        jLabel_valorarPaiE5.setVisible(false);
        jLabel13.setVisible(false);
        jLabel_valorarDificE1.setVisible(false);
        jLabel_valorarDificE2.setVisible(false);
        jLabel_valorarDificE3.setVisible(false);
        jLabel_valorarDificE4.setVisible(false);
        jLabel_valorarDificE5.setVisible(false);
        
        toggleButton_haFetRuta.setSelected(false);
        feta = false;
        
        jLabel_valorarInfE1.setIcon(estrellaGroga);
        jLabel_valorarInfE2.setIcon(estrellaBlanca);
        jLabel_valorarInfE3.setIcon(estrellaBlanca);
        jLabel_valorarInfE4.setIcon(estrellaBlanca);
        jLabel_valorarInfE5.setIcon(estrellaBlanca);
        
        jLabel_valorarSegE1.setIcon(estrellaGroga);
        jLabel_valorarSegE2.setIcon(estrellaBlanca);
        jLabel_valorarSegE3.setIcon(estrellaBlanca);
        jLabel_valorarSegE4.setIcon(estrellaBlanca);
        jLabel_valorarSegE5.setIcon(estrellaBlanca);       
        
        jLabel_valorarPaiE1.setIcon(estrellaGroga);
        jLabel_valorarPaiE2.setIcon(estrellaBlanca);
        jLabel_valorarPaiE3.setIcon(estrellaBlanca);
        jLabel_valorarPaiE4.setIcon(estrellaBlanca);
        jLabel_valorarPaiE5.setIcon(estrellaBlanca);
        
        jLabel_valorarDificE1.setIcon(estrellaGroga);
        jLabel_valorarDificE2.setIcon(estrellaBlanca);
        jLabel_valorarDificE3.setIcon(estrellaBlanca);
        jLabel_valorarDificE4.setIcon(estrellaBlanca);
        jLabel_valorarDificE5.setIcon(estrellaBlanca);
        
        
    }//GEN-LAST:event_jButton_desarValoracioMouseClicked

    private void jTextArea_escriureComentariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea_escriureComentariKeyReleased
        
        if(jTextArea_escriureComentari.getText().length() != 0){
            
            jButton_desarValoracio.setEnabled(true);
            
        }else{
            jButton_desarValoracio.setEnabled(false);
        }
        
    }//GEN-LAST:event_jTextArea_escriureComentariKeyReleased

    private void jButton_cercaFiltreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cercaFiltreMouseEntered
        jButton_cercaFiltre.setIcon(fotoCercarHoover);
        jButton_cercaFiltre.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_cercaFiltreMouseEntered

    private void jButton_cercaFiltreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cercaFiltreMouseExited
        jButton_cercaFiltre.setIcon(fotoCercar);
        jButton_cercaFiltre.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_cercaFiltreMouseExited

    private void jButton_netejaFiltreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejaFiltreMouseEntered
        jButton_netejaFiltre.setIcon(fotoNetejarHoover);
        jButton_netejaFiltre.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_netejaFiltreMouseEntered

    private void jButton_netejaFiltreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejaFiltreMouseExited
        jButton_netejaFiltre.setIcon(fotoNetejar);
        jButton_netejaFiltre.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_netejaFiltreMouseExited

    private void jButton_valorarRutaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_valorarRutaMouseEntered
        jButton_valorarRuta.setIcon(fotoComentarHoover);
        jButton_valorarRuta.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_valorarRutaMouseEntered

    private void jButton_valorarRutaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_valorarRutaMouseExited
        jButton_valorarRuta.setIcon(fotoComentar);
        jButton_valorarRuta.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_valorarRutaMouseExited

    private void jButton_desarValoracioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_desarValoracioMouseEntered
        
        if(jButton_desarValoracio.isEnabled()){
            jButton_desarValoracio.setIcon(fotoDesarCanvisHoover);
            jButton_desarValoracio.setBackground(new Color(255, 163, 0));
        }

    }//GEN-LAST:event_jButton_desarValoracioMouseEntered

    private void jButton_desarValoracioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_desarValoracioMouseExited
        
        if(jButton_desarValoracio.isEnabled()){
            jButton_desarValoracio.setIcon(fotoDesarCanvis);
            jButton_desarValoracio.setBackground(new Color(76,140,43));
        }

    }//GEN-LAST:event_jButton_desarValoracioMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_cercaFiltre;
    private javax.swing.JButton jButton_desarValoracio;
    private javax.swing.JButton jButton_netejaFiltre;
    private javax.swing.JButton jButton_valorarRuta;
    private javax.swing.JCheckBox jCheckBox_feta;
    private javax.swing.JComboBox<Usuari> jComboBox_filtreCreador;
    private javax.swing.JComboBox<String> jComboBox_filtreDific;
    private javax.swing.JComboBox<Usuari> jComboBox_seleccionarCompany;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_mostrarDificE1;
    private javax.swing.JLabel jLabel_mostrarDificE2;
    private javax.swing.JLabel jLabel_mostrarDificE3;
    private javax.swing.JLabel jLabel_mostrarDificE4;
    private javax.swing.JLabel jLabel_mostrarDificE5;
    private javax.swing.JLabel jLabel_mostrarInfE1;
    private javax.swing.JLabel jLabel_mostrarInfE2;
    private javax.swing.JLabel jLabel_mostrarInfE3;
    private javax.swing.JLabel jLabel_mostrarInfE4;
    private javax.swing.JLabel jLabel_mostrarInfE5;
    private javax.swing.JLabel jLabel_mostrarPaiE1;
    private javax.swing.JLabel jLabel_mostrarPaiE2;
    private javax.swing.JLabel jLabel_mostrarPaiE3;
    private javax.swing.JLabel jLabel_mostrarPaiE4;
    private javax.swing.JLabel jLabel_mostrarPaiE5;
    private javax.swing.JLabel jLabel_mostrarSegE1;
    private javax.swing.JLabel jLabel_mostrarSegE2;
    private javax.swing.JLabel jLabel_mostrarSegE3;
    private javax.swing.JLabel jLabel_mostrarSegE4;
    private javax.swing.JLabel jLabel_mostrarSegE5;
    private javax.swing.JLabel jLabel_valorarDificE1;
    private javax.swing.JLabel jLabel_valorarDificE2;
    private javax.swing.JLabel jLabel_valorarDificE3;
    private javax.swing.JLabel jLabel_valorarDificE4;
    private javax.swing.JLabel jLabel_valorarDificE5;
    private javax.swing.JLabel jLabel_valorarInfE1;
    private javax.swing.JLabel jLabel_valorarInfE2;
    private javax.swing.JLabel jLabel_valorarInfE3;
    private javax.swing.JLabel jLabel_valorarInfE4;
    private javax.swing.JLabel jLabel_valorarInfE5;
    private javax.swing.JLabel jLabel_valorarPaiE1;
    private javax.swing.JLabel jLabel_valorarPaiE2;
    private javax.swing.JLabel jLabel_valorarPaiE3;
    private javax.swing.JLabel jLabel_valorarPaiE4;
    private javax.swing.JLabel jLabel_valorarPaiE5;
    private javax.swing.JLabel jLabel_valorarSegE1;
    private javax.swing.JLabel jLabel_valorarSegE2;
    private javax.swing.JLabel jLabel_valorarSegE3;
    private javax.swing.JLabel jLabel_valorarSegE4;
    private javax.swing.JLabel jLabel_valorarSegE5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable_rutesTotals;
    private javax.swing.JTextArea jTextArea_comentarisTotals;
    private javax.swing.JTextArea jTextArea_escriureComentari;
    private javax.swing.JTextField jTextField_filtreDist;
    private javax.swing.JTextField jTextField_filtreTitol;
    private org.milaifontanals.wikiloc.toggle.ToggleButton toggleButton_haFetRuta;
    // End of variables declaration//GEN-END:variables
}
