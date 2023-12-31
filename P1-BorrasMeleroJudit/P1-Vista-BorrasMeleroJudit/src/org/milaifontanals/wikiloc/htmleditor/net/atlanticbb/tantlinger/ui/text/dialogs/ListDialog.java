/*
 * Created on Jan 18, 2006
 *
 */
package org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.ui.text.dialogs;

import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.Icon;

import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.i18n.I18n;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.ui.OptionDialog;
import org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.ui.UIUtils;

import java.util.*;

public class ListDialog extends OptionDialog
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final I18n i18n = I18n.getInstance("net.atlanticbb.tantlinger.ui.text.dialogs");
    
    public static final int UNORDERED = ListAttributesPanel.UL_LIST;
    public static final int ORDERED = ListAttributesPanel.OL_LIST;
    
    private static Icon icon = UIUtils.getIcon(UIUtils.X48, "categories.png"); //$NON-NLS-1$
    private static String title = i18n.str("list_properties"); //$NON-NLS-1$
    private static String desc = i18n.str("list_properties_desc"); //$NON-NLS-1$
    
    private ListAttributesPanel listAttrPanel;

    public ListDialog(Frame parent)
    {
        super(parent, title, desc, icon);
        init();
    }

    public ListDialog(Dialog parent)
    {
        super(parent, title, desc, icon);
        init();
    }
    
    private void init()
    {
        listAttrPanel = new ListAttributesPanel();
        setContentPane(listAttrPanel);
        pack();
        setSize(220, getHeight());
        setResizable(false);
    }
    
    public void setListType(int t)
    {
        listAttrPanel.setListType(t);
    }
    
    public int getListType()
    {
        return listAttrPanel.getListType();
    }
    
    public void setListAttributes(Map attr)
    {
        listAttrPanel.setAttributes(attr);
    }
    
    public Map getListAttributes()
    {
        return listAttrPanel.getAttributes();
    }
}
