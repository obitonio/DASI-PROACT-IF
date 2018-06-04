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
import com.mycompany.proactif.dao.JpaUtil;
import com.mycompany.proactif.entites.Intervention;
import com.mycompany.proactif.util.Comparateur;
import com.mycompany.proactif.util.Comparateur.FILTRES;
import com.mycompany.proactif.util.DebugLogger;
import com.mycompany.proactif.util.GeoTest;
import com.mycompany.proactif.util.Message;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Jean
 */
public class Services {
    
    public enum RetourCreationUtilisateur {
        LatLngIntrouvable,
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
    public static boolean authentifier(String email, String motDePasse) {
        
        commencerTransactionLecture();
        DAOUtilisateur maDAO = new DAOUtilisateur();
        if(maDAO.authentifier(email, motDePasse)){
            finirTransactionLecture();
            return true;
        }
        else{
            finirTransactionLecture();
            return false;
        }  
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
     * @return 
     */
    public static boolean creerDemandeIntervention(Intervention intervention) {
        
        commencerTransactionEcriture();
        DAOIntervention maDAO = new DAOIntervention();
        maDAO.creer(intervention);
        finirTransactionEcriture();
        return true;

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
