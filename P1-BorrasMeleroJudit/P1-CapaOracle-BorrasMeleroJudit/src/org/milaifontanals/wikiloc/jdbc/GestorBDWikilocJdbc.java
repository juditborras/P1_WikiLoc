package org.milaifontanals.wikiloc.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.milaifontanals.wikiloc.model.Comentari;
import org.milaifontanals.wikiloc.model.Companys;
import org.milaifontanals.wikiloc.model.Fetes;
import org.milaifontanals.wikiloc.model.Punt;
import org.milaifontanals.wikiloc.model.Ruta;
import org.milaifontanals.wikiloc.model.Tipus;
import org.milaifontanals.wikiloc.model.Usuari;
import org.milaifontanals.wikiloc.persistencia.GestorBDWikilocException;
import org.milaifontanals.wikiloc.persistencia.IGestorBDWikiloc;

/**
 *
 * @author JUDIT
 */

public class GestorBDWikilocJdbc implements IGestorBDWikiloc{
    
    private Connection conn;
    
    private static PreparedStatement psIniciarSessio;
    private static PreparedStatement psAfegirUsuari;
    
    private static PreparedStatement psAfegirRuta;
    private static PreparedStatement psEditarRuta;
    private static PreparedStatement psEditarTextHtmlRuta;
    private static PreparedStatement psEliminarRuta;
    private static PreparedStatement psObtenirLlistaRuta;
    private static PreparedStatement psObtenirLlistaRutaUsuari;
    private static PreparedStatement psObtenirRuta;
    private static PreparedStatement psObtenirPropietariRuta;
    private static PreparedStatement psVerificarPropietariRuta;
    
    private static PreparedStatement psAfegirFetes;
    private static PreparedStatement psObtenirLlistaFetes;
    
    private static PreparedStatement psObtenirTipusPerId;
    
    private static PreparedStatement psAfegirPuntRuta;
    private static PreparedStatement psEditarPuntRuta;
    private static PreparedStatement psEliminarPuntRuta;
    private static PreparedStatement psObtenirPuntRuta;
    private static PreparedStatement psObtenirLlistaPuntsRuta;
    
    private static PreparedStatement psAfegirComentari;
    private static PreparedStatement psEditarComentari;
    private static PreparedStatement psEliminarComentari;
    private static PreparedStatement psQtatComentarisRuta;
    private static PreparedStatement psQtatComentarisRutaFeta;
    private static PreparedStatement psQtatComentarisRutaNoFeta;
    private static PreparedStatement psMitjaVinf;
    private static PreparedStatement psMitjaVseg;
    private static PreparedStatement psMitjaVpai;
    private static PreparedStatement psMitjaVdific;
    
    private static PreparedStatement psAfegirCompany;
    private static PreparedStatement psEliminarCompany;
    
    private static PreparedStatement psPotBorrarRuta;
    

    public GestorBDWikilocJdbc() throws GestorBDWikilocException{
        this("connexioOracleWikiloc.properties");
    }
    
    public GestorBDWikilocJdbc(String nomFitxerPropietats) throws GestorBDWikilocException{
    
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(nomFitxerPropietats));
            String[] claus = {"url", "usuari", "contrasenya"};
            String[] valors = new String[3];
            for (int i = 0; i < claus.length; i++) {
                valors[i] = props.getProperty(claus[i]);
                if (valors[i] == null || valors[i].isEmpty()) {
                    throw new GestorBDWikilocException("L'arxiu " + nomFitxerPropietats + " no troba la clau " + claus[i]);
                }
            }
            conn = DriverManager.getConnection(valors[0], valors[1], valors[2]);
            conn.setAutoCommit(false);
            
        } catch (IOException ex) {
            throw new GestorBDWikilocException("Problemes en recuperar l'arxiu de configuració " + nomFitxerPropietats + "\n" + ex.getMessage());
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("No es pot establir la connexió.\n" + ex.getMessage());
        }
        
        
        // Una vegada ja hem establert connexió, intentem crear tots els PreparedStatement que usarem a l'aplicació
        String inst = null;
        try {                   
            inst = "select * from usuari where pwd = ? and (login = ? or email = ?)";
            psIniciarSessio = conn.prepareStatement(inst);
            
            inst = "insert into usuari(login,pwd,email) values(?,?,?)";
            psAfegirUsuari = conn.prepareStatement(inst);
            
            inst = "insert into ruta(titol,desc_ruta,text_ruta,dist,temps,desn_p,desn_n,dific,login_usuari) values(?,?,?,?,?,?,?,?,?)";
            psAfegirRuta = conn.prepareStatement(inst);
            
            inst = "update ruta set titol = ?, desc_ruta = ?, text_ruta = ?, dist = ?, temps = ?, desn_p = ?, desn_n = ?, dific = ? where id = ?";
            psEditarRuta = conn.prepareStatement(inst);
            
            inst = "update ruta set text_ruta = ? where id = ?";
            psEditarTextHtmlRuta = conn.prepareStatement(inst);
            
            inst = "delete from ruta where id = ?";
            psEliminarRuta = conn.prepareStatement(inst);
            
            inst = "select r.*, u.* from ruta r join usuari u on r.login_usuari = u.login";
            psObtenirLlistaRuta = conn.prepareStatement(inst);
            
            inst = "select r.*, u.* from ruta r join usuari u on r.login_usuari = u.login where r.login_usuari = ?";
            psObtenirLlistaRutaUsuari = conn.prepareStatement(inst);
            
            inst = "select r.*, u.* from ruta r join usuari u on r.login_usuari = u.login where r.id = ?";
            psObtenirRuta = conn.prepareStatement(inst);
            
            inst = "select u.* from ruta r join usuari u on r.login_usuari = u.login where id = ?";
            psObtenirPropietariRuta = conn.prepareStatement(inst);
            
            inst = "select * from ruta where id = ? and login_usuari = ?";
            psVerificarPropietariRuta = conn.prepareStatement(inst);
            
            inst = "insert into fetes values(?,?,?)";
            psAfegirFetes = conn.prepareStatement(inst);
            
            inst = "select f.*, u.*, r.* \n" +
                    "from fetes f join usuari u on f.login_usuari = u.login\n" +
                    "             join ruta r on f.id_ruta = r.id where f.id_ruta = ?";
            psObtenirLlistaFetes = conn.prepareStatement(inst);
            
            inst = "select * from tipus where id = ?";
            psObtenirTipusPerId = conn.prepareStatement(inst);
            
            inst = "insert into punt(num,nom,desc_punt,lat,lon,alt,id_ruta,id_tipus) values(?,?,?,?,?,?,?,?)";
            psAfegirPuntRuta = conn.prepareStatement(inst);
            
            inst = "update punt set nom = ?, desc_punt = ?, lat = ?, lon = ?, alt = ?, id_tipus = ? where num = ? and id_ruta = ?";
            psEditarPuntRuta = conn.prepareStatement(inst);
            
            inst = "delete from punt where num = ? and id_ruta = ?";
            psEliminarPuntRuta = conn.prepareStatement(inst);
            
            inst = "select p.*, r.*, t.id as id_tipus,t.nom as tipus_nom, u.*\n" +
                   "from punt p join ruta r on p.id_ruta = r.id\n" +
                   "            join tipus t on p.id_tipus = t.id\n" +
                   "            join usuari u on r.login_usuari = u.login\n" +
                   "where p.num = ? and p.id_ruta = ?";
            psObtenirPuntRuta = conn.prepareStatement(inst);
            
            inst = "select p.*, r.*, t.id as id_tipus,t.nom as tipus_nom, u.*\n" +
                    "from punt p join ruta r on p.id_ruta = r.id\n" +
                    "            join tipus t on p.id_tipus = t.id\n" +
                    "            join usuari u on r.login_usuari = u.login\n" +
                    "where p.id_ruta = ?";
            psObtenirLlistaPuntsRuta = conn.prepareStatement(inst);
            
            inst = "insert into comentari(text,v_inf,feta,v_seg,v_pai,dific,login_usuari,id_ruta) values (?,?,?,?,?,?,?,?)";
            psAfegirComentari = conn.prepareStatement(inst);
            
            inst = "update comentari set text = ?, v_inf = ?, v_seg = ?, v_pai = ?, dific = ? where id = ?";
            psEditarComentari = conn.prepareStatement(inst);
            
            inst = "delete from comentari where id = ?";
            psEliminarComentari = conn.prepareStatement(inst);
            
            inst = "select count(*) as qt_comentaris from comentari where id_ruta = ?";
            psQtatComentarisRuta = conn.prepareStatement(inst);
            
            inst = "select count(*) as qt_comentaris from comentari where id_ruta = ? and feta = 1";
            psQtatComentarisRutaFeta = conn.prepareStatement(inst);
            
            inst = "select count(*) as qt_comentaris from comentari where id_ruta = ? and feta = 0";
            psQtatComentarisRutaNoFeta = conn.prepareStatement(inst);
            
            inst = "select avg(v_inf) as mitja from comentari where id_ruta = ?";
            psMitjaVinf = conn.prepareStatement(inst);
            
            inst = "select avg(v_seg) as mitja from comentari where id_ruta = ?";
            psMitjaVseg = conn.prepareStatement(inst);
            
            inst = "select avg(v_pai) as mitja from comentari where id_ruta = ?";
            psMitjaVpai = conn.prepareStatement(inst);
             
            inst = "select avg(dific) as mitja from comentari where id_ruta = ?";
            psMitjaVdific = conn.prepareStatement(inst);
            
            inst = "insert into companys values(?,?)";
            psAfegirCompany = conn.prepareStatement(inst);
            
            inst = "delete from companys where login_usuari = ? and id_comentari = ?";
            psEliminarCompany = conn.prepareStatement(inst);
            
            //inst = "";
            //psPotBorrarRuta = conn.prepareStatement(inst);
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("No es pot crear el PreparedStatement:\n " + inst + "\n" + ex.getMessage());
        }
             
        
    }
    

    @Override
    public void tancarCapa() throws GestorBDWikilocException {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new GestorBDWikilocException("Error en fer rollback final.\n" + ex.getMessage());
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new GestorBDWikilocException("Error en tancar la connexió.\n" + ex.getMessage());
            }
        }
    }

    @Override
    public void confirmarCanvis() throws GestorBDWikilocException {
        try {
            conn.commit();
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error al confirmar els canvis");
        }
    }

    @Override
    public void desferCanvis() throws GestorBDWikilocException {
        try {
            conn.rollback();
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error al desfer els canvis");
        }
    }
    
    

    @Override
    public Usuari iniciarSessio(String login, String pwd) throws GestorBDWikilocException {
        
        try {
            
            ResultSet rsIniciarSessio = null;
    
            psIniciarSessio.setString(1, pwd);
            psIniciarSessio.setString(2, login);
            psIniciarSessio.setString(3, login);
            
            rsIniciarSessio = psIniciarSessio.executeQuery();
            
            
            
            boolean sortida = false;
            if(rsIniciarSessio.next()){
                sortida = true;
            }
            
            Usuari u = null;
            if(rsIniciarSessio != null){
                u = new Usuari(rsIniciarSessio.getString("login"),rsIniciarSessio.getString("pwd"),rsIniciarSessio.getString("email"));
                rsIniciarSessio.close();
            }
            
            return u;
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error al realitzar l'inici de sessió.\n" + ex.getMessage());
        }
        
        
    }
    
    
    @Override
    public boolean afegirUsuari(Usuari u) throws GestorBDWikilocException {
        
        try {
                
            psAfegirUsuari.setString(1, u.getLogin());
            psAfegirUsuari.setString(2, u.getPwd());
            psAfegirUsuari.setString(3, u.getEmail());
            
            int registres_afectats = psAfegirUsuari.executeUpdate();
  
            if(registres_afectats != 1){
                return false;
            }
            
            return true;
            
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean afegirRuta(Ruta r) throws GestorBDWikilocException {
                
        try{
         
            psAfegirRuta.setString(1, r.getTitol());
            psAfegirRuta.setString(2, r.getDescRuta());
            psAfegirRuta.setString(3, r.getTextRuta());
            psAfegirRuta.setDouble(4, r.getDist());
            psAfegirRuta.setInt(5, r.getTemps());
            psAfegirRuta.setInt(6, r.getDesnP());
            psAfegirRuta.setInt(7, r.getDesnN());
            psAfegirRuta.setInt(8, r.getDific());
            psAfegirRuta.setString(9, r.getLoginUsuari().getLogin());

            
            int registres_afectats = psAfegirRuta.executeUpdate();
  
            if(registres_afectats != 1){
                return false;
            }
            
            return true;
            
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean editarRuta(Ruta r) throws GestorBDWikilocException {
        try{
         
            psEditarRuta.setString(1, r.getTitol());
            psEditarRuta.setString(2, r.getDescRuta());
            psEditarRuta.setString(3, r.getTextRuta());
            psEditarRuta.setDouble(4, r.getDist());
            psEditarRuta.setInt(5, r.getTemps());
            psEditarRuta.setInt(6, r.getDesnP());
            psEditarRuta.setInt(7, r.getDesnN());
            psEditarRuta.setInt(8, r.getDific());
            psEditarRuta.setInt(9, r.getId());

            
            int registres_afectats = psEditarRuta.executeUpdate();

            if(registres_afectats != 1){
                return false;
            }
            
            return true;
            
        }catch(Exception ex){
            System.out.println("gestor error: "+ex.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean editarTextHtmlRuta(Integer id, String text_ruta) throws GestorBDWikilocException {
        
        try{
         
            psEditarTextHtmlRuta.setString(1, text_ruta);
            psEditarTextHtmlRuta.setInt(2, id);
            
            int registres_afectats = psEditarTextHtmlRuta.executeUpdate();

            if(registres_afectats != 1){
                return false;
            }
            System.out.println("TRUEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            return true;
            
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean eliminarRuta(Integer id) throws GestorBDWikilocException {

        try{

            psEliminarRuta.setInt(1, id);

            int i = psEliminarRuta.executeUpdate();
            
            if(i != 1){
                return false;
            }
            
            return true;
            
        }catch(Exception ex){
            return false;
        }   
        
    }

    @Override
    public List<Ruta> obtenirLlistaRuta() throws GestorBDWikilocException {
        
        List<Ruta> rutes = new ArrayList();
        
        try {
                                 
            ResultSet rsRutes = null;
            
            
            rsRutes = psObtenirLlistaRuta.executeQuery();
            
            while(rsRutes.next()){
                
                Usuari u = new Usuari(rsRutes.getString("login"),rsRutes.getString("pwd"),rsRutes.getString("email"));
                
                Ruta r = new Ruta(rsRutes.getInt("id"), 
                                    rsRutes.getString("titol"),
                                    rsRutes.getString("desc_ruta"),
                                    rsRutes.getString("text_ruta"),
                                    rsRutes.getDouble("dist"),
                                    rsRutes.getInt("temps"),
                                    rsRutes.getInt("desn_p"),
                                    rsRutes.getInt("desn_n"),
                                    rsRutes.getInt("dific"),
                                    u);
                
               
                
                rutes.add(r);
            }               
            
            if(rsRutes != null){
                rsRutes.close();
            }
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir les rutes.\n" + ex.getMessage());
        }
        
        return rutes;
        
    }
    
    
    @Override
    public List<Ruta> obtenirLlistaRutaUsuari(String usuari) throws GestorBDWikilocException {
        //psObtenirLlistaRutaUsuari
    
        List<Ruta> rutes = new ArrayList();
        try {
            ResultSet rsRutes = null;
            
            psObtenirLlistaRutaUsuari.setString(1, usuari);
            rsRutes = psObtenirLlistaRutaUsuari.executeQuery();
            
            while(rsRutes.next()){
                Usuari u = new Usuari(rsRutes.getString("login"),rsRutes.getString("pwd"),rsRutes.getString("email"));
                
                Ruta r = new Ruta(rsRutes.getInt("id"), 
                                    rsRutes.getString("titol"),
                                    rsRutes.getString("desc_ruta"),
                                    rsRutes.getString("text_ruta"),
                                    rsRutes.getDouble("dist"),
                                    rsRutes.getInt("temps"),
                                    rsRutes.getInt("desn_p"),
                                    rsRutes.getInt("desn_n"),
                                    rsRutes.getInt("dific"),
                                    u);

                rutes.add(r);
            }
            
            if(rsRutes != null){
                rsRutes.close();
            }
            
        
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir les rutes de l'usuari.\n" + ex.getMessage());
        }
        
        return rutes;
    }

    @Override
    public Ruta obtenirRuta(Integer id) throws GestorBDWikilocException {
        
        try {
            
            ResultSet rsObtenirRuta = null;
            psObtenirRuta.setInt(1, id);
            rsObtenirRuta = psObtenirRuta.executeQuery();
            
            Ruta ruta;
            if(rsObtenirRuta.next()){
                
                Usuari u = new Usuari(rsObtenirRuta.getString("login"),rsObtenirRuta.getString("pwd"),rsObtenirRuta.getString("email"));
                
                
                ruta = new Ruta(rsObtenirRuta.getInt("id"), 
                                    rsObtenirRuta.getString("titol"),
                                    rsObtenirRuta.getString("desc_ruta"),
                                    rsObtenirRuta.getString("text_ruta"),
                                    rsObtenirRuta.getDouble("dist"),
                                    rsObtenirRuta.getInt("temps"),
                                    rsObtenirRuta.getInt("desn_p"),
                                    rsObtenirRuta.getInt("desn_n"),
                                    rsObtenirRuta.getInt("dific"),
                                    u);
            }else{
                ruta = null;
            }
            
            if(rsObtenirRuta != null){
                rsObtenirRuta.close();
            }
            
            return ruta;
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la ruta.\n" + ex.getMessage());
        }
        
        
    }

    @Override
    public Usuari obtenirPropietariRuta(Integer id) throws GestorBDWikilocException {
        
        try {
            
            ResultSet rsObtenirPropietariRuta = null;
            psObtenirPropietariRuta.setInt(1, id);
            rsObtenirPropietariRuta = psObtenirPropietariRuta.executeQuery();
            
            Usuari usuari;
            if(rsObtenirPropietariRuta.next()){
                
                usuari = new Usuari(rsObtenirPropietariRuta.getString("login"),rsObtenirPropietariRuta.getString("pwd"),rsObtenirPropietariRuta.getString("email"));
                
                
                
            }else{
                usuari = null;
            }
            
            if(rsObtenirPropietariRuta != null){
                rsObtenirPropietariRuta.close();
            }
            
            return usuari;
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir el propietari de la ruta.\n" + ex.getMessage());
        }
    }

    @Override
    public boolean verificarPropietariRuta(String login, Integer id) throws GestorBDWikilocException {
        
        try {
            
            ResultSet rsVerificarPropietariRuta = null;
    
            psVerificarPropietariRuta.setInt(1, id);
            psVerificarPropietariRuta.setString(2, login);
            
            rsVerificarPropietariRuta = psVerificarPropietariRuta.executeQuery();
            
            boolean sortida = false;
            if(rsVerificarPropietariRuta.next()){
                sortida = true;
            }
            
            if(rsVerificarPropietariRuta != null){
                rsVerificarPropietariRuta.close();
            }
            
            return sortida;
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error al verificar el propietari de la ruta.\n" + ex.getMessage());
        }
        
    }

    @Override
    public boolean afegirFetes(Fetes f) throws GestorBDWikilocException {
        
        try{
         
            java.sql.Date getimestmp = new java.sql.Date(f.getMt().getTime());
            
            psAfegirFetes.setString(1, f.getLoginUsuari().getLogin());
            psAfegirFetes.setDate(2, getimestmp);
            psAfegirFetes.setInt(3, f.getIdRuta().getId());

            
            int registres_afectats = psAfegirFetes.executeUpdate();
  
            if(registres_afectats != 1){
                return false;
            }
            
            return true;
            
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public List<Fetes> obtenirLlistaFetes(Integer id) throws GestorBDWikilocException {
        
        List<Fetes> fetes = new ArrayList();
        
        try {
                                 
            ResultSet rsObtenirLlistaFetes = null;
            
            psObtenirLlistaFetes.setInt(1, id);
            rsObtenirLlistaFetes = psObtenirLlistaFetes.executeQuery();
            
            while(rsObtenirLlistaFetes.next()){
                
                Usuari u = new Usuari(rsObtenirLlistaFetes.getString("login"),rsObtenirLlistaFetes.getString("pwd"),rsObtenirLlistaFetes.getString("email"));
                Ruta ruta = new Ruta(rsObtenirLlistaFetes.getInt("id"), 
                                    rsObtenirLlistaFetes.getString("titol"),
                                    rsObtenirLlistaFetes.getString("desc_ruta"),
                                    rsObtenirLlistaFetes.getString("text_ruta"),
                                    rsObtenirLlistaFetes.getDouble("dist"),
                                    rsObtenirLlistaFetes.getInt("temps"),
                                    rsObtenirLlistaFetes.getInt("desn_p"),
                                    rsObtenirLlistaFetes.getInt("desn_n"),
                                    rsObtenirLlistaFetes.getInt("dific"),
                                    u);
                
                Fetes f = new Fetes(u,rsObtenirLlistaFetes.getDate("mt"),ruta);
               
                fetes.add(f);
            }               
            
            if(rsObtenirLlistaFetes != null){
                rsObtenirLlistaFetes.close();
            }
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir els usuaris que han fet la ruta.\n" + ex.getMessage());
        }
        
        return fetes;
    }

    @Override
    public Tipus obtenirTipusPerId(Integer id) throws GestorBDWikilocException {
        
        try {
            
            ResultSet rsObtenirTipusPerId = null;
    
            psObtenirTipusPerId.setInt(1, id);
            rsObtenirTipusPerId = psObtenirTipusPerId.executeQuery();
            
            Tipus tipus;
            if(rsObtenirTipusPerId.next()){
                //PROVISIONAL
                //tipus = new Tipus(rsObtenirTipusPerId.getInt("id"),rsObtenirTipusPerId.getString("nom"),rsObtenirTipusPerId.getBlob("icona"));
                tipus = new Tipus(rsObtenirTipusPerId.getInt("id"),rsObtenirTipusPerId.getString("nom"),null);
                
                
            }else{
                tipus = null;
            }
            
            if(rsObtenirTipusPerId != null){
                rsObtenirTipusPerId.close();
            }
            
            return tipus;
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir el tipus.\n" + ex.getMessage());
        }
    }

    @Override
    public boolean afegirPuntRuta(Punt p) throws GestorBDWikilocException {
        
       try{

            psAfegirPuntRuta.setInt(1, p.getNum());
            psAfegirPuntRuta.setString(2, p.getNom());
            psAfegirPuntRuta.setString(3, p.getDescPunt());
            psAfegirPuntRuta.setInt(4, p.getLat());
            psAfegirPuntRuta.setInt(5, p.getLon());
            psAfegirPuntRuta.setInt(6, p.getAlt());
            psAfegirPuntRuta.setInt(7, p.getIdRuta().getId());
            psAfegirPuntRuta.setInt(8, p.getIdTipus().getId());

            
            int registres_afectats = psAfegirPuntRuta.executeUpdate();
  
            if(registres_afectats != 1){
                return false;
            }
            
            return true;
            
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean editarPuntRuta(Punt p, Integer id) throws GestorBDWikilocException {
        
         try{
            
            psEditarPuntRuta.setString(1, p.getNom());
            psEditarPuntRuta.setString(2, p.getDescPunt());
            psEditarPuntRuta.setInt(3, p.getLat());
            psEditarPuntRuta.setInt(4, p.getLon());
            psEditarPuntRuta.setInt(5, p.getAlt());
            psEditarPuntRuta.setInt(6, p.getIdTipus().getId());
            psEditarPuntRuta.setInt(7, p.getNum());
            psEditarPuntRuta.setInt(8, p.getIdRuta().getId());

            
            int registres_afectats = psEditarPuntRuta.executeUpdate();
  
            if(registres_afectats != 1){
                return false;
            }
            
            return true;
            
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean eliminarPuntRuta(Integer num, Integer id) throws GestorBDWikilocException {
        
        try{
            
            psEliminarPuntRuta.setInt(1, num);
            psEliminarPuntRuta.setInt(2, id);
            
            int registres_afectats = psEliminarPuntRuta.executeUpdate();
  
            if(registres_afectats != 1){
                return false;
            }
            
            return true;
            
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean eliminarLlistaPuntsRuta(List<Punt> p) throws GestorBDWikilocException {
        
        for(Punt punt : p){
            
            if(!eliminarPuntRuta(punt.getNum(), punt.getIdRuta().getId())){
                return false;
            }
            
        }
        return true;
    }

    @Override
    public Punt obtenirPuntRuta(Integer num, Integer id) throws GestorBDWikilocException {
        
        try {
            
            ResultSet rsObtenirPuntRuta = null;
    
            psObtenirPuntRuta.setInt(1, num);
            psObtenirPuntRuta.setInt(2, id);
            rsObtenirPuntRuta = psObtenirPuntRuta.executeQuery();
            
            Punt punt;
            if(rsObtenirPuntRuta.next()){
                
                
                Tipus tipus = new Tipus(rsObtenirPuntRuta.getInt("id_tipus"), rsObtenirPuntRuta.getString("tipus_nom"),null);
               
                
                Usuari u = new Usuari(rsObtenirPuntRuta.getString("login"),rsObtenirPuntRuta.getString("pwd"),rsObtenirPuntRuta.getString("email"));
                Ruta ruta = new Ruta(rsObtenirPuntRuta.getInt("id"), 
                                    rsObtenirPuntRuta.getString("titol"),
                                    rsObtenirPuntRuta.getString("desc_ruta"),
                                    rsObtenirPuntRuta.getString("text_ruta"),
                                    rsObtenirPuntRuta.getDouble("dist"),
                                    rsObtenirPuntRuta.getInt("temps"),
                                    rsObtenirPuntRuta.getInt("desn_p"),
                                    rsObtenirPuntRuta.getInt("desn_n"),
                                    rsObtenirPuntRuta.getInt("dific"),
                                    u);
                
                punt = new Punt(rsObtenirPuntRuta.getInt("num"),
                                rsObtenirPuntRuta.getString("nom"),
                                rsObtenirPuntRuta.getString("desc_punt"),
                                rsObtenirPuntRuta.getInt("lat"),
                                rsObtenirPuntRuta.getInt("lon"),
                                rsObtenirPuntRuta.getInt("alt"),
                                ruta,
                                tipus);
            }else{
                punt = null;
            }
            
            if(rsObtenirPuntRuta != null){
                rsObtenirPuntRuta.close();
            }
            
            return punt;
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir el punt.\n" + ex.getMessage());
        }
    }

    @Override
    public List<Punt> obtenirLlistaPuntsRuta(Integer id) throws GestorBDWikilocException {
        
        List<Punt> punts = new ArrayList();
        
        try {
                                 
            ResultSet rsObtenirLlistaPuntsRuta = null;
            
            psObtenirLlistaPuntsRuta.setInt(1, id);
            rsObtenirLlistaPuntsRuta = psObtenirLlistaPuntsRuta.executeQuery();
            
            while(rsObtenirLlistaPuntsRuta.next()){
                
               Tipus tipus = new Tipus(rsObtenirLlistaPuntsRuta.getInt("id_tipus"), rsObtenirLlistaPuntsRuta.getString("tipus_nom"),null);
                
                Usuari u = new Usuari(rsObtenirLlistaPuntsRuta.getString("login"),rsObtenirLlistaPuntsRuta.getString("pwd"),rsObtenirLlistaPuntsRuta.getString("email"));
                
                Ruta ruta = new Ruta(rsObtenirLlistaPuntsRuta.getInt("id"), 
                                    rsObtenirLlistaPuntsRuta.getString("titol"),
                                    rsObtenirLlistaPuntsRuta.getString("desc_ruta"),
                                    rsObtenirLlistaPuntsRuta.getString("text_ruta"),
                                    rsObtenirLlistaPuntsRuta.getDouble("dist"),
                                    rsObtenirLlistaPuntsRuta.getInt("temps"),
                                    rsObtenirLlistaPuntsRuta.getInt("desn_p"),
                                    rsObtenirLlistaPuntsRuta.getInt("desn_n"),
                                    rsObtenirLlistaPuntsRuta.getInt("dific"),
                                    u);
                
                Punt punt = new Punt(rsObtenirLlistaPuntsRuta.getInt("num"),
                                rsObtenirLlistaPuntsRuta.getString("nom"),
                                rsObtenirLlistaPuntsRuta.getString("desc_punt"),
                                rsObtenirLlistaPuntsRuta.getInt("lat"),
                                rsObtenirLlistaPuntsRuta.getInt("lon"),
                                rsObtenirLlistaPuntsRuta.getInt("alt"),
                                ruta,
                                tipus);
               
                punts.add(punt);
            }               
            
            if(rsObtenirLlistaPuntsRuta != null){
                rsObtenirLlistaPuntsRuta.close();
            }
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir els punts de la ruta.\n" + ex.getMessage());
        }
        
        return punts;
        
    }

    @Override
    public boolean afegirComentari(Comentari c, String login, Integer id) throws GestorBDWikilocException {
        
        try{
         
            psAfegirComentari.setString(1, c.getText());
            psAfegirComentari.setInt(2, c.getVinf());
            psAfegirComentari.setBoolean(3, c.getFeta());
            
            if(c.getVseg() != null){
                psAfegirComentari.setInt(4, c.getVseg());
            }else{
                psAfegirComentari.setNull(4, java.sql.Types.INTEGER);
            }
            if(c.getVpai() != null){
                psAfegirComentari.setInt(5, c.getVpai());
            }else{
                psAfegirComentari.setNull(5, java.sql.Types.INTEGER);
            }
            if(c.getDific() != null){
                psAfegirComentari.setInt(6, c.getDific());
            }else{
                psAfegirComentari.setNull(6, java.sql.Types.INTEGER);
            }
            
            psAfegirComentari.setString(7, login);
            psAfegirComentari.setInt(8, id);

            
            int registres_afectats = psAfegirComentari.executeUpdate();
  
            if(registres_afectats != 1){
                return false;
            }
            
            return true;
            
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean editarComentari(Comentari c, String login, Integer id) throws GestorBDWikilocException {

        try{
         
            psEditarComentari.setString(1, c.getText());
            psEditarComentari.setInt(2, c.getVinf());
            psEditarComentari.setInt(3, c.getVseg());
            psEditarComentari.setInt(4, c.getVpai());
            psEditarComentari.setInt(5, c.getDific());
            psEditarComentari.setInt(6, id);

            
            int registres_afectats = psEditarComentari.executeUpdate();
  
            if(registres_afectats != 1){
                return false;
            }
            
            return true;
            
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean eliminarComentari(Integer id) throws GestorBDWikilocException {
        
        try{
            
            psEliminarComentari.setInt(1, id);
            
            int registres_afectats = psEliminarComentari.executeUpdate();
  
            if(registres_afectats != 1){
                return false;
            }
            
            return true;
            
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public Integer qtatComentarisRuta(Integer id) throws GestorBDWikilocException {
        
        try {
            
            ResultSet rsQtatComentarisRuta = null;
            
            psQtatComentarisRuta.setInt(1, id);
            rsQtatComentarisRuta = psQtatComentarisRuta.executeQuery();
            
            int qt;
            if(rsQtatComentarisRuta.next()){
                
                qt = rsQtatComentarisRuta.getInt("qt_comentaris");
                
            }else{
                qt = -1;
            }
            
            System.out.println("QT: "+qt);
            
            if(rsQtatComentarisRuta != null){
                rsQtatComentarisRuta.close();
            }
            
            return qt;
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la quantitat de comentaris.\n" + ex.getMessage());
        }
    }

    @Override
    public Integer qtatComentarisRutaFeta(Integer id) throws GestorBDWikilocException {
        
        try {
            
            ResultSet rsQtatComentarisRutaFeta = null;
            
            psQtatComentarisRutaFeta.setInt(1, id);
            rsQtatComentarisRutaFeta = psQtatComentarisRutaFeta.executeQuery();
            
            int qt;
            if(rsQtatComentarisRutaFeta.next()){
                
                qt = rsQtatComentarisRutaFeta.getInt("qt_comentaris");
                
            }else{
                qt = -1;
            }
            
            if(rsQtatComentarisRutaFeta != null){
                rsQtatComentarisRutaFeta.close();
            }
            
            return qt;
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la quantitat de comentaris fets i que constin com a fet.\n" + ex.getMessage());
        }
    }

    @Override
    public Integer qtatComentarisRutaNoFeta(Integer id) throws GestorBDWikilocException {
        
        try {
            
            ResultSet rsQtatComentarisRutaNoFeta = null;
            
            psQtatComentarisRutaNoFeta.setInt(1, id);
            rsQtatComentarisRutaNoFeta = psQtatComentarisRutaNoFeta.executeQuery();
            
            int qt;
            if(rsQtatComentarisRutaNoFeta.next()){
                
                qt = rsQtatComentarisRutaNoFeta.getInt("qt_comentaris");
                
            }else{
                qt = -1;
            }
            
            if(rsQtatComentarisRutaNoFeta != null){
                rsQtatComentarisRutaNoFeta.close();
            }
            
            return qt;
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la quantitat de comentaris fets i que constin com a no fet.\n" + ex.getMessage());
        }
    }

    @Override
    public Double mitjaVinf(Integer id) throws GestorBDWikilocException {
        
        try {
            
            ResultSet rsMitjaVinf = null;
            
            psMitjaVinf.setInt(1, id);
            rsMitjaVinf = psMitjaVinf.executeQuery();
            
            double mitja;
            if(rsMitjaVinf.next()){
                
                mitja = rsMitjaVinf.getDouble("mitja");
                
            }else{
                mitja = -1;
            }
            
            if(rsMitjaVinf != null){
                rsMitjaVinf.close();
            }
            
            return mitja;
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la mitja de v_inf.\n" + ex.getMessage());
        }
        
    }

    @Override
    public Double mitjaVseg(Integer id) throws GestorBDWikilocException {
        
        try {
            
            ResultSet rsMitjaVseg = null;
            
            psMitjaVseg.setInt(1, id);
            rsMitjaVseg = psMitjaVseg.executeQuery();
            
            double mitja;
            if(rsMitjaVseg.next()){
                
                mitja = rsMitjaVseg.getDouble("mitja");
                
            }else{
                mitja = -1;
            }
            
            if(rsMitjaVseg != null){
                rsMitjaVseg.close();
            }
            
            return mitja;
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la mitja de v_seg.\n" + ex.getMessage());
        }
    }

    @Override
    public Double mitjaVpai(Integer id) throws GestorBDWikilocException {
        
        try {
            
            ResultSet rsMitjaVpai = null;
            
            psMitjaVpai.setInt(1, id);
            rsMitjaVpai = psMitjaVpai.executeQuery();
            
            double mitja;
            if(rsMitjaVpai.next()){
                
                mitja = rsMitjaVpai.getDouble("mitja");
                
            }else{
                mitja = -1;
            }
            
            if(rsMitjaVpai != null){
                rsMitjaVpai.close();
            }
            
            return mitja;
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la mitja de v_pai.\n" + ex.getMessage());
        }
    }

    @Override
    public Double mitjaVdific(Integer id) throws GestorBDWikilocException {
        
        try {
            
            ResultSet rsMitjaVdific = null;
            
            psMitjaVdific.setInt(1, id);
            rsMitjaVdific = psMitjaVdific.executeQuery();
            
            double mitja;
            if(rsMitjaVdific.next()){
                
                mitja = rsMitjaVdific.getDouble("mitja");
                
            }else{
                mitja = -1;
            }
            
            if(rsMitjaVdific != null){
                rsMitjaVdific.close();
            }
            
            return mitja;
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la mitja de dific.\n" + ex.getMessage());
        }
    }

    @Override
    public boolean afegirCompany(Companys c) throws GestorBDWikilocException {
        
        try{
         
            psAfegirCompany.setString(1, c.getLoginUsuari().getLogin());
            psAfegirCompany.setInt(2, c.getIdComentari().getId());

            
            int registres_afectats = psAfegirCompany.executeUpdate();
  
            if(registres_afectats != 1){
                return false;
            }
            
            return true;
            
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean eliminarCompany(String login_usuari, Integer id_comentari) throws GestorBDWikilocException {
        
         try{
            
            psEliminarCompany.setString(1, login_usuari);
            psEliminarCompany.setInt(2, id_comentari);
            
            int registres_afectats = psEliminarCompany.executeUpdate();
  
            if(registres_afectats != 1){
                return false;
            }
            
            return true;
            
        }catch(Exception ex){
            return false;
        }
        
    }



   
}
