package org.milaifontanals.wikiloc.model;

import java.awt.Image;
import java.util.Objects;
import org.milaifontanals.wikiloc.exception.WikilocModelException;

/**
 *
 * @author JUDIT
 */

public class Punt {
    
    private Integer num;
    private String nom;
    private String descPunt;
    private byte[] foto;
    private Integer lat;
    private Integer lon;
    private Integer alt;
    
    private Ruta idRuta;
    private Tipus idTipus;
    
    public Punt(Integer num, String nom, String descPunt, byte[] foto, Integer lat, Integer lon, Integer alt, Ruta idRuta, Tipus idTipus) {
        setNum(num);
        setNom(nom);
        setDescPunt(descPunt);
        setFoto(foto);
        setLat(lat);
        setLon(lon);
        setAlt(alt);
        setIdRuta(idRuta);
        setIdTipus(idTipus);
    }
    
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        if(num == null || num > 999999){
            throw new WikilocModelException("El número del punt és obligatori");
        }
        this.num = num;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if(nom == null || nom.length() == 0 || nom.length() > 40){
            throw new WikilocModelException("El nom del punt és obligatori i no pot sobrepassar els 40 caràcters");
        }
        this.nom = nom;
    }

    public String getDescPunt() {
        return descPunt;
    }

    public void setDescPunt(String descPunt) {
        if(descPunt == null || descPunt.length() == 0 || descPunt.length() > 200){
            throw new WikilocModelException("La descripció del punt és obligatòria i no pot sobrepassar els 200 caràcters");
        }
        this.descPunt = descPunt;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        
        this.foto = foto;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        if(lat == null || lat > 999999 || lat < -999999){
            throw new WikilocModelException("La latitud és obligatòria");
        }
        this.lat = lat;
    }

    public Integer getLon() {
        return lon;
    }

    public void setLon(Integer lon) {
        if(lon == null || lon > 999999 || lon < 0){
            throw new WikilocModelException("La longitud és obligatòria i no pot ser negativa");
        }
        this.lon = lon;
    }

    public Integer getAlt() {
        return alt;
    }

    public void setAlt(Integer alt) {
        if(alt > 999999 || alt < -999999){
            throw new WikilocModelException("L'altitud no pot ésser major de 999999");
        }
        this.alt = alt;
    }

    public Ruta getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Ruta idRuta) {
        if(idRuta.getId() == null || idRuta.getId() > 999999){
            throw new WikilocModelException("És obligatori que el punt pertanyi a una ruta");
        }
        this.idRuta = idRuta;
    }

    public Tipus getIdTipus() {
        return idTipus;
    }

    public void setIdTipus(Tipus idTipus) {
        if(idTipus.getId() == null || idTipus.getId() > 999 || idTipus.getId() < 0){
            throw new WikilocModelException("És obligatori que el punt tingui assignat un tipus");
        }
        this.idTipus = idTipus;
    }    
    
    //Punt idèntic per num i idRuta
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.num);
        hash = 31 * hash + Objects.hashCode(this.idRuta);
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
        final Punt other = (Punt) obj;
        if (!Objects.equals(this.num, other.num)) {
            return false;
        }
        return Objects.equals(this.idRuta, other.idRuta);
    }
    
    @Override
    public String toString() {
        return "Punt{" + "num=" + num + ", nom=" + nom + ", descPunt=" + descPunt + ", foto=" + foto + ", lat=" + lat + ", lon=" + lon + ", alt=" + alt + ", idRuta=" + idRuta + ", idTipus=" + idTipus + '}';
    }
}
