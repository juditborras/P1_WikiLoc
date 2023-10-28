package org.milaifontanals.wikiloc.model;

import java.awt.Image;
import java.util.Objects;
import org.milaifontanals.wikiloc.exception.WikilocModelException;

/**
 *
 * @author JUDIT
 */

public class Usuari {

    private String login;
    private String pwd;
    private String email;
    private Image foto;
    
    //Constructor mínim
    public Usuari(String login, String pwd, String email) {
        setLogin(login);
        setPwd(pwd);
        setEmail(email);
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if(login == null || login.length() == 0 || login.length() > 15){
            throw new WikilocModelException("L'usuari ha de tenir obligatòriament un login i aquest no pot sobrepassar els 15 caràcters");
        }        
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        if(pwd == null || pwd.length() == 0 || pwd.length() > 256){
            throw new WikilocModelException("L'usuari ha de tenir obligatòriament una contrasenya la qual no pot sobrepassar els 256 caràcters");
        }        
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!isValidEmailAddress(email)){
            throw new WikilocModelException("L'usuari ha de tenir obligatòriament un correu vàlid");
        }
        this.email = email;
    }
    
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }
    
    //Usuari idèntic per login i email
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.login);
        hash = 41 * hash + Objects.hashCode(this.email);
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
        final Usuari other = (Usuari) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }   
    
    @Override
    public String toString() {
        return "Usuari{" + "login=" + login + ", pwd=" + pwd + ", email=" + email + ", foto=" + foto + '}';
    }
}
