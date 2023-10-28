package org.milaifontanals.wikiloc.model;

import java.util.Objects;
import org.milaifontanals.wikiloc.exception.WikilocModelException;

/**
 *
 * @author JUDIT
 */

public class Companys {
    
    Usuari loginUsuari;
    Comentari idComentari;
    
    public Companys(Usuari loginUsuari, Comentari idComentari) {
        setLoginUsuari(loginUsuari);
        setIdComentari(idComentari);
    }    
    
    public Usuari getLoginUsuari() {
        return loginUsuari;
    }

    public void setLoginUsuari(Usuari loginUsuari) {
        if(loginUsuari.getLogin() == null || loginUsuari.getLogin().length() == 0 || loginUsuari.getLogin().length() > 15){
            throw new WikilocModelException("És obligatori indicar qui és el company de ruta");
        }
        this.loginUsuari = loginUsuari;
    }

    public Comentari getIdComentari() {
        return idComentari;
    }

    public void setIdComentari(Comentari idComentari) {
        if(idComentari.getId() == null || idComentari.getId() > 999999){
            throw new WikilocModelException("És obligatori identificar el comentari en què s'indica el company de ruta");
        }
        this.idComentari = idComentari;
    }
    
    //Company idèntic per loginUsuari i idComentari
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.loginUsuari);
        hash = 37 * hash + Objects.hashCode(this.idComentari);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Companys other = (Companys) obj;
        if (!Objects.equals(this.loginUsuari, other.loginUsuari)) {
            return false;
        }
        return Objects.equals(this.idComentari, other.idComentari);
    }    
    
    @Override
    public String toString() {
        return "Companys{" + "loginUsuari=" + loginUsuari + ", idComentari=" + idComentari + '}';
    }
}
