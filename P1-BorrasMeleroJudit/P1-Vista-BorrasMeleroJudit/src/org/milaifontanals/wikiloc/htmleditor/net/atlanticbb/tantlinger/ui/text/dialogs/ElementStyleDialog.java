/*
 * Created on Jan 17, 2006
 *
 */
package org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.ui.text.dialogs;

import java.awt.Dialog;
import java.awt.Frame;
import java.util.Map;

import javax.swing.Icon;

import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.i18n.I18n;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.ui.OptionDialog;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.ui.UIUtils;



public class ElementStyleDialog extends OptionDialog
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final I18n i18n = I18n.getInstance("net.atlanticbb.tantlinger.ui.text.dialogs");
    
    private static Icon icon = UIUtils.getIcon(UIUtils.X48, "pencil.png"); //$NON-NLS-1$
    private static String title = i18n.str("element_style"); //$NON-NLS-1$
    private static String desc = i18n.str("element_style_desc"); //$NON-NLS-1$
    
    private StyleAttributesPanel stylePanel;

    public ElementStyleDialog(Frame parent)
    {
        super(parent, title, desc, icon);   
        init();
    }

    public ElementStyleDialog(Dialog parent)
    {
        super(parent, title, desc, icon);
        init();
    }
    
    private void init()
    {
        stylePanel = new StyleAttributesPanel();
        setContentPane(stylePanel);        
        pack();
        setSize(300, getHeight());
        setResizable(false);
    }
    
    public void setStyleAttributes(Map attr)
    {
        stylePanel.setAttributes(attr);
    }
    
    public Map getStyleAttributes()
    {
        return stylePanel.getAttributes();
    }
}
