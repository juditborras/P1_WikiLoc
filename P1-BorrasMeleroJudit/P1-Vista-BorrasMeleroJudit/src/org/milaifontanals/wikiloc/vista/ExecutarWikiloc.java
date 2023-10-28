package org.milaifontanals.wikiloc.vista;

import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author JUDIT
 */

public class ExecutarWikiloc {

    public static void main(String[] args) {
        
        IniciarSessio iniciarSessio = new IniciarSessio();
                
        ImageIcon img = new ImageIcon("img"+File.separator+"wikiloc_logo_simple.png");

        iniciarSessio.setIconImage(img.getImage());
        
        iniciarSessio.setExtendedState(iniciarSessio.MAXIMIZED_BOTH); 
        iniciarSessio.setResizable(false);
        iniciarSessio.setLocationRelativeTo(null);
        iniciarSessio.setVisible(true);

               
    }
    
}
