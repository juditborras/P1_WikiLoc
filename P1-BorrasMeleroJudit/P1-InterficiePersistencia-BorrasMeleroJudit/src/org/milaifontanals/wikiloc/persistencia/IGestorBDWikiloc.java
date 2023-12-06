package org.milaifontanals.wikiloc.persistencia;

import java.util.List;
import org.milaifontanals.wikiloc.model.Comentari;
import org.milaifontanals.wikiloc.model.Companys;
import org.milaifontanals.wikiloc.model.Fetes;
import org.milaifontanals.wikiloc.model.Punt;
import org.milaifontanals.wikiloc.model.Ruta;
import org.milaifontanals.wikiloc.model.Tipus;
import org.milaifontanals.wikiloc.model.Usuari;

/**
 *
 * @author JUDIT
 */

public interface IGestorBDWikiloc {
    
    // <editor-fold defaultstate="collapsed" desc="MÈTODES DE LA BD">
    
    /**
     * Tanca la connexió de la BD
     *
     * @throws org.milaifontanals.wikiloc.persistencia.GestorBDWikilocException
     */
    void tancarCapa() throws GestorBDWikilocException;   
    
    /**
     * Confirma els canvis efectuats a la BD
     *
     * @throws GestorBDWikilocException
     */
    void confirmarCanvis() throws GestorBDWikilocException;

    /**
     * Eliminar les transaccions no confirmades
     *
     * @throws GestorBDWikilocException
     */
    void desferCanvis() throws GestorBDWikilocException;    
        
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="MÈTODES DE LA CLASSE USUARI"> 
    
    /**
     * Iniciar sessió usuari de la BD
     *
     * @param login login de l'usuari
     * @param pwd contrasenya de l'usuari
     * @return S'ha verificat o no l'inici de sessió
     * @throws GestorBDWikilocException
     */
    Usuari iniciarSessio(String login, String pwd) throws GestorBDWikilocException; 
    
    /**
     * Inserir nou usuari a la BD
     *
     * @param u Usuari a inserir
     * @return S'ha verificat o no la inserció de l'usuari
     * @throws GestorBDWikilocException
     */
    boolean afegirUsuari(Usuari u) throws GestorBDWikilocException;
    
    /**
     * Obtenir usuari de la BD
     *
     * @param login login de l'usuari
     * @return usuari
     * @throws GestorBDWikilocException
     */
    Usuari obtenirUsuari(String login) throws GestorBDWikilocException;
    
    /**
     * Edició del camp foto de l'usuari a la BD
     *
     * @param u Usuari a editar
     * @param url_foto foto de l'usuari a editar
     * @return S'ha verificat o no l'edició del camp foto
     * @throws GestorBDWikilocException
     */
    boolean editarFotoUsuari(Usuari u, String url_foto) throws GestorBDWikilocException;
    
    /**
     * Edició del camp email de l'usuari a la BD
     *
     * @param u Usuari a editar
     * @param email email a editar
     * @return S'ha verificat o no l'edició del camp email
     * @throws GestorBDWikilocException
     */
    boolean editarEmailUsuari(Usuari u, String email) throws GestorBDWikilocException;
    
    /**
     * Edició del camp pwd de l'usuari a la BD
     *
     * @param u Usuari a editar
     * @param pwd pwd a editar
     * @return S'ha verificat o no l'edició del camp pwd
     * @throws GestorBDWikilocException
     */
    boolean editarPwdUsuari(Usuari u, String pwd) throws GestorBDWikilocException;
        
    // </editor-fold>
       
    // <editor-fold defaultstate="collapsed" desc="MÈTODES DE LA CLASSE RUTA">
    
    /**
     * Insereix ruta a la BD
     *
     * @param r Ruta a inserir
     * @return S'ha confirmat o no la inserció de la ruta
     * @throws GestorBDWikilocException
     */
    boolean afegirRuta(Ruta r) throws GestorBDWikilocException;  
        
    /**
     * Insereix ruta i els seus punts de ruta
     *
     * @param ruta ruta a inserir
     * @param List<Punt> punts de ruta
     * @return S'ha confirmat o no la inserció de la ruta
     * @throws GestorBDWikilocException
     */
    boolean afegirRutaAmbPunts(Ruta ruta, List<Punt> punts_ruta) throws GestorBDWikilocException;
    
    
    
    /**
     * Editar ruta a la BD
     *
     * @param r Ruta a editar
     * @return S'ha confirmat o no l'edició de la ruta
     * @throws GestorBDWikilocException
     */   
    boolean editarRuta(Ruta r) throws GestorBDWikilocException;
    
        /**
     * Editar text Html de la ruta
     *
     * @param id id de la ruta a editar
     * @param text_ruta text html de la ruta a editar
     * @return S'ha confirmat o no l'edició del text html de la ruta
     * @throws GestorBDWikilocException
     */   
    boolean editarTextHtmlRuta(Integer id, String text_ruta) throws GestorBDWikilocException;
    
    /**
     * Eliminar ruta de la BD
     *
     * @param id id de la ruta a eliminar
     * @return S'ha confirmat o no l'eliminació de la ruta
     * @throws GestorBDWikilocException
     */    
    boolean eliminarRuta(Integer id) throws GestorBDWikilocException;
    
    /**
     * Obtenir llista de ruta de la BD
     *
     * @return Llista de rutes recuperades
     * @throws GestorBDWikilocException
     */    
    List<Ruta> obtenirLlistaRuta() throws GestorBDWikilocException;
        
    /**
     * Obtenir llista de ruta de la BD
     *
     * @param usuari usuari del que volem obtenir les seves rutes
     * @return Llista de rutes recuperades de l'usuari
     * @throws GestorBDWikilocException
     */    
    List<Ruta> obtenirLlistaRutaUsuari(String usuari) throws GestorBDWikilocException;
    
    /**
     * Obtenir ruta de la BD
     *
     * @param id id de la ruta indicada
     * @return Ruta indicada
     * @throws GestorBDWikilocException
     */     
    Ruta obtenirRuta(Integer id) throws GestorBDWikilocException;
        
    /**
     * Obtenir propietari de ruta de la BD
     *
     * @param id id de la ruta indicada
     * @return Usuari propietari de la ruta
     * @throws GestorBDWikilocException
     */
    Usuari obtenirPropietariRuta(Integer id) throws GestorBDWikilocException;

    /**
     * Verificar propietari de ruta de la BD
     *
     * @param login Usuari a verificar
     * @param id id de la ruta
     * @return S'ha verificat o no l'usuari de la ruta
     * @throws GestorBDWikilocException
     */    
    boolean verificarPropietariRuta(String login, Integer id) throws GestorBDWikilocException;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="MÈTODES DE LA CLASSE FETES">    
    
    /**
     * Inserir fetes a la BD
     *
     * @param f Fetes a inserir
     * @return S'ha verificat o no la inserció de fetes
     * @throws GestorBDWikilocException
     */
    boolean afegirFetes(Fetes f) throws GestorBDWikilocException;
    
    
    /**
     * Inserir fetes a la BD
     *
     * @param login Login usuari
     * @param id_ruta Ruta afegir com a feta
     * @return S'ha verificat o no la inserció de fetes
     * @throws GestorBDWikilocException
     */
    boolean afegirFetes(String login, int id_ruta) throws GestorBDWikilocException;
    
    /**
     * Obtenir llistat fetes de la BD
     *
     * @param id id de la ruta indicada
     * @return Llista de Fetes
     * @throws GestorBDWikilocException
     */
    List<Fetes> obtenirLlistaFetes(Integer id) throws GestorBDWikilocException;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="MÈTODES DE LA CLASSE TIPUS">
        
    /**
     * Obtenir informació del tipus de punt de la BD
     *
     * @param id id del tipus
     * @return Tipus indicat
     * @throws GestorBDWikilocException
     */
    Tipus obtenirTipusPerId(Integer id) throws GestorBDWikilocException;
        
    /**
     * Obtenir llista dels tipus de punts de la BD
     *
     * @return llista de tipus
     * @throws GestorBDWikilocException
     */
    List<Tipus> obtenirLlistaTipus() throws GestorBDWikilocException;
    
    /**
     * Obtenir informació del tipus de punt de la BD
     *
     * @param id_ruta id de la ruta
     * @param id_punt id del punt
     * @return Tipus del punt indicat de la ruta indicada
     * @throws GestorBDWikilocException
     */
    Tipus obtenirTipusPunt(Integer id_ruta, Integer id_punt) throws GestorBDWikilocException;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="MÈTODES DE LA CLASSE PUNT">
    
    /**
     * Inserir punt a la ruta de la BD
     *
     * @param p Punt a inserir a la ruta
     * @param url_foto ruta física de la foto
     * @return S'ha verificat o no la inserció del punt
     * @throws GestorBDWikilocException
     */    
    boolean afegirPuntRuta(Punt p, String url_foto) throws GestorBDWikilocException;
    
    /**
     * Editar punt de la ruta de la BD
     *
     * @param p Punt a editar de la ruta
     * @param id id de la ruta a la qual pertany el punt
     * @param url_foto ruta física de la foto
     * @return S'ha verificat o no l'edició del punt
     * @throws GestorBDWikilocException
     */
    boolean editarPuntRuta(Punt p, Integer id, String url_foto) throws GestorBDWikilocException;
    
    /**
     * Editar punt de la ruta de la BD
     *
     * @param p Punt a editar de la ruta
     * @param id id de la ruta a la qual pertany el punt
     * @return S'ha verificat o no l'edició del punt
     * @throws GestorBDWikilocException
     */
    boolean editarPuntRutaSenseFoto(Punt p, Integer id) throws GestorBDWikilocException;
    
    /**
     * Eliminar punt de la ruta de la BD
     *
     * @param num número del punt a eliminar de la ruta
     * @param id id de la ruta a la qual pertany el punt
     * @return S'ha verificat o no l'eliminació del punt
     * @throws GestorBDWikilocException
     */
    boolean eliminarPuntRuta(Integer num, Integer id) throws GestorBDWikilocException;
    
    
    /**
     * Eliminar punt de la ruta de la BD
     *
     * @param id_ruta Identificador de la ruta
     * @return S'ha verificat o no l'eliminació del punt
     * @throws GestorBDWikilocException
     */
    boolean eliminarPuntRutaTots(Integer id_ruta) throws GestorBDWikilocException;
    
    
    /**
     * Eliminar conjunt de punts de la ruta de la BD
     *
     * @param p llista de punts a eliminar de la ruta
     * @return S'ha verificat o no l'eliminació dels punts
     * @throws GestorBDWikilocException
     */
    boolean eliminarLlistaPuntsRuta(List<Punt> p) throws GestorBDWikilocException;
        
    /**
     * Obtenir punt de la ruta de la BD
     *
     * @param num número del punt indicat
     * @param id id de la ruta a la qual pertany el punt
     * @return Punt indicat
     * @throws GestorBDWikilocException
     */
    Punt obtenirPuntRuta(Integer num, Integer id) throws GestorBDWikilocException;
    
    /**
     * Obtenir conjunt de punts de la ruta de la BD
     *
     * @param id id de la ruta de la qual volem obtenir tots els punts
     * @return Punt indicat
     * @throws GestorBDWikilocException
     */
    List<Punt> obtenirLlistaPuntsRuta(Integer id) throws GestorBDWikilocException;
        
    // </editor-fold>   
        
    // <editor-fold defaultstate="collapsed" desc="MÈTODES DE LA CLASSE COMENTARI">
    
    /**
     * Inserir comentari a la ruta de la BD
     *
     * @param c Comentari a inserir a la ruta
     * @param login Usuari que realitza el comentari
     * @param id id de la ruta comentada
     * @return S'ha verificat o no la inserció del comentari
     * @throws GestorBDWikilocException
     */
    boolean afegirComentari(Comentari c, String login, Integer id) throws GestorBDWikilocException;
        
    /**
     * Editar comentari d'una ruta de la BD
     *
     * @param c Comentari a editar de la ruta
     * @param login Usuari que ha realitzat el comentari
     * @param id id de la ruta comentada
     * @return S'ha verificat o no l'edició del comentari
     * @throws GestorBDWikilocException
     */
    boolean editarComentari(Comentari c, String login, Integer id) throws GestorBDWikilocException; 
        
    /**
     * Eliminar comentari d'una ruta de la BD
     *
     * @param id id del comentari a esborrar
     * @return S'ha verificat o no l'eliminació del comentari
     * @throws GestorBDWikilocException
     */
    boolean eliminarComentari(Integer id) throws GestorBDWikilocException;
        
    /**
     * Obtenir llistat de comentaris d'una ruta de la BD
     *
     * @param id id de la ruta comentada
     * @return Llista de comentaris
     * @throws GestorBDWikilocException
     */
    List<Comentari> obtenirLlistaComentaris(Integer id) throws GestorBDWikilocException;
    
    /**
     * Quantificar total de comentaris d'una ruta de la BD
     *
     * @param id id de la ruta indicada
     * @return Quantitat de comentaris que té la ruta indicada
     * @throws GestorBDWikilocException
     */
    Integer qtatComentarisRuta(Integer id) throws GestorBDWikilocException;
       
    /**
     * Quantificar total de comentaris d'una ruta feta de la BD
     *
     * @param id id de la ruta indicada
     * @return Quantitat de comentaris que té la ruta feta indicada
     * @throws GestorBDWikilocException
     */
    Integer qtatComentarisRutaFeta(Integer id) throws GestorBDWikilocException;
    
    /**
     * Quantificar total de comentaris d'una ruta no feta de la BD
     *
     * @param id id de la ruta indicada
     * @return Quantitat de comentaris que té la ruta no feta indicada
     * @throws GestorBDWikilocException
     */
    Integer qtatComentarisRutaNoFeta(Integer id) throws GestorBDWikilocException;
        
    /**
     * Mitja de la valoració de la informació d'una ruta de la BD
     *
     * @param id id de la ruta indicada
     * @return Mitja de la valoració de la informació
     * @throws GestorBDWikilocException
     */
    Double mitjaVinf(Integer id) throws GestorBDWikilocException;
        
    /**
     * Mitja de la valoració del seguiment d'una ruta de la BD
     *
     * @param id id de la ruta indicada
     * @return Mitja de la valoració del seguiment
     * @throws GestorBDWikilocException
     */
    Double mitjaVseg(Integer id) throws GestorBDWikilocException;    
    
    /**
     * Mitja de la valoració del paisatge d'una ruta de la BD
     *
     * @param id id de la ruta indicada
     * @return Mitja de la valoració del paisatge
     * @throws GestorBDWikilocException
     */
    Double mitjaVpai(Integer id) throws GestorBDWikilocException;    
    
    /**
     * Mitja de la valoració de la dificultat d'una ruta de la BD
     *
     * @param id id de la ruta indicada
     * @return Mitja de la valoració de la dificultat
     * @throws GestorBDWikilocException
     */
    Double mitjaVdific(Integer id) throws GestorBDWikilocException;    
    
    // </editor-fold>   
    
    // <editor-fold defaultstate="collapsed" desc="MÈTODES DE LA CLASSE COMPANYS"> 
    
    /**
     * Inserir company a la ruta de la BD
     *
     * @param c Company que volem inserir a la ruta
     * @return S'ha verificat o no la inserció del company de ruta
     * @throws GestorBDWikilocException
     */
    boolean afegirCompany(Companys c) throws GestorBDWikilocException;
        
    /**
     * Eliminar company de la ruta de la BD
     *
     * @param login Company que volem eliminar de la ruta
     * @param id id del comentari
     * @return S'ha verificat o no l'eliminació del company de ruta
     * @throws GestorBDWikilocException
     */
    boolean eliminarCompany(String login, Integer id) throws GestorBDWikilocException;
    
    
    /**
     * Obtenir company del comentari de la BD
     *
     * @param id_comentari Comentari
     * @return Company del comentari
     * @throws GestorBDWikilocException
     */
    Companys obtenirCompany(Integer id_comentari) throws GestorBDWikilocException;
    
    
    
     /**
     * Obtenir les rutes que compleixin les doncidicons de filtratge
     *
     * @param titol Part del titol de la ruta
     * @param dific Dificultat de la ruta
     * @param dist Distancia de la ruta
     * @param login_usuari Usuari que ha creat la ruta
     * @return Company del comentari
     * @throws GestorBDWikilocException
     */
    List<Ruta> filtreRutaCreades(String titol, int dific, double dist, String login_usuari) throws GestorBDWikilocException;
    
    // </editor-fold>    
}
