/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.milaifontanals.wikiloc.breadcrumb.com.raven.component.BreadcrumbItem;
import org.milaifontanals.wikiloc.breadcrumb.com.raven.event.EventItemSelected;
import org.milaifontanals.wikiloc.model.Punt;
import org.milaifontanals.wikiloc.model.Ruta;
import org.milaifontanals.wikiloc.model.Usuari;
import org.milaifontanals.wikiloc.persistencia.GestorBDWikilocException;

/**
 *
 * @author JUDIT
 */
public class panellAfegir extends javax.swing.JPanel {

    /**
     * Creates new form panellAfegir
     */
    
    Usuari usuari_loginat;
    Ruta nova_ruta;
    List<Punt> llistaPunts;
    
    public panellAfegir(){
        
    }
    public panellAfegir(JPanel jPanel_menu, JPanel jPanel_principal, Usuari usuari_loginat, int contador) {
        
        initComponents();
        jPanel_menu.setVisible(false);
                
        this.usuari_loginat = usuari_loginat;
        
        
//        breadcrumb1.addItem("Item 1");
//        breadcrumb1.addItem("Item 2");
//        breadcrumb1.addItem("Item 3");
        
        
        
        breadcrumb1.setEvent(new EventItemSelected() {
            @Override
            public void selected(BreadcrumbItem item) {
                
                jPanel_menu.setVisible(false);
                jLabel2.setVisible(false);
                jLabel4.setVisible(false);
                jLabel3.setVisible(false);
                
                //System.out.println("ITEM SELECTED: "+item.getIndex());
                switch(item.getIndex()){
                    case 0:
                             
                        nova_ruta = panellAfegir_ruta.retornarNovaRuta();
                        llistaPunts = panellAfegir_rutaPunts.retornaLlistaPunts();
                        if(contador==1){
                            nova_ruta = null;
                            llistaPunts = new ArrayList();
                        }
                        
                        new CambiaPanel(jPanel_formulari,new panellAfegir_ruta(breadcrumb1, usuari_loginat,llistaPunts,nova_ruta,contador));
                        
                        
                        
                        //System.out.println("NOVA RUTA V1: "+nova_ruta);
                        if(llistaPunts!=null){
                            //System.out.println("LLISTA DE PUNTS: "+llistaPunts.size());
                        }
                        break;

                    case 1:
                        nova_ruta = panellAfegir_ruta.retornarNovaRuta();
                        llistaPunts = panellAfegir_rutaPunts.retornaLlistaPunts();
                        
                        if(contador==1){
                            nova_ruta = null;
                            llistaPunts = new ArrayList();
                        }
                         
                        if(llistaPunts!=null){
                            //System.out.println("LLISTA DE PUNTS: "+llistaPunts.size());
                        }
                        
                        new CambiaPanel(jPanel_formulari,new panellAfegir_rutaPunts(breadcrumb1,nova_ruta,llistaPunts));
                       
                        
                        break;
                        
                    case 2:
                        nova_ruta = panellAfegir_ruta.retornarNovaRuta();
                        llistaPunts = panellAfegir_rutaPunts.retornaLlistaPunts();
                        new CambiaPanel(jPanel_formulari,new panellAfegir_rutaResum(usuari_loginat,nova_ruta,llistaPunts));
                        break;
                }
                
                
           
                
                
            }

        });

        
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
        jSeparator1 = new javax.swing.JSeparator();
        jPanel_formulari = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        breadcrumb1 = new org.milaifontanals.wikiloc.breadcrumb.com.raven.component.Breadcrumb();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Publicar nova ruta");

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(1021, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 90));

        jPanel_formulari.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_formulari.setLayout(new javax.swing.BoxLayout(jPanel_formulari, javax.swing.BoxLayout.LINE_AXIS));
        add(jPanel_formulari, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 1310, 710));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        breadcrumb1.setColor1(new java.awt.Color(76, 140, 43));
        breadcrumb1.setColor2(new java.awt.Color(255, 174, 0));
        breadcrumb1.setColorSelected(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(343, 343, 343)
                .addComponent(breadcrumb1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(395, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(breadcrumb1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1310, 110));

        jLabel2.setFont(new java.awt.Font("Calibri", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(76, 140, 43));
        jLabel2.setText("Prémer el botó");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 300, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/mapa.png"))); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, -1, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 3, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(76, 140, 43));
        jLabel3.setText("per començar a crear una ruta");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 620, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.milaifontanals.wikiloc.breadcrumb.com.raven.component.Breadcrumb breadcrumb1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel_formulari;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
