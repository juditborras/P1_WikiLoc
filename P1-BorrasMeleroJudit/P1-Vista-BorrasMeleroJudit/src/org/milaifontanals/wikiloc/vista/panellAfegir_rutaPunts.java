/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
import org.milaifontanals.wikiloc.breadcrumb.com.raven.component.Breadcrumb;
import org.milaifontanals.wikiloc.components.TextPrompt;
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
    
    ImageIcon fotoDesarCanvis = new ImageIcon("img"+File.separator+"desarCanvis.png");
    ImageIcon fotoDesarCanvisHoover = new ImageIcon("img"+File.separator+"desarCanvisHoover.png");
    ImageIcon fotoEliminarPunt = new ImageIcon("img"+File.separator+"eliminarPunt.png");
    ImageIcon fotoEliminarPuntHoover = new ImageIcon("img"+File.separator+"eliminarPuntHoover.png");
    ImageIcon fotoEliminarPunts = new ImageIcon("img"+File.separator+"eliminarPunts.png");
    ImageIcon fotoEliminarPuntsHoover = new ImageIcon("img"+File.separator+"eliminarPuntsHoover.png");
    ImageIcon fotoAfegirPunt = new ImageIcon("img"+File.separator+"afegirPunt.png");
    ImageIcon fotoAfegirPuntHoover = new ImageIcon("img"+File.separator+"afegirPuntHoover.png");
    ImageIcon fotoFletxaAmunt = new ImageIcon("img"+File.separator+"fletxaAmunt.png");
    ImageIcon fotoFletxaAmuntHoover = new ImageIcon("img"+File.separator+"fletxaAmuntHoover.png");
    ImageIcon fotoFletxaAvall = new ImageIcon("img"+File.separator+"fletxaAvall.png");
    ImageIcon fotoFletxaAvallHoover = new ImageIcon("img"+File.separator+"fletxaAvallHoover.png");
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
    
    
    public panellAfegir_rutaPunts(Breadcrumb breadcrumb1,Ruta nova_ruta, List<Punt> punts_p) {
        
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new Color(255,255,255)));
        UIManager.put("List.selectionBackground", new ColorUIResource(new Color(255,174,0)));
        
        initComponents();
        
        jComboBox_tipusPunt.setSize(165, 33);
        jComboBox_tipusPunt.setUI(panellCataleg.ColorArrowUI.createUI(jComboBox_tipusPunt));
        
        TextPrompt placeHolder_nom = new TextPrompt("Mirador Gresolet",jTextField_nomPunt);
        TextPrompt placeHolder_desc = new TextPrompt("Situat a 1.557 metres d'alçada",jTextArea_descPunt);
        TextPrompt placeHolder_lat = new TextPrompt("550",jTextField_latPunt);
        TextPrompt placeHolder_lon = new TextPrompt("389",jTextField_lonPunt);
        TextPrompt placeHolder_alt = new TextPrompt("1557",jTextField_altPunt);
        
        jButton_desarCanvisPunts.setDisabledIcon(new ImageIcon(createDisabledIcon(jButton_desarCanvisPunts.getIcon())));
        
        this.breadcrumb1 = breadcrumb1;
        dlm = new DefaultListModel();
        
        if(punts_p!=null){
            llistaPuntsRuta = punts_p;
            
            dlm.clear();
            //System.out.println("QT PUNTS DE RUTA: "+dlm);
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
        
        jLabel2.setVisible(false);
        jSeparator1.setVisible(false);
        jLabel3.setVisible(false);
        jSeparator2.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jSeparator3.setVisible(false);
        jLabel6.setVisible(false);
        jSeparator4.setVisible(false);
        jLabel7.setVisible(false);
        jSeparator5.setVisible(false);
        jLabel8.setVisible(false);
        
        
        jTextField_ordrePunt.setText(llistaPuntsRuta.size()+1+"");
        
        llistaTipusPunts = new ArrayList();
        
        try {
            gestorBDWikilocJdbc = new GestorBDWikilocJdbc();
            
            llistaTipusPunts = gestorBDWikilocJdbc.obtenirLlistaTipus();

            //System.out.println("LEN: " + llistaTipusPunts.size());
            Tipus[] tip = new Tipus[llistaTipusPunts.size()];
            tip = llistaTipusPunts.toArray(tip);

            //jComboBox_tipusPunt = new JComboBox(tip);
            //System.out.println("ITEM COUNT: " + jComboBox_tipusPunt.getItemCount());
            //jComboBox_tipusPunt.setSelectedIndex(-1);

            jComboBox_tipusPunt.setModel(new DefaultComboBoxModel<>(llistaTipusPunts.toArray(new Tipus[0])));
            jComboBox_tipusPunt.setSelectedIndex(-1);
            
            //jComboBox_tipusPunt.setModel(new DefaultComboBoxModel<>(llistaTipusPunts.toArray(new Tipus[0])));


        } catch (GestorBDWikilocException ex) {
           JOptionPane.showMessageDialog(null,
                            "Error: No s'ha pogut els tipus de punts de la ruta: "+ex.getMessage() ,
                            "Error - Obtenir tipus de punts de la ruta", JOptionPane.ERROR_MESSAGE);
        }
        
        //System.out.println("NOVA RUTA: "+nova_ruta);
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList_puntsRuta = new javax.swing.JList<>();
        jButton_desarCanvisPunts = new javax.swing.JButton();
        jButton_netejarPunts = new javax.swing.JButton();
        jButton_eliminarPunt = new javax.swing.JButton();
        jButton_afegirPunt = new javax.swing.JButton();
        jButton_pujar = new javax.swing.JButton();
        jButton_baixar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_ordrePunt = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jTextField_nomPunt = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_descPunt = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jTextField_latPunt = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jTextField_lonPunt = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jTextField_altPunt = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jButton_seleccionarFoto = new javax.swing.JButton();
        jLabel_fotoPunt = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox_tipusPunt = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(76, 140, 43));
        jLabel1.setText("Atenció: els canvis no es desaran si no completeu les dades dels punts de la ruta seguint l'ordre de les pantalles!");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jList_puntsRuta.setBorder(null);
        jList_puntsRuta.setFont(new java.awt.Font("Calibri", 2, 20)); // NOI18N
        jList_puntsRuta.setForeground(new java.awt.Color(153, 153, 153));
        jList_puntsRuta.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList_puntsRutaValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList_puntsRuta);

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

        jButton_netejarPunts.setBackground(new java.awt.Color(76, 140, 43));
        jButton_netejarPunts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/eliminarPunts.png"))); // NOI18N
        jButton_netejarPunts.setToolTipText("Eliminar tots els punts de ruta");
        jButton_netejarPunts.setBorder(null);
        jButton_netejarPunts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_netejarPunts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_netejarPuntsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_netejarPuntsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_netejarPuntsMouseExited(evt);
            }
        });

        jButton_eliminarPunt.setBackground(new java.awt.Color(76, 140, 43));
        jButton_eliminarPunt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/eliminarPunt.png"))); // NOI18N
        jButton_eliminarPunt.setToolTipText("Eliminar el punt de ruta seleccionat");
        jButton_eliminarPunt.setBorder(null);
        jButton_eliminarPunt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_eliminarPunt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_eliminarPuntMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_eliminarPuntMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_eliminarPuntMouseExited(evt);
            }
        });

        jButton_afegirPunt.setBackground(new java.awt.Color(76, 140, 43));
        jButton_afegirPunt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/afegirPunt.png"))); // NOI18N
        jButton_afegirPunt.setToolTipText("Afegir nou punt de ruta");
        jButton_afegirPunt.setBorder(null);
        jButton_afegirPunt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_afegirPunt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_afegirPuntMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_afegirPuntMouseExited(evt);
            }
        });
        jButton_afegirPunt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_afegirPuntActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_desarCanvisPunts, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_netejarPunts, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_eliminarPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_afegirPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_pujar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_baixar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_pujar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_baixar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_afegirPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton_desarCanvisPunts, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_eliminarPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_netejarPunts, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel2.setText("Número:");

        jTextField_ordrePunt.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_ordrePunt.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_ordrePunt.setBorder(null);
        jTextField_ordrePunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_ordrePuntKeyReleased(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(76, 140, 43));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel3.setText("Nom:");

        jTextField_nomPunt.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextField_nomPunt.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_nomPunt.setBorder(null);
        jTextField_nomPunt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_nomPuntKeyReleased(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(76, 140, 43));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel4.setText("Descripció:");

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
        jScrollPane2.setViewportView(jTextArea_descPunt);

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel5.setText("Latitud:");

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

        jSeparator3.setForeground(new java.awt.Color(76, 140, 43));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel6.setText("Longitud:");

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

        jSeparator4.setForeground(new java.awt.Color(76, 140, 43));

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel7.setText("Altitud:");

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

        jSeparator5.setForeground(new java.awt.Color(76, 140, 43));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator1)
                                    .addComponent(jTextField_ordrePunt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2)
                                    .addComponent(jTextField_nomPunt)))
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator3)
                            .addComponent(jTextField_latPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator4)
                            .addComponent(jTextField_lonPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator5)
                            .addComponent(jTextField_altPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_ordrePunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_nomPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_latPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_lonPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField_altPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

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

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jLabel8.setText("Tipus:");

        jComboBox_tipusPunt.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jComboBox_tipusPunt.setForeground(new java.awt.Color(153, 153, 153));
        jComboBox_tipusPunt.setBorder(null);
        jComboBox_tipusPunt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tipusPuntItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton_seleccionarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_fotoPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_tipusPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_fotoPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_seleccionarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox_tipusPunt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("Introduïu els paràmetres que componen els punts de la ruta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator6))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addContainerGap(621, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

//            System.out.println("ordre: "+jTextField_ordrePunt.getText().length());
//            System.out.println("nom: "+jTextField_nomPunt.getText().length());
//            System.out.println("textarea: "+jTextArea_descPunt.getText().length());
//            System.out.println("lat: "+jTextField_latPunt.getText().length());
//            System.out.println("lon: "+jTextField_lonPunt.getText().length());
//            System.out.println("alt: "+jTextField_altPunt.getText().length());
//            System.out.println("tipus: "+jComboBox_tipusPunt.getSelectedItem());
            
            if (jTextField_ordrePunt.getText().length() != 0 && jTextField_nomPunt.getText().length() != 0 && jTextArea_descPunt.getText().length() != 0 && jTextField_latPunt.getText().length() != 0 && jTextField_lonPunt.getText().length() != 0 && jTextField_altPunt.getText().length() != 0 && jComboBox_tipusPunt.getSelectedItem() != null) {
                jButton_desarCanvisPunts.setEnabled(true);
                dadesCorrectes = true;
                //System.out.println("nom punt entra");
            } else {
                jButton_desarCanvisPunts.setEnabled(false);
                dadesCorrectes = false;
                //System.out.println("nom punt no entra");
            }
        } else {
            //System.out.println("entra else i no hauria deeeeee");
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
            //System.out.println("SOC NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }else{
            //System.out.println("NO SOC NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        
        
        Punt punt = new Punt(jTextField_nomPunt.getText(),jTextArea_descPunt.getText(),Integer.parseInt(jTextField_latPunt.getText()),Integer.parseInt(jTextField_lonPunt.getText()),Integer.parseInt(jTextField_altPunt.getText()),Integer.parseInt(jTextField_ordrePunt.getText()),null,(Tipus)jComboBox_tipusPunt.getSelectedItem());
        punt.setTmpFoto(novaImatgeSeleccionada);
        punt.setTmpUrlFoto(tmpUrlFoto);
        llistaPuntsRuta.add(punt);
            //dlm = new DefaultListModel();
            dlm.clear();
            //System.out.println("QT PUNTS DE RUTA: "+dlm);
            for (Punt p : llistaPuntsRuta) {

                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());

            }
            jList_puntsRuta.setModel(dlm);
            jList_puntsRuta = new JList(dlm);
            //System.out.println(jList_puntsRuta.getModel().toString());


        
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
        
        jLabel2.setVisible(false);
        jSeparator1.setVisible(false);
        jLabel3.setVisible(false);
        jSeparator2.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jSeparator3.setVisible(false);
        jLabel6.setVisible(false);
        jSeparator4.setVisible(false);
        jLabel7.setVisible(false);
        jSeparator5.setVisible(false);
        jLabel8.setVisible(false);

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
                           "Eliminar punt", JOptionPane.YES_NO_OPTION,
                           JOptionPane.INFORMATION_MESSAGE);
        
        
        if (resposta == 0) {
            //dlm.removeElement(punt_seleccionat);
            //System.out.println("ESBORRARE: "+punt_seleccionat);
            
            Punt p_esborrar = null;
            int i=0;
            int pos_borrar = 0;
            for (Punt p : llistaPuntsRuta) {
                if(p.getNom().equals(punt_seleccionat.getNom()) && p.getOrdre()==punt_seleccionat.getOrdre()){
                   pos_borrar = i; 
                    //System.out.println("LLISTA ESBORRAR; "+pos_borrar);
                }
                i++;
            }
            
            llistaPuntsRuta.remove(pos_borrar);
            
            
            
            dlm.clear();
            //System.out.println("QT PUNTS DE RUTA: "+dlm);
            i=1;
            for (Punt p : llistaPuntsRuta) {
                p.setOrdre(i);
                dlm.addElement(p.getOrdre().toString() + " - " + p.getNom().toString());
                //System.out.println(p.getOrdre().toString() + " - " + p.getNom().toString());

                i++;
            }
            jList_puntsRuta.setModel(dlm);
            jList_puntsRuta = new JList(dlm);
            //System.out.println(jList_puntsRuta.getModel().toString());
        }
        
    }//GEN-LAST:event_jButton_eliminarPuntMouseClicked

    private void jButton_netejarPuntsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejarPuntsMouseClicked
        
        int resposta = JOptionPane.showConfirmDialog(null, "Estàs segur d'eliminar TOTS els punts de ruta associats a la ruta?",
                            "Eliminar punts de ruta", JOptionPane.YES_NO_OPTION,
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
            
            jLabel2.setVisible(false);
            jSeparator1.setVisible(false);
            jLabel3.setVisible(false);
            jSeparator2.setVisible(false);
            jLabel4.setVisible(false);
            jLabel5.setVisible(false);
            jSeparator3.setVisible(false);
            jLabel6.setVisible(false);
            jSeparator4.setVisible(false);
            jLabel7.setVisible(false);
            jSeparator5.setVisible(false);
            jLabel8.setVisible(false);

            jButton_desarCanvisPunts.setVisible(false);
        }
        
    }//GEN-LAST:event_jButton_netejarPuntsMouseClicked

    private void jList_puntsRutaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList_puntsRutaValueChanged
        
        if (!evt.getValueIsAdjusting()) {

            JList jlist = (JList) evt.getSource();

            int idx = jlist.getSelectedIndex();

            if (llistaPuntsRuta.size() > 0 && idx != -1) {
                //System.out.println("INDEX: " + idx);
                punt_seleccionat = llistaPuntsRuta.get(idx);
                //System.out.println("INDEX OBJ: "+punt_seleccionat);
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
            
            jLabel2.setVisible(true);
            jSeparator1.setVisible(true);
            jLabel3.setVisible(true);
            jSeparator2.setVisible(true);
            jLabel4.setVisible(true);
            jLabel5.setVisible(true);
            jSeparator3.setVisible(true);
            jLabel6.setVisible(true);
            jSeparator4.setVisible(true);
            jLabel7.setVisible(true);
            jSeparator5.setVisible(true);
            jLabel8.setVisible(true);

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
        
        jLabel2.setVisible(true);
        jSeparator1.setVisible(true);
        jLabel3.setVisible(true);
        jSeparator2.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        jSeparator3.setVisible(true);
        jLabel6.setVisible(true);
        jSeparator4.setVisible(true);
        jLabel7.setVisible(true);
        jSeparator5.setVisible(true);
        jLabel8.setVisible(true);

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

    private void jButton_eliminarPuntMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_eliminarPuntMouseEntered
        jButton_eliminarPunt.setIcon(fotoEliminarPuntHoover);
        jButton_eliminarPunt.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_eliminarPuntMouseEntered

    private void jButton_eliminarPuntMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_eliminarPuntMouseExited
        jButton_eliminarPunt.setIcon(fotoEliminarPunt);
        jButton_eliminarPunt.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_eliminarPuntMouseExited

    private void jButton_netejarPuntsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejarPuntsMouseEntered
        jButton_netejarPunts.setIcon(fotoEliminarPuntsHoover);
        jButton_netejarPunts.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_netejarPuntsMouseEntered

    private void jButton_netejarPuntsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejarPuntsMouseExited
        jButton_netejarPunts.setIcon(fotoEliminarPunts);
        jButton_netejarPunts.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_netejarPuntsMouseExited

    private void jButton_afegirPuntMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_afegirPuntMouseEntered
        jButton_afegirPunt.setIcon(fotoAfegirPuntHoover);
        jButton_afegirPunt.setBackground(new Color(255,163,0));
    }//GEN-LAST:event_jButton_afegirPuntMouseEntered

    private void jButton_afegirPuntMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_afegirPuntMouseExited
        jButton_afegirPunt.setIcon(fotoAfegirPunt);
        jButton_afegirPunt.setBackground(new Color(76,140,43));
    }//GEN-LAST:event_jButton_afegirPuntMouseExited

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


    public void modificacionsCampsPunt(){
        
        if(ordre_canviat || nomPunt_canviat || descPunt_canviat || lat_canviat || lon_canviat || alt_canviat || tipusPunt_canviada || fotoPunt_canviada || botoPujarClicat || botoBaixarClicat){
            jButton_desarCanvisPunts.setEnabled(true);
            //System.out.println("C");
        }else{
            jButton_desarCanvisPunts.setEnabled(false);
            //System.out.println("D");
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
            //System.out.println("DADES CORRECTES");

            if(breadcrumb1.getComponentCount()<4){
                    
                breadcrumb1.addItem("Resum"); 


            }
            //breadcrumb1.addItem("Item 2"); 

             
        }else{
//            System.out.println("DADES INCORRECTES!!");
//            System.out.println("RESUM: ");
//            System.out.println("jTextField_ordrePunt.getText().length(): "+jTextField_ordrePunt.getText().length());
//            System.out.println("jTextField_nomPunt.getText().length(): "+jTextField_nomPunt.getText().length());
//            System.out.println("jTextArea_descPunt.getText().length(): "+jTextArea_descPunt.getText().length());
//            System.out.println("jTextField_latPunt.getText().length(): "+jTextField_latPunt.getText().length());
//            System.out.println("jTextField_lonPunt.getText().length(): "+jTextField_lonPunt.getText().length());
//            System.out.println("jTextField_altPunt.getText().length(): "+jTextField_altPunt.getText().length());
//            System.out.println("jComboBox_tipusPunt.getSelectedItem(): "+jComboBox_tipusPunt.getSelectedItem());

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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_fotoPunt;
    private javax.swing.JList<String> jList_puntsRuta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextArea jTextArea_descPunt;
    private javax.swing.JTextField jTextField_altPunt;
    private javax.swing.JTextField jTextField_latPunt;
    private javax.swing.JTextField jTextField_lonPunt;
    private javax.swing.JTextField jTextField_nomPunt;
    private javax.swing.JTextField jTextField_ordrePunt;
    // End of variables declaration//GEN-END:variables
}
