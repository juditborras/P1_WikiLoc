package org.milaifontanals.wikiloc.jdbc.proves;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.milaifontanals.wikiloc.jdbc.GestorBDWikilocJdbc;
import org.milaifontanals.wikiloc.model.Comentari;
import org.milaifontanals.wikiloc.model.Fetes;
import org.milaifontanals.wikiloc.model.Punt;
import org.milaifontanals.wikiloc.model.Ruta;
import org.milaifontanals.wikiloc.model.Tipus;
import org.milaifontanals.wikiloc.model.Usuari;
import org.milaifontanals.wikiloc.persistencia.GestorBDWikilocException;

/**
 *
 * @author JUDIT
 */

public class ProvesCapaOracleWikilocJDBC {

    public static void main(String[] args) {
        
        
        try {
            
            //Establim connexió amb la BD
            GestorBDWikilocJdbc gb = new GestorBDWikilocJdbc("connexioOracleWikiloc.properties");
         
            //Creem un objecte ruta de prova
            Ruta r = new Ruta(22,"Ruta 1","Descripció","Text",245.0,30,23,45,1,new Usuari("jborras2","pwd12345","jborras2@milaifontanals.org"));
            
            //Obtenir ruta id = 3 de la BD
            Ruta r_db = gb.obtenirRuta(3);
            
            //Iniciar sessió amb un usuari determinat
            Usuari u_login = gb.iniciarSessio("jborras2","pwd12345");
            if(u_login != null){
                System.out.println("Login correcte"); 
            }else{
                System.out.println("Login incorrecte");
            }
            
            //Inserir ruta de prova a la BD
            if(gb.afegirRuta(r)){
                System.out.println("Afegir ruta efectuat correctament");
            }else{
                System.out.println("Error en afegir la ruta");
            }
            
            //Creem un objecte ruta editada de prova
            r = new Ruta(4,"Ruta editada","Descripció","Text",245.0,30,23,45,1,new Usuari("eborras","1234","eborras@milaifontanals,org"));
            
            //Comprovar si la ruta editada s'ha inserit a la BD
            if(gb.editarRuta(r)){
                System.out.println("Editar ruta efecutat correctament");
            }else{
                System.out.println("Error en editar la ruta");
            }
                       
            //Esborrar ruta editada de prova de la BD
            if(gb.eliminarRuta(4)){
                System.out.println("Eliminar ruta efectuat correctament");
            }else{
                System.out.println("Error en eliminar la ruta");
            }
            
            //Obtenir el total de rutes existents dins la BD            
            List<Ruta> rutes = gb.obtenirLlistaRuta();
            System.out.println("Obtenir llistat de les rutes:");
            for(Ruta ruta: rutes){
                System.out.println(ruta);
            }
            
            //Obtenir ruta id = 2 de la BD            
            r = gb.obtenirRuta(2);
            System.out.println("La ruta obtenida: " + r);
                        
            //Obtenir l'usuari propietari de la ruta id = 1
            Usuari u = gb.obtenirPropietariRuta(1);
            System.out.println("El propietari de la ruta 1 és: " + u);
            
            //Verificar l'usuari propietari de la ruta id = 1
            if(gb.verificarPropietariRuta("jborras2",1)){
                System.out.println("jborras2 és propietaria de la ruta");
            }else{
                System.out.println("jborras2 no és la propietaria de la ruta");
            }
            
            //Inserir a un usuari que ha completat la ruta id = 22
            if(gb.afegirFetes(new Fetes(new Usuari("cgomez","pwd101010","cgomez@milaifontanals.org"),new Date(),r_db))){
                System.out.println("Ruta feta enregistrada correctament");
            }else{
                System.out.println("Ruta feta no s'ha pogut guardar");
            }
            
            //Obtenir dades d'aquells qui han realitzat la ruta id = 3
            List<Fetes> fetes = gb.obtenirLlistaFetes(3);
            System.out.println("Llistat de fetes de la ruta 3:");
            for(Fetes f : fetes){
                System.out.println(f);
            }
            
            //Esbrinar quin tipus de punt és el tipus id = 1
            Tipus tipus = gb.obtenirTipusPerId(1);
            System.out.println("Tipus 1: " + tipus);            
            
            //Afegir un nou punt a la ruta id = 22
            if(gb.afegirPuntRuta(new Punt(40,"Nom punt","Desc punt",143,67,23,r_db,tipus))){
                System.out.println("Punt de ruta afegir correctament");
            }else{
                System.out.println("Error en afegir el punt de ruta");
            }
                    
            //Obtenir les dades del punt 7 de la ruta id = 3
            Punt punt = gb.obtenirPuntRuta(7,3);
            System.out.println("Punt de ruta num 7 de la ruta 3: " + punt);
            
            //Editar lat del punt 7 de la ruta id = 3
            punt.setLat(37);
            if(gb.editarPuntRuta(punt,punt.getIdRuta().getId())){
                System.out.println("Punt de ruta editat correctament");
            }else{
                System.out.println("Error en editar el punt de ruta");
            }
            
            //Esborrar el punt 7 de la ruta id = 3
            if(gb.eliminarPuntRuta(7,3)){
                System.out.println("Punt de ruta eliminat correctament");
            }else{
                System.out.println("Error en eliminar el punt de ruta");
            }
            
            //Esborrar un grapat de punts de la ruta id = 3
            List<Punt> punts_esborrar = new ArrayList();
            punts_esborrar.add(gb.obtenirPuntRuta(6,3));
            punts_esborrar.add(gb.obtenirPuntRuta(5,3));
            
            if(gb.eliminarLlistaPuntsRuta(punts_esborrar)){
                System.out.println("Els punts s'han eliminat correctament");
            }else{
                System.out.println("Els punts no s'han esborrat");
            }
            
            //Obtenir el llistat de punts que formen la ruta id = 3
            List<Punt> punts_ruta = gb.obtenirLlistaPuntsRuta(3);
            System.out.println("Punts de la ruta 3:");
            for(Punt pnt : punts_ruta){
                System.out.println(pnt);
            }
        
            //Inserir comentari a ruta id = 1 no feta
            if(gb.afegirComentari(new Comentari("text comentari",2,new Usuari("cgomez","pwd101010","cgomez@milaifontanals.org"),r_db),"cgomez",1)){
                System.out.println("Comentari afegit correctament");
            }else{
                System.out.println("Error a l'afegir el comentari");
            }
            
            //Eliminar comentari amb id = 1
            if(gb.eliminarComentari(1)){
                System.out.println("Comentari eliminat correctament");
            }else{
                System.out.println("Error a eliminar el comentari");
            }

           
            System.out.println("Comentaris totals de la ruta 3: " + gb.qtatComentarisRuta(3));
            System.out.println("Comentaris totals de gent que ha fet la ruta 3: " + gb.qtatComentarisRutaFeta(3));
            System.out.println("Comentaris totals de la gent que no ha fet la ruta 3: " + gb.qtatComentarisRutaNoFeta(3));
            System.out.println("Mitja de valoració ia informació de la ruta 3: " + gb.mitjaVinf(3));
            System.out.println("Mitja de valoració del seguiment de la ruta 3: " + gb.mitjaVseg(3));
            System.out.println("Mitja de valoració del paisatje de la ruta 3: " + gb.mitjaVpai(3));
            System.out.println("Mitja de valoració de dificultat de la ruta 3: " + gb.mitjaVdific(3));
            
            //Esborrar company de ruta
            if(gb.eliminarCompany("dsegura",2)){
                System.out.println("Eliminat el company correctament");
            }else{
                System.out.println("No s'ha pogut eliminar correctament el company de ruta");
            }
            
            //Tancar connexió de la BD
            gb.tancarCapa();
            System.out.println("Connexió tancada");
        
        
        
        } catch (GestorBDWikilocException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        
        
        
    }
    
}
