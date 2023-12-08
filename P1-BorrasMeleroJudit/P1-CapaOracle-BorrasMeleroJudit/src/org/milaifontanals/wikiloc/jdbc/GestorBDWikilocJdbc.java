package org.milaifontanals.wikiloc.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
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
    private static PreparedStatement psObtenirUsuari;
    private static PreparedStatement psEditarFotoUsuari;
    private static PreparedStatement psEditarEmailUsuari;
    private static PreparedStatement psEditarPwdUsuari;
    private static PreparedStatement psObtenirUsuaris;

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
    private static PreparedStatement psObtenirLlistaFetesUsuari;
    private static PreparedStatement psHaFetRuta;

    private static PreparedStatement psObtenirTipusPerId;
    private static PreparedStatement psObtenirLlistaTipus;
    private static PreparedStatement psObtenirTipusPunt;

    private static PreparedStatement psAfegirPuntRuta;
    private static PreparedStatement psEditarPuntRuta;
    private static PreparedStatement editarPuntRutaSenseFoto;
    private static PreparedStatement psEliminarPuntRuta;
    private static PreparedStatement psEliminarPuntRutaTots;
    private static PreparedStatement psObtenirPuntRuta;
    private static PreparedStatement psObtenirLlistaPuntsRuta;
    private static PreparedStatement psEditarOrdrePuntRuta;

    private static PreparedStatement psAfegirComentari;
    private static PreparedStatement psEditarComentari;
    private static PreparedStatement psEliminarComentari;
    private static PreparedStatement psObtenirLlistaComentaris;
    private static PreparedStatement psQtatComentarisRuta;
    private static PreparedStatement psQtatComentarisRutaFeta;
    private static PreparedStatement psQtatComentarisRutaNoFeta;
    private static PreparedStatement psMitjaVinf;
    private static PreparedStatement psMitjaVseg;
    private static PreparedStatement psMitjaVpai;
    private static PreparedStatement psMitjaVdific;

    private static PreparedStatement psAfegirCompany;
    private static PreparedStatement psEliminarCompany;
    private static PreparedStatement psObtenirCompany;

    private static PreparedStatement psPotBorrarRuta;

    private static PreparedStatement psFiltreRutaCreades;
    private static PreparedStatement psFiltreTotalRutes;


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

            inst = "select * from usuari where login = ?";
            psObtenirUsuari = conn.prepareStatement(inst);

            inst = "update usuari set foto = ? where login = ?";
            psEditarFotoUsuari = conn.prepareStatement(inst);
            
            inst = "update usuari set email = ? where login = ?";
            psEditarEmailUsuari = conn.prepareStatement(inst);
            
            inst = "update usuari set pwd = ? where login = ?";
            psEditarPwdUsuari = conn.prepareStatement(inst);

            String returnCols[] = {"ID","TITOL","DESC_RUTA","TEXT_RUTA","DIST","TEMPS","DESN_P","DESN_N","DIFIC","LOGIN_USUARI"};
            inst = "insert into ruta(titol,desc_ruta,text_ruta,dist,temps,desn_p,desn_n,dific,login_usuari) values(?,?,?,?,?,?,?,?,?)";
            psAfegirRuta = conn.prepareStatement(inst,returnCols);

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
            
            inst = "select f.*, u.*, r.* \n"
                    + "from fetes f join usuari u on f.login_usuari = u.login\n"
                    + "             join ruta r on f.id_ruta = r.id where f.login_usuari = ?";
            psObtenirLlistaFetesUsuari = conn.prepareStatement(inst);

            inst = "select * from tipus where id = ?";
            psObtenirTipusPerId = conn.prepareStatement(inst);

            inst ="select * from tipus";
            psObtenirLlistaTipus = conn.prepareStatement(inst);

            inst = "select t.* \n" +
                   "from punt p join tipus t on p.id_tipus = t.id \n" +
                   "where id_ruta = ? and num = ? ";
            psObtenirTipusPunt = conn.prepareStatement(inst);

            inst = "insert into punt(nom,desc_punt,lat,lon,alt,ordre,id_ruta,id_tipus,foto) values(?,?,?,?,?,?,?,?,?)";
            psAfegirPuntRuta = conn.prepareStatement(inst);

            inst = "update punt set nom = ?, desc_punt = ?, foto = ?, lat = ?, lon = ?, alt = ?, id_tipus = ? where num = ? and id_ruta = ?";
            psEditarPuntRuta = conn.prepareStatement(inst);

            inst = "update punt set nom = ?, desc_punt = ?, lat = ?, lon = ?, alt = ?, id_tipus = ? where num = ? and id_ruta = ?";
            editarPuntRutaSenseFoto = conn.prepareStatement(inst);

            inst = "delete from punt where num = ? and id_ruta = ?";
            psEliminarPuntRuta = conn.prepareStatement(inst);

            inst = "delete from punt where id_ruta = ?";
            psEliminarPuntRutaTots = conn.prepareStatement(inst);

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
                    "where p.id_ruta = ?" +
                    "order by p.ordre";
            psObtenirLlistaPuntsRuta = conn.prepareStatement(inst);

            inst = "update punt set ordre = ? where num = ? and id_ruta = ?";
            psEditarOrdrePuntRuta = conn.prepareStatement(inst);

            String returnColsComentari[] = {"ID","TEXT","V_INF","FETA","V_SEG","V_PAI","DIFIC","LOGIN_USUARI","ID_RUTA"};
            inst = "insert into comentari(text,v_inf,feta,v_seg,v_pai,dific,login_usuari,id_ruta) values (?,?,?,?,?,?,?,?)";
            psAfegirComentari = conn.prepareStatement(inst,returnColsComentari);

            inst = "update comentari set text = ?, v_inf = ?, v_seg = ?, v_pai = ?, dific = ? where id = ?";
            psEditarComentari = conn.prepareStatement(inst);

            inst = "delete from comentari where id = ?";
            psEliminarComentari = conn.prepareStatement(inst);

            inst = "select c.*, co.id_comentari as COMPANY_COMENTARI, co.LOGIN_USUARI as COMPANY_LOGIN\n" +
                    "from comentari c left join companys co on c.id = co.id_comentari\n" +
                    "where c.id_ruta = ?\n" +
                    "order by c.mt";
            psObtenirLlistaComentaris = conn.prepareStatement(inst);

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

            inst = "select * from companys where id_comentari = ?";
            psObtenirCompany = conn.prepareStatement(inst);

            inst = "select r.*, u.* from ruta r join usuari u on u.login = r.login_usuari where (? = '-1' or upper(titol) like upper(?)) and (? = -1 or dific = ?) and (dist = -1.0 or dist >= ?) and login_usuari = ?";
            psFiltreRutaCreades = conn.prepareStatement(inst);
            
            inst = "select r.*, u.* \n" +
                    "from ruta r right join fetes f on r.id = f.id_ruta join usuari u on u.login = r.login_usuari \n" +
                    "where (? = '-1' or upper(titol) like upper(?)) and (? = -1 or dific = ?) and (? = -1.0 or dist >= ?) \n" +
                    "and (? = '-1' or r.login_usuari = ?) and (? = '-1' or f.login_usuari = ?) ";
            psFiltreTotalRutes = conn.prepareStatement(inst);
            
            inst = "select * from fetes where login_usuari = ? and id_ruta = ?";
            psHaFetRuta = conn.prepareStatement(inst);
            
            inst = "select * from usuari";
            psObtenirUsuaris = conn.prepareStatement(inst);
            
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
    public Usuari obtenirUsuari(String login) throws GestorBDWikilocException {

        try {

            ResultSet rsObtenirUsuari = null;

            psObtenirUsuari.setString(1, login);

            rsObtenirUsuari = psObtenirUsuari.executeQuery();


            Usuari usuari = null;
            if(rsObtenirUsuari.next()){
                usuari = new Usuari(rsObtenirUsuari.getString("login"),
                        rsObtenirUsuari.getString("pwd"),
                        rsObtenirUsuari.getString("email"));

            }
            try{
                usuari.setFoto(IOUtils.toByteArray(rsObtenirUsuari.getBinaryStream("foto")));
            }catch(Exception ex){
                
            }
            


            if(rsObtenirUsuari!=null){
                rsObtenirUsuari.close();
            }

            return usuari;

        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir les dades de l'usuari.\n" + ex.getMessage());
        }
    }

    @Override
    public boolean editarFotoUsuari(Usuari u, String url_foto) throws GestorBDWikilocException {

        try {
            
            if(url_foto != null){
                FileInputStream fin = new FileInputStream(url_foto);
                psEditarFotoUsuari.setBinaryStream(1, fin);
                psEditarFotoUsuari.setString(2, u.getLogin());
            }else{
                psEditarFotoUsuari.setNull(1, java.sql.Types.BLOB);
                psEditarFotoUsuari.setString(2, u.getLogin());
            }

            int registres_afectats = psEditarFotoUsuari.executeUpdate();

            if (registres_afectats != 1) {
                return false;
            }

            return true;

        } catch (Exception ex) {
            System.out.println("error foto editada: "+ex.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean editarEmailUsuari(Usuari u, String email) throws GestorBDWikilocException {

        try {
            
            psEditarEmailUsuari.setString(1, email);
            psEditarEmailUsuari.setString(2, u.getLogin());
            
            int registres_afectats = psEditarEmailUsuari.executeUpdate();

            if (registres_afectats != 1) {
                return false;
            }

            return true;

        } catch (Exception ex) {
            System.out.println("error email: "+ex.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean editarPwdUsuari(Usuari u, String pwd) throws GestorBDWikilocException {

        try {
            
            psEditarPwdUsuari.setString(1, pwd);
            psEditarPwdUsuari.setString(2, u.getLogin());
            
            int registres_afectats = psEditarPwdUsuari.executeUpdate();

            if (registres_afectats != 1) {
                return false;
            }

            return true;

        } catch (Exception ex) {
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
    public boolean afegirFetes(String login, int id_ruta) throws GestorBDWikilocException {
        try{

            Date d = new Date();
            java.sql.Date getimestmp = new java.sql.Date(d.getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
           
            System.out.println("HORA DE ENTRADA: "+sdf.format(d));

            psAfegirFetes.setString(1, login);
            psAfegirFetes.setDate(2, getimestmp);
            psAfegirFetes.setInt(3, id_ruta);


            int registres_afectats = psAfegirFetes.executeUpdate();

            if(registres_afectats != 1){
                return false;
            }

            return true;

        }catch(Exception ex){
            System.out.println("ERROR AFEGIR FETES!!!! "+ex.getMessage());
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
    public List<Fetes> obtenirLlistaFetesUsuari(String login_usuari) throws GestorBDWikilocException {

        List<Fetes> fetes = new ArrayList();

        try {

            ResultSet rsObtenirLlistaFetesUsuari = null;

            psObtenirLlistaFetesUsuari.setString(1, login_usuari);
            rsObtenirLlistaFetesUsuari = psObtenirLlistaFetesUsuari.executeQuery();

            while(rsObtenirLlistaFetesUsuari.next()){

                Usuari u = new Usuari(rsObtenirLlistaFetesUsuari.getString("login"),rsObtenirLlistaFetesUsuari.getString("pwd"),rsObtenirLlistaFetesUsuari.getString("email"));
                Ruta ruta = new Ruta(rsObtenirLlistaFetesUsuari.getInt("id"),
                                    rsObtenirLlistaFetesUsuari.getString("titol"),
                                    rsObtenirLlistaFetesUsuari.getString("desc_ruta"),
                                    rsObtenirLlistaFetesUsuari.getString("text_ruta"),
                                    rsObtenirLlistaFetesUsuari.getDouble("dist"),
                                    rsObtenirLlistaFetesUsuari.getInt("temps"),
                                    rsObtenirLlistaFetesUsuari.getInt("desn_p"),
                                    rsObtenirLlistaFetesUsuari.getInt("desn_n"),
                                    rsObtenirLlistaFetesUsuari.getInt("dific"),
                                    u);

                Fetes f = new Fetes(u,rsObtenirLlistaFetesUsuari.getDate("mt"),ruta);

                fetes.add(f);
            }

            if(rsObtenirLlistaFetesUsuari != null){
                rsObtenirLlistaFetesUsuari.close();
            }

        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la llista de rutes que ha fet l'usuari indicat per paràmetre.\n" + ex.getMessage());
        }

        return fetes;
    }    
    
    
    @Override
    public Fetes haFetRuta(Ruta ruta, Usuari usuari_loginat) throws GestorBDWikilocException {
        try {
            //psHaFetRuta
            
            ResultSet rsHaFetRuta = null;
            
            psHaFetRuta.setString(1, usuari_loginat.getLogin());
            psHaFetRuta.setInt(2, ruta.getId());
            
            rsHaFetRuta = psHaFetRuta.executeQuery();
            
            if(rsHaFetRuta.next()){
            
                Fetes f = new Fetes(usuari_loginat,rsHaFetRuta.getDate("MT"),ruta);
                
                return f;
            
            }else{
                return null;
            }
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir si l'usuari ha fet la ruta.\n" + ex.getMessage());
        }
        
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
                System.out.println("tipus null");
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
    public List<Tipus> obtenirLlistaTipus() throws GestorBDWikilocException {

        List<Tipus> tipus = new ArrayList();

        try {

            ResultSet rsObtenirLlistaTipus = null;

            rsObtenirLlistaTipus = psObtenirLlistaTipus.executeQuery();

            while (rsObtenirLlistaTipus.next()) {

                Tipus t = new Tipus(rsObtenirLlistaTipus.getInt("id"),rsObtenirLlistaTipus.getString("nom"),IOUtils.toByteArray(rsObtenirLlistaTipus.getBinaryStream("icona")));

                tipus.add(t);
            }

            if (rsObtenirLlistaTipus != null) {
                rsObtenirLlistaTipus.close();
            }

        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la llista dels tipus de punt.\n" + ex.getMessage());
        } catch (IOException ex) {
            throw new GestorBDWikilocException("Error en obtenir la icona dels tipus de punt.\n" + ex.getMessage());
        }

        return tipus;
    }

    @Override
    public Tipus obtenirTipusPunt(Integer id_punt, Integer id_ruta) throws GestorBDWikilocException {
        /*
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
        */
        try {

            ResultSet rsObtenirTipusPunt = null;

            psObtenirTipusPunt.setInt(1, id_ruta);
            psObtenirTipusPunt.setInt(2, id_punt);
            rsObtenirTipusPunt = psObtenirTipusPunt.executeQuery();

            if(rsObtenirTipusPunt==null){
                System.out.println("NULL");
            }else{
                System.out.println("NO NULL");
            }
            rsObtenirTipusPunt.next();
            Tipus tipus = new Tipus(rsObtenirTipusPunt.getInt("id"),rsObtenirTipusPunt.getString("nom"),null);
/*
            if(rsObtenirTipusPunt.next()){

            }else{

            }

            if (rsObtenirTipusPunt != null) {
                rsObtenirTipusPunt.close();
            }
*/
            System.out.println("TIPUS: "+tipus);
            return tipus;

        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir el tipus.\n" + ex.getMessage());
        }

    }

    @Override
    public boolean afegirPuntRuta(Punt p, String url_foto) throws GestorBDWikilocException {

       try{

            psAfegirPuntRuta.setString(1, p.getNom());
            psAfegirPuntRuta.setString(2, p.getDescPunt());
            psAfegirPuntRuta.setInt(3, p.getLat());
            psAfegirPuntRuta.setInt(4, p.getLon());
            psAfegirPuntRuta.setInt(5, p.getAlt());
            psAfegirPuntRuta.setInt(6, p.getOrdre());
            psAfegirPuntRuta.setInt(7, p.getIdRuta().getId());
            psAfegirPuntRuta.setInt(8, p.getIdTipus().getId());

            if(url_foto!=null){
                FileInputStream fin = new FileInputStream(url_foto);
                psAfegirPuntRuta.setBinaryStream(9, fin);
            }else{
                psAfegirPuntRuta.setNull(9, java.sql.Types.BLOB);
            }

            int registres_afectats = psAfegirPuntRuta.executeUpdate();

            if(registres_afectats != 1){
                return false;
            }

            return true;

        }catch(Exception ex){
            System.out.println("ERRROR INSERT: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean editarPuntRuta(Punt p, Integer id, String url_foto) throws GestorBDWikilocException {

         try{

             System.out.println("URL FOTO: "+url_foto);
             System.out.println("ID RUTA: "+p.getIdRuta().getId());
             System.out.println("ID TIPUS RUTA: "+p.getIdTipus().getId());

            psEditarPuntRuta.setString(1, p.getNom());
            psEditarPuntRuta.setString(2, p.getDescPunt());

            FileInputStream fin = new FileInputStream(url_foto);
            psEditarPuntRuta.setBinaryStream(3, fin);
            psEditarPuntRuta.setInt(4, p.getLat());
            psEditarPuntRuta.setInt(5, p.getLon());
            psEditarPuntRuta.setInt(6, p.getAlt());
            psEditarPuntRuta.setInt(7, p.getIdTipus().getId());
            psEditarPuntRuta.setInt(8, p.getNum());
            psEditarPuntRuta.setInt(9, p.getIdRuta().getId());


            int registres_afectats = psEditarPuntRuta.executeUpdate();

            if(registres_afectats != 1){
                return false;
            }

            return true;

        }catch(Exception ex){
             System.out.println("ERROR GESTORRRR: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean editarPuntRutaSenseFoto(Punt p, Integer id) throws GestorBDWikilocException {

        //editarPuntRutaSenseFoto

        try{


             System.out.println("ID RUTA: "+p.getIdRuta().getId());
             System.out.println("ID TIPUS RUTA: "+p.getIdTipus().getId());
             System.out.println("NOM: "+p.getNom());

            editarPuntRutaSenseFoto.setString(1, p.getNom());
            editarPuntRutaSenseFoto.setString(2, p.getDescPunt());

            editarPuntRutaSenseFoto.setInt(3, p.getLat());
            editarPuntRutaSenseFoto.setInt(4, p.getLon());
            editarPuntRutaSenseFoto.setInt(5, p.getAlt());
            editarPuntRutaSenseFoto.setInt(6, p.getIdTipus().getId());
            editarPuntRutaSenseFoto.setInt(7, p.getNum());
            editarPuntRutaSenseFoto.setInt(8, p.getIdRuta().getId());


            int registres_afectats = editarPuntRutaSenseFoto.executeUpdate();

            if(registres_afectats != 1){
                return false;
            }
            System.out.println("TOT CORRECTE. EDITAR");
            return true;

        }catch(Exception ex){
             System.out.println("ERROR GESTORRRR: "+ex.getMessage());
            return false;
        }



    }


    @Override
    public boolean eliminarPuntRuta(Integer num, Integer id) throws GestorBDWikilocException {

        try{
            System.out.println("NUM; "+num+" RUTA: "+id);
            psEliminarPuntRuta.setInt(1, num);
            psEliminarPuntRuta.setInt(2, id);

            int registres_afectats = psEliminarPuntRuta.executeUpdate();

            if(registres_afectats != 1){
                return false;
            }

            return true;

        }catch(Exception ex){
            System.out.println("ERROR BD: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarPuntRutaTots(Integer id_ruta) throws GestorBDWikilocException {
        try{

            psEliminarPuntRutaTots.setInt(1, id_ruta);

            int registres_afectats = psEliminarPuntRutaTots.executeUpdate();

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
                            IOUtils.toByteArray(rsObtenirPuntRuta.getBinaryStream("foto")),
                                rsObtenirPuntRuta.getInt("lat"),
                                rsObtenirPuntRuta.getInt("lon"),
                                rsObtenirPuntRuta.getInt("alt"),
                                rsObtenirPuntRuta.getInt("ordre"),
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
        } catch (IOException ex) {
            throw new GestorBDWikilocException("Error en obtenir la foto.\n" + ex.getMessage());
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
                Punt punt = null;
                if(rsObtenirLlistaPuntsRuta.getBinaryStream("foto")!=null){
                    punt = new Punt(rsObtenirLlistaPuntsRuta.getInt("num"),
                                rsObtenirLlistaPuntsRuta.getString("nom"),
                                rsObtenirLlistaPuntsRuta.getString("desc_punt"),
                                IOUtils.toByteArray(rsObtenirLlistaPuntsRuta.getBinaryStream("foto")),
                                rsObtenirLlistaPuntsRuta.getInt("lat"),
                                rsObtenirLlistaPuntsRuta.getInt("lon"),
                                rsObtenirLlistaPuntsRuta.getInt("alt"),
                               rsObtenirLlistaPuntsRuta.getInt("ordre"),
                                ruta,
                                tipus);
                }else{
                    punt = new Punt(rsObtenirLlistaPuntsRuta.getInt("num"),
                                rsObtenirLlistaPuntsRuta.getString("nom"),
                                rsObtenirLlistaPuntsRuta.getString("desc_punt"),
                                null,
                                rsObtenirLlistaPuntsRuta.getInt("lat"),
                                rsObtenirLlistaPuntsRuta.getInt("lon"),
                                rsObtenirLlistaPuntsRuta.getInt("alt"),
                            rsObtenirLlistaPuntsRuta.getInt("ordre"),
                                ruta,
                                tipus);
                }

                punts.add(punt);
            }

            if(rsObtenirLlistaPuntsRuta != null){
                rsObtenirLlistaPuntsRuta.close();
            }

        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir els punts de la ruta.\n" + ex.getMessage());
        } catch (IOException ex) {
            throw new GestorBDWikilocException("Error en obtenir la foto.\n" + ex.getMessage());
        }

        return punts;

    }


    public boolean editarOrdrePuntRuta(List<Punt> punts){

        //psEditarOrdrePuntRuta
        int qt_ok = 0;

        for(Punt p: punts){

            try{

                psEditarOrdrePuntRuta.setInt(1, p.getOrdre());
                psEditarOrdrePuntRuta.setInt(2, p.getNum());
                psEditarOrdrePuntRuta.setInt(3, p.getIdRuta().getId());


                int registres_afectats = psEditarOrdrePuntRuta.executeUpdate();

                if(registres_afectats == 1){
                    qt_ok++;
                }



            }catch(Exception ex){

            }
        }

        if(qt_ok==punts.size()){
            return true;
        }
        return false;

    }


    @Override
    public int afegirComentari(Comentari c, String login, Integer id) throws GestorBDWikilocException {

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
            
            
            
            
            try (ResultSet generatedKeys = psAfegirComentari.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println("ENTRO ID CORRECTAMENT");
                    System.out.println(generatedKeys.toString());
                    long id_ruta = generatedKeys.getLong(1);
                    System.out.println(id_ruta);
                    
                    return (int)id_ruta;
                   
                }
                else {
                    System.out.println("ERROR!! RELACIONAT AMB ID AUTONUMERIC");
                    return -1;
                }
            }
           



        }catch(Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
            return -1;
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
    public List<Comentari> obtenirLlistaComentaris(Integer id) throws GestorBDWikilocException {

        List<Comentari> comentaris = new ArrayList();

        try {

            ResultSet rsObtenirLlistaComentaris = null;

            psObtenirLlistaComentaris.setInt(1, id);
            rsObtenirLlistaComentaris = psObtenirLlistaComentaris.executeQuery();

            while(rsObtenirLlistaComentaris.next()){
                boolean feta = true;
                if(rsObtenirLlistaComentaris.getInt("feta")==0){
                    feta = false;
                }
                //public Comentari(Integer id, String text, Integer vInf, boolean feta, Integer vSeg, Integer vPai, Integer dific, Date mt, Usuari loginUsuari, Ruta idRuta) {
                Comentari comentari = new Comentari(rsObtenirLlistaComentaris.getInt("id"),
                                    rsObtenirLlistaComentaris.getString("text"),
                                    rsObtenirLlistaComentaris.getInt("v_inf"),
                                    feta,
                                    rsObtenirLlistaComentaris.getInt("v_seg"),
                                    rsObtenirLlistaComentaris.getInt("v_pai"),
                                    rsObtenirLlistaComentaris.getInt("dific"),
                                    rsObtenirLlistaComentaris.getDate("mt"),
                                    new Usuari(rsObtenirLlistaComentaris.getString("login_usuari")),
                                    new Ruta(rsObtenirLlistaComentaris.getInt("id_ruta")));

                /*
                Companys company = null;
                if(rsObtenirLlistaComentaris.getString("COMPANY_LOGIN")!=null){
                    company = new Companys(new Usuari(rsObtenirLlistaComentaris.getString("COMPANY_LOGIN")),comentari);
                }
                */

                comentaris.add(comentari);
            }

            if(rsObtenirLlistaComentaris != null){
                rsObtenirLlistaComentaris.close();
            }

        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la llista de comentaris.\n" + ex.getMessage());
        }

        return comentaris;

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
    public boolean afegirCompany(Usuari usuari_company, int id_comentari) throws GestorBDWikilocException {

        try{

            psAfegirCompany.setString(1, usuari_company.getLogin());
            psAfegirCompany.setInt(2, id_comentari);


            int registres_afectats = psAfegirCompany.executeUpdate();

            if(registres_afectats != 1){
                return false;
            }

            return true;

        }catch(Exception ex){
            System.out.println("ERORR: ??? "+ex.getMessage());
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

    @Override
    public boolean afegirRutaAmbPunts(Ruta ruta, List<Punt> punts_ruta) throws GestorBDWikilocException {


        try{

            psAfegirRuta.setString(1, ruta.getTitol());
            psAfegirRuta.setString(2, ruta.getDescRuta());
            psAfegirRuta.setString(3, ruta.getTextRuta());
            psAfegirRuta.setDouble(4, ruta.getDist());
            psAfegirRuta.setInt(5, ruta.getTemps());
            psAfegirRuta.setInt(6, ruta.getDesnP());
            psAfegirRuta.setInt(7, ruta.getDesnN());
            psAfegirRuta.setInt(8, ruta.getDific());
            psAfegirRuta.setString(9, ruta.getLoginUsuari().getLogin());


            int registres_afectats = psAfegirRuta.executeUpdate();

            if(registres_afectats != 1){
                return false;
            }


            try (ResultSet generatedKeys = psAfegirRuta.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println("ENTRO ID CORRECTAMENT");
                    System.out.println(generatedKeys.toString());
                    long id_ruta = generatedKeys.getLong(1);
                    System.out.println(id_ruta);

                    //Comptar que es facin tots els inserts dels punts correctament
                    int i = 0;
                    System.out.println("ENTRO BUCLE");
                    //Afegir punts de ruta el id acabat de crear, introduir
                    for(Punt punt_ruta : punts_ruta){
                        punt_ruta.setIdRuta(new Ruta((int)id_ruta));
                        if(afegirPuntRuta(punt_ruta,punt_ruta.getTmpUrlFoto())){
                            i++;
                        }else{
                            System.out.println("NO S'HA AFEGIT EL PUNT DE LA RUTA: punt_ruta: "+punt_ruta+" url_tmp foto: "+punt_ruta.getTmpUrlFoto());
                            break;
                        }
                    }


                    if(i==punts_ruta.size()){
                        
                        
                        //Inserir fetes
                        //id_ruta
                        
                        if(afegirFetes(ruta.getLoginUsuari().getLogin(),(int)id_ruta)){
                            System.out.println("TOT OK!!!!");
                            confirmarCanvis();
                            return true;
                        }else{
                            System.out.println("ERROR AMB FETES: "+ruta.getLoginUsuari().getLogin()+" : "+(int)id_ruta);
                            return false;
                        }
       
                    }else{
                        System.out.println("ERROR!! ALGUN PUNT NO ESTA BE");
                        return false;
                    }

                }
                else {
                    System.out.println("ERROR!! RELACIONAT AMB ID AUTONUMERIC");
                    return false;
                }
            }

        }catch(Exception ex){
            System.out.println("ERROR BASE DE DADES: "+ex.getMessage());
            return false;
        }


    }

    @Override
    public Companys obtenirCompany(Integer id_comentari) throws GestorBDWikilocException {
        try {

            ResultSet rsObtenirCompany = null;

            psObtenirCompany.setInt(1, id_comentari);
            rsObtenirCompany = psObtenirCompany.executeQuery();

            Companys company = null;
            if(rsObtenirCompany.next()){
                company = new Companys(new Usuari(rsObtenirCompany.getString("login_usuari")), new Comentari(id_comentari));

            }


            if(rsObtenirCompany!=null){
                rsObtenirCompany.close();
            }


            return company;

        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir el company.\n" + ex.getMessage());
        }
    }

    @Override
    public List<Ruta> filtreRutaCreades(String titol, int dific, double dist, String login_usuari) throws GestorBDWikilocException {
        System.out.println("FUNC: "+titol+" "+dific+" "+dist+" "+login_usuari);
        List<Ruta> rutes = new ArrayList();
        //inst = "select * from ruta where (titol = -1 or upper(titol) like upper(?)) and (dific = -1 or dific = ?) and (dist = -1.0 or dist > ?)";
        try {

            ResultSet rsFiltreRuta = null;
            System.out.println("TITOL:");
            psFiltreRutaCreades.setString(1, titol);
            psFiltreRutaCreades.setString(2, titol+"%");
            System.out.println("DIFIC:");
            psFiltreRutaCreades.setInt(3, dific);
            psFiltreRutaCreades.setInt(4, dific);
            System.out.println("DIST:");
            psFiltreRutaCreades.setDouble(5, dist);
            System.out.println("LOGIN:");
            psFiltreRutaCreades.setString(6, login_usuari);


            rsFiltreRuta = psFiltreRutaCreades.executeQuery();

            while(rsFiltreRuta.next()){
                System.out.println("PRE USUARI");
               Usuari u = new Usuari(rsFiltreRuta.getString("login"),rsFiltreRuta.getString("pwd"),rsFiltreRuta.getString("email"));
                System.out.println("POST USUARI");


                Ruta r = new Ruta(rsFiltreRuta.getInt("id"),
                                    rsFiltreRuta.getString("titol"),
                                    rsFiltreRuta.getString("desc_ruta"),
                                    rsFiltreRuta.getString("text_ruta"),
                                    rsFiltreRuta.getDouble("dist"),
                                    rsFiltreRuta.getInt("temps"),
                                    rsFiltreRuta.getInt("desn_p"),
                                    rsFiltreRuta.getInt("desn_n"),
                                    rsFiltreRuta.getInt("dific"),
                                    u);
                System.out.println("POST RUTA");

                rutes.add(r);
            }

            if(rsFiltreRuta != null){
                rsFiltreRuta.close();
            }

        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la llista de rutes filtrades.\n" + ex.getMessage());
        }

        return rutes;

    }

    @Override
    public List<Ruta> filtreTotalRutes(String titol, int dific, double dist, String creador, String usuari_fet) throws GestorBDWikilocException {
        
        List<Ruta> rutes = new ArrayList();
        
        
        try {
            
            ResultSet rsFiltreTotalRutes = null;
            
            psFiltreTotalRutes.setString(1, titol);
            psFiltreTotalRutes.setString(2, titol+"%");
            psFiltreTotalRutes.setInt(3, dific);
            psFiltreTotalRutes.setInt(4, dific);
            psFiltreTotalRutes.setDouble(5, dist);
            psFiltreTotalRutes.setDouble(6, dist);
            psFiltreTotalRutes.setString(7, creador);
            psFiltreTotalRutes.setString(8, creador);
            psFiltreTotalRutes.setString(9, usuari_fet);
            psFiltreTotalRutes.setString(10, usuari_fet);
            System.out.println("OK");

            /*
            "select r.* \n" +
                    "from ruta r right join fetes f on r.id = f.id_ruta \n" +
                    "where (? = '-1' or upper(titol) like upper(?)) and (? = -1 or dific = ?) and (? = -1.0 or dist >= ?) \n" +
                    "and (? = '-1' or r.login_usuari = ?) and (? = '-1' or f.login_usuari = ?) ";
            */
            
            
            rsFiltreTotalRutes = psFiltreTotalRutes.executeQuery();
            System.out.println("OK2");
            while(rsFiltreTotalRutes.next()){
                Usuari u = new Usuari(rsFiltreTotalRutes.getString("login"),rsFiltreTotalRutes.getString("pwd"),rsFiltreTotalRutes.getString("email"));
                
                
                System.out.println("OK3");
                Ruta r = new Ruta(rsFiltreTotalRutes.getInt("id"),
                                    rsFiltreTotalRutes.getString("titol"),
                                    rsFiltreTotalRutes.getString("desc_ruta"),
                                    rsFiltreTotalRutes.getString("text_ruta"),
                                    rsFiltreTotalRutes.getDouble("dist"),
                                    rsFiltreTotalRutes.getInt("temps"),
                                    rsFiltreTotalRutes.getInt("desn_p"),
                                    rsFiltreTotalRutes.getInt("desn_n"),
                                    rsFiltreTotalRutes.getInt("dific"),
                                    u);
                System.out.println("OK4");

                rutes.add(r);
            }
            
            if(rsFiltreTotalRutes != null){
                rsFiltreTotalRutes.close();
            }
            
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la llista de rutes filtrades.\n" + ex.getMessage());
        }

        return rutes;

        
    }

    @Override
    public List<Usuari> obtenirUsuaris() throws GestorBDWikilocException {
        
        //psObtenirUsuaris
        List<Usuari> usuaris = new ArrayList();
        
        try {
            
            ResultSet rsobtenirUsuaris = null;
            
            rsobtenirUsuaris = psObtenirUsuaris.executeQuery();
            
            while(rsobtenirUsuaris.next()){
                usuaris.add(new Usuari(rsobtenirUsuaris.getString("login"),rsobtenirUsuaris.getString("pwd"),rsobtenirUsuaris.getString("email")));  
            }
            
            if(rsobtenirUsuaris != null){
                rsobtenirUsuaris.close();
            }
            
            
        } catch (SQLException ex) {
            throw new GestorBDWikilocException("Error en obtenir la llista de rutes filtrades.\n" + ex.getMessage());
        }
        
        return usuaris;
        
    }





}
