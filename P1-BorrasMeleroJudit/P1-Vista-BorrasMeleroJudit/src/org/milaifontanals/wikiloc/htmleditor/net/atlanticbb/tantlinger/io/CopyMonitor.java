/*
 * Created on Feb 2, 2006
 *
 */
package org.milaifontanals.wikiloc.htmleditor.net.atlanticbb.tantlinger.io;

public interface CopyMonitor
{
    public void bytesCopied(int numBytes);   
    
    public boolean isCopyAborted();    
}
