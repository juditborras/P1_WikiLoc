
package org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.shef;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.io.IOUtils;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.ui.UIUtils;
import org.milaifontanals.wikiloc.jdbc.GestorBDWikilocJdbc;
import org.milaifontanals.wikiloc.persistencia.GestorBDWikilocException;
import org.milaifontanals.wikiloc.vista.panellCompartides;

/**
 *
 * @author Bob Tantlinger
 */
public class Demo {

    public JFrame frame;
    private final HTMLEditorPane editor;
    public String text_html;
    
    public GestorBDWikilocJdbc gestorBDWikilocJdbc;
    
    public Demo(String text_html, int id_ruta) {
        
        try {
            gestorBDWikilocJdbc = new GestorBDWikilocJdbc();
        } catch (GestorBDWikilocException ex) {
            
        }
        
        this.text_html = text_html;

        editor = new HTMLEditorPane(true);
        
        Path currentRelativePath = Paths.get("");
        //InputStream in = Demo.class.getResourceAsStream("src"+File.separator+"org"+File.separator+"milaifontanals"+File.separator+"wikiloc"+File.separator+"htmleditor"+File.separator+"net"+File.separator+"atlanticbb"+File.separator+"tantlinger"+File.separator+"shef"+File.separator+"htmlsnip.txt");
        InputStream stream = new ByteArrayInputStream(text_html.getBytes(StandardCharsets.UTF_8));
        InputStream in = Demo.class.getResourceAsStream("htmlsnip.txt");
        
        try{
            editor.setText(IOUtils.read(stream));
        }catch(IOException ex) {
            ex.printStackTrace();
        } finally {
            IOUtils.close(stream);
        }


        frame = new JFrame();
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(editor.getEditMenu());
        menuBar.add(editor.getFormatMenu());
        menuBar.add(editor.getInsertMenu());
        frame.setJMenuBar(menuBar);


        frame.setTitle("HTML Editor Demo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().add(editor);
        frame.setVisible(true);
        
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                
                int resposta =JOptionPane.showConfirmDialog(null, "Estàs segur de desar els canvis?",
                "YES_NO_OPTION", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
                System.out.println("LEN: "+editor.wysEditor.getText().length()+" "+editor.wysEditor.getText());
                if(resposta == 0){
                    if(editor.wysEditor.getText().length()==58){
                        JOptionPane.showConfirmDialog(null, "No es pot deixar buit",
                        "ERROR_MESSAGE", JOptionPane.OK_OPTION,
                        JOptionPane.ERROR_MESSAGE);
                    }else{
                        try {
                            if(gestorBDWikilocJdbc.editarTextHtmlRuta(id_ruta, editor.wysEditor.getText())){
                                gestorBDWikilocJdbc.confirmarCanvis();
                                System.out.println("ID RUTA: "+id_ruta);
                                System.out.println("TEXT HTML: "+text_html);
                                
                                JOptionPane.showConfirmDialog(null, "Els canvis s'han desat correctament",
                                        "CLOSED_OPTION", JOptionPane.CLOSED_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                System.out.println("elseeeeeee");
                            }
                        } catch (GestorBDWikilocException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    
                }
                
                
                
                frame.setVisible(false);
                
            }
        });
        
    }
    

    private void printHtml() {
        System.out.println(editor.getText());
    }
    
    
    private Demo demo;
    
    public void main(String args[]) throws InterruptedException, InvocationTargetException {

        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ex){}

        SwingUtilities.invokeAndWait(new Runnable() {

            public void run() {
               demo = new Demo(null,0);
            }
        });
        
        do {
            Thread.sleep(2000);
            
        } while (demo.frame.isVisible());
        
        demo.printHtml();
        
    }

    

}
