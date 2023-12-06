/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.wikiloc.jdbc.GestorBDWikilocJdbc;
import org.milaifontanals.wikiloc.model.Fetes;
import org.milaifontanals.wikiloc.model.Ruta;
import org.milaifontanals.wikiloc.model.Tipus;
import org.milaifontanals.wikiloc.model.Usuari;
import org.milaifontanals.wikiloc.persistencia.GestorBDWikilocException;

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
    
    public panellCataleg(JPanel jPanel_menu, JPanel jPanel_principal, Usuari usuari_loginat) throws GestorBDWikilocException {
        initComponents();
        this.usuari_loginat = usuari_loginat;
        
        jComboBox_filtreDific.addItem("");
        for (int i = 1; i <= 5; i++) {
            jComboBox_filtreDific.addItem(i + "");
        }
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
            for (Ruta r : llistaRutes) {
              
                Fetes f = gestorBDWikilocJdbc.haFetRuta(r, usuari_loginat);
                
                if(f!=null){
                    
                    rowData[0] = format.format(f.getMt());
                }
                
                
                rowData[1] = r.getTitol();
                rowData[2] = r.getDist();
                rowData[3] = r.getTemps();
                rowData[4] = r.getDific();
                rowData[5] = (f!=null?"Si":"No");
                        
                tableModel.addRow(rowData);
            }
                        
            
        } catch (GestorBDWikilocException ex) {
            System.out.println("Error en carregar la taula de rutes fetes");
        }
        

        TableActionEvent2 event = new TableActionEvent2() {
            List<Fetes> llistaRutesCompletades = gestorBDWikilocJdbc.obtenirLlistaFetesUsuari(usuari_loginat.getLogin());
  
            @Override
            public void onEdit(int row) {
                

            }

            @Override
            public void onDelete(int row) {
                
                
                
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

        setBackground(new java.awt.Color(255, 255, 255));

        jTable_rutesTotals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MT", "Ruta", "Dist", "Temps", "Dific", "Feta", "Botons"
            }
        ));
        jTable_rutesTotals.setRowHeight(40);
        jScrollPane1.setViewportView(jTable_rutesTotals);

        jTextField_filtreTitol.setText("jTextField1");

        jTextField_filtreDist.setText("jTextField2");

        jButton_cercaFiltre.setText("cercar");
        jButton_cercaFiltre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_cercaFiltreMouseClicked(evt);
            }
        });

        jButton_netejaFiltre.setText("netejar filtre");
        jButton_netejaFiltre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_netejaFiltreMouseClicked(evt);
            }
        });

        jCheckBox_feta.setText("jCheckBox1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField_filtreTitol, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_filtreDist, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_filtreDific, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_filtreCreador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox_feta)
                        .addGap(47, 47, 47)
                        .addComponent(jButton_cercaFiltre)
                        .addGap(33, 33, 33)
                        .addComponent(jButton_netejaFiltre))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_filtreTitol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_filtreDist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_filtreDific, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cercaFiltre)
                    .addComponent(jButton_netejaFiltre)
                    .addComponent(jCheckBox_feta)
                    .addComponent(jComboBox_filtreCreador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1287, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                dific_filtre = Integer.parseInt((String) jComboBox_filtreDific.getSelectedItem());
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
                }
                
                
                rowData[1] = r.getTitol();
                rowData[2] = r.getDist();
                rowData[3] = r.getTemps();
                rowData[4] = r.getDific();
                rowData[5] = (f!=null?"Si":"No");
                        
                tableModel.addRow(rowData);
            }


            
            
            
            
        } catch (GestorBDWikilocException ex) {
            System.out.println("ERROR DE FILTRE: "+ex.getMessage());
        }
        
        
    }//GEN-LAST:event_jButton_cercaFiltreMouseClicked

    private void jButton_netejaFiltreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_netejaFiltreMouseClicked
        
        
        
        
    }//GEN-LAST:event_jButton_netejaFiltreMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_cercaFiltre;
    private javax.swing.JButton jButton_netejaFiltre;
    private javax.swing.JCheckBox jCheckBox_feta;
    private javax.swing.JComboBox<Usuari> jComboBox_filtreCreador;
    private javax.swing.JComboBox<String> jComboBox_filtreDific;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_rutesTotals;
    private javax.swing.JTextField jTextField_filtreDist;
    private javax.swing.JTextField jTextField_filtreTitol;
    // End of variables declaration//GEN-END:variables
}
