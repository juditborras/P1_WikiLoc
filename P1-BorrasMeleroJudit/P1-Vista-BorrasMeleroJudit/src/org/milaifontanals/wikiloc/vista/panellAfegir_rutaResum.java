/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        String info_ruta = "";
        
        info_ruta += "Títol: " + ruta.getTitol() + "\n\n";
        info_ruta += "Descripció: " + ruta.getDescRuta() + "\n";
        info_ruta += "Descripció en fitxer HTML: " + ruta.getTextRuta() + "\n";
        info_ruta += "Distància: " + ruta.getDist() + "\n";
        info_ruta += "Temps: " + ruta.getTemps() + "\n";
        info_ruta += "DesnP: " + ruta.getDesnP() + "\n";
        info_ruta += "DesnN: " + ruta.getDesnN() + "\n";
        info_ruta += "Dificultat: " + ruta.getDific() + "\n";
        
        jTextArea_dadesRuta.setText(info_ruta);
        
        String info_punts = "";
        
        for(Punt p : llistaPuntsRuta){
            
            
            info_punts += "PUNT " + p.getOrdre() + "\n";
            info_punts += "Nom: " + p.getNom() + "\n";
            info_punts += "Descripció: " + p.getDescPunt() + "\n";
            info_punts += "Latitud: " + p.getLat() + "\n";
            info_punts += "Longitud: " + p.getLon() + "\n";
            info_punts += "Altitud: " + p.getAlt() + "\n";
            info_punts += "Tipus de punt: " + p.getIdTipus().getNom() + "\n";
            info_punts += "Imatge: " + p.getTmpFoto() + "\n\n\n";
            
        }
        
        jTextArea_dadesPunts.setText(info_punts);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_desarBD = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_dadesRuta = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_dadesPunts = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        jButton_desarBD.setText("desar a BD");
        jButton_desarBD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_desarBDMouseClicked(evt);
            }
        });

        jTextArea_dadesRuta.setEditable(false);
        jTextArea_dadesRuta.setColumns(20);
        jTextArea_dadesRuta.setRows(5);
        jScrollPane1.setViewportView(jTextArea_dadesRuta);

        jTextArea_dadesPunts.setEditable(false);
        jTextArea_dadesPunts.setColumns(20);
        jTextArea_dadesPunts.setRows(5);
        jScrollPane2.setViewportView(jTextArea_dadesPunts);

        jLabel1.setText("RESUM DADES A INSERIR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
            .addGroup(layout.createSequentialGroup()
                .addGap(484, 484, 484)
                .addComponent(jButton_desarBD)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(462, 462, 462))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jButton_desarBD)
                .addGap(56, 56, 56))
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
                        System.out.println("HAS AFEGIT CORRECTAMENT LA RUTA I ELS SEUS PUNTS");
                    }else{
                        System.out.println("NO HAS INTRODUIT LA RUTA BE");
                    }
                    
                } catch (GestorBDWikilocException ex) {
                    System.out.println("ERROR: "+ex.getMessage());
                }
            }else{
                System.out.println("NO HAS INTRODUIT TOTS ELS CAMPS OBLIGATORIS DELS PUNTS DE RUTA");
            }
        }else{
            System.out.println("NO HAS INTRODUIT TOTS ELS CAMPS OBLIGATORIS DE LA RUTA");
        }
        

        
        
        
    }//GEN-LAST:event_jButton_desarBDMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_desarBD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea_dadesPunts;
    private javax.swing.JTextArea jTextArea_dadesRuta;
    // End of variables declaration//GEN-END:variables
}