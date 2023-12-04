/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Color;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.milaifontanals.wikiloc.model.Usuari;
import org.milaifontanals.wikiloc.persistencia.GestorBDWikilocException;

/**
 *
 * @author JUDIT
 */
public class Menu extends javax.swing.JFrame {

    Usuari usuari_loginat;
    /**
     * Creates new form Menu
     */
    public Menu(Usuari usuari) {
        initComponents();
        this.usuari_loginat = usuari;
        
        new CambiaPanel(jPanel_principal,new panellinici());
        this.rSButtonMenuInici.setColorNormal(new Color(255,163,0));
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
        jPanel_barraMenu = new javax.swing.JPanel();
        jButtonMenu = new javax.swing.JButton();
        jPanel_menu = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rSButtonMenuCompartides = new org.milaifontanals.wikiloc.rsbuttom.RSButtonMenuDesplegable();
        rSButtonMenuInici = new org.milaifontanals.wikiloc.rsbuttom.RSButtonMenuDesplegable();
        rSButtonMenuAfegir = new org.milaifontanals.wikiloc.rsbuttom.RSButtonMenuDesplegable();
        rSButtonMenuCompletades = new org.milaifontanals.wikiloc.rsbuttom.RSButtonMenuDesplegable();
        rSButtonMenuSortir = new org.milaifontanals.wikiloc.rsbuttom.RSButtonMenuDesplegable();
        rSButtonMenuPerfil = new org.milaifontanals.wikiloc.rsbuttom.RSButtonMenuDesplegable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel_principal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Wikiloc");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_barraMenu.setBackground(new java.awt.Color(76, 140, 43));

        jButtonMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/menu.png"))); // NOI18N
        jButtonMenu.setBorder(null);
        jButtonMenu.setContentAreaFilled(false);
        jButtonMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_barraMenuLayout = new javax.swing.GroupLayout(jPanel_barraMenu);
        jPanel_barraMenu.setLayout(jPanel_barraMenuLayout);
        jPanel_barraMenuLayout.setHorizontalGroup(
            jPanel_barraMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_barraMenuLayout.createSequentialGroup()
                .addComponent(jButtonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1649, Short.MAX_VALUE))
        );
        jPanel_barraMenuLayout.setVerticalGroup(
            jPanel_barraMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_barraMenuLayout.createSequentialGroup()
                .addComponent(jButtonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("GESTIÓ DE RUTES");

        rSButtonMenuCompartides.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/colleccio.png"))); // NOI18N
        rSButtonMenuCompartides.setText("Compartides");
        rSButtonMenuCompartides.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        rSButtonMenuCompartides.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMenuCompartides.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonMenuCompartides.setIconTextGap(25);
        rSButtonMenuCompartides.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rSButtonMenuCompartidesMousePressed(evt);
            }
        });
        rSButtonMenuCompartides.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMenuCompartidesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(rSButtonMenuCompartides, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonMenuCompartides, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        rSButtonMenuInici.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/inici.png"))); // NOI18N
        rSButtonMenuInici.setText("Inici");
        rSButtonMenuInici.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        rSButtonMenuInici.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMenuInici.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonMenuInici.setIconTextGap(25);
        rSButtonMenuInici.setMaximumSize(new java.awt.Dimension(106, 50));
        rSButtonMenuInici.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rSButtonMenuIniciMousePressed(evt);
            }
        });
        rSButtonMenuInici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMenuIniciActionPerformed(evt);
            }
        });

        rSButtonMenuAfegir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/afegir.png"))); // NOI18N
        rSButtonMenuAfegir.setText("Afegir");
        rSButtonMenuAfegir.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        rSButtonMenuAfegir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMenuAfegir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonMenuAfegir.setIconTextGap(25);
        rSButtonMenuAfegir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rSButtonMenuAfegirMousePressed(evt);
            }
        });
        rSButtonMenuAfegir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMenuAfegirActionPerformed(evt);
            }
        });

        rSButtonMenuCompletades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/completada.png"))); // NOI18N
        rSButtonMenuCompletades.setText("Completades");
        rSButtonMenuCompletades.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        rSButtonMenuCompletades.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMenuCompletades.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonMenuCompletades.setIconTextGap(25);
        rSButtonMenuCompletades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rSButtonMenuCompletadesMousePressed(evt);
            }
        });
        rSButtonMenuCompletades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMenuCompletadesActionPerformed(evt);
            }
        });

        rSButtonMenuSortir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/sortir.png"))); // NOI18N
        rSButtonMenuSortir.setText("Sortir");
        rSButtonMenuSortir.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        rSButtonMenuSortir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMenuSortir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonMenuSortir.setIconTextGap(25);
        rSButtonMenuSortir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rSButtonMenuSortirMousePressed(evt);
            }
        });
        rSButtonMenuSortir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMenuSortirActionPerformed(evt);
            }
        });

        rSButtonMenuPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/milaifontanals/wikiloc/components/usuari.png"))); // NOI18N
        rSButtonMenuPerfil.setText("Perfil");
        rSButtonMenuPerfil.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        rSButtonMenuPerfil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMenuPerfil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonMenuPerfil.setIconTextGap(25);
        rSButtonMenuPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rSButtonMenuPerfilMousePressed(evt);
            }
        });
        rSButtonMenuPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMenuPerfilActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("COMPTE USUARI");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 132, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        javax.swing.GroupLayout jPanel_menuLayout = new javax.swing.GroupLayout(jPanel_menu);
        jPanel_menu.setLayout(jPanel_menuLayout);
        jPanel_menuLayout.setHorizontalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addGroup(jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonMenuInici, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonMenuCompletades, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonMenuAfegir, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonMenuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonMenuSortir, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1))
        );
        jPanel_menuLayout.setVerticalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(rSButtonMenuInici, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(rSButtonMenuCompletades, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(rSButtonMenuAfegir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(rSButtonMenuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(rSButtonMenuSortir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel_principal.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_principal.setLayout(new javax.swing.BoxLayout(jPanel_principal, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_barraMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jPanel_principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(636, 636, 636)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel_barraMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1074, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuActionPerformed
        
        
        
        if(!jPanel_menu.isVisible()){
            System.out.println("PRESS");
            jPanel_menu.setVisible(true);
            Animacion.Animacion.mover_derecha(-260, 0, 2, 2, jPanel_menu);
            Animacion.Animacion.mover_izquierda(0, 0, 0, 0, jPanel_principal);
            
            
        }else{
            int posicio = this.jPanel_menu.getX();
            if(posicio > -1){
                Animacion.Animacion.mover_izquierda(0, -260, 2, 2, jPanel_menu);
                Animacion.Animacion.mover_derecha(0, 0, 0, 0, jPanel_principal);

            }else{
                Animacion.Animacion.mover_derecha(-260, 0, 2, 2, jPanel_menu);
                Animacion.Animacion.mover_izquierda(0, 0, 0, 0, jPanel_principal);
            }
        }
        

        
        
    }//GEN-LAST:event_jButtonMenuActionPerformed

    private void rSButtonMenuIniciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMenuIniciActionPerformed
        
        new CambiaPanel(jPanel_principal,new panellinici());
        
        if(this.rSButtonMenuInici.isSelected()){
            this.rSButtonMenuInici.setColorNormal(new Color(255,163,0));
        
            this.rSButtonMenuCompartides.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuCompletades.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuAfegir.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuPerfil.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuSortir.setColorNormal(new Color(238,238,238));
                    
        }
    }//GEN-LAST:event_rSButtonMenuIniciActionPerformed

    private void rSButtonMenuIniciMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonMenuIniciMousePressed
        
        this.rSButtonMenuCompartides.setSelected(false);
        this.rSButtonMenuCompletades.setSelected(false);
        this.rSButtonMenuAfegir.setSelected(false);
        this.rSButtonMenuPerfil.setSelected(false);
        this.rSButtonMenuSortir.setSelected(false);
        
        this.rSButtonMenuInici.setSelected(true);
                
    }//GEN-LAST:event_rSButtonMenuIniciMousePressed

    private void rSButtonMenuCompartidesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMenuCompartidesActionPerformed
        
        try {
            new CambiaPanel(jPanel_principal,new panellCompartides(jPanel_menu, jPanel_principal,usuari_loginat));
        } catch (GestorBDWikilocException ex) {
            
        }
        
        if(this.rSButtonMenuCompartides.isSelected()){
            this.rSButtonMenuCompartides.setColorNormal(new Color(255,163,0));
        
            this.rSButtonMenuInici.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuCompletades.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuAfegir.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuPerfil.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuSortir.setColorNormal(new Color(238,238,238));
        }
    }//GEN-LAST:event_rSButtonMenuCompartidesActionPerformed

    private void rSButtonMenuCompartidesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonMenuCompartidesMousePressed
        
        this.rSButtonMenuInici.setSelected(false);
        this.rSButtonMenuCompletades.setSelected(false);
        this.rSButtonMenuAfegir.setSelected(false);
        this.rSButtonMenuPerfil.setSelected(false);
        this.rSButtonMenuSortir.setSelected(false);
        
        this.rSButtonMenuCompartides.setSelected(true);
        
    }//GEN-LAST:event_rSButtonMenuCompartidesMousePressed

    private void rSButtonMenuCompletadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMenuCompletadesActionPerformed
        
        new CambiaPanel(jPanel_principal,new panellCompletades());
        
        if(this.rSButtonMenuCompletades.isSelected()){
            this.rSButtonMenuCompletades.setColorNormal(new Color(255,163,0));
        
            this.rSButtonMenuInici.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuCompartides.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuAfegir.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuPerfil.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuSortir.setColorNormal(new Color(238,238,238));
        }        
        
    }//GEN-LAST:event_rSButtonMenuCompletadesActionPerformed

    private void rSButtonMenuCompletadesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonMenuCompletadesMousePressed
        
        this.rSButtonMenuInici.setSelected(false);
        this.rSButtonMenuCompartides.setSelected(false);
        this.rSButtonMenuAfegir.setSelected(false);
        this.rSButtonMenuPerfil.setSelected(false);
        this.rSButtonMenuSortir.setSelected(false);
        
        this.rSButtonMenuCompletades.setSelected(true);
    }//GEN-LAST:event_rSButtonMenuCompletadesMousePressed

    private void rSButtonMenuAfegirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMenuAfegirActionPerformed
        
        new CambiaPanel(jPanel_principal,new panellAfegir(jPanel_menu, jPanel_principal,usuari_loginat));
        
        File fitxer = new File("info_ruta.txt");
        fitxer.delete();
        
        if(this.rSButtonMenuAfegir.isSelected()){
            this.rSButtonMenuAfegir.setColorNormal(new Color(255,163,0));
        
            this.rSButtonMenuInici.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuCompartides.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuCompletades.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuPerfil.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuSortir.setColorNormal(new Color(238,238,238));
        }        
        
    }//GEN-LAST:event_rSButtonMenuAfegirActionPerformed

    private void rSButtonMenuAfegirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonMenuAfegirMousePressed
        
        this.rSButtonMenuInici.setSelected(false);
        this.rSButtonMenuCompartides.setSelected(false);
        this.rSButtonMenuCompletades.setSelected(false);
        this.rSButtonMenuPerfil.setSelected(false);
        this.rSButtonMenuSortir.setSelected(false);
        
        this.rSButtonMenuAfegir.setSelected(true);        
        
    }//GEN-LAST:event_rSButtonMenuAfegirMousePressed

    private void rSButtonMenuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMenuPerfilActionPerformed
        
        new CambiaPanel(jPanel_principal,new panellPerfil());
        
        if(this.rSButtonMenuPerfil.isSelected()){
            this.rSButtonMenuPerfil.setColorNormal(new Color(255,163,0));
        
            this.rSButtonMenuInici.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuCompartides.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuCompletades.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuAfegir.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuSortir.setColorNormal(new Color(238,238,238));
        }        
        
    }//GEN-LAST:event_rSButtonMenuPerfilActionPerformed

    private void rSButtonMenuPerfilMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonMenuPerfilMousePressed
        
        this.rSButtonMenuInici.setSelected(false);
        this.rSButtonMenuCompartides.setSelected(false);
        this.rSButtonMenuCompletades.setSelected(false);
        this.rSButtonMenuAfegir.setSelected(false);
        this.rSButtonMenuSortir.setSelected(false);
        
        this.rSButtonMenuPerfil.setSelected(true);        
        
    }//GEN-LAST:event_rSButtonMenuPerfilMousePressed

    private void rSButtonMenuSortirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMenuSortirActionPerformed
        
        //new CambiaPanel(jPanel_principal,new panellSortir());
        
        int opcio = JOptionPane.showConfirmDialog(null, "Estàs segur de tancar la sessió?",
                "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        
        if(this.rSButtonMenuSortir.isSelected()){
            this.rSButtonMenuSortir.setColorNormal(new Color(255,163,0));
        
            this.rSButtonMenuInici.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuCompartides.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuCompletades.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuAfegir.setColorNormal(new Color(238,238,238));
            this.rSButtonMenuPerfil.setColorNormal(new Color(238,238,238));
        }  
        
        if(opcio == 0){
            IniciarSessio iniciarSessio = new IniciarSessio();

            ImageIcon img = new ImageIcon("img" + File.separator + "wikiloc_logo_simple.png");

            iniciarSessio.setIconImage(img.getImage());

            iniciarSessio.setExtendedState(iniciarSessio.MAXIMIZED_BOTH);
            iniciarSessio.setResizable(false);
            iniciarSessio.setLocationRelativeTo(null);
            iniciarSessio.setVisible(true);
            
            this.setVisible(false);
            this.dispose();
            
        }
        
    }//GEN-LAST:event_rSButtonMenuSortirActionPerformed

    private void rSButtonMenuSortirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonMenuSortirMousePressed
        
        this.rSButtonMenuInici.setSelected(false);
        this.rSButtonMenuCompartides.setSelected(false);
        this.rSButtonMenuCompletades.setSelected(false);
        this.rSButtonMenuAfegir.setSelected(false);
        this.rSButtonMenuPerfil.setSelected(false);
                
        this.rSButtonMenuSortir.setSelected(true);            
        
    }//GEN-LAST:event_rSButtonMenuSortirMousePressed

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel_barraMenu;
    private javax.swing.JPanel jPanel_menu;
    private javax.swing.JPanel jPanel_principal;
    private org.milaifontanals.wikiloc.rsbuttom.RSButtonMenuDesplegable rSButtonMenuAfegir;
    private org.milaifontanals.wikiloc.rsbuttom.RSButtonMenuDesplegable rSButtonMenuCompartides;
    private org.milaifontanals.wikiloc.rsbuttom.RSButtonMenuDesplegable rSButtonMenuCompletades;
    private org.milaifontanals.wikiloc.rsbuttom.RSButtonMenuDesplegable rSButtonMenuInici;
    private org.milaifontanals.wikiloc.rsbuttom.RSButtonMenuDesplegable rSButtonMenuPerfil;
    private org.milaifontanals.wikiloc.rsbuttom.RSButtonMenuDesplegable rSButtonMenuSortir;
    // End of variables declaration//GEN-END:variables
}
