/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proactif.services;

import com.google.maps.model.LatLng;
import com.mycompany.proactif.dao.DAOAbstraitUtilisateur;
import com.mycompany.proactif.dao.DAOClient;
import com.mycompany.proactif.dao.DAOEmploye;
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
    public static Utilisateur creerUtilisateur(Utilisateur unUtilisateur) {
        
        Utilisateur utilisateurCree;

        // Vérif des coordonnées GPS
        String adresse = unUtilisateur.getAdresse().toGeoString();
        LatLng coordonneesGPS = GeoTest.getLatLng(adresse);
        if(coordonneesGPS ==  null)
            utilisateurCree = null;
        else{
            unUtilisateur.getAdresse().setCoordonneesGPS(coordonneesGPS);
        
            // Si tout ok alors créer utilisateur
            try {
                commencerTransactionEcriture();
                DAOAbstraitUtilisateur<Utilisateur> maDAOU = new DAOAbstraitUtilisateur();
                maDAOU.creer(unUtilisateur);
                
                
                utilisateurCree = maDAOU.trouverParId(unUtilisateur.getId());

                // Envoyer un mail
                Message.envoyerMail("contact@proactif.fr", unUtilisateur.getEmail(), "[PROACTIF] Bienvenue sur proactif", "Bonjour " + unUtilisateur.getPrenom() + ", \nNous vous confirmons votre inscription au service PROACT'IF.\nCordialement,\nL'équipe PROACT'IF");
                // TODO Message à rédiger
                finirTransactionEcriture();
                return utilisateurCree;
            }
            catch (Exception e) {
                DebugLogger.log("[SERVICE] Création d'un utilisateur", e);
                annulerTransaction();
                utilisateurCree = null;
            }
        }
        return utilisateurCree;
    }
    
    /**
     * Permet de mettre à jour un utilisateur existant
     * @param <T> Le type de l'utilisateur (Employe / Client)
     * @param unUtilisateur L'utilisateur à mettre à jour
     * @return true en cas de succès sinon false
     */
    public static  Utilisateur mettreAJourUtilisateur(Utilisateur unUtilisateur){
        Utilisateur utilisateurMisAjour;
        try {
            commencerTransactionEcriture();
            String adresse = unUtilisateur.getAdresse().toGeoString();
            LatLng coordonneesGPS = GeoTest.getLatLng(adresse);
            
            unUtilisateur.getAdresse().setCoordonneesGPS(coordonneesGPS);
            DAOAbstraitUtilisateur<Utilisateur> maDAO = new DAOAbstraitUtilisateur(unUtilisateur);
            utilisateurMisAjour = maDAO.mettreAJour();
            
            finirTransactionEcriture();
        }
        catch (Exception e) {
            DebugLogger.log("[SERVICE] Mise à jour d'un utilisateur", e);
            annulerTransaction();
            utilisateurMisAjour = null;
        }
        
        return utilisateurMisAjour;
    }
    
    /**
     * Permet de mettre à jour un utilisateur existant
     * @param <T> Le type de l'utilisateur (Employe / Client)
     * @param idUtilisateur L'utilisateur à trouver
     * @param typeUtilisateur Employe ou Client
     * @return true en cas de succès sinon false
     */
    public static  Utilisateur obtenirUtilisateur(long idUtilisateur, Utilisateur typeUtilisateur) {
        Utilisateur utilisateur = typeUtilisateur;
        try {
            commencerTransactionLecture();
            
            DAOAbstraitUtilisateur<Utilisateur> maDAO = new DAOAbstraitUtilisateur(utilisateur);
            utilisateur = maDAO.trouverParId(idUtilisateur);
            
            finirTransactionLecture();
        }
        catch (Exception e) {
            DebugLogger.log("[SERVICE] Mise à jour d'un utilisateur", e);
            annulerTransaction();
            utilisateur = null;
        }
        
        return utilisateur;
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
        
        try {
            commencerTransactionLecture();

            if (maDAO.authentifier(email, motDePasse)) {
                utilisateur = maDAO.getObjetLocal();
            }

            finirTransactionLecture();
        }
        catch (Exception e) {
            DebugLogger.log("[SERVICES] authentifier", e);
            utilisateur = null;
        }
        
        return utilisateur;
    }
    
    /**
     * Permet de trier une liste d'intervention en fonction des critères fournies
     * @param listeInterventions la liste à filtrer
     * @param monFiltre le type de filtre
     * @param croissant true pour croissant, false pour décroissant
     * @return 
     */
    public static boolean trierListe(List<Intervention> listeInterventions,FILTRES monFiltre, boolean croissant){
        
        Comparateur monComparateur = new Comparateur (monFiltre, croissant);
        Collections.sort(listeInterventions, monComparateur);
        return true;
    }
    
    /**
     * Permet de chercher une intervention
     * @param listeIntervention La liste soumise à la recherceh
     * @param motClef Les mots clés de recherche
     * @return Les interventions répondants aux critères
     */
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
        intervention.setClient(client);
        intervention.setDateDebut(new Date());
        commencerTransactionLecture();
        Employe employeattribue = getEmployeLePlusProche(getEmployesDisponibles(), intervention);
        intervention.setEmploye(employeattribue);
        finirTransactionLecture();
        
        if (employeattribue == null) {
            codeRetour = RetourCreationIntervention.AucunEmployeDisponible;
        }
        else {
            
            employeattribue.setDisponibilite(false);
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
                
                Message.envoyerNotification(employeattribue.getTelephone(), "Intervention " + intervention.getClass().getSimpleName() + " demandée le " + intervention.getDateDebut() + " pour " + client.getPrenom() + " " + client.getNom() + ", " + client.getAdresse().toGeoString() + " : " + intervention.getIntitule());
            }
            catch(Exception e) {
                DebugLogger.log("[Services] creerDemandeIntervention", e);
                codeRetour = RetourCreationIntervention.ErreurBase;
                annulerTransaction();
            }
        }
        
        return codeRetour;
    }
    
    /**
     * Permet de cloturer une intervention en cours
     * @param id L'intervention à terminer
     * @param commentaire Le commentaire 
     * @param etat L'état de fin d'intervention
     * @return Si le traitement s'est terminé correctement
     */
    public static RetourTerminerIntervention terminerIntervention(long id, String commentaire, int etat){
        
        RetourTerminerIntervention codeRetour = RetourTerminerIntervention.ErreurBase;
                
        commencerTransactionLecture();
        Intervention intervention = new Intervention();
        DAOIntervention recupIntervention = new DAOIntervention(intervention);
        intervention =recupIntervention.trouverParId(id);
        finirTransactionLecture();
        
        intervention.setEtat(etat);
        intervention.setCommentaireEmploye(commentaire);
        intervention.setDateFin(new Date());
        
        Employe employe = intervention.getEmploye();
        String adresseCLientIntervention = intervention.getClient().getAdresse().toGeoString();
        employe.setPosition(GeoTest.getLatLng(adresseCLientIntervention));
        employe.setDisponibilite(true);
        
        try {
            commencerTransactionEcriture();
            DAOIntervention maDAOIntervention = new DAOIntervention(intervention);
            maDAOIntervention.mettreAJour();
            
            DAOUtilisateur maDAOUtilisateur = new DAOUtilisateur();
            maDAOUtilisateur.setObjetLocal(employe);
            maDAOUtilisateur.mettreAJour();
                        
            if(etat == -1)
                codeRetour = RetourTerminerIntervention.InterventionNonReussie;
            else
                codeRetour = RetourTerminerIntervention.Succes;
        
            finirTransactionEcriture();
            
            Message.envoyerNotification(intervention.getClient().getTelephone(), "Incident le " + intervention.getDateDebut() + ", cloturé à " + intervention.getDateFin() + " : " + intervention.getIntitule() + " Status : " + ((intervention.getEtat() == 1)? "Résolu": "Echec") + ". Commentaire : " + intervention.getCommentaireEmploye());
        }   
        catch(Exception e){
            DebugLogger.log("[SERVICES] TerminerIntervention", e);
            annulerTransaction();
            codeRetour = RetourTerminerIntervention.ErreurBase;
        }
        
        return codeRetour;
    }
    
    public static void recupererToutesLesIntervention(Employe employe){
        

        try{
            commencerTransactionLecture();
            DAOEmploye maDAOC = new DAOEmploye(employe);
            maDAOC.recupererToutesLesIntervention();
            finirTransactionLecture();
        }
        catch(Exception e){
        }
       
        
    }
    
    public static void recupererToutesLesIntervention(Client client){
        

        try{
            commencerTransactionLecture();
            DAOClient maDAOC = new DAOClient(client);
            maDAOC.recupererToutesLesIntervention();
            finirTransactionLecture();
        }
        catch(Exception e){
            System.out.println("#######################");
            System.out.println(e);
        }
       
        
    }
    
    private static void commencerTransactionEcriture() {
       JpaUtil.creerEntityManager();
       JpaUtil.ouvrirTransaction(); 
    }
    
    private static void finirTransactionEcriture() {
       JpaUtil.validerTransaction();
       JpaUtil.fermerEntityManager(); 
    }
    
    private static void annulerTransaction() {
        JpaUtil.annulerTransaction();
    }
    
    public static void finirTransactionLecture() {
       JpaUtil.fermerEntityManager(); 
    }
    
    public static void commencerTransactionLecture() {
       JpaUtil.creerEntityManager(); 
    }
}
