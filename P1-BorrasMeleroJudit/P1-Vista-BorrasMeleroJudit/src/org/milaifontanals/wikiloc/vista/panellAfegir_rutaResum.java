/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Dimension;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import org.milaifontanals.wikiloc.jdbc.GestorBDWikilocJdbc;
import org.milaifontanals.wikiloc.model.Punt;
import org.milaifontanals.wikiloc.model.Ruta;
import org.milaifontanals.wikiloc.model.Usuari;
import org.milaifontanals.wikiloc.persistencia.GestorBDWikilocException;

/**
 *
 * @author JUDIT
 */
public class panellAfegir_rutaResum extends javax.swing.JPanel {

    /**
     * Creates new form panellAfegir_rutaResum
     */
    Usuari usuari_loginat;
    List<Punt> llistaPuntsRuta;
    Ruta ruta;
    private GestorBDWikilocJdbc gestorBDWikilocJdbc;
    
    public panellAfegir_rutaResum(Usuari usuari_loginat, Ruta nova_ruta, List<Punt> punts_p) {
        initComponents();
        this.usuari_loginat = usuari_loginat;
        ruta = nova_ruta;
        llistaPuntsRuta = punts_p;
        
        try {
            gestorBDWikilocJdbc = new GestorBDWikilocJdbc();
        } catch (GestorBDWikilocException ex) {
            
        }
        
        
        try{
            
            String info_ruta = "";
            int i1=1,i2=2,i3=3,i4=4,i5=5;
            int hours, minutes;
            String reempl;
            String i = "";
            
            jTextPane1.setContentType("text/html");
            
            jTextPane1.setPreferredSize(new Dimension(500, 500));
            jTextPane1.setMinimumSize(new Dimension(500, 500));
            jTextPane1.setMaximumSize(new Dimension(500, 500));
            jScrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            jScrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            info_ruta += "<font face='Calibri' color='green'><b>Títol: </b>" + ruta.getTitol() + "</font><br><br>";
            
            info_ruta += "<font face='Calibri'><b>Descripció en text pla: </b>" + ruta.getDescRuta() + "</font><br>";
                            

            reempl = ruta.getDist() + "";
            reempl = reempl.replace('.', ',');
            
            info_ruta += "<font face='Calibri'><b>Distància: </b>" + reempl + " km</font><br>";
            
            hours = ruta.getTemps() / 60;
            minutes = ruta.getTemps() % 60;
            
            info_ruta += "<font face='Calibri'><b>Temps: </b></font><br>";
            info_ruta += "<font face='Calibri'>Hores: " + hours + "h</font><br>";
            info_ruta += "<font face='Calibri'>Minuts: " + minutes + "m</font><br>";
                               
            info_ruta += "<font face='Calibri'><b>Desnivell positiu: </b>" + ruta.getDesnP() + " metres</font><br>";
            info_ruta += "<font face='Calibri'><b>Desnivell negatiu: </b>" + ruta.getDesnN() + " metres</font><br>";
             
            if (ruta.getDific() == 1) {
                i = "★☆☆☆☆";
            } else if (ruta.getDific() == 2) {
                i = "★★☆☆☆";
            } else if (ruta.getDific() == 3) {
                i = "★★★☆☆";
            } else if (ruta.getDific() == 4) {
                i = "★★★★☆";
            } else if (ruta.getDific() == 5) {
                i = "★★★★★";
            }
            
            info_ruta += "<font face='Calibri'><b>Dificultat: </b>" + i + "</font><br>";

            info_ruta += "<font face='Calibri'><b>Descripció en format HTML: </b>" + ruta.getTextRuta() + "</font><br>"; 
            
            jTextPane1.setText(info_ruta);

            String info_punts = "";
            
            jTextPane2.setContentType("text/html");
            
            jTextPane2.setPreferredSize(new Dimension(500, 300));
            jTextPane2.setMinimumSize(new Dimension(500, 300));
            jTextPane2.setMaximumSize(new Dimension(500, 300));
            jScrollPane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            jScrollPane4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            for (Punt p : llistaPuntsRuta) {

                info_punts += "<font face='Calibri' color='green'><b>PUNT </b>" + p.getOrdre() + "</font><br><br>";
                
                info_punts += "<font face='Calibri'><b>Nom: </b>" + p.getNom() + "</font><br>";
                info_punts += "<font face='Calibri'><b>Descripció: </b>" + p.getDescPunt() + "</font><br>";
                info_punts += "<font face='Calibri'><b>Latitud: </b>" + p.getLat() + "</font><br>";
                info_punts += "<font face='Calibri'><b>Longitud: </b>" + p.getLon() + "</font><br>";
                info_punts += "<font face='Calibri'><b>Altitud: </b>" + p.getAlt() + "</font><br>";
                info_punts += "<font face='Calibri'><b>Tipus de punt: </b>" + p.getIdTipus().getNom() + "</font><br>";
                if(p.getTmpFoto()==null){
                    info_punts += "<font face='Calibri'><b>Imatge: </b> Sense imatge</font><br><br><br>";
                }else{
                    info_punts += "<font face='Calibri'><b>Imatge: </b>" + p.getTmpFoto() + "</font><br><br><br>";
                }
                

            }

            jTextPane2.setText(info_punts);           
            
        }catch(Exception ex){
            
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
        jButton_desarBD = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton_desarBD.setBackground(new java.awt.Color(76, 140, 43));
        jButton_desarBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/desarCanvis.png"))); // NOI18N
        jButton_desarBD.setBorder(null);
        jButton_desarBD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_desarBD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_desarBDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_desarBDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_desarBDMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 3, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(76, 140, 43));
        jLabel2.setText("Atenció: els canvis no es desaran si no completeu les dades requerides de la ruta i els punts de ruta seguint l'ordre de les pantalles!");

        jTextPane1.setEditable(false);
        jTextPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextPane1.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextPane1.setForeground(new java.awt.Color(153, 153, 153));
        jTextPane1.setMaximumSize(new java.awt.Dimension(500, 500));
        jTextPane1.setMinimumSize(new java.awt.Dimension(500, 500));
        jScrollPane3.setViewportView(jTextPane1);

        jTextPane2.setEditable(false);
        jTextPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTextPane2.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N
        jTextPane2.setForeground(new java.awt.Color(153, 153, 153));
        jTextPane2.setMaximumSize(new java.awt.Dimension(500, 300));
        jTextPane2.setMinimumSize(new java.awt.Dimension(500, 300));
        jScrollPane4.setViewportView(jTextPane2);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Resum de les dades introduïdes");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Dades de la ruta:");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Dades dels punts de la ruta:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton_desarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton_desarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addContainerGap(341, Short.MAX_VALUE))
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

    private void jButton_desarBDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_desarBDMouseClicked
        
        
        //Comprovar que tots els camps obligatoris per crear una ruta i els punts estiguin introduits
        int i = 0;
        
        if(ruta.getTitol().length()>0 && ruta.getDescRuta().length()>0 && ruta.getTextRuta().length()>0){
            for(Punt p : llistaPuntsRuta){
                if(p.getNom().length()>0 && p.getDescPunt().length()>0 && p.getIdTipus() != null){
                    i++;
                }else{
                    break;
                }
            }
            
            if(i == llistaPuntsRuta.size()){
                try {
                    if(gestorBDWikilocJdbc.afegirRutaAmbPunts(ruta, llistaPuntsRuta)){
                        //System.out.println("HAS AFEGIT CORRECTAMENT LA RUTA I ELS SEUS PUNTS");
                        File f = new File("info_ruta.txt");
                        f.delete();
                        
                        new CambiaPanel(this,new panellAfegir_ruta(null, usuari_loginat,null,null,1));
                        
                        
                        
                    }else{
                        //System.out.println("NO HAS INTRODUIT LA RUTA BE");
                    }
                    
                } catch (GestorBDWikilocException ex) {
                    //System.out.println("ERROR: "+ex.getMessage());
                }
            }else{
                //System.out.println("NO HAS INTRODUIT TOTS ELS CAMPS OBLIGATORIS DELS PUNTS DE RUTA");
            }
        }else{
            //System.out.println("NO HAS INTRODUIT TOTS ELS CAMPS OBLIGATORIS DE LA RUTA");
        }
        

        
        
        
    }//GEN-LAST:event_jButton_desarBDMouseClicked

    private void jButton_desarBDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_desarBDMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_desarBDMouseEntered

    private void jButton_desarBDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_desarBDMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_desarBDMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_desarBD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables
}
