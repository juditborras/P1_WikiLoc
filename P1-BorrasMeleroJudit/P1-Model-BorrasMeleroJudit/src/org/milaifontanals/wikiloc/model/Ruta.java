package org.milaifontanals.wikiloc.model;

import java.util.Objects;
import org.milaifontanals.wikiloc.exception.WikilocModelException;

/**
 *
 * @author JUDIT
 */

public class Ruta {
    
    private Integer id;
    private String titol;
    private String descRuta;
    private String textRuta;
    private Double dist;
    private Integer temps;
    private Integer desnP;
    private Integer desnN;
    private Integer dific;
    
    private Usuari loginUsuari;
    
    //Constructor mínim
    public Ruta(String titol, String descRuta, String textRuta, Double dist, Integer temps, Integer desnP, Integer desnN, Integer dific, Usuari loginUsuari) {
        setTitol(titol);
        setDescRuta(descRuta);
        setTextRuta(textRuta);
        setDist(dist);
        setTemps(temps);
        setDesnP(desnP);
        setDesnN(desnN);
        setDific(dific);
        setLoginUsuari(loginUsuari);
    }
    
    //Constructor complet
    public Ruta(Integer id, String titol, String descRuta, String textRuta, Double dist, Integer temps, Integer desnP, Integer desnN, Integer dific, Usuari loginUsuari) {
        setId(id);
        setTitol(titol);
        setDescRuta(descRuta);
        setTextRuta(textRuta);
        setDist(dist);
        setTemps(temps);
        setDesnP(desnP);
        setDesnN(desnN);
        setDific(dific);
        setLoginUsuari(loginUsuari);
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        if(titol == null || titol.length() == 0 || titol.length() > 60){
            throw new WikilocModelException("La ruta ha de tenir obligatòriament un títol i no pot sobrepassar els 60 caràcters");
        }
        this.titol = titol;
    }

    public String getDescRuta() {
        return descRuta;
    }

    public void setDescRuta(String descRuta) {
        if(descRuta == null || descRuta.length() == 0 || descRuta.length() > 1200){
            throw new WikilocModelException("La ruta ha de tenir obligatòriament una descripció i no pot sobrepassar els 1200 caràcters");
        }        
        this.descRuta = descRuta;
    }

    public String getTextRuta() {
        return textRuta;
    }

    public void setTextRuta(String textRuta) {
        if(textRuta == null || textRuta.length() == 0 || textRuta.length() > 1200){
            throw new WikilocModelException("La ruta ha de tenir obligatòriament una descripció format html i no pot sobrepassar els 1200 caràcters");
        }        
        this.textRuta = textRuta;
    }

    public Double getDist() {
        return dist;
    }

    public void setDist(Double dist) {
        if(dist == null || dist > 999999 || dist < 0){
            throw new WikilocModelException("S'ha d'indicar la distància total d'una ruta i aquesta no pot ser negativa");
        }
        this.dist = dist;
    }

    public Integer getTemps() {
        return temps;
    }

    public void setTemps(Integer temps) {
        if(temps == null || temps > 999999 || temps < 0){
            throw new WikilocModelException("S'ha d'indicar el temps total a recórrer una ruta i aquest no pot ser negatiu");
        }        
        this.temps = temps;
    }

    public Integer getDesnP() {
        return desnP;
    }

    public void setDesnP(Integer desnP) {
        if(desnP == null || desnP > 999999 || desnP < 0){
            throw new WikilocModelException("S'ha d'indicar el desnivell positiu d'una ruta");
        }         
        this.desnP = desnP;
    }

    public Integer getDesnN() {
        return desnN;
    }

    public void setDesnN(Integer desnN) {
        if(desnN == null || desnN > 999999 || desnN < 0){
            throw new WikilocModelException("S'ha d'indicar el desnivell negatiu d'una ruta (en valor positiu)");
        }         
        this.desnN = desnN;
    }

    public Integer getDific() {
        return dific;
    }

    public void setDific(Integer dific) {
        if(dific == null || dific < 1 || dific > 5){
            throw new WikilocModelException("L'assignació de la dificultat és un camp obligatori i els seus valors se situen entre l'1 i el 5");
        }         
        this.dific = dific;
    }

    public Usuari getLoginUsuari() {
        return loginUsuari;
    }

    public void setLoginUsuari(Usuari loginUsuari) {
        if(loginUsuari.getLogin() == null || loginUsuari.getLogin().length() == 0 || loginUsuari.getLogin().length() > 15){
            throw new WikilocModelException("És obligatori indicar el propietari de la ruta");
        }
        this.loginUsuari = loginUsuari;
    }
    
    //Ruta idèntica per id i títol
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.titol);
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
        final Ruta other = (Ruta) obj;
        if (!Objects.equals(this.titol, other.titol)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    } 
    
    @Override
    public String toString() {
        return "Ruta{" + "id=" + id + ", titol=" + titol + ", descRuta=" + descRuta + ", textRuta=" + textRuta + ", dist=" + dist + ", temps=" + temps + ", desnP=" + desnP + ", desnN=" + desnN + ", dific=" + dific + ", loginUsuari=" + loginUsuari + '}';
    }
}
