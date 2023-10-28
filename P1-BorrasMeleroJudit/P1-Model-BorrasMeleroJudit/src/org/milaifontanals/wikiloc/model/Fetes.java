package org.milaifontanals.wikiloc.model;

import java.util.Date;
import java.util.Objects;
import org.milaifontanals.wikiloc.exception.WikilocModelException;

/**
 *
 * @author JUDIT
 */

public class Fetes {
   
    private Usuari loginUsuari;
    private Date mt;
    private Ruta idRuta;
    
    public Fetes(Usuari loginUsuari, Date mt, Ruta idRuta) {
        setLoginUsuari(loginUsuari);
        setMt(mt);
        setIdRuta(idRuta);
    }
    
    public Usuari getLoginUsuari() {
        return loginUsuari;
    }

    public void setLoginUsuari(Usuari loginUsuari) {
        if(loginUsuari.getLogin() == null || loginUsuari.getLogin().length() == 0 || loginUsuari.getLogin().length() > 15){
            throw new WikilocModelException("És obligatori indicar l'usuari que ha realitzat la ruta");
        }
        this.loginUsuari = loginUsuari;
    }

    public Date getMt() {
        return mt;
    }

    public void setMt(Date mt) {
        Date avui = new Date();
        
        if (mt.after(avui)) {
            throw new WikilocModelException("La data de la ruta completada no pot ser futura");
        }
        this.mt = mt;
    }

    public Ruta getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Ruta idRuta) {
        if(idRuta.getId() == null || idRuta.getId() > 999999){
            throw new WikilocModelException("És obligatori indicar la ruta realitzada");
        }
        this.idRuta = idRuta;
    }
    
    //Fetes idèntic per loginUsuari i mt
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.loginUsuari);
        hash = 71 * hash + Objects.hashCode(this.mt);
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
        final Fetes other = (Fetes) obj;
        if (!Objects.equals(this.loginUsuari, other.loginUsuari)) {
            return false;
        }
        return Objects.equals(this.mt, other.mt);
    }
    
    @Override
    public String toString() {
        return "Fetes{" + "loginUsuari=" + loginUsuari + ", mt=" + mt + ", idRuta=" + idRuta + '}';
    }
}
