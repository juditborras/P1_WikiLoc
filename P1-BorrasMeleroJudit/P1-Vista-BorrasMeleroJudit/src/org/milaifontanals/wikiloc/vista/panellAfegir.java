/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
    public panellAfegir(JPanel jPanel_menu, JPanel jPanel_principal, Usuari usuari_loginat) {
        
        initComponents();

                
        this.usuari_loginat = usuari_loginat;
        
//        breadcrumb1.addItem("Item 1");
//        breadcrumb1.addItem("Item 2");
//        breadcrumb1.addItem("Item 3");
        
        
        
        breadcrumb1.setEvent(new EventItemSelected() {
            @Override
            public void selected(BreadcrumbItem item) {
                
                jPanel_menu.setVisible(false);
                System.out.println("ITEM SELECTED: "+item.getIndex());
                switch(item.getIndex()){
                    case 0:
                                                                                     
                        new CambiaPanel(jPanel_formulari,new panellAfegir_ruta(breadcrumb1, usuari_loginat,llistaPunts));
                        nova_ruta = panellAfegir_ruta.retornarNovaRuta();
                        llistaPunts = panellAfegir_rutaPunts.retornaLlistaPunts();
                        System.out.println("NOVA RUTA V1: "+nova_ruta);
                        if(llistaPunts!=null){
                            System.out.println("LLISTA DE PUNTS: "+llistaPunts.size());
                        }
                        break;

                    case 1:
                        nova_ruta = panellAfegir_ruta.retornarNovaRuta();
                        llistaPunts = panellAfegir_rutaPunts.retornaLlistaPunts();
                        if(llistaPunts!=null){
                            System.out.println("LLISTA DE PUNTS: "+llistaPunts.size());
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
        jPanel_formulari = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        breadcrumb1 = new org.milaifontanals.wikiloc.breadcrumb.com.raven.component.Breadcrumb();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1190, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 70));

        jPanel_formulari.setBackground(new java.awt.Color(204, 255, 255));
        jPanel_formulari.setLayout(new javax.swing.BoxLayout(jPanel_formulari, javax.swing.BoxLayout.LINE_AXIS));
        add(jPanel_formulari, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 1190, 570));

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(530, Short.MAX_VALUE)
                .addComponent(breadcrumb1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(breadcrumb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1190, 90));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.milaifontanals.wikiloc.breadcrumb.com.raven.component.Breadcrumb breadcrumb1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel_formulari;
    // End of variables declaration//GEN-END:variables
}
