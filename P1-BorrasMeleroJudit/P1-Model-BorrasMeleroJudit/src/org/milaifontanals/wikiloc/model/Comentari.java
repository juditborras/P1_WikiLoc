package org.milaifontanals.wikiloc.model;

import java.util.Date;
import java.util.Objects;
import org.milaifontanals.wikiloc.exception.WikilocModelException;

/**
 *
 * @author JUDIT
 */

public class Comentari {

    private Integer id;
    private String text;
    private Integer vInf;
    private boolean feta;
    private Integer vSeg;
    private Integer vPai;
    private Integer dific;
    private Date mt;
    
    private Usuari loginUsuari;
    private Ruta idRuta;
    
    //Constructor mínim
    public Comentari(String text, Integer vInf, Usuari loginUsuari, Ruta idRuta) {
        setText(text);
        setVinf(vInf);
        setLoginUsuari(loginUsuari);
        setIdRuta(idRuta);
    }
    
    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        
        if(text == null || text.length() == 0){
            throw new WikilocModelException("És obligatori que el comentari tingui contingut");
        }
        this.text = text;
    }

    public Integer getVinf() {
        return vInf;
    }

    public void setVinf(Integer vInf) {
        if(vInf == null || vInf < 1 || vInf > 5){
            throw new WikilocModelException("La valoració de la informació és un camp obligatori i els seus valors se situen entre l'1 i el 5");
        }
        this.vInf = vInf;
    }

    public boolean getFeta() {
        return feta;
    }

    public void setFeta(boolean feta) {
        this.feta = feta;
    }

    public Integer getVseg() {
        return vSeg;
    }

    public void setVseg(Integer vSeg) {
        if(vSeg == null || vSeg < 1 || vSeg > 5 || !this.feta){
            throw new WikilocModelException("La valoració del seguiment és un camp obligatori en cas que s'hagi realitzat la ruta i els seus valors se situen entre l'1 i el 5");
        }        
        this.vSeg = vSeg;
    }

    public Integer getVpai() {
        return vPai;
    }

    public void setVpai(Integer vPai) {
        if(vPai == null || vPai < 1 || vPai > 5 || !this.feta){
            throw new WikilocModelException("La valoració del paisatge és un camp obligatori en cas que s'hagi realitzat la ruta i els seus valors se situen entre l'1 i el 5");
        }        
        this.vPai = vPai;
    }

    public Integer getDific() {
        return dific;
    }

    public void setDific(Integer dific) {
        if(dific == null || dific < 1 || dific > 5 || !this.feta){
            throw new WikilocModelException("La valoració de la dificultat és un camp obligatori en cas que s'hagi realitzat la ruta i els seus valors se situen entre l'1 i el 5");
        }        
        this.dific = dific;
    }

    public Date getMt() {
        return mt;
    }

    public Usuari getLoginUsuari() {
        return loginUsuari;
    }

    public void setLoginUsuari(Usuari loginUsuari) {
        if(loginUsuari.getLogin() == null || loginUsuari.getLogin().length() == 0 || loginUsuari.getLogin().length() > 15){
            throw new WikilocModelException("És obligatori que el comentari pertanyi a un usuari");
        }
        this.loginUsuari = loginUsuari;
    }

    public Ruta getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Ruta idRuta) {
        if(idRuta.getId() == null || idRuta.getId() > 999999){
            throw new WikilocModelException("És obligatori que el comentari prengui com a referència una ruta");
        }
        this.idRuta = idRuta;
    }
    
    //Comentari idèntic per id, loginUsuari i idRuta
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.loginUsuari);
        hash = 97 * hash + Objects.hashCode(this.idRuta);
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
        final Comentari other = (Comentari) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.loginUsuari, other.loginUsuari)) {
            return false;
        }
        return Objects.equals(this.idRuta, other.idRuta);
    }

    @Override
    public String toString() {
        return "Comentari{" + "id=" + id + ", text=" + text + ", v_inf=" + vInf + ", feta=" + feta + ", v_seg=" + vSeg + ", v_pai=" + vPai + ", dific=" + dific + ", mt=" + mt + ", loginUsuari=" + loginUsuari + ", idRuta=" + idRuta + '}';
    }    
}
