
package org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.shef;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.io.IOUtils;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.ui.UIUtils;

/**
 *
 * @author Bob Tantlinger
 */
public class Demo {

    public JFrame frame;
    private final HTMLEditorPane editor;
    public String text_html;
    
    public Demo(String text_html) {
        
        
        
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
        
    }

    private void printHtml() {
        System.out.println(editor.getText());
    }
    
    
    private static Demo demo;
    
    public static void main(String args[]) throws InterruptedException, InvocationTargetException {

        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ex){}

        SwingUtilities.invokeAndWait(new Runnable() {

            public void run() {
               demo = new Demo(null);
            }
        });
        
        do {
            Thread.sleep(2000);
            
        } while (demo.frame.isVisible());
        
        demo.printHtml();
        
    }

    

}
