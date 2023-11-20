package org.milaifontanals.wikiloc.model;

import java.awt.Image;
import java.util.Objects;
import org.milaifontanals.wikiloc.exception.WikilocModelException;

/**
 *
 * @author JUDIT
 */

public class Tipus {
    
    private Integer id;
    private String nom;
    private byte[] icona;
    
    public Tipus(String nom, byte[] icona) {
        setNom(nom);
        setIcona(icona);
    }
    
    public Tipus(Integer id, String nom, byte[] icona) {
        this.id = id;
        setNom(nom);
        setIcona(icona);
    }
      
    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if(nom == null || nom.length() == 0 || nom.length() > 40){
            throw new WikilocModelException("El tipus ha de tenir obligatòriament un nom i no pot sobrepassar els 40 caràcters");
        }
        this.nom = nom;
    }

    public byte[] getIcona() {
        return icona;
    }

    public void setIcona(byte[] icona) {
        this.icona = icona;
    }
    
    //Tipus idèntic per id, nom i icona
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.nom);
        hash = 53 * hash + Objects.hashCode(this.icona);
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
        final Tipus other = (Tipus) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.icona, other.icona);
    }
    
    @Override
    public String toString() {
        return nom;
    }    
    
}
