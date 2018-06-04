/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.services;

import com.google.maps.model.LatLng;
import com.mycompany.proactif.dao.DAOAbstraitUtilisateur;;
import com.mycompany.proactif.dao.DAOIntervention;
import com.mycompany.proactif.entites.Utilisateur;
import com.mycompany.proactif.dao.DAOUtilisateur;
import static com.mycompany.proactif.dao.DAOEmploye.getEmployeLePlusProche;
import static com.mycompany.proactif.dao.DAOEmploye.getEmployesDisponibles;
import com.mycompany.proactif.dao.JpaUtil;
import com.mycompany.proactif.entites.Client;
import com.mycompany.proactif.entites.Employe;
import com.mycompany.proactif.entites.Intervention;
import com.mycompany.proactif.util.Comparateur;
import com.mycompany.proactif.util.Comparateur.FILTRES;
import com.mycompany.proactif.util.DebugLogger;
import com.mycompany.proactif.util.GeoTest;
import com.mycompany.proactif.util.Message;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * TODO : Tous les algos vont dans services + Instancier objet dans le service.
 * @author Jean
 */
public class Services {
    
    public enum RetourCreationUtilisateur {
        LatLngIntrouvable,
        ErreurBase,
        Succes
    };
    
    public enum RetourCreationIntervention {
        AucunEmployeDisponible,
        ErreurBase,
        Succes
    };
    
    public enum RetourTerminerIntervention {
        InterventionNonReussie,
        ErreurBase,
        Succes
    };
    
    
    
    /**
     * Permet de créer un utilisateur de tout type
     * @param <T> Le type d'utilisateur à créer (Client, Employé)
     * @param unUtilisateur L'utilisateur
     * @return Le résultat du traitement
     */
    public static <T extends Utilisateur> RetourCreationUtilisateur creerUtilisateur(T unUtilisateur) {
        
        RetourCreationUtilisateur codeRetour = RetourCreationUtilisateur.Succes;

        // Vérif des coordonnées GPS
        String adresse = unUtilisateur.getAdresse().toGeoString();
        LatLng coordonneesGPS = GeoTest.getLatLng(adresse);

        if (null == coordonneesGPS)
            codeRetour = RetourCreationUtilisateur.LatLngIntrouvable;
        else
            unUtilisateur.getAdresse().setCoordonneesGPS(coordonneesGPS);
     
            
        
        // Si tout ok alors créer utilisateur
        if (codeRetour == RetourCreationUtilisateur.Succes) {
           try {
               commencerTransactionEcriture();
               DAOAbstraitUtilisateur<T> maDAO = new DAOAbstraitUtilisateur();
               maDAO.creer(unUtilisateur);

               // Envoyer un mail
               Message.envoyerMail("contact@proactif.fr", unUtilisateur.getEmail(), "[PROACTIF] Bienvenue sur proactif", "Message");
               // TODO Message à rédiger
           }
           catch (Exception e) {
               DebugLogger.log("[SERVICE] Création d'un utilisateur", e);
               codeRetour = RetourCreationUtilisateur.ErreurBase;
           }
           finally {
               finirTransactionEcriture();
           }
        }
        
        return codeRetour;
    }
    
    /**
     * Permet de vérifier les identifiants d'un utilisateur
     * @param email Email de l'utilisateur
     * @param motDePasse Mot de passe de l'utilisateur
     * @return true en cas de succès, false en cas d'echec
     */
    public static Utilisateur authentifier(String email, String motDePasse) {
        
        Utilisateur utilisateur = null;
        DAOUtilisateur maDAO = new DAOUtilisateur();
        
        commencerTransactionLecture();
        
        if (maDAO.authentifier(email, motDePasse)) {
            utilisateur = maDAO.getObjetLocal();
        }
  
        finirTransactionLecture();
        
        return utilisateur;
    }
    public static boolean trierListe(List<Intervention> listeInterventions,FILTRES monFiltre, boolean croissant){
        
        Comparateur monComparateur = new Comparateur (monFiltre, croissant);
        Collections.sort(listeInterventions, monComparateur);
        return true;
    }
    public static List<Intervention> rechercher(List<Intervention> listeIntervention, String motClef){
        
        List<Intervention> maNouvelleListe = new ArrayList<>();
        
        for(Intervention monIntervention : listeIntervention){
            if(monIntervention.toString().toLowerCase().contains(motClef.toLowerCase()))
               maNouvelleListe.add(monIntervention);
        }
        return maNouvelleListe;
    }
    
    /**
     * Créer une demande d'intervention
     * @param intervention La demande à créer
     * @param client Le client qui effectue la demande
     * @return 
     */
    public static RetourCreationIntervention creerDemandeIntervention(Client client, Intervention intervention) {
        
        RetourCreationIntervention codeRetour = RetourCreationIntervention.Succes;
     
        commencerTransactionLecture();
        Employe employeattribue = getEmployeLePlusProche(getEmployesDisponibles(), intervention);
        finirTransactionLecture();
        
        if(employeattribue == null){
            codeRetour = RetourCreationIntervention.AucunEmployeDisponible;
        }
        else {
            
            intervention.setEmploye(employeattribue);
            intervention.setDateDebut(new Date());
            employeattribue.setDisponibilite(0);
            employeattribue.getListeDesInterventions().add(intervention);
            client.getListeDesInterventions().add(intervention);
            
            try {
                commencerTransactionEcriture(); 
                                
                DAOIntervention maDAO = new DAOIntervention();
                maDAO.creer(intervention);
                
                DAOUtilisateur maDAOUtilisateur = new DAOUtilisateur();
                maDAOUtilisateur.setObjetLocal(employeattribue);
                maDAOUtilisateur.mettreAJour();
                
                maDAOUtilisateur.setObjetLocal(client);
                maDAOUtilisateur.mettreAJour();
                
                finirTransactionEcriture();
            }
            catch(Exception e) {
                DebugLogger.log("[Service] creerDemandeIntervention", e);
                codeRetour = RetourCreationIntervention.ErreurBase;
            }
        }
        
        return codeRetour;
    }
    
    public static RetourTerminerIntervention TerminerIntervention(Intervention intervention, String commentaire, int etat){
        
        RetourTerminerIntervention codeRetour= RetourTerminerIntervention.ErreurBase;
        
        intervention.setEtat(etat);
        intervention.setCommentaireEmploye(commentaire);
        intervention.setDateFin(new Date());
        
        Employe employe = intervention.getEmploye();
        String adresseCLientIntervention = intervention.getClient().getAdresse().toGeoString();
        employe.setPosition(GeoTest.getLatLng(adresseCLientIntervention));
        employe.setDisponibilite(1);
        
        try{
            commencerTransactionEcriture();
            DAOIntervention maDAOIntervention = new DAOIntervention();
            maDAOIntervention.setObjetLocal(intervention);
            maDAOIntervention.mettreAJour();
            
            DAOUtilisateur maDAOUtilisateur = new DAOUtilisateur();
            maDAOUtilisateur.setObjetLocal(employe);
            maDAOUtilisateur.mettreAJour();
                        
            if(etat == -1)
                codeRetour = RetourTerminerIntervention.InterventionNonReussie;
            else
                codeRetour = RetourTerminerIntervention.Succes;
            }
            catch(Exception e){
                codeRetour = RetourTerminerIntervention.ErreurBase;
            }
            finally{
                finirTransactionEcriture();
            }
        return codeRetour;
    }
    
    
    
    private static void commencerTransactionEcriture() {
       JpaUtil.creerEntityManager();
       JpaUtil.ouvrirTransaction(); 
    }
    
    private static void finirTransactionEcriture() {
       JpaUtil.validerTransaction();
       JpaUtil.fermerEntityManager(); 
    }
    
    private static void finirTransactionLecture() {
       JpaUtil.fermerEntityManager(); 
    }
    
    private static void commencerTransactionLecture() {
       JpaUtil.creerEntityManager(); 
    }
}
