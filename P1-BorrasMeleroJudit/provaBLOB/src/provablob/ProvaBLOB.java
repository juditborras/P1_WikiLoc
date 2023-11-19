/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package provablob;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author JUDIT
 */
public class ProvaBLOB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String sourceData = "iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAACXBIWXMAAAsTAAALEwEAmpwYAAABC0lEQVR4nO2YwWrCQBCG84AeQh7GiLmYHAIiut69+AK+QC6+wB7yAGErUlBo0hLbNEQxv6yYk4dCaeG3nQ/mtJeP2ZnZYR1H+CG06/ZSz1syhHbd3p2gPaiVAkOknrcUwVoyqKQG8b+b5C2KUE2nvIIb34cJQ+RhyCtoyZMEpt/Hx3jMKWg5HQ7YzufYDYecgh2l1jBBgDKOOQUt56bB82KB7WCAz9mMT7CjyjJkQYDXKOIUvNK22K9WeLJNNJkQCt44FgU2cYyX0YhTsKNYr2F8H+9fjCQRfJwrbombpGIdM2fmQV2yPnUn5mUhZ163DPPCSr/y178YqQgqyaCSGqz/XJNo9i9gwfkeFxTCYHLkv3b+AAAAAElFTkSuQmCC";
        
        JFrame jf = new JFrame();

        // create a buffered image
        
        byte[] imageByte;
        
        byte[] image = Base64.getDecoder().decode(sourceData);
        
        BufferedImage bf = byteArrayToImage(image);
        JLabel wIcon = new JLabel(new ImageIcon(bf));

        jf.add(wIcon);
        
        jf.setSize(500,800);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
    
    public static BufferedImage byteArrayToImage(byte[] bytes){  
        BufferedImage bufferedImage=null;
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return bufferedImage;
}
    
}
